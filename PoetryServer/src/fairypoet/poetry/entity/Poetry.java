package fairypoet.poetry.entity;

public class Poetry {
	String poetryname;
	int poetryid;
	String author;
	String content;
	
public Poetry()
{
	}
public Poetry(int newid)
{
	poetryid=newid;
	}
public Poetry(String newname)
{
	poetryname=newname;
	}
public Poetry(String newname,String newauthor)
{
	poetryname=newname;
	author=newauthor;
	}

public Poetry(String newpoetryname,int newid,String newauthor,String newcontent){
	
	poetryname=newpoetryname;
	poetryid=newid;
	author=newauthor;
	content=newcontent;
}
public Poetry(String newname,String newauthor,String newcontent)
{
	poetryname=newname;
	author=newauthor;
	content=newcontent;
	}

public void setPoetry(String newpoetryname,int newid,String newauthor,String newcontent){
	
	poetryname=newpoetryname;
	poetryid=newid;
	author=newauthor;
	content=newcontent;
}

public int getId(){
	return poetryid;
}
public String getName(){
	return poetryname;
}
public String getAuthor(){
	return author;
}
public String getContent(){
	return content;
}

}