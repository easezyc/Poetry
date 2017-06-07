package fairypoet.poetry.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fairypoet.poetry.entity.Sentence;
import java.util.ArrayList;

public class Sentencedao {
	protected Statement sql=null;
	protected ResultSet rs=null;
	
	public int insertSentence(Connection conn, Sentence sentence) throws SQLException {
		int mark=0;
		sql=conn.createStatement();
		String condition="insert into sentence (sen1,sen2) values('"+sentence.getSentence1()+"','"+sentence.getSentence2()+"')";		
		mark=sql.executeUpdate(condition);
		return mark;
}
	public Sentence getSentence(Connection conn,int sid)throws SQLException{
		Sentence sentence=new Sentence();
		sql=conn.createStatement();
		String sql2="select * from sentence where sid='"+sid+"'";
		rs=sql.executeQuery(sql2);
		while(rs.next()){
			sentence=new Sentence(rs.getString(1),rs.getString(2),rs.getInt(3));
		}
		return sentence;
	}
	public ArrayList<Sentence> getAllsentence(Connection conn, String word) throws SQLException {
		ArrayList<Sentence> array=new ArrayList<Sentence>();
		sql=conn.createStatement();
		String condition="Select *from sentence where sen1 like '%?%' OR sen2 like '%?%'";
		String condition2=condition.replace("?", word);
		rs=sql.executeQuery(condition2);
		while(rs.next()){
			Sentence sentence=new Sentence();
			sentence.setSentence(rs.getString(1),rs.getString(2),rs.getInt(3));
			array.add(sentence);
			}
		return array;
	}
	public ArrayList<Sentence> showProblem(Connection conn)throws SQLException{
		ArrayList<Sentence> problemlist=new ArrayList<Sentence>();
		sql=conn.createStatement();
		try{
			String sql2="Select * from sentence order by RAND() limit 5";
			rs=sql.executeQuery(sql2);
			while(rs.next()){
				Sentence sentence=new Sentence(rs.getString(1),rs.getString(2),rs.getInt(3));
				problemlist.add(sentence);
			}
		} catch (Exception e) {
		    e.printStackTrace();
		}
		return problemlist;
	}
}
