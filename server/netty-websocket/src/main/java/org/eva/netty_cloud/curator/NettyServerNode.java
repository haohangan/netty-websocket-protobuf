package org.eva.netty_cloud.curator;

import java.util.Objects;

import com.google.common.base.Strings;
import com.google.gson.Gson;

/**
 * zookeeper中共享的消息
 * 
 * @author 976175665
 * @date 2017年4月6日 下午8:13:06
 */
public class NettyServerNode {
	private String nodeName;
	private String url;
	private String subprotocol;
	private int connNum;// 连接的用户数目
	private String accessKey;// 连接到该服务端的验证码

	/**
	 * 构造器
	 * 
	 * @author YKSE
	 *
	 */
	public static class Builder {
		private String nodeName;
		private String url;
		private String subprotocol;
		private int connNum;// 连接的用户数目
		private String accessKey;// 连接到该服务端的验证码

		public static NettyServerNode buildFromJson(String json) {
			Gson gson = new Gson();
			return gson.fromJson(json, NettyServerNode.class);
		}

		public Builder setNodeName(String nodeName) {
			this.nodeName = nodeName;
			return this;
		}

		public Builder setUrl(String url) {
			this.url = url;
			return this;
		}

		public Builder setConnNum(int connNum) {
			this.connNum = connNum;
			return this;
		}

		public Builder setAccessKey(String accessKey) {
			this.accessKey = accessKey;
			return this;
		}

		public Builder setSubprotocol(String subprotocol) {
			this.subprotocol = subprotocol;
			return this;
		}

		public NettyServerNode build() {
			NettyServerNode node = new NettyServerNode();
			node.setNodeName(nodeName);
			node.setUrl(url);
			node.setSubprotocol(subprotocol);
			node.setConnNum(connNum);
			node.setAccessKey(accessKey);
			return node;
		}
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getConnNum() {
		return connNum;
	}

	public void setConnNum(int connNum) {
		this.connNum = connNum;
	}

	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public String getSubprotocol() {
		return subprotocol;
	}

	public void setSubprotocol(String subprotocol) {
		this.subprotocol = subprotocol;
	}

	public String toJson() throws Exception {
		if (Strings.isNullOrEmpty(nodeName) || Strings.isNullOrEmpty(url)) {
			throw new Exception("名称 或者 url 不能为空");
		}
		Gson gson = new Gson();
		return gson.toJson(this);
	}

	public NettyServerNode() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(nodeName);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof NettyServerNode) {
			NettyServerNode that = (NettyServerNode) obj;
			return Objects.equals(this.nodeName, that.nodeName);
		}
		return false;
	}

}
