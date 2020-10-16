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
@WebServlet({ "/Calculator", "/calculator" })
public class Calculator extends HttpServlet {
	
	private Map<String, Operator> operatorTable = new HashMap<String, Operator>();
       
    public Calculator() {
        operatorTable.put("+", new PlusOperator());
        operatorTable.put("-", new MinusOperator());
        operatorTable.put("*", new MultipleOperator());
        operatorTable.put("/", new DivideOperator());
       
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String op = request.getParameter("op");
		double v1 = Double.parseDouble(request.getParameter("v1"));
		double v2 = Double.parseDouble(request.getParameter("v2"));
		
		response.setContentType("test/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<h1>계산결과</h1>");
		out.println("결과 : ");
	    try {
	       Operator operator = operatorTable.get(op);
	       if(operator == null) {
	          out.println("존재하지 않는 연산입니다");
	       }else {
	          double result = operator.execute(v1, v2);
	          out.println(String.format("%.3f", result));
	       }
	    }catch(Exception e) {
	       out.println("연산 오류가 발생하였습니다.");
	    }
	    out.println("</body></html>");
	    
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
