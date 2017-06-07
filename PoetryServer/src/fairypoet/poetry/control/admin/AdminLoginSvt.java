package fairypoet.poetry.control.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fairypoet.poetry.biz.AdministratorService;
import fairypoet.poetry.entity.Administrator;

/**
 * Servlet implementation class AdminLoginSvt
 */
@WebServlet("/AdminLoginSvt")
public class AdminLoginSvt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLoginSvt() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String id=request.getParameter("adminid");
		String pwd=request.getParameter("pwd");
		Administrator admin=new Administrator(id,pwd);
		AdministratorService service=new AdministratorService();
		HttpSession session=request.getSession(true);
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
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		String jsonStr;
		if(mark==1){
			session.setAttribute("admin",admin);
			jsonStr = "{\"backnews\":\"suc\"}";
		}
		else {
			session.setAttribute("admin",null);
			jsonStr = "{\"backnews\":\"wrong\"}";
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
