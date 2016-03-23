﻿/// Simple, really.  There is no need to initialize or even refer to TaskManager.
/// When the first Task is created in an application, a "TaskManager" GameObject
/// will automatically be added to the scene root with the TaskManager component
/// attached.  This component will be responsible for dispatching all coroutines
/// behind the scenes.
///
/// Task also provides an event that is triggered when the coroutine exits.

using UnityEngine;
using System.Collections;
using ResetCore.Util;
using System.Collections.Generic;

/// A Task object represents a coroutine.  Tasks can be started, paused, and stopped.
/// It is an error to attempt to start a task that has been stopped or which has
/// naturally terminated.

namespace ResetCore.Util
{
    public class CoroutineTaskManager : MonoSingleton<CoroutineTaskManager>
    {
        private static Dictionary<string, CoroutineTask> taskList = new Dictionary<string, CoroutineTask>();

        /// <summary>
        /// 协程任务类
        /// </summary>
        class CoroutineTask
        {

            private IEnumerator iEnumer;
            private System.Action<bool> callBack;

            public string name
            {
                get;
                private set;
            }

            public bool running
            {
                get;
                private set;
            }

            public bool paused
            {
                get;
                private set;
            }

            public CoroutineTask(string name, IEnumerator iEnumer, System.Action<bool> callBack = null, bool autoStart = true)
            {
                this.name = name;
                this.iEnumer = iEnumer;
                this.callBack = (comp) =>
                {
                    if (callBack != null)
                        callBack(comp);
                    taskList.Remove(name);
                };
                running = false;
                paused = false;

                if (autoStart == true)
                {
                    Start();
                }
            }

            public void Start()
            {
                running = true;
                CoroutineTaskManager.Instance.StartCoroutine(DoTask());
            }

            public void Pause()
            {
                paused = true;
            }

            public void Unpause()
            {
                paused = false;
            }

            public void Stop()
            {
                running = false;
                callBack(false);
            }

            private IEnumerator DoTask()
            {
                IEnumerator e = iEnumer;
                while (running)
                {
                    if (paused)
                    {
                        yield return null;
                    }
                    else
                    {
                        if (e != null && e.MoveNext())
                        {
                            yield return e.Current;
                        }
                        else
                        {
                            running = false;
                        }
                    }
                }
                callBack(true);
            }


        }

        public override void Init()
        {
            base.Init();
            taskList = new Dictionary<string, CoroutineTask>();
        }


        /// <summary>
        /// 开始一个新任务
        /// </summary>
        /// <param name="taskName"></param>
        /// <param name="iEnumer"></param>
        /// <param name="callBack"></param>
        /// <param name="autoStart"></param>
        public void DoTask(string taskName, IEnumerator iEnumer, System.Action<bool> callBack = null, bool autoStart = true)
        {
            if (taskList.ContainsKey(taskName))
            {
                Debug.logger.LogError("添加新任务", "任务重名！" + taskName);
            }
            taskList.Add(taskName, new CoroutineTask(taskName, iEnumer, callBack, autoStart));
        }

        /// <summary>
        /// 暂停协程
        /// </summary>
        /// <param name="taskName"></param>
        public void Pause(string taskName)
        {
            if (!taskList.ContainsKey(taskName))
            {
                Debug.logger.LogError("暂停任务", "不存在该任务" + taskName);
                return;
            }
            taskList[taskName].Pause();

        }

        /// <summary>
        /// 取消暂停某个协程
        /// </summary>
        /// <param name="taskName"></param>
        public void Unpause(string taskName)
        {
            if (!taskList.ContainsKey(taskName))
            {
                Debug.logger.LogError("重新开始任务", "不存在该任务" + taskName);
                return;
            }
            taskList[taskName].Unpause();
        }
        
        /// <summary>
        /// 停止特定协程
        /// </summary>
        /// <param name="taskName"></param>
        public void Stop(string taskName)
        {
            if (!taskList.ContainsKey(taskName))
            {
                Debug.logger.LogError("停止任务", "不存在该任务" + taskName);
                return;
            }
            taskList[taskName].Stop();
        }

        /// <summary>
        /// 停止所有协程
        /// </summary>
        public void StopAll()
        {
            foreach (CoroutineTask task in taskList.Values)
            {
                task.Stop();
            }
        }

        /// <summary>
        /// 等待一段时间再执行时间
        /// </summary>
        /// <param name="callBack"></param>
        /// <param name="time"></param>
        public void WaitTodo(System.Action callBack, int time)
        {
            DoTask(callBack.GetHashCode().ToString() + time.ToString(), DoWaitTodo(callBack, time));
        }

        private IEnumerator DoWaitTodo(System.Action callBack, int time)
        {
            yield return new WaitForSeconds(time);
            callBack();
        }
    }

}