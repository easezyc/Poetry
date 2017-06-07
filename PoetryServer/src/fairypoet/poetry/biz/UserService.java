package fairypoet.poetry.biz;

import java.sql.Connection;
import java.sql.SQLException;

import fairypoet.poetry.base.DBconnection;
import fairypoet.poetry.dao.Userdao;
import fairypoet.poetry.entity.User;

public class UserService extends DBconnection{
	Connection conn=null;
	public int register(User user) throws ClassNotFoundException, SQLException {
		conn=getCon();
		int mark=0;
		Userdao dao=new Userdao();
		mark =dao.register(conn,user);
		closeConn();
		return mark;
	}
	public int login(User user) throws ClassNotFoundException, SQLException {
		conn=getCon();
		int mark=0;
		Userdao dao=new Userdao();
		mark =dao.login(conn,user);
		closeConn();
		return mark;
	}
	public int changeUname(User user) throws ClassNotFoundException, SQLException {
		conn=getCon();
		int mark=0;
		Userdao dao=new Userdao();
		mark =dao.changeUname(conn,user);
		closeConn();
		return mark;
	}
	public int changePassword(User user) throws ClassNotFoundException, SQLException {
		conn=getCon();
		int mark=0;
		Userdao dao=new Userdao();
		mark =dao.changePassword(conn,user);
		closeConn();
		return mark;
	}
	public int checkId(String id) throws ClassNotFoundException, SQLException{
		conn=getCon();
		int mark=0;
		Userdao dao=new Userdao();
		mark=dao.checkId(conn,id);
		closeConn();
		return mark;
	}
	public String getPwd(String id)throws ClassNotFoundException, SQLException{
		conn=getCon();
		String pwd="";
		Userdao dao=new Userdao();
		pwd=dao.getPwd(conn,id);
		closeConn();
		return pwd;
	}
}
