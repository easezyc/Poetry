package fairypoet.poetry.entity;

public class User {
	String uid,uname,password;
	public User(){
		
	}
	public User(String newid){
		uid=newid;
	}
	public User(String newid,String newpwd){
		uid=newid;
		password=newpwd;
	}
	public User(String newuid,String newuname,String newpassword){
		uid=newuid;
		uname=newuname;
		password=newpassword;
	}
	public String getUid(){
		return uid;
	}
	public void setUid(String newuid){
		uid=newuid;
	}
	public String getUname(){
		return uname;
	}
	public void setUname(String newuname){
		uname=newuname;
	}
	public String getPassword(){
		return password;
	}
	public void setPassword(String newpwd){
		password=newpwd;
	}
}
