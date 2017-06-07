package fairypoet.poetry.biz;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import fairypoet.poetry.base.DBconnection;
import fairypoet.poetry.dao.*;
import fairypoet.poetry.entity.*;

public class FriendService extends DBconnection{
	Connection conn=null;
	public int addFriend(String id,String fid) throws ClassNotFoundException, SQLException {
		conn=getCon();
		int mark=0;
		FriendDao dao=new FriendDao();
		mark =dao.addFriend(conn, id, fid);
		closeConn();
		return mark;
	}
	
	public int checkId(String id,String fid) throws ClassNotFoundException, SQLException{
		conn=getCon();
		int mark=0;
		FriendDao dao=new FriendDao();
		mark=dao.checkId(conn,id,fid);
		closeConn();
		return mark;
	}
	
	public int check(String id) throws ClassNotFoundException, SQLException{
		conn=getCon();
		int mark=0;
		FriendDao dao=new FriendDao();
		mark=dao.check(conn,id);
		closeConn();
		return mark;
	}
	
	public ArrayList<Friend> showFriend1(String uid) throws ClassNotFoundException, SQLException{
		conn=getCon();
		FriendDao dao=new FriendDao();
		ArrayList<Friend> friend=dao.showFriend1(conn, uid);
		closeConn();
		return friend;
	}
	public ArrayList<Friend> showFriend2(String uid) throws ClassNotFoundException, SQLException{
		conn=getCon();
		FriendDao dao=new FriendDao();
		ArrayList<Friend> friend=dao.showFriend2(conn, uid);
		closeConn();
		return friend;
	}
}
