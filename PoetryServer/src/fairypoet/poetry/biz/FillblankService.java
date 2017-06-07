package fairypoet.poetry.biz;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

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
	public ArrayList<Fillblank> showProblem(int pnum) throws ClassNotFoundException, SQLException{
		conn=getCon();
		Fillblankdao dao=new Fillblankdao();
		ArrayList<Fillblank> problem=dao.showProblem(conn,pnum);
		closeConn();
		return problem;
	}
	public int InsertFillblank(Fillblank fillblank)throws ClassNotFoundException, SQLException{
		conn=getCon();
		int mark=0;
		Fillblankdao dao=new Fillblankdao();
		mark=dao.InsertFillblank(conn, fillblank);
		closeConn();
		return mark;
	
	}
}
