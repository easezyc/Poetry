package fairypoet.poetry.biz;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import fairypoet.poetry.base.DBconnection;
import fairypoet.poetry.dao.*;
import fairypoet.poetry.entity.*;

public class UserPoetryService extends DBconnection{
	Connection conn=null;
	public Poetry showPoetry(String uid) throws ClassNotFoundException, SQLException{
		conn=getCon();
		UserPoetryDao dao=new UserPoetryDao();
		Poetry poetry=dao.showPoetry(conn, uid);
		closeConn();
		return poetry;
	}
	
	public ArrayList<Poetry> showLearnedPoetry(String uid) throws ClassNotFoundException, SQLException{
		conn=getCon();
		UserPoetryDao dao=new UserPoetryDao();
		ArrayList<Poetry> poetry=dao.showLearnedPoetry(conn, uid);
		closeConn();
		return poetry;
	}
	
	public UserPoetry showLearnedPnum(String uid) throws ClassNotFoundException, SQLException{
		conn=getCon();
		UserPoetryDao dao=new UserPoetryDao();
		UserPoetry userpoetry=dao.showLearnedPnum(conn, uid);
		closeConn();
		return userpoetry;
	}
	
	public int findUser(String uid) throws ClassNotFoundException, SQLException{
		conn=getCon();
		int mark=0;
		UserPoetryDao dao=new UserPoetryDao();
		mark =dao.findUser(conn,uid);
		closeConn();
		return mark;
	}
	
	public UserPoetry findUP(String uid) throws ClassNotFoundException, SQLException{
		conn=getCon();
		UserPoetryDao dao=new UserPoetryDao();
		UserPoetry up=dao.findUP(conn,uid);
		closeConn();
		return up;
	}
	
	public UserPoetry setPoetry(String uid) throws ClassNotFoundException, SQLException{
		conn=getCon();
		UserPoetryDao dao=new UserPoetryDao();
		UserPoetry userpoetry=dao.setPoetry(conn,uid);
		closeConn();
		return userpoetry;
	}
	
	public UserPoetry addUser(String uid) throws ClassNotFoundException, SQLException{
		conn=getCon();
		UserPoetryDao dao=new UserPoetryDao();
		UserPoetry userpoetry=dao.addUser(conn,uid);
		closeConn();
		return userpoetry;
	}
}