package controller.user;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.*;
import javax.servlet.http.*;
import dlince.GmailServlet;
import model.entity.*;
import com.google.appengine.api.users.UserServiceFactory;
import controller.PMF;


public class ControllerView extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		PersistenceManager pm=PMF.get().getPersistenceManager();
		if(UserServiceFactory.getUserService().getCurrentUser()==null){
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/Views/Error/account.jsp");
			rd.forward(req, resp);
		}else{
			String query1 = "select from " + UserJava.class.getName()+" where email=='"+UserServiceFactory.getUserService().getCurrentUser()+"' && status==true";
			List<UserJava> account = (List<UserJava>)pm.newQuery(query1).execute();
			if(account.isEmpty()){
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/Views/Error/registry.jsp");
				rd.forward(req, resp);
			}
			else{
				if(Access.isAccess(account.get(0).getIdRole(),req.getRequestURI())){
					//inicio de codigo
					String id=req.getParameter("id");
					UserJava user = pm.getObjectById(UserJava.class,Long.parseLong(id));
					req.setAttribute("user", user);
					String query = "select from " + Role.class.getName();
					List<Role> roles = (List<Role>)pm.newQuery(query).execute();
					req.setAttribute("roles", roles);
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/Views/Users/view.jsp");
					rd.forward(req, resp);
					//final de codigo
				}else{
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/Views/Error/permission.jsp");
					rd.forward(req, resp);
				}
			}
		}
	}
}
