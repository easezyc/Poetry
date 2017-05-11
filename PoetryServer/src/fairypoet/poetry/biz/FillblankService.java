package fairypoet.poetry.biz;

import java.sql.Connection;
import java.sql.SQLException;
import fairypoet.poetry.base.DBconnection;
import fairypoet.poetry.dao.*;
import fairypoet.poetry.entity.*;

public class FillblankService extends DBconnection{
	Connection conn=null;
	public Fillblank getProblem(int id) throws ClassNotFoundException, SQLException{
		conn=getCon();
		Fillblankdao dao=new Fillblankdao();
		Fillblank fillblank=dao.getProblem(conn, id);
		closeConn();
		return fillblank;
	}
}
