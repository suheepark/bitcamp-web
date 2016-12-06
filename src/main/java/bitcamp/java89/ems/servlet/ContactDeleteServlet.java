package bitcamp.java89.ems.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import bitcamp.java89.ems.dao.impl.ContactMySQLDao;
import bitcamp.java89.ems.vo.Contact;

@WebServlet("/contact/delete")
public class ContactDeleteServlet extends AbstractServlet {
  
  ServletConfig config;

  @Override
  public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
    try {
      ContactMySQLDao contactDao = ContactMySQLDao.getInstance();
      response.setContentType("text/plain;charset=UTF-8");
      PrintWriter out = response.getWriter();
      
      if (!contactDao.existEmail(request.getParameter("email"))) {
        out.println("이메일을 찾지 못했습니다.");
        return;
      }
      contactDao.delete(request.getParameter("email"));
      out.println("삭제하였습니다.");
      
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
  
}
