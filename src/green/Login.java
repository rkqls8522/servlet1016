package green;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet({ "/Login", "/login" })
public class Login extends HttpServlet {
       
	private Map<String,Score> scoreTable = new HashMap<String,Score>();
    public Login() {
        super();
        scoreTable.put("1", new Green());
        scoreTable.put("2", new Bus());
        scoreTable.put("3", new Tire());
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		int id = Integer.parseInt(request.getParameter("id"));
//		String pwd = request.getParameter("pwd");

	    int v1 = Integer.parseInt(request.getParameter("v1"));
	    int v2 = Integer.parseInt(request.getParameter("v2"));
	      
		
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<h1>계산 후 </h1>");
		out.println("<p>id : " + id + "<p>");
//		out.println("<p>pwd : " + pwd + "<p>");
		out.println("<br>");
		try {
			Score score = scoreTable.get(id);
			if(score == null) {
				out.println("존재하지 않음");
				
			}else {
				int result = score.go(v1,v2);
				out.println(result);
			}
			
			
		}catch(Exception e) {
			out.println("연산 오류 발생");
		}
		out.println("</body><html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
