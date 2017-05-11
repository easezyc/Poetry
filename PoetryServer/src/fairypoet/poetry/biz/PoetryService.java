package fairypoet.poetry.biz;

import java.sql.Connection;
import java.sql.SQLException;
import fairypoet.poetry.base.DBconnection;
import fairypoet.poetry.dao.Poetrydao;
import fairypoet.poetry.entity.*;

public class PoetryService extends DBconnection{

	Connection conn=null;
	
	public Poetry getPoetry(int pid) throws ClassNotFoundException, SQLException{
		conn=getCon();
		Poetrydao dao=new Poetrydao();
		Poetry poetry=dao.getPoetry(conn,pid);
		//dao.getPoetry(conn, uid, pid);
		//UserPoetry poetry=dao.getPoetry(conn, uid, pid);
		closeConn();
		return poetry;
	}
	
}
