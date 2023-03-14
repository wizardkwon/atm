package atm;

public class Account {
	public static int LIMIT = 3;
	private String id;
	private int account;
	private int money;
	
	
	public Account( String id, int account, int money) {
		this.account = account;
		this.id = id;
		this.money = money;
	}
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public int getAccount() {
		return this.account;
	}
	public void setAccount(int account) {
		this.account = account;
	}
	
	public int getMoney() {
		return this.money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	


	
}
