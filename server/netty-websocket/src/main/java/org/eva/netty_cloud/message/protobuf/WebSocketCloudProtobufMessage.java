// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: WSMessage.proto

package org.eva.netty_cloud.message.protobuf;

public final class WebSocketCloudProtobufMessage {
  private WebSocketCloudProtobufMessage() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_WSCMessage_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_WSCMessage_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\017WSMessage.proto\"\300\001\n\nWSCMessage\022\013\n\003mid\030" +
      "\001 \001(\003\022!\n\004type\030\002 \001(\0162\023.WSCMessage.MsgType" +
      "\022\013\n\003uid\030\003 \001(\t\022\014\n\004t_id\030\004 \001(\t\022\013\n\003txt\030\005 \001(\t" +
      "\022\014\n\004node\030\006 \001(\t\022\021\n\tbroadcast\030\007 \001(\010\"9\n\007Msg" +
      "Type\022\007\n\003MSG\020\000\022\007\n\003TXT\020\001\022\010\n\004TIME\020\002\022\007\n\003REG\020" +
      "\003\022\t\n\005GROUP\020\004BG\n$org.eva.netty_cloud.mess" +
      "age.protobufB\035WebSocketCloudProtobufMess" +
      "ageP\001b\006proto3"
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
    internal_static_WSCMessage_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_WSCMessage_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_WSCMessage_descriptor,
        new java.lang.String[] { "Mid", "Type", "Uid", "TId", "Txt", "Node", "Broadcast", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
