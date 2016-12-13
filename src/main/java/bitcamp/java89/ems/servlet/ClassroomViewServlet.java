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

@WebServlet("/classroom/view")
public class ClassroomViewServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  ServletConfig config;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      ClassroomMySQLDao classroomDao = ClassroomMySQLDao.getInstance();
      ArrayList<Classroom> list = classroomDao.getList();
      response.setContentType("text/plain;charset=UTF-8");
      PrintWriter out = response.getWriter();
      
      for (Classroom classroom : list) {
        if (classroom.getRoomNo() == Integer.parseInt(request.getParameter("roomno"))) {
          out.printf("강의실 번호 : %d\n", classroom.getRoomNo());
          out.printf("수용인원 : %d\n", classroom.getCapacity());
          out.printf("강의명 : %s\n", classroom.getClassName());
          out.printf("강의 시간 : %s\n", classroom.getClassTime());
          out.printf("프로젝터 유무 : %s\n", (classroom.isProjector()) ? "YES" : "NO");
          out.printf("사물함 유무 : %s\n", (classroom.isLocker()) ? "YES" : "NO");
          out.println("-----------------------");
        }
      }
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
  
}
