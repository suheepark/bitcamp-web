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

import bitcamp.java89.ems.dao.impl.ContactMySQLDao;
import bitcamp.java89.ems.vo.Contact;

@WebServlet("/contact/view")
public class ContactViewServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  ServletConfig config;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      ContactMySQLDao contactDao = ContactMySQLDao.getInstance();
      response.setContentType("text/plain;charset=UTF-8");
      PrintWriter out = response.getWriter();
      
      String name = request.getParameter("name");
      ArrayList<Contact> list = contactDao.getListByName(name);
      for (Contact contact : list) {
        if (contact.getName().equals(name)) {
          out.printf("이름: %s\n", contact.getName());
          out.printf("직위: %s\n", contact.getPosition());
          out.printf("전화: %s\n", contact.getTel());
          out.printf("이메일: %s\n", contact.getEmail());
          out.println("-------------------");
        }
      }
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
  
}
