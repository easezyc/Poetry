package fairypoet.poetry.control.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fairypoet.poetry.biz.SentenceService;
import fairypoet.poetry.entity.Sentence;

/**
 * Servlet implementation class InsertSentence
 */
@WebServlet("/InsertSentence")
public class InsertSentence extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertSentence() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//int sid=Integer.valueOf(request.getParameter("sid"));
		String sen1=request.getParameter("sen1");
		String sen2=request.getParameter("sen2");
		
		Sentence sentence=new Sentence(sen1,sen2);
		SentenceService service=new SentenceService();
		
		int mark=0;
		String jsonStr="";
		try {
			mark=service.insertSentence(sentence);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(mark==1)
		{
			jsonStr= "{\"backnews\":\"T\"}";
		}
		else
		{
			jsonStr= "{\"backnews\":\"F\"}";
		}
		PrintWriter out = null;
		try {
		    out = response.getWriter();
		    out.write(jsonStr);
			} catch (IOException e) {
			    e.printStackTrace();
			} finally {
			    if (out != null) {
			        out.close();
			    }
			    }
		}
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
