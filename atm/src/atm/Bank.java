package atm;

import java.util.Scanner;

public class Bank {

	private Scanner scan;

	private String brandName;
	private int log;
	private UserManager um;
	private AccountManager am;

	// Banking 관련 메소드
	public Bank(String brandName) {
		this.brandName = brandName;
		this.log = -1;
		this.scan = new Scanner(System.in);
		this.um = new UserManager();
		this.am = new AccountManager();
	}

	private void printMainMenu() {
		System.out.println("1. 회원가입");
		System.out.println("2. 회원탈퇴");
		System.out.println("3. 계좌신청");
		System.out.println("4. 계좌철회");
		System.out.println("5. 로그인");
		System.out.println("6. 로그아웃");
		System.out.println("0. 종료");
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
			System.out.println("==========전체 회원 목록=========");
			printUserInfo();
			System.out.println("==============================");
			System.out.println("==========전체 계좌 목록=========");
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
		System.out.println("시스템이 종료되었습니다.");
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
					System.out.println("로그인 성공");
					userMenu();
				}
			}
		}

	}

	private void logout() {
		if (this.log != -1) {
			System.out.println("로그아웃 성공");
			this.log = -1;
		}

	}

	private void userMenu() {
		while (true) {
			System.out.println("1. 입금 2. 출금 3. 이체 4.뒤로가기");
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
		System.out.print("내 계좌:");
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
			System.out.print("이제할 계좌: ");
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
				System.out.print("이체할 금액: ");
				int transMoney = this.scan.nextInt();
				int curMoney = this.um.getUserList().get(log).getList().get(myIndex).getMoney();
				if (transMoney <= curMoney) {
					int yourCurMoney = this.am.getAccountList().get(yourIndex).getMoney();
					this.um.getUserList().get(log).getList().get(myIndex).setMoney(curMoney - transMoney);

					this.am.getAccountList().get(yourIndex).setMoney(yourCurMoney + transMoney);

				}
			} else {
				System.out.println("본인계좌 또는 없는 계좌로는 이체가 불가합니다.");
			}

		} else {
			System.out.println("본인의 계좌를 다시 확인 하시오.");
		}

	}

	private void minusMoney() {
		System.out.print("출금할 계좌: ");
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
			System.out.print("출금 금액:");
			int money = this.scan.nextInt();
			int curMoney = this.um.getUserList().get(log).getList().get(index).getMoney();
			if (curMoney >= money) {
				this.um.getUserList().get(log).getList().get(index).setMoney(curMoney - money);
			} else {
				System.out.println("잔액이 부족합니다.");
			}
		} else {
			System.out.println("계좌번호를 다시 확인해 주세요.");
		}
	}

	private void plusMoney() {
		System.out.print("입금할 계좌: ");
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
			System.out.print("입금 금액:");
			int money = this.scan.nextInt();
			int curMoney = this.um.getUserList().get(log).getList().get(index).getMoney();
			this.um.getUserList().get(log).getList().get(index).setMoney(money + curMoney);
		} else {
			System.out.println("계좌번호를 다시 확인해 주세요.");
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
						System.out.println("계좌번호를 다시 확인하세요.");
					}

				} else {
					System.out.println("계좌가 존재하지 않습니다.");
				}
			} else {
				System.out.println("비밀번호가 일치하지 않습니다.");
			}
		} else {
			System.out.println("회원정보를 확인하세요.");
		}

	}

	private void createAccount() {
		System.out.print("id : ");
		String id = this.scan.next();
		System.out.print("password : ");
		String password = this.scan.next();

		// 복제본 반환 받음
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
				System.out.println("비밀번호가 일치하지 않습니다.");
			}
		} else {
			System.out.println("회원정보를 확인하세요.");
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
				System.out.println("비밀번호를 다시 확인해 주세요");
			}
		} else {
			System.out.println("로그인 후 이용가능 합니다.");
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
			System.out.println("회원가입 성공");
		} else {
			System.out.println("중복된 아이디가 존재합니다.");
		}
	}

}