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

@WebServlet("/classroom/list")
public class ClassroomListServlet extends AbstractServlet {
  
  ServletConfig config;

  @Override
  public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
    try {
      ClassroomMySQLDao classroomDao = ClassroomMySQLDao.getInstance();
      ArrayList<Classroom> list = classroomDao.getList();
      response.setContentType("text/plain;charset=UTF-8");
      PrintWriter out = response.getWriter();
      
      for (Classroom classroom : list) {
        out.printf("%d %d %s %s %s %s\n",
            classroom.getRoomNo(), classroom.getCapacity(),
            classroom.getClassName(), classroom.getClassTime(),
            classroom.isProjector() ? "Yes" : "No", 
            classroom.isLocker() ? "Yes" : "No");
      }
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
  
}
