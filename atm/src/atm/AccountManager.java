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
	// Account �� ����
	// Create
	public void addAccount(Account account) {
		this.list.add(account);
		
		System.out.println("============���� ȸ�� ����===========");
		for(int i=0;i<this.list.size();i++) {
			System.out.println("ID: "+this.list.get(i).getId()
							  +" ACC: "+this.list.get(i).getAccount()
							  +" MONEY: "+this.list.get(i).getMoney());
		}
		System.out.println("ȸ������ ����");
}
	// Read
	// Update
}
