package fairypoet.poetry.biz;
import java.sql.Connection;
import java.sql.SQLException;

import fairypoet.poetry.base.DBconnection;
import fairypoet.poetry.dao.*;
import fairypoet.poetry.entity.*;
public class PkService extends DBconnection{
	Connection conn=null;
	public int Delete(String email,int m) throws ClassNotFoundException, SQLException {
		conn=getCon();
		PKdao dao=new PKdao();
		int mark =dao.Delete(conn, email, m);
		closeConn();
		return mark;
	}
	public PK QueryEmail2(PK pk) throws ClassNotFoundException, SQLException {
		conn=getCon();
		PKdao dao=new PKdao();
		pk =dao.QueryEmail2(conn, pk);
		closeConn();
		return pk;
	}
	public PK CreatePK(String email,String problemlist) throws ClassNotFoundException, SQLException {
		conn=getCon();
		PKdao dao=new PKdao();
		PK pk =dao.CreatePK(conn, email,problemlist);
		closeConn();
		return pk;
	}
	public PK QueryEmail1(PK pk) throws ClassNotFoundException, SQLException {
		conn=getCon();
		PKdao dao=new PKdao();
		pk =dao.QueryEmail1(conn, pk);
		closeConn();
		return pk;
	}
	public int SetTime(PK pk) throws ClassNotFoundException, SQLException {
		conn=getCon();
		PKdao dao=new PKdao();
		int mark =dao.SetTime(conn, pk);
		closeConn();
		return mark;
	}
	public PK GetTime(PK pk) throws ClassNotFoundException, SQLException {
		conn=getCon();
		PKdao dao=new PKdao();
		pk =dao.GetTime(conn, pk);
		closeConn();
		return pk;
	}
}
