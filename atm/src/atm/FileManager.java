package atm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class FileManager {
	private AccountManager am;
	private UserManager um;
	private String fileName;

	private File file;

	private FileWriter fileWriter;
	private FileReader fileReader;
	private BufferedReader bufferedReader;
	
	public FileManager(String brandName) {
		this.fileName = brandName + "BankUser.txt";
		
		this.file = new File(this.fileName);
		
		this.um = new UserManager();
		this.am = new AccountManager();
		
	}
	public void save() {
//		String userData = "";
//		String userAcc = "";
//		for(int i=0;i<this.um.getUserList().size();i++) {
//			while(this.am.getAccountList().size() > 0) {
//			userData += this.um.getUserList().get(i).getId() + "/"
//					+ this.um.getUserList().get(i).getName() + "/"
//					+ this.um.getUserList().get(i).getPassword() + "\n";
//			}
//		}
//		
//		
//		for(int i=0; i<this.am.getAccountList().size();i++) {
//			userAcc += this.am.getAccountList().get(i).getUserId() + "/"
//					+ this.am.getAccountList().get(i).getAccNum() + "/"
//					+ this.am.getAccountList().get(i).getMoney() + "\n";
//		}
//		userAcc = userAcc.substring(0, userAcc.length() -1);
//		try {
//			this.fileWriter = new FileWriter(file);
//			this.fileWriter.write(userData);
//			this.fileWriter.write(userAcc);
//			fileWriter.close();
//			System.out.println("파일 저장 성공");
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.err.println("파일 저장 실패");
//		}

		
	}
}
