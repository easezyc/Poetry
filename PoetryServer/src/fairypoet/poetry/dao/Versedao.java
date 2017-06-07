package fairypoet.poetry.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fairypoet.poetry.entity.Verse;
import java.util.ArrayList;

public class Versedao {
	protected Statement sql=null;
	protected ResultSet rs=null;

	
	public int insertVerse(Connection conn, Verse verse) throws SQLException {
		int mark=0;
		sql=conn.createStatement();
		String condition="insert into verse (uid,pname,pcontent) values('"+verse.getuid()+"','"+verse.getpname()+"','"+verse.getpcontent()+"')";		
		mark=sql.executeUpdate(condition);
		return mark;
}

	public ArrayList<Verse> getAllverse(Connection conn, String uid) throws SQLException {
		ArrayList<Verse> array=new ArrayList<Verse>();
		sql=conn.createStatement();
		String condition="Select *from verse where uid ='"+uid+"'";		
		rs=sql.executeQuery(condition);
		while(rs.next()){
			Verse verse=new Verse(uid);
			verse.setverse(rs.getString(1), rs.getString(2), rs.getString(3));
			array.add(verse);
		}
		return array;
} 

}
