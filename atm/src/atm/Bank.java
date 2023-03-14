package atm;

import java.util.ArrayList;
import java.util.Scanner;

public class Bank {
	private UserManager um;
	private AccountManager am;
	private String bankName;
	private int endSystem;
	private int log;
	private Scanner scan;

	public void init() {
		this.um = new UserManager(); // private list를 가져올 준비완료
		this.am = new AccountManager();// private list를 가져올 준비완료
		this.scan = new Scanner(System.in);
		this.endSystem = -1;
		this.log = -1;
	}

	public Bank(String bankName) {
		this.bankName = bankName;
	}

	// Banking 관련 메소드
	public int inputNumber() {
		System.out.print("메뉴선택: ");
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
			System.out.println("로그인 상태에서는 이용할 수 없습니다.");
		}
	}
//	public void addAcc() {
//		if(log != -1) {
//			if(this.am.)
//		}else{
//			System.out.println("로그인 후 사용 가능합니다.");
//		}
//	}
//		

	public void checkId(User user) {
		boolean check = false;
		for (int i = 0; i < this.um.getUserList().size(); i++) {
			if (this.um.getUserList().get(i).getUserId().equals(user.getUserId())) {
				check = true;
				System.out.println("중복된 아이디가 존재합니다.");
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
			System.out.println("로그인 후 이용하실 수 있습니다.");
		}
	}
	
	public void loginCheck(String id, String password) {
		for(int i=0; i<this.um.getUserList().size();i++) {
			
			if(this.um.getUserList().get(i).getUserId().equals(id) 
					&& this.um.getUserList().get(i).getUserPassword().equals(password)) {
				log = i;
				System.out.println("로그인 하셨습니다.");
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
				System.out.println("이미 로그인 상태입니다.");
			}
	}

	public void run() {
		init();
		while (endSystem == -1) {
			System.out.println("==============" + this.bankName + "=============");
			System.out.println("1. 회원가입 2. 탈퇴 3. 계좌신청 4. 로그인 5. 종료");
			int select = inputNumber();
			if (select == 1) {
				join();
			} else if (select == 2) {
				delete();
			} else if (select == 3) {
//				addAcc();
			} else if (select == 4) {
				login();
			} else if (select == 5) {
				this.endSystem = 1;
				System.out.println("시스템종료");
			}
		}
	}
}
