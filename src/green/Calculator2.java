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

   //�������, ��������� operatorTable
   //������ Ÿ���� Map<String ,Operator>
   //�����ڸ� �̿��Ͽ� ��ü�� �����ϼ���, ���������ڴ� private
   //Map�� interface �̱� ������ �ٷ� ��ü ���� �Ұ�
   //�װ��� �ڽ��� HashMap�� �̿��ϼ���, ������ 
   //�θ� �ڽİ�ü�� ����Ų��. 
   
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
      
      //Ŭ���̾�Ʈ�� ����ϱ� ���� �غ��Ѵ�. 
      response.setContentType("text/html; charset=utf-8");
      PrintWriter out = response.getWriter();
      response.getWriter().append("Served at: ").append(request.getContextPath());
      out.println("<html><body>");
      out.println("<h1>�����</h1>");
      out.println("��� : ");
      
      //������ ������ �� ����� Ŭ���̾�Ʈ�� ���
      try {
         Operator operator = operatorTable.get(op);
         if(operator == null) {
            out.println("�������� �ʴ� �������Դϴ�.");
         }else {
            double result = operator.execute(v1, v2);
            out.println(String.format("%.3f", result));
         }
      }catch(Exception e) {
         out.println("���� ������ �߻��Ͽ����ϴ�.");
      }
      out.println("</body></html>");
   }


   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      doGet(request, response);
   }

}