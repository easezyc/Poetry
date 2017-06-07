package fairypoet.poetry.entity;

public class Administrator {
String id,pwd;
int sup;
public Administrator(){
	
}
public Administrator(String newid,String newpwd){
	id=newid;
	pwd=newpwd;
}
public void setId(String newid){
	id=newid;
}
public String getId(){
	return id;
}
public void setPwd(String newpwd){
	pwd=newpwd;
}
public String getPwd(){
	return pwd;
}

}