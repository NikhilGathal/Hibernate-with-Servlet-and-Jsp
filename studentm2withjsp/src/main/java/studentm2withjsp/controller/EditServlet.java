package studentm2withjsp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import studentm2withjsp.dao.StudentDao;
import studentm2withjsp.dto.Student;

@WebServlet("/edit")
public class EditServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		String address = req.getParameter("address");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		long phone = Long.parseLong(req.getParameter("phone"));

		double fees = Double.parseDouble(getServletContext().getInitParameter("fees"));
		StudentDao dao = new StudentDao();
		List<Student> students = dao.getAllStudents();
		boolean value = true;

		for (Student student : students) {
			if (email.equals(student.getEmail())) {
//				email is already present
				if (student.getId() == id) {
//					means it is id of the person im updating
					break;
				} else {
//					others email
					value = false;
					break;
				}
			}
		}

		Student student = new Student();
		student.setAddress(address);
		student.setEmail(email);
		student.setFees(fees);
		student.setId(id);
		student.setName(name);
		student.setPassword(password);
		student.setPhone(phone);

		String studentwhochangeddetail = null;
		Cookie[] cookies = req.getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("studentwhologgedin")) {
				studentwhochangeddetail = cookie.getValue();
				break;
			}
		}

		if (value) {
			req.setAttribute("name", studentwhochangeddetail);
			dao.updateStudent(student);
			req.setAttribute("list", students);
			RequestDispatcher dispatcher = req.getRequestDispatcher("display.jsp");
			dispatcher.forward(req, resp);
		} else {
			req.setAttribute("message", "Email already exist");
			req.setAttribute("student", dao.findStudentById(id));
			RequestDispatcher dispatcher = req.getRequestDispatcher("edit.jsp");
			dispatcher.include(req, resp);
		}

	}

}
