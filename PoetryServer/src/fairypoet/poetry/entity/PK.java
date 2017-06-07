package fairypoet.poetry.entity;

public class PK {
	protected int time1,time2;
	protected String email1="",email2="";
	protected String problemlist="";
	protected String backnews="";
	protected int wrongnum1,wrongnum2,emailnum;
	public void SetEmailnum(int mark){
		emailnum=mark;
	}
	public int GetEmailnum(){
		return emailnum;
	}
	public void SetWrongnum1(int n){
		wrongnum1=n;
	}
	public void SetWrongnum2(int n){
		wrongnum2=n;
	}
	public int GetWrongnum1(){
		return wrongnum1;
	}
	public int GetWrongnum2(){
		return wrongnum2;
	}
	public PK(){
		
	}
	public PK(int t1,int t2,String e1,String e2,String pro){
		time1=t1;
		time2=t2;
		email1=e1;
		email2=e2;
		problemlist=pro;
	}
	public void SetBacknews(String back){
		backnews=back;
	}
	public String GetBacknews(){
		return backnews;
	}
	public void SetTime1(int t1){
		time1=t1;
	}
	public void SetTime2(int t2){
		time2=t2;
	}
	public int GetTime1(){
		return time1;
	}
	public int GetTime2(){
		return time2;
	}
	public void SetEmail1(String e){
		email1=e;
	}
	public void SetEmail2(String e){
		email2=e;
	}
	public String GetEmail1(){
		return email1;
	}
	public String GetEmail2(){
		return email2;
	}
	public void SetProblemlist(String str){
		problemlist=str;
	}
	public String GetProblemlist(){
		return problemlist;
	}
}
