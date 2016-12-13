package bitcamp.java89.ems.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems.dao.impl.ClassroomMySQLDao;
import bitcamp.java89.ems.vo.Classroom;

@WebServlet("/classroom/list")
public class ClassroomListServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  ServletConfig config;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    
    try {
      ClassroomMySQLDao classroomDao = ClassroomMySQLDao.getInstance();
      ArrayList<Classroom> list = classroomDao.getList();
      
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<title>강의실관리-목록</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>강의실 정보</h1>");
      out.println("<a href='form.html'>추가</a><br>");
      out.println("<table border='1'>");
      out.println("<tr>");
      out.println("<th>강의실번호</th><th>수용인원</th><th>강의명</th><th>강의시간</th>"
          + "<th>프로젝터유무</th><th>사물함유무</th>");
      out.println("</tr>");
      
      for (Classroom classroom : list) {
        out.println("<tr> <!-- td : table row data -->");
        out.printf("<td><a href='view?roomno=%1$d'>%d</a></td><td>%d</td>"
            + "<td>%s</td><td>%s</td><td>%b</td><td>%b</td>\n",
            classroom.getRoomNo(), classroom.getCapacity(),
            classroom.getClassName(), classroom.getClassTime(),
            classroom.isProjector(), classroom.isLocker());
        out.println("</tr>");
      }
      
      out.println("</table>");
      out.println("</body>");
      out.println("</html>");
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
  
}
