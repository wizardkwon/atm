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
		this.fileName = brandName + "BankData.txt";
		this.file = new File(this.fileName);
		this.um = new UserManager();
		this.am = new AccountManager();

	}

	public void save() {
		String userData = "";

		for (int i = 0; i < this.um.getUserList().size(); i++) {

			for (int j = 0; j < this.am.getAccountList().size(); j++) {
				if (this.um.getUserList().get(i).getId().equals(this.am.getAccountList().get(j).getUserId())) {
					userData += this.um.getUserList().get(i).getId() + "/" + this.um.getUserList().get(i).getName()
							+ "/" + this.um.getUserList().get(i).getPassword() + "/"
							+ this.am.getAccountList().get(j).getAccNum() + "/"
							+ this.am.getAccountList().get(j).getMoney() + "\n";
				}
			}
			int count = 0;
			for (int j = 0; j < this.am.getAccountList().size(); j++) {
				if (this.um.getUserList().get(i).getId().equals(this.am.getAccountList().get(j).getUserId())) {
					count++;
				}
			}
			if (count == 0) {
				userData += this.um.getUserList().get(i).getId() + "/" + this.um.getUserList().get(i).getName() + "/"
						+ this.um.getUserList().get(i).getPassword() + "\n";
			}
		}

		userData = userData.substring(0, userData.length() - 1);
		try {
			this.fileWriter = new FileWriter(file);
			this.fileWriter.write(userData);
			fileWriter.close();
			System.out.println("파일 저장 성공");
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("파일 저장 실패");
		}

	}

	public void load() {
		if (this.file.exists()) {
			try {
				FileReader fr = new FileReader(this.file);
				BufferedReader br = new BufferedReader(fr);

				while (br.ready()) {
					String data = br.readLine();
					String[] dataArr = data.split("/");

					String id = dataArr[0];
					String password = dataArr[1];
					String name = dataArr[2];

					User user = new User(id, password, name);
					int length = dataArr.length;
					this.um.addUser(user);
					if(length > 3) {
					String acc = dataArr[3];
					int money = Integer.parseInt(dataArr[4]);
					Account accs = new Account(id, acc, money);
					this.am.createAccount(accs);
					}
				}
//				this.um.addUser(user);
				System.out.println("파일 load 성공");
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println("파일 load 실패");
			}
		} else {
			System.out.println("저장된 파일이 없습니다.");
			return;
		}

	}
}
