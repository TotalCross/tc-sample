package totalcross.sample.util;

import java.sql.SQLException;
import java.util.ArrayList;

import totalcross.db.sqlite.SQLiteUtil;
import totalcross.sql.PreparedStatement;
import totalcross.sql.ResultSet;
import totalcross.sql.Statement;
import totalcross.sys.Settings;

public class SQLiteManager {
	
	private static SQLiteManager instance = null;
	private SQLiteUtil util;
	
	private SQLiteManager() {
		
		try {
			util = new SQLiteUtil(Settings.appPath,"SQLiteExample.db");
			createUserTable();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static SQLiteManager getInstance() {
		
		if(instance == null) {
			instance = new SQLiteManager();
		}
		
		return instance;
	}

	public void createUserTable() {
		
		try {
			
			Statement st = util.con().createStatement();
			st.execute("CREATE TABLE IF NOT EXISTS USERS (NAME VARCHAR(50), PHONE VARCHAR(15), EMAIL VARCHAR(20), PASSWORD VARCHAR(20))");
			st.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteUser(String email) {
		try {
			Statement st = util.con().createStatement();
			st.execute("Delete from USERS where EMAIL = 'email' ");
			st.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public Boolean insertUsers(User user) {
		
		Boolean success = true;
		
		try {
		
			String sql = "INSERT INTO USERS VALUES (?,?,?,?)";
			PreparedStatement st = util.con().prepareStatement(sql);
			
			st.setString(1, user.getName());
			st.setString(2, user.getPhone());
			st.setString(3, user.getMail());
			st.setString(4, user.getPassword());
			
			st.executeUpdate();
			st.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			success = false;
		}
		
		return success;
	}
	
	public ArrayList<User> getUsers() {
		
		ArrayList<User> users = new ArrayList<>();
		
		try {
			
			Statement st = util.con().createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM USERS");
			
			while (rs.next()){
				
				User user = new User();
				user.setName(rs.getString("NAME"));
				user.setPhone(rs.getString("PHONE"));
				user.setMail(rs.getString("EMAIL"));
				user.setPassword(rs.getString("PASSWORD"));
				
				users.add(user);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return users;
	}
}

