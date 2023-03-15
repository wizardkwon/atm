package atm;

import java.util.Scanner;

public class Bank {

	private Scanner scan;

	private String brandName;
	private int log;
	private UserManager um;
	private AccountManager am;

	// Banking ���� �޼ҵ�
	public Bank(String brandName) {
		this.brandName = brandName;
		this.log = -1;
		this.scan = new Scanner(System.in);
		this.um = new UserManager();
		this.am = new AccountManager();
	}

	private void printMainMenu() {
		System.out.println("1. ȸ������");
		System.out.println("2. ȸ��Ż��");
		System.out.println("3. ���½�û");
		System.out.println("4. ����öȸ");
		System.out.println("5. �α���");
		System.out.println("6. �α׾ƿ�");
		System.out.println("0. ����");
	}

	private int inputNumber() {
		int number = -1;

		System.out.print("input : ");
		String input = this.scan.next();

		try {
			number = Integer.parseInt(input);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return number;
	}

	private void printUserInfo() {
		for (int i = 0; i < this.um.getUserList().size(); i++) {
			System.out.println("ID: " + this.um.getUserList().get(i).getId() + " NAME: "
					+ this.um.getUserList().get(i).getName() + " PASS: " + this.um.getUserList().get(i).getPassword());
		}

	}

	private void printAccInfo() {
		for (int i = 0; i < this.am.getAccountList().size(); i++) {
			System.out.println("ID: " + this.am.getAccountList().get(i).getUserId() + " ACC: "
					+ this.am.getAccountList().get(i).getAccNum() + " MONEY: "
					+ this.am.getAccountList().get(i).getMoney());
		}

	}

	public void run() {
		while (true) {
			System.out.println("==========��ü ȸ�� ���=========");
			printUserInfo();
			System.out.println("==============================");
			System.out.println("==========��ü ���� ���=========");
			printAccInfo();
			System.out.println("==============================");
			printMainMenu();
			int sel = inputNumber();

			if (sel == 1)
				joinUser();
			else if (sel == 2)
				leaveUser();
			else if (sel == 3)
				createAccount();
			else if (sel == 4)
				deleteAccount();
			else if (sel == 5)
				login();
			else if (sel == 6)
				logout();
			else if (sel == 0)
				break;
		}
		System.out.println("�ý����� ����Ǿ����ϴ�.");
	}

	private void login() {
		if (this.log == -1) {
			System.out.println("ID: ");
			String id = this.scan.next();
			System.out.println("PASS: ");
			String pass = this.scan.next();
			for (int i = 0; i < this.um.getUserList().size(); i++) {
				if (this.um.getUserList().get(i).getId().equals(id)
						&& this.um.getUserList().get(i).getPassword().equals(pass)) {
					log = i;
					System.out.println("�α��� ����");
					userMenu();
				}
			}
		}

	}

	private void logout() {
		if (this.log != -1) {
			System.out.println("�α׾ƿ� ����");
			this.log = -1;
		}

	}

	private void userMenu() {
		while (true) {
			System.out.println("1. �Ա� 2. ��� 3. ��ü 4.�ڷΰ���");
			int select = inputNumber();
			if (select == 1) {
				plusMoney();
			} else if (select == 2) {
				minusMoney();
			} else if (select == 3) {
				transfMoney();
			} else if (select == 4) {
				break;
			}
		}

	}

	private void transfMoney() {
		System.out.print("�� ����:");
		String myAcc = this.scan.next();
		boolean myAccCheck = false;
		int myIndex = -1;
		for (int i = 0; i < this.um.getUserList().get(log).getList().size(); i++) {
			if (this.um.getUserList().get(log).getList().get(i).getAccNum().equals(myAcc)) {
				myAccCheck = true;
				myIndex = i;
			}
		}
		if (myAccCheck) {
			System.out.print("������ ����: ");
			String acc = this.scan.next();
			boolean yourAccCheck = false;
			int yourIndex = -1;
			for (int i = 0; i < this.am.getAccountList().size(); i++) {
				if (!this.um.getUserList().get(log).getId().equals(this.am.getAccountList().get(i).getUserId())) {
					if (this.am.getAccountList().get(i).getAccNum().equals(acc)) {
						yourAccCheck = true;
						yourIndex = i;
					}
				}
			}
			if (yourAccCheck) {
				System.out.print("��ü�� �ݾ�: ");
				int transMoney = this.scan.nextInt();
				int curMoney = this.um.getUserList().get(log).getList().get(myIndex).getMoney();
				if (transMoney <= curMoney) {
					int yourCurMoney = this.am.getAccountList().get(yourIndex).getMoney();
					this.um.getUserList().get(log).getList().get(myIndex).setMoney(curMoney - transMoney);

					this.am.getAccountList().get(yourIndex).setMoney(yourCurMoney + transMoney);

				}
			} else {
				System.out.println("���ΰ��� �Ǵ� ���� ���·δ� ��ü�� �Ұ��մϴ�.");
			}

		} else {
			System.out.println("������ ���¸� �ٽ� Ȯ�� �Ͻÿ�.");
		}

	}

	private void minusMoney() {
		System.out.print("����� ����: ");
		String acc = this.scan.next();
		boolean check = false;
		int index = -1;
		for (int i = 0; i < this.um.getUserList().get(log).getAccountSize(); i++) {
			if (this.um.getUserList().get(log).getList().get(i).getAccNum().equals(acc)) {
				check = true;
				index = i;
			}
		}
		if (check) {
			System.out.print("��� �ݾ�:");
			int money = this.scan.nextInt();
			int curMoney = this.um.getUserList().get(log).getList().get(index).getMoney();
			if (curMoney >= money) {
				this.um.getUserList().get(log).getList().get(index).setMoney(curMoney - money);
			} else {
				System.out.println("�ܾ��� �����մϴ�.");
			}
		} else {
			System.out.println("���¹�ȣ�� �ٽ� Ȯ���� �ּ���.");
		}
	}

	private void plusMoney() {
		System.out.print("�Ա��� ����: ");
		String acc = this.scan.next();
		boolean check = false;
		int index = 0;
		for (int i = 0; i < this.um.getUserList().get(log).getAccountSize(); i++) {
			if (this.um.getUserList().get(log).getList().get(i).getAccNum().equals(acc)) {
				check = true;
				index = i;
			}
		}
		if (check) {
			System.out.print("�Ա� �ݾ�:");
			int money = this.scan.nextInt();
			int curMoney = this.um.getUserList().get(log).getList().get(index).getMoney();
			this.um.getUserList().get(log).getList().get(index).setMoney(money + curMoney);
		} else {
			System.out.println("���¹�ȣ�� �ٽ� Ȯ���� �ּ���.");
		}

	}

	private void deleteAccount() {
		System.out.print("id : ");
		String id = this.scan.next();
		System.out.print("password : ");
		String password = this.scan.next();

		User user = this.um.getUserById(id);

		if (user != null) {
			if (user.getPassword().equals(password)) {
				if (user.getAccountSize() > 0) {
					int delIndex = -1;
					System.out.println("Delete Acc (oooo-oooo): ");
					String accNum = this.scan.next();
					for (int i = 0; i < user.getAccountSize(); i++) {
						if (user.getList().get(i).getAccNum().equals(accNum)) {
							delIndex = i;
						}
					}
					if (delIndex != -1) {
						this.am.deleteAccount(delIndex);
						user.deleteAccount(delIndex);
					} else {
						System.out.println("���¹�ȣ�� �ٽ� Ȯ���ϼ���.");
					}

				} else {
					System.out.println("���°� �������� �ʽ��ϴ�.");
				}
			} else {
				System.out.println("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
			}
		} else {
			System.out.println("ȸ�������� Ȯ���ϼ���.");
		}

	}

	private void createAccount() {
		System.out.print("id : ");
		String id = this.scan.next();
		System.out.print("password : ");
		String password = this.scan.next();

		// ������ ��ȯ ����
		User user = this.um.getUserById(id);

		if (user != null) {
			if (user.getPassword().equals(password)) {

				if (user.getAccountSize() < Account.LIMIT) {
					Account account = this.am.createAccount(new Account(id));
					this.um.setUser(user, account);

					User update = this.um.getUserById(id);
					System.out.println(update.getAccountSize());

				}
			} else {
				System.out.println("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
			}
		} else {
			System.out.println("ȸ�������� Ȯ���ϼ���.");
		}

	}

	private void leaveUser() {

		if (log != -1) {
			System.out.println("PASSWORD: ");
			String pass = this.scan.next();
			if (this.um.getUserList().get(log).getPassword().equals(pass)) {

				for (int i = 0; i < this.am.getAccountList().size(); i++) {
					if (this.am.getAccountList().get(i).getUserId().equals(this.um.getUserList().get(log).getId())) {
						this.am.deleteAccount(i);
						i--;
					}
				}
				this.um.deleteUser(log);

			} else {
				System.out.println("��й�ȣ�� �ٽ� Ȯ���� �ּ���");
			}
		} else {
			System.out.println("�α��� �� �̿밡�� �մϴ�.");
		}

	}

	private void joinUser() {
		System.out.print("id : ");
		String id = this.scan.next();
		System.out.print("password : ");
		String password = this.scan.next();
		System.out.print("name : ");
		String name = this.scan.next();

		User user = new User(id, password, name);
		if (this.um.addUser(user) != null) {
			System.out.println("ȸ������ ����");
		} else {
			System.out.println("�ߺ��� ���̵� �����մϴ�.");
		}
	}

}