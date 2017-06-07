package fairypoet.poetry.biz;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import fairypoet.poetry.base.DBconnection;
import fairypoet.poetry.dao.Poetrydao;
import fairypoet.poetry.entity.*;

public class PoetryService extends DBconnection{

	Connection conn=null;
	

	public Poetry getPoetrycontent(int id) throws ClassNotFoundException, SQLException{
		conn=getCon();
		Poetrydao dao=new Poetrydao();
		Poetry poetry=dao.getPoetrycontent1(conn,id);
		closeConn();
		return poetry;
}
	public Poetry getPoetrycontent(String name) throws ClassNotFoundException, SQLException{
		conn=getCon();
		Poetrydao dao=new Poetrydao();
		Poetry poetry=dao.getPoetrycontent2(conn,name);
		closeConn();
		return poetry;
}
	public int insertPoetry(Poetry poetry) throws ClassNotFoundException, SQLException{
		int mark=0;
		conn=getCon();
		Poetrydao dao=new Poetrydao();
		mark=dao.insertPoetry(conn, poetry);
		closeConn();
		return mark;
}
	public ArrayList<Poetry> getAllpoetry(String word) throws ClassNotFoundException, SQLException{
		conn=getCon();
		Poetrydao dao=new Poetrydao();
		ArrayList<Poetry> array=new ArrayList<Poetry>();
		array=dao.getAllpoetry(conn,word);
		closeConn();
		return array;
}
	public Poetry getPoetry(int pid) throws ClassNotFoundException, SQLException{
		conn=getCon();
		Poetrydao dao=new Poetrydao();
		Poetry poetry=dao.getPoetry(conn,pid);
		closeConn();
		return poetry;
	}
	public int count() throws ClassNotFoundException, SQLException{
		int num=0;
		conn=getCon();
		Poetrydao dao=new Poetrydao();
		num=dao.count(conn);
		closeConn();
		return num;
}		
	
}
