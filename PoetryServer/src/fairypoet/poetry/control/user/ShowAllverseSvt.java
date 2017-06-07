package fairypoet.poetry.control.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fairypoet.poetry.biz.VerseService;
import fairypoet.poetry.entity.Verse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * Servlet implementation class ShowAllverseSvt
 */
@WebServlet("/ShowAllverseSvt")
public class ShowAllverseSvt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowAllverseSvt() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id=request.getParameter("uid");
		ArrayList<Verse> array=new ArrayList<Verse>();
		VerseService service=new VerseService();
		try {
			array=service.getAllverse(id);
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
				object.put("id",array.get(i).getuid());
				object.put("name", array.get(i).getpname());
				object.put("content",array.get(i).getpcontent());
				
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

