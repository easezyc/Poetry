package fairypoet.poetry.entity;


public class Sentence {
	int sid;
	String sen1;
	String sen2;

	public Sentence()
	{
		
	}
	public Sentence(String nsen1,String nsen2)
	{
		sen1=nsen1;
		sen2=nsen2;
	}
	public Sentence(String nsen1,String nsen2,int nsid)
	{
		sid=nsid;
		sen1=nsen1;
		sen2=nsen2;
	}
	public void setSid(int nsid)
	{
		sid=nsid;
	}
	public void setSentence1(String nsen1)
	{
		sen1=nsen1;
	}
	public void setSentence2(String nsen2)
	{
		sen2=nsen2;
	}
	public void setSentence(String nsen1,String nsen2,int nsid)
	{
		sid=nsid;
		sen1=nsen1;
		sen2=nsen2;
	}
	public int getSid()
	{
		return sid;
	}
	public String getSentence1()
	{
		return sen1;
	}
	public String getSentence2()
	{
		return sen2;
	}
	
	
	
	
	
}
