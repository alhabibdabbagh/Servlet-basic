package com.servlet.demo.annotion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.servlet.demo.annotion.model.User;

@WebServlet(name = "AccountManager", urlPatterns = "/user/account", loadOnStartup = 0) // burda servlet oldu hemde web
																						// xml de tanimmadan
// java nin sondugu annotion  

public class ManagerAccount extends HttpServlet {

	private static final long serialVersionUID = -2017975795588355130L;

	private int userIdConuter;
	private List<User> users;

	@Override
	public void init() throws ServletException {

		userIdConuter = 0;
		users = Collections.synchronizedList(new ArrayList<User>());// collections sychronized servlet multiThread
																	// calistigi icin
		// ve butun threadlar bu listeyi eklemek istedigi icin o yuzden
		// Collections.synchronized

	}

	@Override
	public void destroy() {

		users.clear();// clear yapiyoruz null a cekmiyoruz
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		loadUserAccountList(resp);

	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = delete(req);
		if (user != null) {
			resp.getWriter().println("<html>");
			resp.getWriter().println("<head>");
			resp.getWriter().println("user delete");
			resp.getWriter().println("</head>");
			resp.getWriter().println("<body>");
			resp.getWriter().println("<h4>the user  a pain of id is delete" + user.getId());
			resp.getWriter().println("</body>");
			resp.getWriter().println("</html>");
		} else {
			resp.getWriter().println("<html>");
			resp.getWriter().println("<head>");
			resp.getWriter().println("user delete");
			resp.getWriter().println("</head>");
			resp.getWriter().println("<body>");
			resp.getWriter().println("<h2>no user to delete");
			resp.getWriter().println("</body>");
			resp.getWriter().println("</html>");
		}

	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		User user = updateUserServlet(req);
		if (user != null) {
			resp.getWriter().println("<html>");
			resp.getWriter().println("<head>");
			resp.getWriter().println("the user is updatedd");
			resp.getWriter().println("</head>");
			resp.getWriter().println("<body>");
			resp.getWriter().println("the user is been username " + user.getUsername());
			resp.getWriter().println("</body>");
			resp.getWriter().println("</html>");
		} else {
			resp.getWriter().println("<html>");
			resp.getWriter().println("<head>");
			resp.getWriter().println("no update");
			resp.getWriter().println("</head>");
			resp.getWriter().println("<body>");
			resp.getWriter().println("no update ");
			resp.getWriter().println("</body>");
			resp.getWriter().println("</html>");
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		addUserFromHttpRequest(req);
		resp.sendRedirect("../user_isOn.html");
	}

	private void loadUserAccountList(HttpServletResponse resp) throws IOException {

		resp.sendRedirect("../user_account.html");

	}

	private synchronized void addUserFromHttpRequest(HttpServletRequest req) {
		String username = req.getParameter("username");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		User user = new User(userIdConuter, username, email, password);
		userIdConuter++;
		users.add(user);
	}

	private synchronized User delete(HttpServletRequest req) {
		String username = req.getParameter("username");
		Iterator<User> userIterator = users.iterator();
		User user = null;
		boolean remove = false;
		while (userIterator.hasNext()) {
			user = userIterator.next();
			if (user.getUsername().equalsIgnoreCase(username)) {
				userIterator.remove();
				remove = true;
				break;
			}
		}
		if (remove) {
			return user;
		}
		return null;
	}

	private synchronized User updateUserServlet(HttpServletRequest req) {

		String username = req.getParameter("username");
		String email = req.getParameter("email");
		String password = req.getParameter("password");

		int myIndex = -1;
		int indexCounter = 0;
		Iterator<User> useriterator = users.iterator();
		while (useriterator.hasNext()) {
			User user = useriterator.next();
			if (user.getUsername().equalsIgnoreCase(username)) {
				myIndex = indexCounter;
				break;
			}
			indexCounter++;

		}
		if (myIndex > -1) {
			User user = users.get(myIndex);
			user.setEmail(email);
			user.setPassword(password);
			user.setUsername(username);
			user.setId(myIndex);
			return user;
		}
		return null;

	}

}
