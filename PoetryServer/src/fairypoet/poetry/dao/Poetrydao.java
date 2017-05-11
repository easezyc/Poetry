package fairypoet.poetry.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fairypoet.poetry.entity.*;

public class Poetrydao {
	protected Statement sql=null;
	protected ResultSet rs1=null;
	protected ResultSet rs2=null;
	protected ResultSet rs3=null;
	public Poetry getPoetry(Connection conn,int pid)throws SQLException{
		Poetry poetry=new Poetry();
		sql=conn.createStatement();
		String sql2="select * from poetry where poetryid='"+pid+"'";
		rs1=sql.executeQuery(sql2);
		while(rs1.next()){
			poetry=new Poetry(rs1.getString(1),rs1.getInt(2),rs1.getString(3),rs2.getString(4));
		}
		return poetry;
	}
}
