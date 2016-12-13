package bitcamp.java89.ems.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems.dao.impl.ClassroomMySQLDao;
import bitcamp.java89.ems.vo.Classroom;

@WebServlet("/classroom/view")
public class ClassroomViewServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  ServletConfig config;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>강의실관리-상세정보</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>강의실 정보</h1>");
    out.println("<form action='update' method='POST'>");
    
    try {
      int roomno = Integer.parseInt(request.getParameter("roomno"));
      ClassroomMySQLDao classroomDao = ClassroomMySQLDao.getInstance();
      Classroom classroom = classroomDao.getDetail(roomno);
      
      if (classroom == null) {
        throw new Exception("해당 강의실번호의 정보가 없습니다.");
      }
      
      out.println("<table border='1'>");
      out.printf("<tr><th>강의실번호</th><td><input name='roomno' type='text' value='%s' readOnly></td></tr>\n", classroom.getRoomNo());
      out.printf("<tr><th>수용인원</th><td><input name='capacity' type='text' value='%s'></td></tr>\n", classroom.getCapacity());
      out.printf("<tr><th>강의명</th><td><input name='classname' type='text' value='%s'></td></tr>\n", classroom.getClassName());
      out.printf("<tr><th>강의시간</th><td><input name='classtime' type='text' value='%s'></td></tr>\n", classroom.getClassTime());
      out.printf("<tr><th>프로젝터</th><td><input name='projector' type='radio' value='true' %s>유"
          + "<input name='projector' type='radio' value='false' %s>무</td></tr>\n",
          (classroom.isProjector() ? "checked":""), (classroom.isProjector() ? "":"checked"));
      out.printf("<tr><th>사물함</th><td><input name='locker' type='radio' value='true' %s>유"
          + "<input name='locker' type='radio' value='false' %s>무</td></tr>\n",
          (classroom.isLocker()? "checked":""), (classroom.isLocker()? "":"checked"));
      out.println("</table>");
      out.println("<button type='submit'>변경</button>");
      out.printf(" <a href='delete?roomno=%d'>삭제</a>\n", classroom.getRoomNo());
      
    } catch (Exception e) {
      out.printf("<p>%s</p>\n", e.getMessage());
    }
    out.println(" <a href='list'>목록</a>");
    out.println("</form>");
    out.println("</body>");
    out.println("</html>");
  }
  
}
