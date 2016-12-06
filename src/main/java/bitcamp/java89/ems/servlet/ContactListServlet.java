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

@WebServlet("/contact/list")
public class ContactListServlet extends AbstractServlet {
  
  ServletConfig config;

  @Override
  public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
    try {
      ContactMySQLDao contactDao = ContactMySQLDao.getInstance();
      ArrayList<Contact> list = contactDao.getList();
      response.setContentType("text/plain;charset=UTF-8");
      PrintWriter out = response.getWriter();
      for (Contact contact : list) {
        out.printf("%s %s %s %s\n",
        contact.getName(), contact.getPosition(),
        contact.getTel(), contact.getEmail());
      }
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
  
}
