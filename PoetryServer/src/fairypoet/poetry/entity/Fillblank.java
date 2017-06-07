package fairypoet.poetry.entity;

public class Fillblank {
	int poetryid,problemid,start,length;
	public Fillblank(){
		
	}
	public Fillblank(int newpoetryid,int newproblemid,int newstart,int newlength){
		poetryid=newpoetryid;
		problemid=newproblemid;
		start=newstart;
		length=newlength;
	}
	public Fillblank(int newpoetryid,int newstart,int newlength){
		poetryid=newpoetryid;
		start=newstart;
		length=newlength;
	}
	public void setFillblank(int newpoetryid,int newproblemid,int newstart,int newlength){
		poetryid=newpoetryid;
		problemid=newproblemid;
		start=newstart;
		length=newlength;
	}
	public void setPoetryid(int newpoetryid){
		poetryid=newpoetryid;
	}
	public int getPoetryid(){
		return poetryid;
	}
	public void setProblemid(int newproblemid){
		problemid=newproblemid;
	}
	public int getProblemid(){
		return problemid;
	}
	public void setStart(int newstart){
		start=newstart;
	}
	public int getStart(){
		return start;
	}
	public void setLength(int newlength){
		length=newlength;
	}
	public int getLength(){
		return length;
	}
}

