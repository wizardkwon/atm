package atm;

import java.util.ArrayList;

public class User {
	private String userId;
	private String userName;
	private String userPassword;
	private ArrayList<Account>accs;
	
	public User(String userId,String userName,String userPassword ) {
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
	}
	public User(String userId,String userPassword) {
		this.userId = userId;
		this.userPassword = userPassword;
	}
	public User(ArrayList<Account>accs) {
		this.accs = accs;
	}
	
	public ArrayList<Account> getAccs() {
		return this.accs;
	}
	public  void setAccs( ArrayList<Account> accs) {
		this.accs = accs;
	}
	
	
	public String getUserId() {
		return this.userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return this.userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return this.userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	
	
	
}
