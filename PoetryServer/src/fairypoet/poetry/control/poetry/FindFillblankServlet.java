package fairypoet.poetry.control.poetry;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fairypoet.poetry.biz.FillblankService;
import fairypoet.poetry.entity.Fillblank;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class FindFillblankServlet extends HttpServlet {

	public FindFillblankServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid=request.getParameter("problemid");
		int problem=Integer.parseInt(pid);
		Fillblank fillblank=new Fillblank();
		FillblankService service1=new FillblankService();
		
		try {
			fillblank=service1.getProblem(problem);
		} catch (ClassNotFoundException e) {
			// TODO �Զ���ɵ� catch ��
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO �Զ���ɵ� catch ��
			e.printStackTrace();
		}
		response.setContentType("text/html;charset=GBK");
		PrintWriter out = null;
		JSONObject jsonStr = new JSONObject();
		try {
			jsonStr.put("start",fillblank.getStart());
			jsonStr.put("length",fillblank.getLength());	
			jsonStr.put("poetryid", fillblank.getPoetryid());
			jsonStr.put("problemid", fillblank.getProblemid());
			} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
