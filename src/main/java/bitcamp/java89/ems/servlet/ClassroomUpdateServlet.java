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

@WebServlet("/classroom/update")
public class ClassroomUpdateServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  ServletConfig config;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    request.setCharacterEncoding("UTF-8");
    response.setHeader("Refresh", "1;url=list");
    
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    
    Classroom classroom = new Classroom(); 
    classroom.setRoomNo(Integer.parseInt(request.getParameter("roomno")));
    classroom.setCapacity(Integer.parseInt(request.getParameter("capacity")));
    classroom.setClassName(request.getParameter("classname"));
    classroom.setClassTime(request.getParameter("classtime"));
    classroom.setProjector(Boolean.parseBoolean(request.getParameter("projector")));
    classroom.setLocker(Boolean.parseBoolean(request.getParameter("locker")));
    
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>강의실관리-변경</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>변경 결과</h1>");
    
    try {
      ClassroomMySQLDao classroomDao = ClassroomMySQLDao.getInstance();
      int roomno = Integer.parseInt(request.getParameter("roomno"));
      
      if (!classroomDao.existRoomNo(roomno)) {
        throw new Exception("강의실 정보를 찾지 못했습니다.");
      }
      
      classroomDao.update(classroom);
      out.println("<p>변경하였습니다.</p>");
      
    } catch (Exception e) {
      out.printf("<p>%s</p>\n", e.getMessage());
    }
    out.println("</body>");
    out.println("</html>");
  }
  
}
