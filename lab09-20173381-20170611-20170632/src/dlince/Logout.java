package dlince;

import java.io.IOException;


import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import controller.PMF;
import model.entity.*;

@SuppressWarnings("serial")
public class Logout extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)throws IOException, ServletException {
		UserService us = UserServiceFactory.getUserService();
		User user = us.getCurrentUser();
		resp.sendRedirect(us.createLogoutURL("/index.html"));
		
	}
}