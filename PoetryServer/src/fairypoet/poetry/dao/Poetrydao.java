package fairypoet.poetry.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fairypoet.poetry.entity.Poetry;
import java.util.ArrayList;
public class Poetrydao {
	protected Statement sql=null;
	protected ResultSet rs=null;

	
	public int insertPoetry(Connection conn, Poetry poetry) throws SQLException {
		int mark=0;
		sql=conn.createStatement();
		String condition="insert into poetry (poetryname,author,content) values('"+poetry.getName()+"','"+poetry.getAuthor()+"','"+poetry.getContent()+"')";		
		mark=sql.executeUpdate(condition);
		return mark;
}

	public Poetry getPoetrycontent1(Connection conn, int id) throws SQLException {
		Poetry poetry=new Poetry(id);
		sql=conn.createStatement();
		String condition="Select *from poetry where poetryid ='"+id+"'";		
		rs=sql.executeQuery(condition);
		while(rs.next()){
			poetry.setPoetry(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getString(4));
		}
		return poetry;
} 
	public Poetry getPoetrycontent2(Connection conn, String name) throws SQLException {
		Poetry poetry=new Poetry(name);
		sql=conn.createStatement();
		String condition="Select *from poetry where poetryname ='"+name+"' ";		
		rs=sql.executeQuery(condition);
		while(rs.next()){
			poetry.setPoetry(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getString(4));
		}
		return poetry;
} 
	public ArrayList<Poetry> getAllpoetry(Connection conn, String word) throws SQLException {
		ArrayList<Poetry> array=new ArrayList<Poetry>();
		sql=conn.createStatement();
		String condition="Select *from poetry where poetryname like '%?%' OR content like '%?%' OR author like '%?%' ";
		String condition2=condition.replace("?", word);
		rs=sql.executeQuery(condition2);
		while(rs.next()){
			Poetry poetry=new Poetry();
			poetry.setPoetry(rs.getString(1),rs.getInt(2),rs.getString(3),rs.getString(4));
			array.add(poetry);
			}
		return array;
}
	public Poetry getPoetry(Connection conn,int pid)throws SQLException{
		Poetry poetry=new Poetry();
		sql=conn.createStatement();
		String sql2="select * from poetry where poetryid='"+pid+"'";
		rs=sql.executeQuery(sql2);
		while(rs.next()){
			poetry=new Poetry(rs.getString(1),rs.getInt(2),rs.getString(3),rs.getString(4));
		}
		System.out.println(poetry.getContent());
		return poetry;
	}
	public int count(Connection conn)throws SQLException{
		int num=0;
		sql=conn.createStatement();
		String condition="select count(*) from poetry p";
		rs=sql.executeQuery(condition);
		while(rs.next()){
			num=rs.getInt(1);
		}
		return num;
	}
}

