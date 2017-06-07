package fairypoet.poetry.control.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fairypoet.poetry.biz.FillblankService;
import fairypoet.poetry.entity.Fillblank;

/**
 * Servlet implementation class InsertFillblankSvt
 */
@WebServlet("/InsertFillblankSvt")
public class InsertFillblankSvt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertFillblankSvt() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int poetryid=Integer.valueOf(request.getParameter("poetryid"));
		int start=Integer.valueOf(request.getParameter("start"));
		int length=Integer.valueOf(request.getParameter("length"));
		
		Fillblank fillblank =new Fillblank(poetryid,start,length);
		FillblankService service=new FillblankService();
		
		int mark=0;
		String jsonStr="";
		try {
			mark=service.InsertFillblank(fillblank);
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
