package fairypoet.poetry.control.poetry;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//import net.sf.json.*;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fairypoet.poetry.biz.*;
import fairypoet.poetry.entity.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class UserPoetryServlet extends HttpServlet{
	public UserPoetryServlet(){
		super();
	}
	public void destroy(){
		super.destroy();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userid=request.getParameter("uid");
		String poetryid=request.getParameter("poetrynum");
		int uid=Integer.parseInt(userid);
		int pid=Integer.parseInt(poetryid);
		UserPoetry userpoetry=new UserPoetry();
		ArrayList<Poetry> poetrylist=new ArrayList<Poetry>();
		UserPoetryService service1=new UserPoetryService();
		
		try {
			userpoetry=service1.setPoetry(uid, pid);
			System.out.println(userpoetry.getUid());
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		try {
			poetrylist=service1.showPoetry(uid, pid);
			System.out.println(poetrylist.toString());
			//userpoetry.setUid(uid);
			
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		request.setAttribute("userpoetry", userpoetry);
		response.setContentType("text/html;charset=GBK");

		JSONArray arrayList = new JSONArray();

		try {
			
			for(int i=0;i<poetrylist.size();i++){
		
				JSONObject jsonObject=new JSONObject();
				jsonObject.put("name", poetrylist.get(i).getName());
				jsonObject.put("id", poetrylist.get(i).getId());
				jsonObject.put("author", poetrylist.get(i).getAuthor());
				jsonObject.put("content", poetrylist.get(i).getContent());

				arrayList.add(jsonObject);
				//System.out.println(jsonObject);
			}
			
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		PrintWriter out = null;
		try {
		    out = response.getWriter();
		    out.print(arrayList.toString());
			} catch (IOException e) {
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
