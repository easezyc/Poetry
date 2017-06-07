package fairypoet.poetry.biz;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import fairypoet.poetry.base.DBconnection;
import fairypoet.poetry.dao.Versedao;
import fairypoet.poetry.entity.*;

public class VerseService extends DBconnection {
	Connection conn=null;
	
	public int InsertVerse(Verse verse) throws ClassNotFoundException, SQLException
	{
		conn=getCon();
		int mark=0;
		Versedao dao=new Versedao();
		mark=dao.insertVerse(conn, verse);
		closeConn();
		return mark;
	}
	
	public ArrayList<Verse> getAllverse(String uid) throws ClassNotFoundException, SQLException{
		conn=getCon();
		Versedao dao=new Versedao();
		ArrayList<Verse> array=new ArrayList<Verse>();
		array=dao.getAllverse(conn,uid);
		closeConn();
		return array;
}
	

}
