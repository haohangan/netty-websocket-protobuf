// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: WSMessage.proto

package org.eva.netty_cloud.message.protobuf;

public interface WSCMessageOrBuilder extends
    // @@protoc_insertion_point(interface_extends:WSCMessage)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>optional int64 mid = 1;</code>
   */
  long getMid();

  /**
   * <code>optional .WSCMessage.MsgType type = 2;</code>
   */
  int getTypeValue();
  /**
   * <code>optional .WSCMessage.MsgType type = 2;</code>
   */
  org.eva.netty_cloud.message.protobuf.WSCMessage.MsgType getType();

  /**
   * <code>optional string uid = 3;</code>
   */
  java.lang.String getUid();
  /**
   * <code>optional string uid = 3;</code>
   */
  com.google.protobuf.ByteString
      getUidBytes();

  /**
   * <code>optional string t_id = 4;</code>
   */
  java.lang.String getTId();
  /**
   * <code>optional string t_id = 4;</code>
   */
  com.google.protobuf.ByteString
      getTIdBytes();

  /**
   * <code>optional string txt = 5;</code>
   */
  java.lang.String getTxt();
  /**
   * <code>optional string txt = 5;</code>
   */
  com.google.protobuf.ByteString
      getTxtBytes();

  /**
   * <code>optional string node = 6;</code>
   */
  java.lang.String getNode();
  /**
   * <code>optional string node = 6;</code>
   */
  com.google.protobuf.ByteString
      getNodeBytes();

  /**
   * <code>optional bool broadcast = 7;</code>
   */
  boolean getBroadcast();
}
