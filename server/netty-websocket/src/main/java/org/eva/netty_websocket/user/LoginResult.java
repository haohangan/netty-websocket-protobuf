package org.eva.netty_websocket.user;

/**
 * 登陆结果
 * 
 * @author 976175665
 * @date 2017年4月8日 下午6:03:58
 */
public class LoginResult {
	private boolean checkpwd;// 密码检验是否通过
	private UserRole role;// 确定的用户角色
	
	public LoginResult(boolean checkpwd) {
		super();
		this.checkpwd = checkpwd;
	}

	public LoginResult(boolean checkpwd, UserRole role) {
		super();
		this.checkpwd = checkpwd;
		this.role = role;
	}

	public boolean isCheckpwd() {
		return checkpwd;
	}

	public void setCheckpwd(boolean checkpwd) {
		this.checkpwd = checkpwd;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}
	
}
