package fairypoet.poetry.control.poetry;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fairypoet.poetry.biz.*;
import fairypoet.poetry.entity.*;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class UserPoetryServlet extends HttpServlet{
	public UserPoetryServlet(){
		super();
	}
	public void destroy(){
		super.destroy();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uid=request.getParameter("uid");
		UserPoetry userpoetry=new UserPoetry();
		Poetry poetry=new Poetry();
		UserPoetryService service1=new UserPoetryService();
		int mark=0;
		
		try {
			mark=service1.findUser(uid);
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		if(mark==0){
			try {
				userpoetry=service1.addUser(uid);
			} catch (ClassNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}
		
		try {
			poetry=service1.showPoetry(uid);			
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		request.setAttribute("userpoetry", userpoetry);
		response.setContentType("text/html;charset=GBK");
		JSONObject jsonObject=new JSONObject();

		try {
			jsonObject.put("name", poetry.getName());
			jsonObject.put("id", poetry.getId());
			jsonObject.put("author", poetry.getAuthor());
			jsonObject.put("content", poetry.getContent());
			
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
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
		doGet(request,response);
	}
}
