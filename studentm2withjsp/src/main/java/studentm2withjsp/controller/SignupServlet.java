package studentm2withjsp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import studentm2withjsp.dto.Student;
import studentm2withjsp.dao.StudentDao;


public class SignupServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String name=req.getParameter("name");
		String address=req.getParameter("address");
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		long phone=Long.parseLong(req.getParameter("phone"));
		ServletContext context=getServletContext();
		double fees=Double.parseDouble(context.getInitParameter("fees"));
		
		Student student=new Student();
		student.setAddress(address);
		student.setEmail(email);
		student.setFees(fees);
		student.setName(name);
		student.setPassword(password);
		student.setPhone(phone);
		
//		first i need to check whether this email is already present in the
//		database or not if it is present then i wont t=save this data 
//		if its not present means ill save the data
		
		StudentDao studentDao=new StudentDao();
		List<Student> list=studentDao.getAllStudents();
		boolean value=true;
		for(Student st:list) {
			if(email.equals(st.getEmail())) {
				value=false;
				break;
			}
		}
		
		if(value) {
//			value=true  if that email doesnot exist in the database
//			sign up
			studentDao.saveStudent(student);
			req.setAttribute("message","Sign Successfully please login");
			RequestDispatcher dispatcher=req.getRequestDispatcher("login.jsp");
			dispatcher.forward(req, resp);
		}else {
//			value=false if that email already exist in the db
			req.setAttribute("message", "Email already exist ");
			RequestDispatcher dispatcher=req.getRequestDispatcher("signup.jsp");
			dispatcher.include(req, resp);
			
		}
		
		
	}
}
