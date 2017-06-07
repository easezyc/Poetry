package fairypoet.poetry.control.poetry;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.SQLException;
import net.sf.json.JSONObject;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//import net.sf.json.JSONArray;

//import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fairypoet.poetry.biz.PoetryService;
import fairypoet.poetry.entity.Poetry;
//import net.sf.json.JSONObject;
/**
 * Servlet implementation class ShowPoetrySvt
 */
@WebServlet("/ShowPoetrySvt")
//@ResponseBody
public class ShowPoetrySvt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowPoetrySvt() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id=Integer.valueOf(request.getParameter("id"));
		//String name=request.getParameter("name");
		//System.out.println(id);
		Poetry poetry=new Poetry(id);
		PoetryService service=new PoetryService();
		try {
			poetry=service.getPoetrycontent(id);
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		request.setAttribute("poetry", poetry);
		response.setContentType("text/html;charset=GBK");
		//String jsonStr=Integer.toString(id)+" "+poetry.getName()+" "+poetry.getAuthor()+" "+poetry.getContent();
		//String json =poetry.getName()+" "+poetry.getAuthor()+" "+ poetry.getContent();
		


		String idd=String.valueOf(poetry.getId());
		String author=poetry.getAuthor();
		String content=poetry.getContent();
		JSONObject jsonStr = new JSONObject();
		//jsonStr=JSONObject.stringToValue(author);
			jsonStr.put("id",idd);
			jsonStr.put("author",author);
			jsonStr.put("content",content);
		
		PrintWriter out = null;
		try {
		    out = response.getWriter();
		    out.print(jsonStr.toString());
			} catch (IOException e) {
			} finally {
			    if (out != null) {
			        out.close();
			    }
			}
	}

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
