package fairypoet.poetry.control.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fairypoet.poetry.biz.UserService;
import fairypoet.poetry.entity.User;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class RegisterServlet
 */
public class UserRegisterServlet extends HttpServlet {

    public UserRegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void destroy() {
		super.destroy();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String id=request.getParameter("uid");
		String name=request.getParameter("uname");
		String pwd=request.getParameter("password");
		User user=new User(id,name,pwd);
		UserService service=new UserService();
		int mark=0;
		
		try {
			mark=service.checkId(id);
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		JSONObject jsonStr = new JSONObject();
		if(mark==1){
			try {
				//mark=0;
				jsonStr.put("backnews","flase");
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else{
			mark=0;
			try {
				mark=service.register(user);
			} catch (ClassNotFoundException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			if(mark==1){
				try {
					jsonStr.put("backnews",true);
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else{
				try {
					jsonStr.put("backnews",false);
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = null;
		try {
		    out = response.getWriter();
		    out.print(jsonStr.toString());
			} catch (IOException e) {
			    e.printStackTrace();
			} finally {
			    if (out != null) {
			        out.close();
			    }
			}
	}

}
