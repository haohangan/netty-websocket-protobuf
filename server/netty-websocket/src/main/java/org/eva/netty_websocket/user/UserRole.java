package org.eva.netty_websocket.user;

/**
 * 角色分类
 * 
 * @author 976175665
 * @date 2017年4月8日 下午6:04:34
 */
public enum UserRole {
	User("normal_user"), Server("server_user"), Unknown("illegal_user");

	private String rolename;

	private UserRole(String rolename) {
		this.rolename = rolename;
	}

	public String getRolename() {
		return rolename;
	}
}
