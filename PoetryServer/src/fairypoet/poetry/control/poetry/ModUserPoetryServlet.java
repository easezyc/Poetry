package fairypoet.poetry.control.poetry;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fairypoet.poetry.biz.UserPoetryService;
import net.sf.json.JSONObject;

public class ModUserPoetryServlet extends HttpServlet {

    public ModUserPoetryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uid=request.getParameter("uid");
		UserPoetryService service1=new UserPoetryService();
		try {
			service1.setPoetry(uid);
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("backnews", true);
		PrintWriter out = null;
		try {
		    out = response.getWriter();
		    out.print(jsonObject.toString());
			} catch (IOException e) {
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
