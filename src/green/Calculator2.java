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

   //멤버변수, 멤버변수명 operatorTable
   //데이터 타입은 Map<String ,Operator>
   //생성자를 이용하여 객체를 생성하세요, 접근제한자는 private
   //Map은 interface 이기 때문에 바로 객체 생성 불가
   //그것의 자식인 HashMap을 이용하세요, 다형성 
   //부모가 자식객체를 가리킨다. 
   
   private Map<String, Operator> operatorTable = new HashMap<String, Operator>();
   
   
    public Calculator() {
        //super();
        // TODO Auto-generated constructor stub
       //연산자 처리기를 등록 
       //객체의 참조변수(operatorTable)의 메서드은 put을 이용하여
       //첫 번째 파라미터 문자열 "+", 두 번째 파라미터는 PlusOperator 클래스의 객체입니다. 
       //그런데 일반적으로 객체를 생성 후 그것을 파라미터로 전달하지만 다른데에서 
       //사용하지 않으면 바로 객체를 생성하여 함수의 파라미터로 전달가능 
       //마이너스, 곱하기, 나누기 
      /*
       * PlusOperator p = new PlusOperator();
       *  MinusOperator m = new MinusOperator();
       * MultipleOperator mu = new MultipleOperator(); 
       * DivideOperator d = new
       * DivideOperator();
       * 
       * operatorTable.put("+", p); 
       * operatorTable.put("-", m); 
       * operatorTable.put("*",
       * mu); operatorTable.put("/", d);
       */
       //아래와 위 같음 
       
       operatorTable.put("+", new PlusOperator());
       operatorTable.put("-", new MinusOperator());
       operatorTable.put("*", new MultipleOperator());
       operatorTable.put("/", new DivideOperator());
       
       
    }


   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      //request.setCharacterEncoding("UTF-8");
      
      
      //클라이언트에서 보낸 값을 꺼낸다. 
      //문자열 변수 하나 선언하고 doGet(이 함수)의 첫번째 파라미터 request를 이용하여
      //HttpServletRequest 의 메서드인 getParameter를 이용하여 값을 가져오는데
      //그 파라미터는 문자열 "op" 이고 requestParameter() 메서드의 반환 값을 
      //위에서 선언한 문자열 변수에 저장
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