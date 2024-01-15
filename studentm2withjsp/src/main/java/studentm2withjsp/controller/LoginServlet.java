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
import javax.servlet.http.HttpSession;

import studentm2withjsp.dao.StudentDao;
import studentm2withjsp.dto.Student;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String email = req.getParameter("email");
		String password = req.getParameter("password");
//	1 st i need to check whether this email is present or not
		StudentDao studentDao = new StudentDao();
		List<Student> list = studentDao.getAllStudents();
		boolean value = false;
		String studentPassword = null;
		String studentName = null;
		String sn = "";

//	i need to iterate 
		for (Student student : list) {
			if (email.equals(student.getEmail())) {
				value = true;
//			on the same time im taking student password also
				studentPassword = student.getPassword();
				studentName = student.getName();

				for (int i = 0; i < studentName.length(); i++) {
					char ch = studentName.charAt(i);
					if (ch != ' ') {
						//System.out.println(ch);
						sn = sn + ch;
					}
				}
				break;
			}
		}

		// create cookie
		Cookie cookie = new Cookie("studentwhologgedin", sn);
		resp.addCookie(cookie);
		// now cookie is present in browser
		
		
		// create http session 
		HttpSession httpsesion = req.getSession();
		httpsesion.setAttribute("studentwhologgedin", sn);
		
		
		

		if (value) {
//		value=true when that email is present
//		then check with the password
			if (password.equals(studentPassword)) {
				// login success

				req.setAttribute("list", list);
				RequestDispatcher dispatcher = req.getRequestDispatcher("display.jsp");
				dispatcher.forward(req, resp);
			} else {
//			invalid password
				req.setAttribute("message", "Invalid Password");
				RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
				dispatcher.include(req, resp);
			}
		} else {
//		email doesnot exist
			req.setAttribute("message", "Invalid Email");
			RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
			dispatcher.include(req, resp);
		}

	}
}