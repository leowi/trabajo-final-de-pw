package dlince;

import java.io.IOException;


import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
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
public class Login extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)throws IOException, ServletException {
	
		UserService us = UserServiceFactory.getUserService();
		User user = us.getCurrentUser();
		if(user == null){
			resp.sendRedirect(us.createLoginURL("/login"));
		}else{
			
			
			RequestDispatcher rd=getServletContext().getRequestDispatcher("/WEB-INF/Views/Home/index.jsp");
			rd.forward(req, resp);	
		}
	}
}