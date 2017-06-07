package fairypoet.poetry.control.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import fairypoet.poetry.biz.UserService;
import fairypoet.poetry.entity.User;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class UserLoginServlet
 */
@WebServlet("/UserLoginServlet")
public class UserLoginServlet extends HttpServlet {       

    public UserLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void destroy() {
		super.destroy();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id=request.getParameter("uid");
		String pwd=request.getParameter("password");
		User user=new User(id,pwd);
		UserService service=new UserService();
		int mark=0;
		try {
			mark=service.login(user);
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		JSONObject jsonStr = new JSONObject();
		if(mark==1){
			try {
				jsonStr.put("username",user.getUname());
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//jsonStr = "{\"backnews\":\"登录成功\"}";
		}
		else {
			try {
				jsonStr.put("backnews","");
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//jsonStr = "{\"backnews\":\"登录失败\"}";
		}
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
