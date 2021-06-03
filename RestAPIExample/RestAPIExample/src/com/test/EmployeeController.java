package com.test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/employee")
public class EmployeeController {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/add")
	public Response saveEmployee(Employee emp) throws SQLException, IOException {		
		addEmployee(emp);
		return Response.status(200).entity(emp+"").build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/get")
	public Response getEmployee() throws SQLException, IOException {	
		return Response.status(200).entity(""+getEmployees()).build();
	}

	public ArrayList<Employee> getEmployees() throws SQLException, IOException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/temp", "root", "root");
			if (con != null) {
				ArrayList<Employee> employees = new ArrayList<Employee>();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("select * from employees");
				while(rs.next()) {
					Employee employee = new Employee();
					employee.setEname(rs.getString("ename"));
					employee.setCity(rs.getString("city"));
					employee.setDeptId(rs.getInt("dept_id"));
					employee.setMobile(rs.getString("mobile"));
					employee.setAge(rs.getInt("age"));
					employee.setSalary(rs.getInt("salary"));
					employees.add(employee);
				}
				return employees;

			} else {
				System.out.println("Not connected to the database!");
				return null;
			}

		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String addEmployee(Employee employee) throws SQLException, IOException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/temp", "root", "root");
			if (con != null) {
				PreparedStatement stmt = con.prepareStatement("insert into employees (ename,city,dept_id,mobile,age,salary) values(?,?,?,?,?,?)");
				stmt.setString(1, employee.getEname());
				stmt.setString(2, employee.getCity());
				stmt.setInt(3, employee.getDeptId());
				stmt.setString(4, employee.getMobile());
				stmt.setInt(5, employee.getAge());
				stmt.setInt(6, employee.getSalary());
				int i = stmt.executeUpdate();
				if (i >= 1) {
					System.out.println("Inserted successfully");
					return "Success";
				}

			} else {
				System.out.println("Not connected to the database!");
			}

		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Failed";
	}
}
