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

public class CreateProblemServlet extends HttpServlet{
	public CreateProblemServlet(){
		super();
	}
	public void destroy(){
		super.destroy();
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pid=request.getParameter("problemid");
		int problem=Integer.parseInt(pid);
		Fillblank fillblank=new Fillblank();
		FillblankService service=new FillblankService();
		try {
			fillblank=service.getProblem(problem);
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		PrintWriter out = null;
		JSONObject jsonStr = new JSONObject();
		try {
			jsonStr.put("poetryid",fillblank.getPoetryid());
			jsonStr.put("problemid",pid);
			jsonStr.put("start",fillblank.getStart());
			jsonStr.put("length",fillblank.getLength());			
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//String jsonStr=pid+"/"+fillblank.getPoetryid()+"/"+fillblank.getStart()+"/"+fillblank.getLength();
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