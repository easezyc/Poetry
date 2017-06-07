package fairypoet.poetry.dao;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import fairypoet.poetry.entity.*;

public class Fillblankdao {
	protected Statement sql=null;
	protected ResultSet rs=null;
	
	public Fillblank getProblem(Connection conn,int id)throws SQLException{
		Fillblank fillblank=new Fillblank();
		sql=conn.createStatement();
		String condition="Select * from fillblank where problemid='"+id+"'";
		rs=sql.executeQuery(condition);
		while(rs.next()){
			fillblank.setProblemid(rs.getInt(2));
			fillblank.setPoetryid(rs.getInt(1));
			fillblank.setStart(rs.getInt(3));
			fillblank.setLength(rs.getInt(4));
		}
		return fillblank;
	}
	public ArrayList<Fillblank> showProblem(Connection conn,int pnum)throws SQLException{
		ArrayList<Fillblank> problemlist=new ArrayList<Fillblank>();
		sql=conn.createStatement();
		
		try{
			String sql2="Select * from fillblank where poetryid between 1 and '"+pnum+"' order by RAND() limit 5";
			rs=sql.executeQuery(sql2);
			while(rs.next()){
				Fillblank fillblank=new Fillblank(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4));
				problemlist.add(fillblank);
			}
		} catch (Exception e) {
		    e.printStackTrace();
		}
		return problemlist;
	}
	public int InsertFillblank(Connection conn,Fillblank fillblank)throws SQLException{
		int mark=0;
		sql=conn.createStatement();
		String condition="insert into fillblank (poetryid,start,length) values('"+fillblank.getPoetryid()+"','"+fillblank.getStart()+"','"+fillblank.getStart()+"')";		
		mark=sql.executeUpdate(condition);
		return mark;
	}
}
