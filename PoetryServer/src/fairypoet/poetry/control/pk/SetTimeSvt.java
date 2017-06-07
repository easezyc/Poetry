package fairypoet.poetry.control.pk;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import fairypoet.poetry.biz.PkService;
import fairypoet.poetry.entity.PK;

public class SetTimeSvt extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SetTimeSvt() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email=request.getParameter("email");
		int mark=Integer.parseInt(request.getParameter("mark"));
		int time=Integer.parseInt(request.getParameter("time"));
		int wrongnum=Integer.parseInt(request.getParameter("wrongnum"));
		PK pk=new PK();
		pk.SetEmailnum(mark);
		if(mark==1){
			pk.SetEmail1(email);
			pk.SetTime1(time);
			pk.SetWrongnum1(wrongnum);
		}
		else{
			pk.SetEmail2(email);
			pk.SetTime2(time);
			pk.SetWrongnum2(wrongnum);
		}
		PkService service=new PkService();
		int result=0;
		try {
			result=service.SetTime(pk);
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		JSONObject jsonStr = new JSONObject();
		jsonStr.put("backnews",result);
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

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request,response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
