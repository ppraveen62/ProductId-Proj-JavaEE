package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class idSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public idSearch() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter("uid"));
			PrintWriter out = response.getWriter();
			
			Class.forName("com.mysql.cj.jdbc.Driver");                                                         
			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce","root","root"); 
			Statement smt=con.createStatement();
			ResultSet rs=smt.executeQuery("select * from eproduct");
		
			int i=0;
			
			while(rs.next()) {
				
				if (rs.getInt("ID")==id) {
					i+=1;
					
					out.println("Product ID: "+id+"<br>");
					out.println("Name of product: "+rs.getString("name")+"<br>");
					out.println("Price of product: "+rs.getInt("price")+"<br>");
					out.println("Date of product Added: "+rs.getDate("date_added")+"<br>");
				}
				
			}
			
			smt.close();
			con.close();
			
			if (i==0) {
				
				RequestDispatcher rd =request.getRequestDispatcher("sreach.html");
				rd.include(request, response);
				out.println("<center> <span style = 'color:red'> ID - "+id+" Invalid, Not Found In Database </span> </center>");
				
			}
			
			
				
			
		}
		catch (Exception e){
			System.out.println(e);
			
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
