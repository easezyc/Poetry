package fairypoet.poetry.entity;

public class Friend {
	String uid,friendid;
	public Friend(){
		
	}
	public Friend(String newid,String newfid){
		uid=newid;
		friendid=newfid;
	}

	public String getUid(){
		return uid;
	}
	public void setUid(String newuid){
		uid=newuid;
	}
	public String getFriendid(){
		return friendid;
	}
	public void setFriendid(String newfid){
		friendid=newfid;
	}

}
