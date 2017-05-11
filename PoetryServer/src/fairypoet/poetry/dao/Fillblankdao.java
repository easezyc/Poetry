package fairypoet.poetry.dao;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
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
	
}
