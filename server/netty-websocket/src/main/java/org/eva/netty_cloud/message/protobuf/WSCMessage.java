// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: WSMessage.proto

package org.eva.netty_cloud.message.protobuf;

/**
 * Protobuf type {@code WSCMessage}
 */
public  final class WSCMessage extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:WSCMessage)
    WSCMessageOrBuilder {
  // Use WSCMessage.newBuilder() to construct.
  private WSCMessage(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private WSCMessage() {
    mid_ = 0L;
    type_ = 0;
    uid_ = "";
    tId_ = "";
    txt_ = "";
    node_ = "";
    broadcast_ = false;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  @SuppressWarnings("unused")
private WSCMessage(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    int mutable_bitField0_ = 0;
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!input.skipField(tag)) {
              done = true;
            }
            break;
          }
          case 8: {

            mid_ = input.readInt64();
            break;
          }
          case 16: {
            int rawValue = input.readEnum();

            type_ = rawValue;
            break;
          }
          case 26: {
            java.lang.String s = input.readStringRequireUtf8();

            uid_ = s;
            break;
          }
          case 34: {
            java.lang.String s = input.readStringRequireUtf8();

            tId_ = s;
            break;
          }
          case 42: {
            java.lang.String s = input.readStringRequireUtf8();

            txt_ = s;
            break;
          }
          case 50: {
            java.lang.String s = input.readStringRequireUtf8();

            node_ = s;
            break;
          }
          case 56: {

            broadcast_ = input.readBool();
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
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return org.eva.netty_cloud.message.protobuf.WebSocketCloudProtobufMessage.internal_static_WSCMessage_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return org.eva.netty_cloud.message.protobuf.WebSocketCloudProtobufMessage.internal_static_WSCMessage_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            org.eva.netty_cloud.message.protobuf.WSCMessage.class, org.eva.netty_cloud.message.protobuf.WSCMessage.Builder.class);
  }

  /**
   * Protobuf enum {@code WSCMessage.MsgType}
   */
  public enum MsgType
      implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <code>MSG = 0;</code>
     */
    MSG(0),
    /**
     * <code>TXT = 1;</code>
     */
    TXT(1),
    /**
     * <code>TIME = 2;</code>
     */
    TIME(2),
    /**
     * <code>REG = 3;</code>
     */
    REG(3),
    /**
     * <code>GROUP = 4;</code>
     */
    GROUP(4),
    UNRECOGNIZED(-1),
    ;

    /**
     * <code>MSG = 0;</code>
     */
    public static final int MSG_VALUE = 0;
    /**
     * <code>TXT = 1;</code>
     */
    public static final int TXT_VALUE = 1;
    /**
     * <code>TIME = 2;</code>
     */
    public static final int TIME_VALUE = 2;
    /**
     * <code>REG = 3;</code>
     */
    public static final int REG_VALUE = 3;
    /**
     * <code>GROUP = 4;</code>
     */
    public static final int GROUP_VALUE = 4;


    public final int getNumber() {
      if (this == UNRECOGNIZED) {
        throw new java.lang.IllegalArgumentException(
            "Can't get the number of an unknown enum value.");
      }
      return value;
    }

    /**
     * @deprecated Use {@link #forNumber(int)} instead.
     */
    @java.lang.Deprecated
    public static MsgType valueOf(int value) {
      return forNumber(value);
    }

    public static MsgType forNumber(int value) {
      switch (value) {
        case 0: return MSG;
        case 1: return TXT;
        case 2: return TIME;
        case 3: return REG;
        case 4: return GROUP;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<MsgType>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static final com.google.protobuf.Internal.EnumLiteMap<
        MsgType> internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<MsgType>() {
            public MsgType findValueByNumber(int number) {
              return MsgType.forNumber(number);
            }
          };

    public final com.google.protobuf.Descriptors.EnumValueDescriptor
        getValueDescriptor() {
      return getDescriptor().getValues().get(ordinal());
    }
    public final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptorForType() {
      return getDescriptor();
    }
    public static final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptor() {
      return org.eva.netty_cloud.message.protobuf.WSCMessage.getDescriptor().getEnumTypes().get(0);
    }

    private static final MsgType[] VALUES = values();

    public static MsgType valueOf(
        com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
        throw new java.lang.IllegalArgumentException(
          "EnumValueDescriptor is not for this type.");
      }
      if (desc.getIndex() == -1) {
        return UNRECOGNIZED;
      }
      return VALUES[desc.getIndex()];
    }

    private final int value;

    private MsgType(int value) {
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:WSCMessage.MsgType)
  }

  public static final int MID_FIELD_NUMBER = 1;
  private long mid_;
  /**
   * <code>optional int64 mid = 1;</code>
   */
  public long getMid() {
    return mid_;
  }

  public static final int TYPE_FIELD_NUMBER = 2;
  private int type_;
  /**
   * <code>optional .WSCMessage.MsgType type = 2;</code>
   */
  public int getTypeValue() {
    return type_;
  }
  /**
   * <code>optional .WSCMessage.MsgType type = 2;</code>
   */
  public org.eva.netty_cloud.message.protobuf.WSCMessage.MsgType getType() {
    org.eva.netty_cloud.message.protobuf.WSCMessage.MsgType result = org.eva.netty_cloud.message.protobuf.WSCMessage.MsgType.valueOf(type_);
    return result == null ? org.eva.netty_cloud.message.protobuf.WSCMessage.MsgType.UNRECOGNIZED : result;
  }

  public static final int UID_FIELD_NUMBER = 3;
  private volatile java.lang.Object uid_;
  /**
   * <code>optional string uid = 3;</code>
   */
  public java.lang.String getUid() {
    java.lang.Object ref = uid_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      uid_ = s;
      return s;
    }
  }
  /**
   * <code>optional string uid = 3;</code>
   */
  public com.google.protobuf.ByteString
      getUidBytes() {
    java.lang.Object ref = uid_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      uid_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int T_ID_FIELD_NUMBER = 4;
  private volatile java.lang.Object tId_;
  /**
   * <code>optional string t_id = 4;</code>
   */
  public java.lang.String getTId() {
    java.lang.Object ref = tId_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      tId_ = s;
      return s;
    }
  }
  /**
   * <code>optional string t_id = 4;</code>
   */
  public com.google.protobuf.ByteString
      getTIdBytes() {
    java.lang.Object ref = tId_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      tId_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int TXT_FIELD_NUMBER = 5;
  private volatile java.lang.Object txt_;
  /**
   * <code>optional string txt = 5;</code>
   */
  public java.lang.String getTxt() {
    java.lang.Object ref = txt_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      txt_ = s;
      return s;
    }
  }
  /**
   * <code>optional string txt = 5;</code>
   */
  public com.google.protobuf.ByteString
      getTxtBytes() {
    java.lang.Object ref = txt_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      txt_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int NODE_FIELD_NUMBER = 6;
  private volatile java.lang.Object node_;
  /**
   * <code>optional string node = 6;</code>
   */
  public java.lang.String getNode() {
    java.lang.Object ref = node_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      node_ = s;
      return s;
    }
  }
  /**
   * <code>optional string node = 6;</code>
   */
  public com.google.protobuf.ByteString
      getNodeBytes() {
    java.lang.Object ref = node_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      node_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int BROADCAST_FIELD_NUMBER = 7;
  private boolean broadcast_;
  /**
   * <code>optional bool broadcast = 7;</code>
   */
  public boolean getBroadcast() {
    return broadcast_;
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
    if (mid_ != 0L) {
      output.writeInt64(1, mid_);
    }
    if (type_ != org.eva.netty_cloud.message.protobuf.WSCMessage.MsgType.MSG.getNumber()) {
      output.writeEnum(2, type_);
    }
    if (!getUidBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, uid_);
    }
    if (!getTIdBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 4, tId_);
    }
    if (!getTxtBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 5, txt_);
    }
    if (!getNodeBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 6, node_);
    }
    if (broadcast_ != false) {
      output.writeBool(7, broadcast_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (mid_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, mid_);
    }
    if (type_ != org.eva.netty_cloud.message.protobuf.WSCMessage.MsgType.MSG.getNumber()) {
      size += com.google.protobuf.CodedOutputStream
        .computeEnumSize(2, type_);
    }
    if (!getUidBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, uid_);
    }
    if (!getTIdBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, tId_);
    }
    if (!getTxtBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(5, txt_);
    }
    if (!getNodeBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(6, node_);
    }
    if (broadcast_ != false) {
      size += com.google.protobuf.CodedOutputStream
        .computeBoolSize(7, broadcast_);
    }
    memoizedSize = size;
    return size;
  }

  private static final long serialVersionUID = 0L;
  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof org.eva.netty_cloud.message.protobuf.WSCMessage)) {
      return super.equals(obj);
    }
    org.eva.netty_cloud.message.protobuf.WSCMessage other = (org.eva.netty_cloud.message.protobuf.WSCMessage) obj;

    boolean result = true;
    result = result && (getMid()
        == other.getMid());
    result = result && type_ == other.type_;
    result = result && getUid()
        .equals(other.getUid());
    result = result && getTId()
        .equals(other.getTId());
    result = result && getTxt()
        .equals(other.getTxt());
    result = result && getNode()
        .equals(other.getNode());
    result = result && (getBroadcast()
        == other.getBroadcast());
    return result;
  }

  @SuppressWarnings("unchecked")
@java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptorForType().hashCode();
    hash = (37 * hash) + MID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getMid());
    hash = (37 * hash) + TYPE_FIELD_NUMBER;
    hash = (53 * hash) + type_;
    hash = (37 * hash) + UID_FIELD_NUMBER;
    hash = (53 * hash) + getUid().hashCode();
    hash = (37 * hash) + T_ID_FIELD_NUMBER;
    hash = (53 * hash) + getTId().hashCode();
    hash = (37 * hash) + TXT_FIELD_NUMBER;
    hash = (53 * hash) + getTxt().hashCode();
    hash = (37 * hash) + NODE_FIELD_NUMBER;
    hash = (53 * hash) + getNode().hashCode();
    hash = (37 * hash) + BROADCAST_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(
        getBroadcast());
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static org.eva.netty_cloud.message.protobuf.WSCMessage parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.eva.netty_cloud.message.protobuf.WSCMessage parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.eva.netty_cloud.message.protobuf.WSCMessage parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.eva.netty_cloud.message.protobuf.WSCMessage parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.eva.netty_cloud.message.protobuf.WSCMessage parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.eva.netty_cloud.message.protobuf.WSCMessage parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.eva.netty_cloud.message.protobuf.WSCMessage parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static org.eva.netty_cloud.message.protobuf.WSCMessage parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.eva.netty_cloud.message.protobuf.WSCMessage parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.eva.netty_cloud.message.protobuf.WSCMessage parseFrom(
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
  public static Builder newBuilder(org.eva.netty_cloud.message.protobuf.WSCMessage prototype) {
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
   * Protobuf type {@code WSCMessage}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:WSCMessage)
      org.eva.netty_cloud.message.protobuf.WSCMessageOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return org.eva.netty_cloud.message.protobuf.WebSocketCloudProtobufMessage.internal_static_WSCMessage_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return org.eva.netty_cloud.message.protobuf.WebSocketCloudProtobufMessage.internal_static_WSCMessage_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              org.eva.netty_cloud.message.protobuf.WSCMessage.class, org.eva.netty_cloud.message.protobuf.WSCMessage.Builder.class);
    }

    // Construct using org.eva.netty_cloud.message.protobuf.WSCMessage.newBuilder()
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
      mid_ = 0L;

      type_ = 0;

      uid_ = "";

      tId_ = "";

      txt_ = "";

      node_ = "";

      broadcast_ = false;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return org.eva.netty_cloud.message.protobuf.WebSocketCloudProtobufMessage.internal_static_WSCMessage_descriptor;
    }

    public org.eva.netty_cloud.message.protobuf.WSCMessage getDefaultInstanceForType() {
      return org.eva.netty_cloud.message.protobuf.WSCMessage.getDefaultInstance();
    }

    public org.eva.netty_cloud.message.protobuf.WSCMessage build() {
      org.eva.netty_cloud.message.protobuf.WSCMessage result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public org.eva.netty_cloud.message.protobuf.WSCMessage buildPartial() {
      org.eva.netty_cloud.message.protobuf.WSCMessage result = new org.eva.netty_cloud.message.protobuf.WSCMessage(this);
      result.mid_ = mid_;
      result.type_ = type_;
      result.uid_ = uid_;
      result.tId_ = tId_;
      result.txt_ = txt_;
      result.node_ = node_;
      result.broadcast_ = broadcast_;
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
      if (other instanceof org.eva.netty_cloud.message.protobuf.WSCMessage) {
        return mergeFrom((org.eva.netty_cloud.message.protobuf.WSCMessage)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(org.eva.netty_cloud.message.protobuf.WSCMessage other) {
      if (other == org.eva.netty_cloud.message.protobuf.WSCMessage.getDefaultInstance()) return this;
      if (other.getMid() != 0L) {
        setMid(other.getMid());
      }
      if (other.type_ != 0) {
        setTypeValue(other.getTypeValue());
      }
      if (!other.getUid().isEmpty()) {
        uid_ = other.uid_;
        onChanged();
      }
      if (!other.getTId().isEmpty()) {
        tId_ = other.tId_;
        onChanged();
      }
      if (!other.getTxt().isEmpty()) {
        txt_ = other.txt_;
        onChanged();
      }
      if (!other.getNode().isEmpty()) {
        node_ = other.node_;
        onChanged();
      }
      if (other.getBroadcast() != false) {
        setBroadcast(other.getBroadcast());
      }
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
      org.eva.netty_cloud.message.protobuf.WSCMessage parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (org.eva.netty_cloud.message.protobuf.WSCMessage) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private long mid_ ;
    /**
     * <code>optional int64 mid = 1;</code>
     */
    public long getMid() {
      return mid_;
    }
    /**
     * <code>optional int64 mid = 1;</code>
     */
    public Builder setMid(long value) {
      
      mid_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional int64 mid = 1;</code>
     */
    public Builder clearMid() {
      
      mid_ = 0L;
      onChanged();
      return this;
    }

    private int type_ = 0;
    /**
     * <code>optional .WSCMessage.MsgType type = 2;</code>
     */
    public int getTypeValue() {
      return type_;
    }
    /**
     * <code>optional .WSCMessage.MsgType type = 2;</code>
     */
    public Builder setTypeValue(int value) {
      type_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional .WSCMessage.MsgType type = 2;</code>
     */
    public org.eva.netty_cloud.message.protobuf.WSCMessage.MsgType getType() {
      org.eva.netty_cloud.message.protobuf.WSCMessage.MsgType result = org.eva.netty_cloud.message.protobuf.WSCMessage.MsgType.valueOf(type_);
      return result == null ? org.eva.netty_cloud.message.protobuf.WSCMessage.MsgType.UNRECOGNIZED : result;
    }
    /**
     * <code>optional .WSCMessage.MsgType type = 2;</code>
     */
    public Builder setType(org.eva.netty_cloud.message.protobuf.WSCMessage.MsgType value) {
      if (value == null) {
        throw new NullPointerException();
      }
      
      type_ = value.getNumber();
      onChanged();
      return this;
    }
    /**
     * <code>optional .WSCMessage.MsgType type = 2;</code>
     */
    public Builder clearType() {
      
      type_ = 0;
      onChanged();
      return this;
    }

    private java.lang.Object uid_ = "";
    /**
     * <code>optional string uid = 3;</code>
     */
    public java.lang.String getUid() {
      java.lang.Object ref = uid_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        uid_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>optional string uid = 3;</code>
     */
    public com.google.protobuf.ByteString
        getUidBytes() {
      java.lang.Object ref = uid_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        uid_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>optional string uid = 3;</code>
     */
    public Builder setUid(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      uid_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional string uid = 3;</code>
     */
    public Builder clearUid() {
      
      uid_ = getDefaultInstance().getUid();
      onChanged();
      return this;
    }
    /**
     * <code>optional string uid = 3;</code>
     */
    public Builder setUidBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      uid_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object tId_ = "";
    /**
     * <code>optional string t_id = 4;</code>
     */
    public java.lang.String getTId() {
      java.lang.Object ref = tId_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        tId_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>optional string t_id = 4;</code>
     */
    public com.google.protobuf.ByteString
        getTIdBytes() {
      java.lang.Object ref = tId_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        tId_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>optional string t_id = 4;</code>
     */
    public Builder setTId(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      tId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional string t_id = 4;</code>
     */
    public Builder clearTId() {
      
      tId_ = getDefaultInstance().getTId();
      onChanged();
      return this;
    }
    /**
     * <code>optional string t_id = 4;</code>
     */
    public Builder setTIdBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      tId_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object txt_ = "";
    /**
     * <code>optional string txt = 5;</code>
     */
    public java.lang.String getTxt() {
      java.lang.Object ref = txt_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        txt_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>optional string txt = 5;</code>
     */
    public com.google.protobuf.ByteString
        getTxtBytes() {
      java.lang.Object ref = txt_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        txt_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>optional string txt = 5;</code>
     */
    public Builder setTxt(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      txt_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional string txt = 5;</code>
     */
    public Builder clearTxt() {
      
      txt_ = getDefaultInstance().getTxt();
      onChanged();
      return this;
    }
    /**
     * <code>optional string txt = 5;</code>
     */
    public Builder setTxtBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      txt_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object node_ = "";
    /**
     * <code>optional string node = 6;</code>
     */
    public java.lang.String getNode() {
      java.lang.Object ref = node_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        node_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>optional string node = 6;</code>
     */
    public com.google.protobuf.ByteString
        getNodeBytes() {
      java.lang.Object ref = node_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        node_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>optional string node = 6;</code>
     */
    public Builder setNode(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      node_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional string node = 6;</code>
     */
    public Builder clearNode() {
      
      node_ = getDefaultInstance().getNode();
      onChanged();
      return this;
    }
    /**
     * <code>optional string node = 6;</code>
     */
    public Builder setNodeBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      node_ = value;
      onChanged();
      return this;
    }

    private boolean broadcast_ ;
    /**
     * <code>optional bool broadcast = 7;</code>
     */
    public boolean getBroadcast() {
      return broadcast_;
    }
    /**
     * <code>optional bool broadcast = 7;</code>
     */
    public Builder setBroadcast(boolean value) {
      
      broadcast_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional bool broadcast = 7;</code>
     */
    public Builder clearBroadcast() {
      
      broadcast_ = false;
      onChanged();
      return this;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:WSCMessage)
  }

  // @@protoc_insertion_point(class_scope:WSCMessage)
  private static final org.eva.netty_cloud.message.protobuf.WSCMessage DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new org.eva.netty_cloud.message.protobuf.WSCMessage();
  }

  public static org.eva.netty_cloud.message.protobuf.WSCMessage getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<WSCMessage>
      PARSER = new com.google.protobuf.AbstractParser<WSCMessage>() {
    public WSCMessage parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new WSCMessage(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<WSCMessage> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<WSCMessage> getParserForType() {
    return PARSER;
  }

  public org.eva.netty_cloud.message.protobuf.WSCMessage getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

