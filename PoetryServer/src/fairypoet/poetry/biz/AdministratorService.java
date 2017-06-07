package fairypoet.poetry.biz;

import java.sql.Connection;
import java.sql.SQLException;


import fairypoet.poetry.base.DBconnection;
import fairypoet.poetry.dao.Administratordao;
import fairypoet.poetry.entity.*;

public class AdministratorService extends DBconnection {
	Connection conn=null;
	public int checkId(String id) throws ClassNotFoundException, SQLException{
		conn=getCon();
		int mark=0;
		Administratordao dao=new Administratordao();
		mark=dao.checkId(conn,id);
		closeConn();
		return mark;
}
	public int modpwd(Administrator admin) throws ClassNotFoundException, SQLException{
		conn=getCon();
		int mark=0;
		Administratordao dao=new Administratordao();
		mark =dao.modpwd(conn, admin);
		closeConn();
		return mark;
}
	public int query(Administrator admin) throws ClassNotFoundException, SQLException{
		conn=getCon();
		int mark=0;
		Administratordao dao=new Administratordao();
		mark=dao.query(conn, admin);
		closeConn();
		return mark;
}
	public int add(Administrator admin) throws ClassNotFoundException, SQLException {
		conn=getCon();
		int mark=0;
		Administratordao dao=new Administratordao();
		mark =dao.add(conn,admin);
		closeConn();
		return mark;
}

}
