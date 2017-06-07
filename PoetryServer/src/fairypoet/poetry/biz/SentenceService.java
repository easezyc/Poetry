package fairypoet.poetry.biz;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import fairypoet.poetry.base.DBconnection;
import fairypoet.poetry.dao.Sentencedao;
import fairypoet.poetry.entity.*;

public class SentenceService extends DBconnection {

	Connection conn=null;
	
	public Sentence getSentence(int sid) throws ClassNotFoundException, SQLException{
		conn=getCon();
		Sentencedao dao=new Sentencedao();
		Sentence sentence=dao.getSentence(conn,sid);
		closeConn();
		return sentence;
}
	public int insertSentence(Sentence sentence) throws ClassNotFoundException, SQLException{
		int mark=0;
		conn=getCon();
		Sentencedao dao=new Sentencedao();
		mark=dao.insertSentence(conn, sentence);
		closeConn();
		return mark;
}
	public ArrayList<Sentence> getAllsentence(String word) throws ClassNotFoundException, SQLException{
		conn=getCon();
		Sentencedao dao=new Sentencedao();
		ArrayList<Sentence> array=new ArrayList<Sentence>();
		array=dao.getAllsentence(conn,word);
		closeConn();
		return array;
}
	public ArrayList<Sentence> showProblem() throws ClassNotFoundException, SQLException{
		conn=getCon();
		Sentencedao dao=new Sentencedao();
		ArrayList<Sentence> problem=dao.showProblem(conn);
		closeConn();
		return problem;
	}
	
}
