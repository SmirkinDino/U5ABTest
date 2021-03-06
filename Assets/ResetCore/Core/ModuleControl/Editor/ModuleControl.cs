﻿using UnityEngine;
using UnityEditor;
using System.Collections;
using System.Collections.Generic;
using ResetCore.Util;
using ResetCore.Asset;
using System.IO;
using System;
using System.Reflection;
using System.Xml.Linq;
using ResetCore.Xml;

namespace ResetCore.ModuleControl
{
    public class ModuleControl
    {
        
        public static bool isDevelopMode
        {
            get
            {
                return ContainSymbol(ModuleConst.DeveloperSymbolName);
            }
            set
            {
                if (value == true)
                {
                    AddSymbol(ModuleConst.DeveloperSymbolName);
                }
                else
                {
                    RemoveSymbol(ModuleConst.DeveloperSymbolName);
                }
            }
        }



        [InitializeOnLoad]
        public class ResetCoreLoad
        {

            static ResetCoreLoad()
            {
                if (Application.isPlaying) return;
                int isShow = PlayerPrefs.GetInt("ShowResetModuleController", 1);
                if (isShow == 1)
                {
                    EditorApplication.update += Update;
                }

                //如果为开发者模式则关闭自动检查模块功能
                if (isDevelopMode) return;

                if (!Directory.Exists(PathConfig.ResetCoreTempPath))
                {
                    //初始化模块
                    Array symbolArr = Enum.GetValues(typeof(MODULE_SYMBOL));
                    foreach (MODULE_SYMBOL symbol in symbolArr)
                    {
                        if (ModuleConst.defaultSymbol.Contains(symbol))
                        {
                            AddModule(symbol);
                        }
                        else
                        {
                            RemoveModule(symbol);
                        }
                    }
                    //将额外工具移至工程目录
                    MoveToolsToProject();
                    //将SDK移至工程目录备份
                    MoveSDKToTemp();
                }

                CheckAllSymbol();

                //检查核心数据
                CheckCoreData();

            }

            static void Update()
            {
                bool isSuccess = EditorApplication.ExecuteMenuItem("Tools/ResetCore Module Controller");
                if (isSuccess) EditorApplication.update -= Update;
            }
        }

        public static void CheckAllSymbol()
        {
            Array symbolArr = Enum.GetValues(typeof(MODULE_SYMBOL));
            List<MODULE_SYMBOL> symbols = GetSymbolInfo();
            bool needRestart = false;

            for (int i = 0; i < symbolArr.Length; i++)
            {
                MODULE_SYMBOL symbol = (MODULE_SYMBOL)symbolArr.GetValue(i);
                string tempPath = ModuleConst.GetSymbolTempPath(symbol);
                string modulePath = ModuleConst.GetSymbolPath(symbol);

                EditorUtility.DisplayProgressBar("Check Modules", "Checking Modules " + symbol.ToString() + " " + (i+1) + "/" + symbolArr.Length, (float)i+1 / (float)symbolArr.Length);
                //检查模块备份
                if (!Directory.Exists(tempPath))
                {
                    if (!Directory.Exists(modulePath))
                    {
                        Debug.logger.LogError("ResetCoreError", "Lose the module " + symbol.ToString());
                    }
                    else
                    {
                        PathEx.MakeDirectoryExist(tempPath);
                        DirectoryEx.DirectoryCopy(modulePath, tempPath, true);
                        EditorUtility.DisplayProgressBar("Check Modules", "Copy Module " +
                            symbol.ToString() + "to backup " + (i + 1) + "/" +
                            symbolArr.Length, (float)(i + 1) / (float)symbolArr.Length);
                    }
                }
                //存在宏定义 但是不存在实际模块
                if (symbols.Contains(symbol) 
                    && (!Directory.Exists(modulePath)
                    || Directory.GetFiles(modulePath).Length == 0))
                {
                    AddModule(symbol);
                    EditorUtility.DisplayProgressBar("Check Modules", "Add Module " + 
                        symbol.ToString() + "to ResetCore " + (i + 1) + "/" + 
                        symbolArr.Length, (float)(i + 1) / (float)symbolArr.Length);
                    needRestart = true;
                }
                //不存在宏定义 但是存在实际模块 添加模块
                if (!symbols.Contains(symbol)
                    && Directory.Exists(modulePath)
                    && Directory.GetFiles(modulePath).Length != 0)
                {
                    if (ModuleConst.defaultSymbol.Contains(symbol))
                    {
                        AddModule(symbol);
                        EditorUtility.DisplayProgressBar("Check Modules", "Add Module " +
                            symbol.ToString() + "to ResetCore " + (i + 1) + "/" +
                            symbolArr.Length, (float)(i + 1) / (float)symbolArr.Length);
                        needRestart = true;
                    }
                    else
                    {
                        RemoveModule(symbol);
                        EditorUtility.DisplayProgressBar("Check Modules", "Remove Module " +
                            symbol.ToString() + "from ResetCore " + (i + 1) + "/" +
                            symbolArr.Length, (float)(i + 1) / (float)symbolArr.Length);
                        needRestart = true;
                    }
                }

            }
            AssetDatabase.Refresh();

            EditorUtility.ClearProgressBar();

            if (needRestart)
            {
                EditorUtility.DisplayDialog("Need Restart Project",
                    "You may need to Restart the project to apply your setting", "Ok");
            }
        }

        //检查是否存在该模块
        public static bool ContainSymbol(MODULE_SYMBOL symbol)
        {
            return ContainSymbol(ModuleConst.SymbolName[symbol]);
        }
        public static bool ContainSymbol(string symbol)
        {
            List<string> symbols = PlayerSettings.GetScriptingDefineSymbolsForGroup(
                EditorUserBuildSettings.selectedBuildTargetGroup).ParseList(';');
            return symbols.Contains(symbol);
        }

        //添加预编译宏
        public static void AddSymbol(MODULE_SYMBOL symbol)
        {
            string symbolName = ModuleConst.SymbolName[symbol];
            AddSymbol(symbolName);
        }
        public static void AddSymbol(string symbolName)
        {
            List<string> symbols = PlayerSettings.GetScriptingDefineSymbolsForGroup(
                EditorUserBuildSettings.selectedBuildTargetGroup).ParseList(';');
            if (symbols.Contains(symbolName)) return;

            symbols.Add(symbolName);
            string defines = symbols.ListConvertToString(';');
            PlayerSettings.SetScriptingDefineSymbolsForGroup(EditorUserBuildSettings.selectedBuildTargetGroup, defines);
        }

        //移除预编译宏
        public static void RemoveSymbol(MODULE_SYMBOL symbol)
        {
            string symbolName = ModuleConst.SymbolName[symbol];
            RemoveSymbol(symbolName);
        }

        public static void RemoveSymbol(string symbolName)
        {
            List<string> symbols = PlayerSettings.GetScriptingDefineSymbolsForGroup(EditorUserBuildSettings.selectedBuildTargetGroup).ParseList(';');

            if (!symbols.Contains(symbolName)) return;
            symbols.Remove(symbolName);
            string defines = symbols.ListConvertToString(';');
            PlayerSettings.SetScriptingDefineSymbolsForGroup(EditorUserBuildSettings.selectedBuildTargetGroup, defines);
        }

        //添加模块
        public static bool AddModule(MODULE_SYMBOL symbol)
        {
            string tempPath = ModuleConst.GetSymbolTempPath(symbol);
            string modulePath = ModuleConst.GetSymbolPath(symbol);

            //检查目录如果不存在则拷贝
            if (!Directory.Exists(modulePath))
            {
                if (Directory.Exists(tempPath))
                {
                    PathEx.MakeDirectoryExist(modulePath);
                    DirectoryEx.DirectoryCopy(tempPath, modulePath, true);
                }
                else
                {
                    Debug.logger.Log("can't find the module path" + modulePath);
                    return false;
                }
            }
            AddSymbol(symbol);

            return true;
        }

        //移除模块
        public static bool RemoveModule(MODULE_SYMBOL symbol)
        {
            string tempPath = ModuleConst.GetSymbolTempPath(symbol);
            string modulePath = ModuleConst.GetSymbolPath(symbol);

            //如果找不到备份文件夹则复制到备份文件夹，如果备份文件夹中有了，则直接删除
            if (!Directory.Exists(tempPath))
            {
                PathEx.MakeDirectoryExist(tempPath);
                Debug.logger.Log("can't find the temp directory, will move the module to the temp directory");
                if (Directory.Exists(modulePath))
                {
                    Directory.Move(modulePath, tempPath);
                    Directory.Delete(modulePath, true);
                }
            }
            else
            {
                if (Directory.Exists(modulePath))
                {
                    Directory.Delete(modulePath, true);
                }
            }
            RemoveSymbol(symbol);

            return true;
        }

        public static void ApplySymbol(Dictionary<MODULE_SYMBOL, bool> isImportDict)
        {
            List<MODULE_SYMBOL> moduleList = new List<MODULE_SYMBOL>();
            foreach (KeyValuePair<MODULE_SYMBOL, bool> isImport in isImportDict)
            {
                if (ModuleConst.defaultSymbol.Contains(isImport.Key))
                {
                    AddSymbol(isImport.Key);
                    moduleList.Add(isImport.Key);
                    continue;
                }
                if (isImport.Value == false)
                {
                    RemoveSymbol(isImport.Key);
                }
                else if (isImport.Value == true)
                {
                    AddSymbol(isImport.Key);
                    moduleList.Add(isImport.Key);
                }
            }
            SetSymbolInfo(moduleList);
            CheckAllSymbol();
        }

        private static readonly string symbolInfoFilePathpath = PathEx.Combine(PathConfig.ResetCorePath, "Core", ModuleConst.symbolInfoFileName);
        public static List<MODULE_SYMBOL> GetSymbolInfo()
        {
            XDocument xDoc;
            if (!File.Exists(symbolInfoFilePathpath))
            {
                SetSymbolInfo(ModuleConst.defaultSymbol);
            }
            xDoc = XDocument.Load(symbolInfoFilePathpath);
            List<string> symbolNames = xDoc.ReadValueFromXML<List<string>>(new string[] { "Symbols" });
            List<MODULE_SYMBOL> symbolList = new List<MODULE_SYMBOL>();
            foreach (string name in symbolNames)
            {
                symbolList.Add((MODULE_SYMBOL)Enum.Parse(typeof(MODULE_SYMBOL), name));
            }
            return symbolList;
        }

        public static void SetSymbolInfo(List<MODULE_SYMBOL> symbolInfo)
        {
            XDocument xDoc;
            if (!File.Exists(symbolInfoFilePathpath))
            {
                xDoc = new XDocument(new XElement("Root"));
                xDoc.SafeSaveWithoutDeclaration(symbolInfoFilePathpath);
            }
            xDoc = XDocument.Load(symbolInfoFilePathpath);
            List<string> symbolNames = new List<string>();
            foreach (MODULE_SYMBOL symbol in symbolInfo)
            {
                symbolNames.Add(symbol.ToString());
            }
            xDoc.WriteValueToXML(symbolInfoFilePathpath, new string[] { "Symbols" }, symbolNames);
        }

        //刷新备份文件夹
        public static void RefreshBackUp()
        {
            ModuleControl.CheckAllSymbol();
            Array symbolArr = Enum.GetValues(typeof(MODULE_SYMBOL));
            foreach(MODULE_SYMBOL symbol in symbolArr)
            {
                string modulePath = ModuleConst.GetSymbolPath(symbol);
                string moduleTempPath = ModuleConst.GetSymbolTempPath(symbol);
                if (!Directory.Exists(modulePath)) return;

                if (Directory.Exists(moduleTempPath))
                {
                    Directory.Delete(moduleTempPath, true);
                    Directory.CreateDirectory(moduleTempPath);
                }
                DirectoryEx.DirectoryCopy(modulePath, moduleTempPath, true);
            }
            
            ModuleControl.CheckAllSymbol();
        }

        //删除ResetCore
        public static void RemoveResetCore()
        {
            if (EditorUtility.DisplayDialog("Remove ResetCore",
                    "Do you want to remove the ResetCore? it can't be undo.", "Ok", "No"))
            {
                EditorUtility.DisplayProgressBar("Removing ResetCore", "Remove Main Finder", 0f);

                if(Directory.Exists(PathConfig.ResetCorePath))
                    Directory.Delete(PathConfig.ResetCorePath, true);

                EditorUtility.DisplayProgressBar("Removing ResetCore", "Remove Temp Finder", 0.5f);

                if (Directory.Exists(PathConfig.ResetCoreTempPath))
                    Directory.Delete(PathConfig.ResetCoreTempPath, true);

                EditorUtility.DisplayProgressBar("Removing ResetCore", "Remove SDK Finder", 0.7f);

                //移除额外工具
                if (Directory.Exists(PathConfig.SDKPath))
                    Directory.Delete(PathConfig.SDKPath, true);

                EditorUtility.DisplayProgressBar("Removing ResetCore", "Remove Symbols", 0.9f);

                //移除所有预编译
                Array symbolArr = Enum.GetValues(typeof(MODULE_SYMBOL));
                foreach(MODULE_SYMBOL symbol in symbolArr)
                {
                    RemoveSymbol(symbol);
                }
                EditorUtility.DisplayProgressBar("Removing ResetCore", "Finish", 1f);

                if(EditorUtility.DisplayDialog("Need Restart Project",
                    "You may need to Restart the project to apply your setting", "Ok", "No"))
                {
                    EditorApplication.OpenProject(PathConfig.projectPath);

                }
            }
        }

        //将核心数据拷贝至Resources下
        private static void CheckCoreData()
        {
            string localCoreDataPath = PathConfig.GetLocalGameDataPath(PathConfig.DataType.Core);
            PathEx.MakeDirectoryExist(PathConfig.localCoreDataBackupPath);
            PathEx.MakeDirectoryExist(localCoreDataPath);
            if (!Directory.Exists(localCoreDataPath) && 
                Directory.GetFiles(PathConfig.localCoreDataBackupPath).Length > 0)
            {
                if (Directory.Exists(PathConfig.localCoreDataBackupPath))
                {
                    PathEx.MakeDirectoryExist(localCoreDataPath);
                    DirectoryEx.DirectoryCopy(PathConfig.localCoreDataBackupPath,
                        localCoreDataPath, true);
                }
                else
                {
                    Debug.logger.LogError("Core Data Error", "You Lost Your Core Data");
                }
            }
        }

        //将额外工具解压到工程目录下
        private static void MoveToolsToProject()
        {
            if (File.Exists(PathConfig.ExtraToolPathInPackage))
            {
                PathEx.MakeDirectoryExist(PathConfig.ExtraToolPath);
                CompressHelper.DecompressToDirectory(PathConfig.ExtraToolPath, PathConfig.ExtraToolPathInPackage);
            }
            else
            {
                Debug.logger.LogWarning("Reset Core 初始化","未找到工具集");
            }
        }

        //将额外工具解压到工程目录下
        private static void MoveSDKToTemp()
        {
            if (File.Exists(PathConfig.SDKPathInPackage))
            {
                PathEx.MakeDirectoryExist(PathConfig.SDKBackupPath);
                CompressHelper.DecompressToDirectory(PathConfig.SDKBackupPath, PathConfig.SDKPathInPackage);
            }
            else
            {
                Debug.logger.LogWarning("Reset Core 初始化", "SDK工具");
            }
        }

        

    }

}
