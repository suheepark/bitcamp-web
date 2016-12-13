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

@WebServlet("/classroom/add")
public class ClassroomAddServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  ServletConfig config;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
      classroomDao.insert(classroom);
      out.println("등록하였습니다.");
      
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
  
}
