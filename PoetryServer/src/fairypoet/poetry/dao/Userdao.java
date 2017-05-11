package fairypoet.poetry.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fairypoet.poetry.entity.User;

public class Userdao {
	protected Statement sql=null;
	protected ResultSet rs=null;
	public int register(Connection conn, User user) throws SQLException {
		sql=conn.createStatement();
		int mark=0;
		String condition="insert into users values('"+user.getUid()+"','"+user.getUname()+"','"+user.getPassword()+"')";		
		mark=sql.executeUpdate(condition);
		return mark;
	}
	public int login(Connection conn, User user) throws SQLException {
		sql=conn.createStatement();
		int mark=0;
		String condition="Select * from users where uid ='"+user.getUid()+"' and password ='"+user.getPassword()+"'";
		rs=sql.executeQuery(condition);
		while(rs.next()){
			//user.setUid(rs.getString(1));
			user.setUname(rs.getString(2));
			mark=1;
		}
		return mark;
	}
	/*public int selectPassword(Connection conn,User user) throws SQLException {
		sql=conn.createStatement();
		int mark=0;
		String condition="Select *from users where uid ='"+user.getUid()+"' and password ='"+user.getPassword()+"'";
		rs=sql.executeQuery(condition);
		while(rs.next()){
			//user.setUid(rs.getString(1));
			user.setUname(rs.getString(2));
			mark=1;
		}
		return mark;
	}*/
	public int changePassword(Connection conn, User user) throws SQLException {
		sql=conn.createStatement();
		int mark=0;
		String condition="update users set password ='"+user.getPassword()+"' where uid='"+user.getUid()+"'";
		mark=sql.executeUpdate(condition);
		return mark;
	}
	public int changeUname(Connection conn, User user) throws SQLException {
		sql=conn.createStatement();
		int mark=0;
		String condition="update users set uname ='"+user.getUname()+"' where uid='"+user.getUid()+"'";
		mark=sql.executeUpdate(condition);
		return mark;
	}
	public int checkId(Connection conn,String id) throws SQLException{
		sql=conn.createStatement();
		int mark=0;
		String condition="select * from users where uid ='"+id+"'";
		rs=sql.executeQuery(condition);
		while(rs.next()){
			//User user=new User(rs.getString(1));
			mark=1;
		}
		return mark;
	}
}
