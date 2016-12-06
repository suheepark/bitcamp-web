package bitcamp.java89.ems.dao.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bitcamp.java89.ems.dao.ContactDao;
import bitcamp.java89.ems.util.DataSource;
import bitcamp.java89.ems.vo.Contact;

public class ContactMySQLDao implements ContactDao {
  
  DataSource ds;
  
  private ContactMySQLDao() {
    ds = DataSource.getInstance();
  }
  static ContactMySQLDao instance;
  public static ContactMySQLDao getInstance() {
    if (instance == null) {
      instance = new ContactMySQLDao();
    }
    return instance;
  }

  public ArrayList<Contact> getList() throws Exception{
    ArrayList<Contact> list = new ArrayList<>();
    Connection con = ds.getConnection();
    try (
        PreparedStatement stmt = con.prepareStatement("select posi, name, tel, email from ex_contacts");
        ResultSet rs = stmt.executeQuery();
        )
    {
      while (rs.next()) {
        Contact contact = new Contact();
        contact.setName(rs.getString("name"));
        contact.setPosition(rs.getString("posi"));
        contact.setTel(rs.getString("tel"));
        contact.setEmail(rs.getString("email"));
        list.add(contact);
      }
    } finally {
      ds.returnConnection(con);
    }
    return list;
  }
  
  public ArrayList<Contact> getListByName(String name) throws Exception {
    ArrayList<Contact> list = new ArrayList<>();
    Connection con = ds.getConnection();
    try (
        PreparedStatement stmt = con.prepareStatement("select posi, name, tel, email from ex_contacts where name=?");
        )
    {
      stmt.setString(1, name);
      ResultSet rs = stmt.executeQuery();
      
      while (rs.next()) {
        Contact contact = new Contact();
        contact.setName(rs.getString("name"));
        contact.setPosition(rs.getString("posi"));
        contact.setTel(rs.getString("tel"));
        contact.setEmail(rs.getString("email"));
        list.add(contact);
      }
      rs.close();
    } finally {
      ds.returnConnection(con);
    }
    return list;
    
  }
  
  synchronized public void insert(Contact contact) throws Exception {
    Connection con = ds.getConnection();
    try (
        PreparedStatement stmt = con.prepareStatement("insert into ex_contacts(name,posi,tel,email) values(?,?,?,?)");
        )
    {
      stmt.setString(1, contact.getName());
      stmt.setString(2, contact.getPosition());
      stmt.setString(3, contact.getTel());
      stmt.setString(4, contact.getEmail());
      stmt.executeUpdate();
    } finally {
      ds.returnConnection(con);
    }
  }
  
  synchronized public void update(Contact contact) throws Exception{
    Connection con = ds.getConnection();
    try (
        PreparedStatement stmt = con.prepareStatement("update ex_contacts set name=?, posi=?, tel=? where email=?");
        )
    {
      stmt.setString(1, contact.getName());
      stmt.setString(2, contact.getPosition());
      stmt.setString(3, contact.getTel());
      stmt.setString(4, contact.getEmail());
      stmt.executeUpdate();
    } finally {
      ds.returnConnection(con);
    }
  }
  
  synchronized public void delete(String email) throws Exception {
    Connection con = ds.getConnection();
    try (
        PreparedStatement stmt = con.prepareStatement("delete from ex_contacts where email=?");
        )
    {
      stmt.setString(1, email);
      stmt.executeUpdate();
    } finally {
      ds.returnConnection(con);
    }
  }

  public boolean existEmail(String email) throws Exception {
    Connection con = ds.getConnection();
    try (
        PreparedStatement stmt = con.prepareStatement("select * from ex_contacts where email=?");
        )
    {
      stmt.setString(1, email);
      ResultSet rs = stmt.executeQuery();
      
      if (rs.next()) {
        rs.close();
        return true;
      } else {
        rs.close();
        return false;
      }
    } finally {
      ds.returnConnection(con);
    }
  }

}
