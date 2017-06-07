package fairypoet.poetry.control.poetry;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fairypoet.poetry.biz.UserPoetryService;
import fairypoet.poetry.entity.Poetry;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class LearnedPoetryServlet extends HttpServlet {

    public LearnedPoetryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void destroy(){
		super.destroy();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uid=request.getParameter("uid");
		ArrayList<Poetry> poetrylist=new ArrayList<Poetry>();
		UserPoetryService service1=new UserPoetryService();
		int mark=0;
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		JSONObject jsonStr = new JSONObject();
		JSONArray arrayList = new JSONArray();

		try {
			mark=service1.findUser(uid);
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		if(mark==0){
			try {
				jsonStr.put("backnews","用户还没有开始背诗");
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
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
		else{
			try {
				poetrylist=service1.showLearnedPoetry(uid);
			} catch (ClassNotFoundException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}

			try {
				
				for(int i=0;i<poetrylist.size();i++){		
					JSONObject jsonObject=new JSONObject();
					jsonObject.put("name", poetrylist.get(i).getName());
					jsonObject.put("id", poetrylist.get(i).getId());
					jsonObject.put("author", poetrylist.get(i).getAuthor());
					jsonObject.put("content", poetrylist.get(i).getContent());

					arrayList.add(jsonObject);
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
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
