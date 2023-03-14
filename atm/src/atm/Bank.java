package atm;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Bank {
	private UserManager um;
	private AccountManager am;
	private String bankName;
	private int endSystem;
	private int log;
	private Scanner scan;
	private Random ran;

	public void init() {
		this.um = new UserManager(); // private list�� ������ �غ�Ϸ�
		this.am = new AccountManager();// private list�� ������ �غ�Ϸ�
		this.scan = new Scanner(System.in);
		this.ran = new Random();
		this.endSystem = -1;
		this.log = -1;
	}

	public Bank(String bankName) {
		this.bankName = bankName;
	}

	// Banking ���� �޼ҵ�
	public int inputNumber() {
		System.out.print("�޴�����: ");
		int select = this.scan.nextInt();

		return select;
	}

	public void join() {
		if(log == -1) {
		System.out.print("ID: ");
		String userId = this.scan.next();
		System.out.print("PASSWORD: ");
		String userPassword = this.scan.next();
		System.out.print("NAME: ");
		String userName = this.scan.next();

		User user = new User(userId, userName, userPassword );
		checkId(user);
		}else {
			System.out.println("�α��� ���¿����� �̿��� �� �����ϴ�.");
		}
	}
	public void addAcc() {
		if(log != -1) {
			User user = this.um.getUser(this.log);
			// 
			if(user.getAccountSize()< Account.LIMIT) {
				int acc = ran.nextInt(8999) + 1000;
				System.out.println("�߰��� ���¹�ȣ : "+ acc);
//				System.out.println("���� ��ü ����: "+ this.am.getAccountList().size());
				boolean check = true;
				for(int i=0; i<this.am.getAccountList().size();i++) {
					if(this.am.getAccountList().get(i).getAccount() == acc) {
						check = false;
					}
				}
				if(check) {
					Account account = new Account(this.um.getUserList().get(log).getUserId(), acc, 0 );	
					this.am.addAccount(account);
					user.addAccount(account);
					System.out.println("����ī��Ʈ: "+ user.getAccs().size());
				}
			}else {
				System.out.println("���´� 3�� ������ ���� �����մϴ�.");
			}
		}else{
			System.out.println("�α��� �� ��� �����մϴ�.");
		}
	}
		

	public void checkId(User user) {
		boolean check = false;
		for (int i = 0; i < this.um.getUserList().size(); i++) {
			if (this.um.getUserList().get(i).getUserId().equals(user.getUserId())) {
				check = true;
				System.out.println("�ߺ��� ���̵� �����մϴ�.");
				return;
			}
		}
		if (!check) {
			this.um.addUser(user);
		}
	}

	public void delete() {
		if (this.log != -1) {
			System.out.print("PASSWORD: ");
			String userPassword = this.scan.next();
			
			if (this.um.getUserList().get(log).getUserPassword().equals(userPassword)) {
				this.um.deleteUser(log);
			}
		}else {
			System.out.println("�α��� �� �̿��Ͻ� �� �ֽ��ϴ�.");
		}
	}
	
	public void loginCheck(String id, String password) {
		for(int i=0; i<this.um.getUserList().size();i++) {
			
			if(this.um.getUserList().get(i).getUserId().equals(id) 
					&& this.um.getUserList().get(i).getUserPassword().equals(password)) {
				log = i;
				System.out.println("�α��� �ϼ̽��ϴ�.");
			}
		}
	}
	
	public void login() {
		if(this.log == -1) {
			System.out.print("ID: ");
			String id = this.scan.next();
			System.out.print("PASSWORD: ");
			String password = this.scan.next();
			loginCheck(id,password);
		}else {
				System.out.println("�̹� �α��� �����Դϴ�.");
			}
	}
	public void logout() {
		this.log = -1;
		System.out.println("�α׾ƿ�!!!");
	}

	public void run() {
		init();
		while (endSystem == -1) {
			System.out.println("==============" + this.bankName + "=============");
			System.out.println("1. ȸ������ 2. Ż�� 3. ���½�û 4. �α��� 5.�α׾ƿ� 6. ����");
			int select = inputNumber();
			if (select == 1) {
				join();
			} else if (select == 2) {
				delete();
			} else if (select == 3) {
				addAcc();
			} else if (select == 4) {
				login();
			}else if (select == 5) {
				logout();
			} else if (select == 6) {
				this.endSystem = 1;
				System.out.println("�ý�������");
			}
		}
	}
}
