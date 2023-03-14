package atm;

import java.util.ArrayList;

public class AccountManager {
	private static ArrayList<Account>list = new ArrayList<Account>();
	public ArrayList<Account> getAccountList() {
		return this.list;
	}

	public void setAccountList(ArrayList<Account> accountList) {
		this.list = accountList;
	}
	// Account 에 대한
	// Create
	public void addAccount(Account account) {
		this.list.add(account);
		
		System.out.println("============현재 회원 정보===========");
		for(int i=0;i<this.list.size();i++) {
			System.out.println("ID: "+this.list.get(i).getId()
							  +" ACC: "+this.list.get(i).getAccount()
							  +" MONEY: "+this.list.get(i).getMoney());
		}
		System.out.println("회원가입 성공");
}
	// Read
	// Update
}
