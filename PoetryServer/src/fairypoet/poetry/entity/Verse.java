package fairypoet.poetry.entity;

public class Verse {

	String uid;
	String pname;
	String pcontent;
	
	public Verse(String id)
	{
		uid=id;
	}
	public Verse(String id,String name,String content)
	{
		uid=id;
		pname=name;
		pcontent=content;
	}
	public Verse(String name,String content)
	{
		pname=name;
		pcontent=content;
	}
	 public String getuid(){
		 return uid;
	}
	 public String getpname()
	 {
		 return pname;
	 }
	 public String getpcontent()
	 {
		 return pcontent;
	 }
	 public void setuid(String id)
	 {
		 uid=id;
	 }
	 public void setpname(String name)
	 {
		 pname=name;
	 }
	 public void setpcontent(String content)
	 {
		 pcontent=content;
	 }
	 public void setverse(String id,String name,String content)
	 {
		 uid=id;
		 pname=name;
		 pcontent=content;
	 }
	
}
