package fairypoet.poetry.biz;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import fairypoet.poetry.base.DBconnection;
import fairypoet.poetry.dao.*;
import fairypoet.poetry.entity.*;

public class UserPoetryService extends DBconnection{
	Connection conn=null;
	public ArrayList<Poetry> showPoetry(int uid,int pid) throws ClassNotFoundException, SQLException{
		conn=getCon();
		UserPoetryDao dao=new UserPoetryDao();
		//UserPoetry userpoetry=dao.setPoetry(conn,uid,pid);
		ArrayList<Poetry> poetry=dao.showPoetry(conn, uid, pid);
		closeConn();
		return poetry;
	}
	
	public UserPoetry setPoetry(int uid,int pid) throws ClassNotFoundException, SQLException{
		conn=getCon();
		UserPoetryDao dao=new UserPoetryDao();
		UserPoetry userpoetry=dao.setPoetry(conn,uid,pid);
		//dao.getPoetry(conn, uid, pid);
		//UserPoetry poetry=dao.getPoetry(conn, uid, pid);
		closeConn();
		return userpoetry;
	}
}