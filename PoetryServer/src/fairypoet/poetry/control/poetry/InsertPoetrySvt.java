package fairypoet.poetry.control.poetry;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fairypoet.poetry.biz.PoetryService;
import fairypoet.poetry.entity.Poetry;

public class InsertPoetrySvt extends HttpServlet {

    public InsertPoetrySvt() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name=request.getParameter("poetryname");
		String author=request.getParameter("poetryauthor");
		String content=request.getParameter("poetrycontent");
		
		Poetry poetry=new Poetry(name,author,content);
		PoetryService service=new PoetryService();
		int mark=0;
		String jsonStr="";
		try {
			mark=service.insertPoetry(poetry);
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}	
}
