package fairypoet.poetry.control.poetry;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fairypoet.poetry.biz.SentenceService;
import fairypoet.poetry.entity.Sentence;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class SentenceServlet extends HttpServlet {

	public SentenceServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getParameter("uid");
		SentenceService service2=new SentenceService();
		ArrayList<Sentence> problemlist=new ArrayList<Sentence>();

		try {
			problemlist=service2.showProblem();
		} catch (ClassNotFoundException e) {
			// TODO �Զ���ɵ� catch ��
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO �Զ���ɵ� catch ��
			e.printStackTrace();
		}
		response.setContentType("text/html;charset=GBK");
		JSONArray arrayList=new JSONArray();
		PrintWriter out = null;
		try {
			for(int i=0;i<problemlist.size();i++){
				JSONObject jsonStr = new JSONObject();
				jsonStr.put("problemid", problemlist.get(i).getSid());
				jsonStr.put("sentence1", problemlist.get(i).getSentence1());
				jsonStr.put("sentence2", problemlist.get(i).getSentence2());
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

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
