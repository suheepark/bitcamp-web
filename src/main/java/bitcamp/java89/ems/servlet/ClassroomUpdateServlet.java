package bitcamp.java89.ems.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import bitcamp.java89.ems.dao.impl.ClassroomMySQLDao;
import bitcamp.java89.ems.vo.Classroom;

@WebServlet("/classroom/update")
public class ClassroomUpdateServlet extends AbstractServlet {
  
  ServletConfig config;

  @Override
  public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
    try {
      ClassroomMySQLDao classroomDao = ClassroomMySQLDao.getInstance();
      ArrayList<Classroom> list = classroomDao.getList();
      response.setContentType("text/plain;charset=UTF-8");
      PrintWriter out = response.getWriter();
      
      Classroom classroom = new Classroom(); 
      classroom.setRoomNo(Integer.parseInt(request.getParameter("roomno")));
      classroom.setCapacity(Integer.parseInt(request.getParameter("capacity")));
      classroom.setClassName(request.getParameter("classname"));
      classroom.setClassTime(request.getParameter("classtime"));
      classroom.setProjector(Boolean.parseBoolean(request.getParameter("projector")));
      classroom.setLocker(Boolean.parseBoolean(request.getParameter("locker")));
      classroomDao.update(classroom);
      out.println("변경하였습니다.");
      
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
  
}
