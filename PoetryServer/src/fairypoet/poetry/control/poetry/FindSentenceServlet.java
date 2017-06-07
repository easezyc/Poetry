package fairypoet.poetry.control.poetry;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fairypoet.poetry.biz.SentenceService;
import fairypoet.poetry.entity.Sentence;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class FindSentenceServlet extends HttpServlet {

	public FindSentenceServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid=request.getParameter("problemid");
		int problem=Integer.parseInt(pid);
		Sentence sentence=new Sentence();
		SentenceService service1=new SentenceService();
		
		try {
			sentence=service1.getSentence(problem);
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		response.setContentType("text/html;charset=GBK");
		PrintWriter out = null;
		JSONObject jsonStr = new JSONObject();
		try {
			jsonStr.put("sentence1",sentence.getSentence1());
			jsonStr.put("sentence2",sentence.getSentence2());	
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
