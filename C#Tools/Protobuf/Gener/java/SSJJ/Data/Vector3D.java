// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Vector3D.proto

package SSJJ.Data;

public final class Vector3D {
  private Vector3D() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface Vector3DDataOrBuilder extends
      // @@protoc_insertion_point(interface_extends:SSJJ.Data.Vector3DData)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>optional float x = 1;</code>
     */
    boolean hasX();
    /**
     * <code>optional float x = 1;</code>
     */
    float getX();

    /**
     * <code>optional float y = 2;</code>
     */
    boolean hasY();
    /**
     * <code>optional float y = 2;</code>
     */
    float getY();

    /**
     * <code>optional float z = 3;</code>
     */
    boolean hasZ();
    /**
     * <code>optional float z = 3;</code>
     */
    float getZ();
  }
  /**
   * Protobuf type {@code SSJJ.Data.Vector3DData}
   */
  public  static final class Vector3DData extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:SSJJ.Data.Vector3DData)
      Vector3DDataOrBuilder {
    // Use Vector3DData.newBuilder() to construct.
    private Vector3DData(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private Vector3DData() {
      x_ = 0F;
      y_ = 0F;
      z_ = 0F;
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private Vector3DData(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 13: {
              bitField0_ |= 0x00000001;
              x_ = input.readFloat();
              break;
            }
            case 21: {
              bitField0_ |= 0x00000002;
              y_ = input.readFloat();
              break;
            }
            case 29: {
              bitField0_ |= 0x00000004;
              z_ = input.readFloat();
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return SSJJ.Data.Vector3D.internal_static_SSJJ_Data_Vector3DData_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return SSJJ.Data.Vector3D.internal_static_SSJJ_Data_Vector3DData_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              SSJJ.Data.Vector3D.Vector3DData.class, SSJJ.Data.Vector3D.Vector3DData.Builder.class);
    }

    private int bitField0_;
    public static final int X_FIELD_NUMBER = 1;
    private float x_;
    /**
     * <code>optional float x = 1;</code>
     */
    public boolean hasX() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>optional float x = 1;</code>
     */
    public float getX() {
      return x_;
    }

    public static final int Y_FIELD_NUMBER = 2;
    private float y_;
    /**
     * <code>optional float y = 2;</code>
     */
    public boolean hasY() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>optional float y = 2;</code>
     */
    public float getY() {
      return y_;
    }

    public static final int Z_FIELD_NUMBER = 3;
    private float z_;
    /**
     * <code>optional float z = 3;</code>
     */
    public boolean hasZ() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>optional float z = 3;</code>
     */
    public float getZ() {
      return z_;
    }

    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeFloat(1, x_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeFloat(2, y_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeFloat(3, z_);
      }
      unknownFields.writeTo(output);
    }

    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeFloatSize(1, x_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeFloatSize(2, y_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeFloatSize(3, z_);
      }
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof SSJJ.Data.Vector3D.Vector3DData)) {
        return super.equals(obj);
      }
      SSJJ.Data.Vector3D.Vector3DData other = (SSJJ.Data.Vector3D.Vector3DData) obj;

      boolean result = true;
      result = result && (hasX() == other.hasX());
      if (hasX()) {
        result = result && (
            java.lang.Float.floatToIntBits(getX())
            == java.lang.Float.floatToIntBits(
                other.getX()));
      }
      result = result && (hasY() == other.hasY());
      if (hasY()) {
        result = result && (
            java.lang.Float.floatToIntBits(getY())
            == java.lang.Float.floatToIntBits(
                other.getY()));
      }
      result = result && (hasZ() == other.hasZ());
      if (hasZ()) {
        result = result && (
            java.lang.Float.floatToIntBits(getZ())
            == java.lang.Float.floatToIntBits(
                other.getZ()));
      }
      result = result && unknownFields.equals(other.unknownFields);
      return result;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptorForType().hashCode();
      if (hasX()) {
        hash = (37 * hash) + X_FIELD_NUMBER;
        hash = (53 * hash) + java.lang.Float.floatToIntBits(
            getX());
      }
      if (hasY()) {
        hash = (37 * hash) + Y_FIELD_NUMBER;
        hash = (53 * hash) + java.lang.Float.floatToIntBits(
            getY());
      }
      if (hasZ()) {
        hash = (37 * hash) + Z_FIELD_NUMBER;
        hash = (53 * hash) + java.lang.Float.floatToIntBits(
            getZ());
      }
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static SSJJ.Data.Vector3D.Vector3DData parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static SSJJ.Data.Vector3D.Vector3DData parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static SSJJ.Data.Vector3D.Vector3DData parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static SSJJ.Data.Vector3D.Vector3DData parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static SSJJ.Data.Vector3D.Vector3DData parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static SSJJ.Data.Vector3D.Vector3DData parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static SSJJ.Data.Vector3D.Vector3DData parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static SSJJ.Data.Vector3D.Vector3DData parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static SSJJ.Data.Vector3D.Vector3DData parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static SSJJ.Data.Vector3D.Vector3DData parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(SSJJ.Data.Vector3D.Vector3DData prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code SSJJ.Data.Vector3DData}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:SSJJ.Data.Vector3DData)
        SSJJ.Data.Vector3D.Vector3DDataOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return SSJJ.Data.Vector3D.internal_static_SSJJ_Data_Vector3DData_descriptor;
      }

      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return SSJJ.Data.Vector3D.internal_static_SSJJ_Data_Vector3DData_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                SSJJ.Data.Vector3D.Vector3DData.class, SSJJ.Data.Vector3D.Vector3DData.Builder.class);
      }

      // Construct using SSJJ.Data.Vector3D.Vector3DData.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      public Builder clear() {
        super.clear();
        x_ = 0F;
        bitField0_ = (bitField0_ & ~0x00000001);
        y_ = 0F;
        bitField0_ = (bitField0_ & ~0x00000002);
        z_ = 0F;
        bitField0_ = (bitField0_ & ~0x00000004);
        return this;
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return SSJJ.Data.Vector3D.internal_static_SSJJ_Data_Vector3DData_descriptor;
      }

      public SSJJ.Data.Vector3D.Vector3DData getDefaultInstanceForType() {
        return SSJJ.Data.Vector3D.Vector3DData.getDefaultInstance();
      }

      public SSJJ.Data.Vector3D.Vector3DData build() {
        SSJJ.Data.Vector3D.Vector3DData result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public SSJJ.Data.Vector3D.Vector3DData buildPartial() {
        SSJJ.Data.Vector3D.Vector3DData result = new SSJJ.Data.Vector3D.Vector3DData(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.x_ = x_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.y_ = y_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.z_ = z_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder clone() {
        return (Builder) super.clone();
      }
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return (Builder) super.setField(field, value);
      }
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return (Builder) super.clearField(field);
      }
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return (Builder) super.clearOneof(oneof);
      }
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, Object value) {
        return (Builder) super.setRepeatedField(field, index, value);
      }
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return (Builder) super.addRepeatedField(field, value);
      }
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof SSJJ.Data.Vector3D.Vector3DData) {
          return mergeFrom((SSJJ.Data.Vector3D.Vector3DData)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(SSJJ.Data.Vector3D.Vector3DData other) {
        if (other == SSJJ.Data.Vector3D.Vector3DData.getDefaultInstance()) return this;
        if (other.hasX()) {
          setX(other.getX());
        }
        if (other.hasY()) {
          setY(other.getY());
        }
        if (other.hasZ()) {
          setZ(other.getZ());
        }
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      public final boolean isInitialized() {
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        SSJJ.Data.Vector3D.Vector3DData parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (SSJJ.Data.Vector3D.Vector3DData) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private float x_ ;
      /**
       * <code>optional float x = 1;</code>
       */
      public boolean hasX() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>optional float x = 1;</code>
       */
      public float getX() {
        return x_;
      }
      /**
       * <code>optional float x = 1;</code>
       */
      public Builder setX(float value) {
        bitField0_ |= 0x00000001;
        x_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional float x = 1;</code>
       */
      public Builder clearX() {
        bitField0_ = (bitField0_ & ~0x00000001);
        x_ = 0F;
        onChanged();
        return this;
      }

      private float y_ ;
      /**
       * <code>optional float y = 2;</code>
       */
      public boolean hasY() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>optional float y = 2;</code>
       */
      public float getY() {
        return y_;
      }
      /**
       * <code>optional float y = 2;</code>
       */
      public Builder setY(float value) {
        bitField0_ |= 0x00000002;
        y_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional float y = 2;</code>
       */
      public Builder clearY() {
        bitField0_ = (bitField0_ & ~0x00000002);
        y_ = 0F;
        onChanged();
        return this;
      }

      private float z_ ;
      /**
       * <code>optional float z = 3;</code>
       */
      public boolean hasZ() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      /**
       * <code>optional float z = 3;</code>
       */
      public float getZ() {
        return z_;
      }
      /**
       * <code>optional float z = 3;</code>
       */
      public Builder setZ(float value) {
        bitField0_ |= 0x00000004;
        z_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional float z = 3;</code>
       */
      public Builder clearZ() {
        bitField0_ = (bitField0_ & ~0x00000004);
        z_ = 0F;
        onChanged();
        return this;
      }
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFields(unknownFields);
      }

      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:SSJJ.Data.Vector3DData)
    }

    // @@protoc_insertion_point(class_scope:SSJJ.Data.Vector3DData)
    private static final SSJJ.Data.Vector3D.Vector3DData DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new SSJJ.Data.Vector3D.Vector3DData();
    }

    public static SSJJ.Data.Vector3D.Vector3DData getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    @java.lang.Deprecated public static final com.google.protobuf.Parser<Vector3DData>
        PARSER = new com.google.protobuf.AbstractParser<Vector3DData>() {
      public Vector3DData parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
          return new Vector3DData(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<Vector3DData> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<Vector3DData> getParserForType() {
      return PARSER;
    }

    public SSJJ.Data.Vector3D.Vector3DData getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_SSJJ_Data_Vector3DData_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_SSJJ_Data_Vector3DData_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\016Vector3D.proto\022\tSSJJ.Data\"/\n\014Vector3DD" +
      "ata\022\t\n\001x\030\001 \001(\002\022\t\n\001y\030\002 \001(\002\022\t\n\001z\030\003 \001(\002"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_SSJJ_Data_Vector3DData_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_SSJJ_Data_Vector3DData_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_SSJJ_Data_Vector3DData_descriptor,
        new java.lang.String[] { "X", "Y", "Z", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
