package controller.resource;
import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.users.UserServiceFactory;

import controller.PMF;
import model.entity.*;
@SuppressWarnings("serial")
public class ControllerUpdate extends HttpServlet {
	
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
					Resource resource=pm.getObjectById(Resource.class,Long.parseLong(id));
					req.setAttribute("id", id);
					req.setAttribute("resource", resource);
					
					if(req.getParameter("url")!=null&&req.getParameter("status")!=null){
						if(Resource.isEquals(req.getParameter("url"),Long.parseLong(req.getParameter("id")))){
							String mensaje="El objeto ya existe";
							req.setAttribute("mensaje", mensaje);
						}else{	
							resource.setUrl(req.getParameter("url"));
							resource.setStatus(Boolean.parseBoolean(req.getParameter("status")));
							resp.sendRedirect("/resources/view?id="+req.getParameter("id"));
						}
					}
					RequestDispatcher rd=getServletContext().getRequestDispatcher("/WEB-INF/Views/Resources/update.jsp");
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