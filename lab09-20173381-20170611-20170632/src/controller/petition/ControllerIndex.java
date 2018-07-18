package controller.petition;

import java.io.IOException;
import com.google.appengine.api.users.UserServiceFactory;
import controller.PMF;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.servlet.*;
import javax.servlet.http.*;
import model.entity.*;
import dlince.GmailServlet;

@SuppressWarnings("serial")
public class ControllerIndex extends HttpServlet{
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
					String query = "select from " + Petition.class.getName();
					List<Petition> users = (List<Petition>)pm.newQuery(query).execute();
					req.setAttribute("users", users);
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/Views/Petitions/index.jsp");
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

