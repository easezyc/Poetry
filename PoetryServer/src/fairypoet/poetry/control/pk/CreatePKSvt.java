package fairypoet.poetry.control.pk;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import fairypoet.poetry.biz.FillblankService;
import fairypoet.poetry.biz.PkService;
import fairypoet.poetry.entity.Fillblank;
import fairypoet.poetry.entity.PK;

public class CreatePKSvt extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public CreatePKSvt() {
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
		StringBuffer strbuf=new StringBuffer();
		FillblankService service2=new FillblankService();
		ArrayList<Fillblank> problemlist=new ArrayList<Fillblank>();
		try {
			problemlist=service2.showProblem(20);
		} catch (ClassNotFoundException e) {
			// TODO �Զ���ɵ� catch ��
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO �Զ���ɵ� catch ��
			e.printStackTrace();
		}
		for(int i=0;i<problemlist.size();i++){
			if(i==0)strbuf.append(problemlist.get(i).getProblemid());
			else strbuf.append(","+problemlist.get(i).getProblemid());
		}
		PkService service=new PkService();
		PK pk=new PK();
		try {
			pk=service.CreatePK(email, strbuf.toString());
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		JSONObject jsonStr = new JSONObject();
		jsonStr.put("backnews",pk.GetBacknews() );
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
