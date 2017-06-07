package fairypoet.poetry.control.poetry;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fairypoet.poetry.biz.FillblankService;
import fairypoet.poetry.biz.UserPoetryService;
import fairypoet.poetry.entity.Fillblank;
import fairypoet.poetry.entity.UserPoetry;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class FillblankServlet extends HttpServlet {

	public FillblankServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uid=request.getParameter("uid");
		UserPoetry userpoetry=new UserPoetry();
		UserPoetryService service1=new UserPoetryService();
		FillblankService service2=new FillblankService();
		int poetrynum=0;
		ArrayList<Fillblank> problemlist=new ArrayList<Fillblank>();

		try {
			userpoetry=service1.findUP(uid);
			poetrynum=userpoetry.getPoetryid();
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		try {
			problemlist=service2.showProblem(poetrynum);
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		response.setContentType("text/html;charset=GBK");
		JSONArray arrayList=new JSONArray();
		PrintWriter out = null;
		try {
			for(int i=0;i<problemlist.size();i++){
			JSONObject jsonStr = new JSONObject();
			jsonStr.put("problemid", problemlist.get(i).getProblemid());
			arrayList.add(jsonStr);
			}
			} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
		    out = response.getWriter();
		    out.print(arrayList.toString());
			} catch (IOException e) {
			    e.printStackTrace();
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
