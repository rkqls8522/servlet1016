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
@WebServlet({ "/Calculator2", "/calculator2" })
public class Calculator2 extends HttpServlet {

   //멤버변수, 멤버변수명 operatorTable
   //데이터 타입은 Map<String ,Operator>
   //생성자를 이용하여 객체를 생성하세요, 접근제한자는 private
   //Map은 interface 이기 때문에 바로 객체 생성 불가
   //그것의 자식인 HashMap을 이용하세요, 다형성 
   //부모가 자식객체를 가리킨다. 
   
   private Map<String, Operator> operatorTable = new HashMap<String, Operator>();
   
   
    public Calculator2() {
       
       operatorTable.put("+", new PlusOperator());
       operatorTable.put("-", new MinusOperator());
       operatorTable.put("*", new MultipleOperator());
       operatorTable.put("/", new DivideOperator());
       
       
    }


   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
      String op = request.getParameter("op");
      double v1 = Double.parseDouble(request.getParameter("v1"));
      double v2 = Double.parseDouble(request.getParameter("v2"));
      
      //클라이언트로 출력하기 위해 준비한다. 
      response.setContentType("text/html; charset=utf-8");
      PrintWriter out = response.getWriter();
      response.getWriter().append("Served at: ").append(request.getContextPath());
      out.println("<html><body>");
      out.println("<h1>계산결과</h1>");
      out.println("결과 : ");
      
      //연산을 수행한 후 결과를 클라이언트로 출력
      try {
         Operator operator = operatorTable.get(op);
         if(operator == null) {
            out.println("존재하지 않는 연산자입니다.");
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
      // TODO Auto-generated method stub
      doGet(request, response);
   }

}