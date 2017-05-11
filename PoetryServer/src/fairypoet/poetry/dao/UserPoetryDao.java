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
	
	public UserPoetry setPoetry(Connection conn,int uid,int pid)throws SQLException{
		UserPoetry userpoetry=new UserPoetry();
		sql=conn.createStatement();
		String sql1="update userpoetry set poetryid = poetryid+'"+pid+"' where uid = '"+uid+"'";
		sql.executeUpdate(sql1);
		return userpoetry;
	}
	
	public ArrayList<Poetry> showPoetry(Connection conn,int uid,int pid)throws SQLException{
		ArrayList<Poetry> poetrylist=new ArrayList<Poetry>();
		sql=conn.createStatement();
		//然后在搜索中将poetryid从当前的id-pid开始到当前id显示
		try{
			String sql2="Select * from poetry p,userpoetry u where u.Uid='"+uid+"' and p.poetryid between u.Poetryid+1-'"+pid+"' and u.Poetryid";
			rs2=sql.executeQuery(sql2);
			while(rs2.next()){
				Poetry poetry=new Poetry(rs2.getString(1),rs2.getInt(2),rs2.getString(3),rs2.getString(4));
				poetrylist.add(poetry);
				System.out.println(rs2.getString(1));
			}
		} catch (Exception e) {
		    e.printStackTrace();
		}
		return poetrylist;
	}
	
}
