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
 * Servlet implementation class ModPasswordServlet
 */
@WebServlet("/ModPasswordServlet")
public class ModPasswordServlet extends HttpServlet {

    public ModPasswordServlet() {
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
		String newpwd=request.getParameter("newpassword");
		User user=new User(id,pwd);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
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
		JSONObject jsonStr = new JSONObject();
		if(mark==1){
			int chanmark=0;
			try {
				user.setPassword(newpwd);
				chanmark=service.changePassword(user);
			} catch (ClassNotFoundException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			if(chanmark==1){
				user.setPassword(newpwd);
				try {
					jsonStr.put("backnews",true);
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//jsonStr = "{\"backnews\":\"修改成功\"}";	
			}
			else{
				try {
					jsonStr.put("backnews",false);
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//jsonStr = "{\"backnews\":\"修改失败\"}";	
			}
		}
		else{
			try {
				jsonStr.put("backnews",false);
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//jsonStr = "{\"backnews\":\"密码错误\"}";	
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
