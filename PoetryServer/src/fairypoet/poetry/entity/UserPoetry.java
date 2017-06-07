package fairypoet.poetry.entity;

public class UserPoetry {
	String uid;
	int poetryid;
	public UserPoetry(){
		
	}
	public UserPoetry(String newuid,int newpoetryid){
		poetryid=newpoetryid;
		uid=newuid;
	}
	public void setUserPoetry(String newuid,int newpoetryid){
		poetryid=newpoetryid;
		uid=newuid;
	}
	public void setPoetryid(int newpoetryid){
		poetryid=newpoetryid;
	}
	public int getPoetryid(){
		return poetryid;
	}
	public void setUid(String newuid){
		uid=newuid;
	}
	public String getUid(){
		return uid;
	}
}
