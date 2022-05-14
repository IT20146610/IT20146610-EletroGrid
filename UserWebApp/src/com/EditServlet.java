package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/editurl")
public class EditServlet extends HttpServlet {
	
	private static final String query = "update USERDATA set USERNAME=?,USERADDRESS=?,USERACCOUNT=?,USERCONTACT=?,USEREMAIL=? where id=?";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//get PrintWriter
				PrintWriter pw = resp.getWriter();
				//set content type
				resp.setContentType("text/html");
				//get the id of record
				int id = Integer.parseInt(req.getParameter("id"));
				//get the edit data we want to edit
				String userName = req.getParameter("userName");
				String userAddress = req.getParameter("userAddress");
				String userAccount = req.getParameter("userAccount");
				String userContact = req.getParameter("userContact");
				String userEmail = req.getParameter("userEmail");
				//LOAD jdbc driver
				try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				}catch(ClassNotFoundException cnf) {
					cnf.printStackTrace();
				}
				//generate the connection
				try(Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/test","root","#Ravi@1$2%");
						PreparedStatement ps = con.prepareStatement(query);){
					ps.setString(1, userName);
					ps.setString(2, userAddress);
					ps.setString(3, userAccount);
					ps.setString(4, userContact);
					ps.setString(5, userEmail);
					ps.setInt(6, id);
					int count = ps.executeUpdate();
					if(count==1) {
						pw.println("<h2>Record is Edited Successfully</h2>");
					}else {
						pw.println("<h2>Record is not Edited Successfully</h2>");
					}
				}catch(SQLException se) {
					se.printStackTrace();
					pw.println("<h1>"+se.getMessage()+"</h2>");
				}catch(Exception e) {
					e.printStackTrace();
					pw.println("<h1>"+e.getMessage()+"</h2>");
				}
				pw.println("<a href='Uhome.html'>Home</a>");
				pw.println("<br>");
				pw.println("<a href='userList'>Employee List</a>");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req,resp);
	}
}
