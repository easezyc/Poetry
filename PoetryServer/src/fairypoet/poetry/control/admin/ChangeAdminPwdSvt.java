package fairypoet.poetry.control.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fairypoet.poetry.biz.AdministratorService;
import fairypoet.poetry.entity.Administrator;

/**
 * Servlet implementation class ChangeAdminPwdSvt
 */
@WebServlet("/ChangeAdminPwdSvt")
public class ChangeAdminPwdSvt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeAdminPwdSvt() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				
		String adminid=request.getParameter("adminid");
		String oldpwd=(String) request.getParameter("oldpwd");
		String newpwd=(String) request.getParameter("newpwd");
		Administrator admin=new Administrator();
		Administrator modadmin=new Administrator();
		AdministratorService service=new AdministratorService();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		admin.setId(adminid);
		admin.setPwd(oldpwd);
		modadmin.setId(adminid);
		modadmin.setPwd(newpwd);
		String jsonStr="";
		int mark=0;
		try {
			mark=service.query(admin);
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		if(mark==1){
			if(oldpwd.equals(admin.getPwd())){
				admin.setPwd(newpwd);
				jsonStr = "{\"backnews\":\"T\"}";
			}
		}
		else {
			jsonStr = "{\"backnews\":\"用户名或密码错误\"}";
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
	
