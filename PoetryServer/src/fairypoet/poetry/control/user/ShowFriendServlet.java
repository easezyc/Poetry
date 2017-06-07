package fairypoet.poetry.control.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fairypoet.poetry.biz.FriendService;
import fairypoet.poetry.entity.Friend;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;


public class ShowFriendServlet extends HttpServlet {

    public ShowFriendServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void destroy(){
		super.destroy();
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uid=request.getParameter("uid");
		ArrayList<Friend> friendlist1=new ArrayList<Friend>();
		ArrayList<Friend> friendlist2=new ArrayList<Friend>();

		FriendService service1=new FriendService();
		try {
			friendlist1=service1.showFriend1(uid);			
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		try {
			friendlist2=service1.showFriend2(uid);			
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		response.setContentType("text/html;charset=GBK");

		JSONArray arrayList = new JSONArray();

		try {
			
			for(int i=0;i<friendlist1.size();i++){
		
				JSONObject jsonObject=new JSONObject();
				jsonObject.put("uid", friendlist1.get(i).getUid());
				
				arrayList.add(jsonObject);
			}
			for(int i=0;i<friendlist2.size();i++){
				
				JSONObject jsonObject=new JSONObject();
				jsonObject.put("uid", friendlist2.get(i).getFriendid());
				
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
