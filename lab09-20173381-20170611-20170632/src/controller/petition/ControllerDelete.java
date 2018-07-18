package controller.petition;

import java.io.IOException;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import com.google.appengine.api.users.UserServiceFactory;
import controller.PMF;
import dlince.GmailServlet;
import model.entity.*;

@SuppressWarnings("serial")
public class ControllerDelete extends HttpServlet {
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
					if(account.get(0).getId().equals(Long.parseLong(req.getParameter("id")))){
						RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/Views/Error/same.jsp");
						rd.forward(req, resp);
					}
					else{
						
						String id=req.getParameter("id");											
							Petition u = pm.getObjectById(Petition.class,Long.parseLong(id));
							pm.deletePersistent(u);
							resp.sendRedirect("/users");
											
					}
					
				}else{
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/Views/Error/permission.jsp");
					rd.forward(req, resp);
				}
			}
		}
			
		
	}
}