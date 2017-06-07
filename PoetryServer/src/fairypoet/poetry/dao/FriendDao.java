package fairypoet.poetry.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fairypoet.poetry.entity.Friend;

public class FriendDao {
	protected Statement sql=null;
	protected ResultSet rs=null;
	
	public int addFriend(Connection conn, String id,String fid) throws SQLException {
		sql=conn.createStatement();
		int mark=0;
		String condition="insert into friend values('"+id+"','"+fid+"')";		
		mark=sql.executeUpdate(condition);
		return mark;
	}
	
	public int checkId(Connection conn,String id,String fid) throws SQLException{
		sql=conn.createStatement();
		int mark=0;
		String condition="select * from friend where uid ='"+id+"' and frienduid='"+fid+"' or uid='"+fid+"' and frienduid='"+id+"'";
		rs=sql.executeQuery(condition);
		while(rs.next()){
			mark=1;
		}
		return mark;
	}
	
	public int check(Connection conn,String id) throws SQLException{
		sql=conn.createStatement();
		int mark=0;
		String condition="select * from users where uid ='"+id+"'";
		rs=sql.executeQuery(condition);
		while(rs.next()){
			mark=1;
		}
		return mark;
	}
	
	public ArrayList<Friend> showFriend1(Connection conn,String uid)throws SQLException{
		ArrayList<Friend> friendlist=new ArrayList<Friend>();
		sql=conn.createStatement();
		//然后在搜索中将poetryid从当前的id-pid开始到当前id显示
		try{
			String sql2="Select * from friend where frienduid='"+uid+"'";
			rs=sql.executeQuery(sql2);
			while(rs.next()){
				Friend friend=new Friend(rs.getString(1),rs.getString(2));
				friendlist.add(friend);
			}
		} catch (Exception e) {
		    e.printStackTrace();
		}
		//System.out.println(friendlist.toString());
		return friendlist;
	}
	
	public ArrayList<Friend> showFriend2(Connection conn,String uid)throws SQLException{
		ArrayList<Friend> friendlist=new ArrayList<Friend>();
		sql=conn.createStatement();
		//然后在搜索中将poetryid从当前的id-pid开始到当前id显示
		try{
			String sql2="Select * from friend where uid='"+uid+"'";
			rs=sql.executeQuery(sql2);
			while(rs.next()){
				Friend friend=new Friend(rs.getString(1),rs.getString(2));
				friendlist.add(friend);
			}
		} catch (Exception e) {
		    e.printStackTrace();
		}
		//System.out.println(friendlist.toString());
		return friendlist;
	}
}
