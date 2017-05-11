package fairypoet.poetry.entity;

public class UserPoetry {
	int uid,poetryid;
	public UserPoetry(){
		
	}
	public UserPoetry(int newuid,int newpoetryid){
		poetryid=newpoetryid;
		uid=newuid;
	}
	public void setUserPoetry(int newuid,int newpoetryid){
		poetryid=newpoetryid;
		uid=newuid;
	}
	public void setPoetryid(int newpoetryid){
		poetryid=newpoetryid;
	}
	public int getPoetryid(){
		return poetryid;
	}
	public void setUid(int newuid){
		uid=newuid;
	}
	public int getUid(){
		return uid;
	}
}
