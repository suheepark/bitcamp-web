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

@WebServlet("/classroom/delete")
public class ClassroomDeleteServlet extends AbstractServlet {
  
  ServletConfig config;

  @Override
  public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
    try {
      ClassroomMySQLDao classroomDao = ClassroomMySQLDao.getInstance();
      ArrayList<Classroom> list = classroomDao.getList();
      response.setContentType("text/plain;charset=UTF-8");
      PrintWriter out = response.getWriter();
      
      classroomDao.delete(Integer.parseInt(request.getParameter("roomno")));
      out.println("삭제하였습니다");
      
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
  
}
