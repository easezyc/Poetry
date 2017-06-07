package fairypoet.poetry.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import fairypoet.poetry.entity.Administrator;


public class Administratordao {
	protected Statement sql=null;
	protected ResultSet rs=null;
	public int add(Connection conn, Administrator admin) throws SQLException {
		int mark=0;
		sql=conn.createStatement();
		String condition="insert into administrator (adminid,adminpassword) values('"+admin.getId()+"','"+admin.getPwd()+"')";		
		mark=sql.executeUpdate(condition);
		return mark;
}
	public int modpwd(Connection conn, Administrator admin) throws SQLException {
		sql=conn.createStatement();
		int mark=0;
		String condition="update administrator set adminpassword ='"+admin.getPwd()+"' where adminid='"+admin.getId()+"'";
		mark=sql.executeUpdate(condition);
		return mark;
}
	public int query(Connection conn,Administrator admin) throws SQLException{
		sql=conn.createStatement();
		int mark=0;
		String condition="Select *from administrator where adminid ='"+admin.getId()+"' and adminpassword ='"+admin.getPwd()+"'";
		rs=sql.executeQuery(condition);
		while(rs.next()){
			mark=1;
		}
		return mark;
}
	public int checkId(Connection conn,String id) throws SQLException{
		sql=conn.createStatement();
		int mark=0;
		String condition="Select *from administrator where adminid ='"+id+"'";
		rs=sql.executeQuery(condition);
		while(rs.next()){
			mark=1;
		}
		return mark;
}

}
