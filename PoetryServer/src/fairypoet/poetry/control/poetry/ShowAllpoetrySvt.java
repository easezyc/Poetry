package fairypoet.poetry.control.poetry;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import fairypoet.poetry.biz.PoetryService;
import fairypoet.poetry.entity.Poetry;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * Servlet implementation class ShowAllpoetrySvt
 */
@WebServlet("/ShowAllpoetrySvt")
public class ShowAllpoetrySvt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowAllpoetrySvt() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String word=request.getParameter("word");
		ArrayList<Poetry> array=new ArrayList<Poetry>();
		PoetryService service=new PoetryService();
		try {
			array=service.getAllpoetry(word);
			} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		//request.setAttribute("array", array);
		response.setContentType("text/html;charset=GBK");
		JSONArray jsonarray=new JSONArray();
		
		PrintWriter out = null;
		try {
			out=response.getWriter();
			int a = array.size();
			for(int i=0;i<a;i++)
			{
				JSONObject object = new JSONObject();
				object.put("id",array.get(i).getId());
				object.put("name", array.get(i).getName());
				object.put("author", array.get(i).getAuthor());
				object.put("content",array.get(i).getContent());
				
				jsonarray.add(object);
			}
			out.print(jsonarray.toString());
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
