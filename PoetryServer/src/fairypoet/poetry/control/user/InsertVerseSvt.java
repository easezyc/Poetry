package fairypoet.poetry.control.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fairypoet.poetry.biz.VerseService;
import fairypoet.poetry.entity.Verse;

/**
 * Servlet implementation class InsertVerseSvt
 */
@WebServlet("/InsertVerseSvt")
public class InsertVerseSvt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertVerseSvt() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());

		String id=request.getParameter("uid");
		String name=request.getParameter("pname");
		String content=request.getParameter("pcontent");
		
		Verse verse=new Verse(id,name,content);
		VerseService service=new VerseService();
		int mark=0;
		String jsonStr="";
		try {
			mark=service.InsertVerse(verse);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(mark==1)
		{
			jsonStr = "{\"backnews\":\"T\"}";	
		}
		else{
			jsonStr = "{\"backnews\":\"F\"}";	
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
