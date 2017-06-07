package fairypoet.poetry.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fairypoet.poetry.entity.PK;

public class PKdao {
	protected Statement sql=null;
	protected ResultSet rs=null;
	public PK QueryEmail2(Connection conn,PK pk) throws SQLException {//查看是否存在空的email2
		sql=conn.createStatement();
		String condition="Select *from pk where email2 =''";
		rs=sql.executeQuery(condition);
		if(rs.next()){
			pk.SetEmail1(rs.getString(1));
			pk.SetProblemlist(rs.getString(5));
			condition="update pk set email2 ='"+pk.GetEmail2()+"' where email1='"+pk.GetEmail1()+"'";
			int mark=sql.executeUpdate(condition);
			if(mark==1){
				pk.SetBacknews("ok");
			}
			else{
				pk.SetBacknews("no");
			}
		}
		else{
			pk.SetBacknews("no");
		}
		return pk;
	}
	public PK CreatePK(Connection conn, String email,String problemlist) throws SQLException{
		int mark=0;
		sql=conn.createStatement();
		String condition="insert into pk (email1,time1,email2,time2,problemlist,wrongnum1,wrongnum2,mark) values('"+email+"',0,'',0,'"+problemlist+"',0,0,0)";		
		mark=sql.executeUpdate(condition);
		PK pk=new PK();
		if(mark==0){
			pk.SetBacknews("no");
		}
		else{
			pk.SetBacknews("ok");
			pk.SetEmail1(email);
			pk.SetProblemlist(problemlist);
		}
		return pk;
	}
	public PK QueryEmail1(Connection conn, PK pk) throws SQLException {//查看自己建立的记录是否被填充
		sql=conn.createStatement();
		String condition="Select *from pk where email1 ='"+pk.GetEmail1()+"' and email2 != ''";
		rs=sql.executeQuery(condition);
		if(rs.next()){
			pk.SetEmail1(rs.getString(1));
			pk.SetProblemlist(rs.getString(5));
			pk.SetEmail2(rs.getString(3));
			pk.SetBacknews("ok");
		}
		else{
			pk.SetBacknews("no");
		}
		return pk;
	}
	public int SetTime(Connection conn, PK pk) throws SQLException {
		sql=conn.createStatement();
		String condition="";
		if(pk.GetEmailnum()==1){
			condition="update pk set wrongnum1 = "+pk.GetWrongnum1()+", time1 ="+pk.GetTime1()+" where email1='"+pk.GetEmail1()+"'";
		}
		else{
			condition="update pk set wrongnum2 = "+pk.GetWrongnum2()+", time2 ="+pk.GetTime2()+" where email2='"+pk.GetEmail2()+"'";
		}
		int mark=sql.executeUpdate(condition);
		return mark;
	}
	public int Delete(Connection conn,String email,int m)throws SQLException {
		sql=conn.createStatement();
		String condition;
		if(m==1)
		{
			condition="delete from pk where email1='"+email+"'";
		}
		else{
			condition="delete from pk where email2='"+email+"'";
		}
		int mark=sql.executeUpdate(condition);
		return mark;
	}
	public PK GetTime(Connection conn, PK pk) throws SQLException {
		sql=conn.createStatement();
		String condition="";
		if(pk.GetEmailnum()==1){
			condition="Select *from pk where email1 ='"+pk.GetEmail1()+"'";
		}
		else{
			condition="Select *from pk where email2 ='"+pk.GetEmail2()+"'";
		}
		rs=sql.executeQuery(condition);
		if(rs.next()){
			if(pk.GetEmailnum()==1){
				if(rs.getInt("time2")==0){
					pk.SetBacknews("no");
				}
				else{
					pk.SetEmail1(rs.getString("email1"));
					pk.SetEmail2(rs.getString("email2"));
					pk.SetWrongnum1(rs.getInt("wrongnum1"));
					pk.SetWrongnum2(rs.getInt("wrongnum2"));
					pk.SetTime1(rs.getInt("time1"));
					pk.SetTime2(rs.getInt("time2"));
					pk.SetBacknews("ok");
					if(rs.getInt("mark")==0){
						condition="update pk set mark = 1 where email1='"+pk.GetEmail1()+"'";
						int mark=sql.executeUpdate(condition);
					}
					else{
						condition="delete from pk where email1='"+pk.GetEmail1()+"'";
						int mark=sql.executeUpdate(condition);
					}
				}
			}
			else{
				if(rs.getInt("time1")==0){
					pk.SetBacknews("no");
				}
				else{
					pk.SetEmail1(rs.getString("email1"));
					pk.SetEmail2(rs.getString("email2"));
					pk.SetWrongnum1(rs.getInt("wrongnum1"));
					pk.SetWrongnum2(rs.getInt("wrongnum2"));
					pk.SetTime1(rs.getInt("time1"));
					pk.SetTime2(rs.getInt("time2"));
					pk.SetBacknews("ok");
					if(rs.getInt("mark")==0){
						condition="update pk set mark = 1 where email2='"+pk.GetEmail2()+"'";
						int mark=sql.executeUpdate(condition);
					}
					else{
						condition="delete from pk where email2='"+pk.GetEmail2()+"'";
						int mark=sql.executeUpdate(condition);
					}
				}
			}
		}
		else{
			pk.SetBacknews("no");
		}
		return pk;
	}
}
