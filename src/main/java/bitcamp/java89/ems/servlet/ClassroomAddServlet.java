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

@WebServlet("/classroom/add")
public class ClassroomAddServlet extends HttpServlet {
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
    out.println("<title>강의실관리-등록</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>등록 결과</h1>");
    
    try {
      ClassroomMySQLDao classroomDao = ClassroomMySQLDao.getInstance();
      
      if (classroomDao.existRoomNo(classroom.getRoomNo())) {
        throw new Exception("같은 강의실이 존재합니다. 등록을 취소합니다.");
      }
      
      classroomDao.insert(classroom);
      out.println("<p>등록하였습니다.</p>");
      
    } catch (Exception e) {
      out.printf("<p>%s</p>\n", e.getMessage());
    }
    out.println("</body>");
    out.println("</html>");
  }
  
}
