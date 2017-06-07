package fairypoet.poetry.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fairypoet.poetry.entity.*;

public class UserPoetryDao {
	protected Statement sql=null;
	protected ResultSet rs1=null;
	protected ResultSet rs2=null;
	protected ResultSet rs3=null;
	
	public UserPoetry setPoetry(Connection conn,String uid)throws SQLException{
		UserPoetry userpoetry=new UserPoetry();
		sql=conn.createStatement();
		String sql1="update userpoetry set poetryid = poetryid+1 where uid = '"+uid+"'";
		sql.executeUpdate(sql1);
		return userpoetry;
	}
	
	public int findUser(Connection conn,String uid)throws SQLException{
		int mark=0;
		sql=conn.createStatement();
		String sql1="select * from userpoetry where uid='"+uid+"'";
		rs1=sql.executeQuery(sql1);
		while(rs1.next()){
			mark=1;
		}
		return mark;
	}
	public UserPoetry findUP(Connection conn,String uid)throws SQLException{
		sql=conn.createStatement();
		String sql1="select * from userpoetry where uid='"+uid+"'";
		rs1=sql.executeQuery(sql1);
		UserPoetry userpoetry=new UserPoetry();
		while(rs1.next()){
			userpoetry=new UserPoetry(rs1.getString(1),rs1.getInt(2));
		}
		return userpoetry;
	}
	public UserPoetry addUser(Connection conn,String uid)throws SQLException{
		UserPoetry userpoetry=new UserPoetry();
		sql=conn.createStatement();
		String sql1="insert into userpoetry(uid,poetryid) values('"+uid+"',1)";
		sql.executeUpdate(sql1);
		return userpoetry;
	}
	
	public Poetry showPoetry(Connection conn,String uid)throws SQLException{
		sql=conn.createStatement();
		Poetry poetry=new Poetry();
		//然后在搜索中将poetryid从当前的id-pid开始到当前id显示
		try{
			String sql2="Select * from poetry p,userpoetry u where u.Uid='"+uid+"' and p.poetryid=u.Poetryid+1";
			rs2=sql.executeQuery(sql2);
			while(rs2.next()){
				poetry=new Poetry(rs2.getString(1),rs2.getInt(2),rs2.getString(3),rs2.getString(4));
			}
		} catch (Exception e) {
		    e.printStackTrace();
		}
		return poetry;
	}
	
	public ArrayList<Poetry> showLearnedPoetry(Connection conn,String uid)throws SQLException{
		ArrayList<Poetry> poetrylist=new ArrayList<Poetry>();
		sql=conn.createStatement();
		try{
			String sql2="Select * from poetry p,userpoetry u where u.Uid='"+uid+"' and p.poetryid between 0 and u.Poetryid";
			rs2=sql.executeQuery(sql2);
			while(rs2.next()){
				Poetry poetry=new Poetry(rs2.getString(1),rs2.getInt(2),rs2.getString(3),rs2.getString(4));
				poetrylist.add(poetry);
				//System.out.println(rs2.getString(1));
			}
		} catch (Exception e) {
		    e.printStackTrace();
		}
		return poetrylist;
	}
	
	public UserPoetry showLearnedPnum(Connection conn,String uid)throws SQLException{
		sql=conn.createStatement();
		UserPoetry userpoetry=new UserPoetry();
		try{
			String sql2="Select * from userpoetry where Uid='"+uid+"'";
			rs2=sql.executeQuery(sql2);
			while(rs2.next()){
				userpoetry=new UserPoetry(rs2.getString(1),rs2.getInt(2));
			}
		} catch (Exception e) {
		    e.printStackTrace();
		}
		return userpoetry;
	}
}
