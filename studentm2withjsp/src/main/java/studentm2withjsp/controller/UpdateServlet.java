package studentm2withjsp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import studentm2withjsp.dao.StudentDao;
import studentm2withjsp.dto.Student;

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int id = Integer.parseInt(req.getParameter("id"));
		StudentDao dao = new StudentDao();
		Student student = dao.findStudentById(id);

		HttpSession httpsession = req.getSession();

		String name = (String) httpsession.getAttribute("studentwhologgedin");
		if (name != null) {

			req.setAttribute("student", student);
			RequestDispatcher dispatcher = req.getRequestDispatcher("edit.jsp");
			dispatcher.forward(req, resp);

			// means they come from log in page

		} else {
			// means they copied url
			req.setAttribute("message", "hey scammer login first");
			RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
			dispatcher.include(req, resp);
		}

	}

}
