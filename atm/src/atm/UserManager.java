package atm;

import java.util.ArrayList;


public class UserManager {
	private static ArrayList<User> list = new ArrayList<User>();

	public ArrayList<User> getUserList() {
		return this.list;
	}

	public void setUserList(ArrayList<User> userList) {
		this.list = userList;
	}
	
	// Create
	public void addUser(User user) {
			this.list.add(user);
			
			System.out.println("============���� ȸ�� ����===========");
			for(int i=0;i<this.list.size();i++) {
				System.out.println("ID: "+this.list.get(i).getUserId()
								  +" NAME: "+this.list.get(i).getUserName());
			}
			System.out.println("ȸ������ ����");
	}
	// User �� ����
	// Read
//	public User getUser(int index) {
//		User user = this.list.get(index);
//		
//		User reqObj = new User();
//		return reqObj;
//	}
	
	// Update
	
	// delete
	public void deleteUser(int index) {
		this.list.remove(index);
		for(int i=0;i<this.list.size();i++) {
			System.out.println("ID: "+this.list.get(i).getUserId()
							  +" NAME: "+this.list.get(i).getUserName());
		}
	}
	
}
