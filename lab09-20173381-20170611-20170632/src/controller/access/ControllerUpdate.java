package controller.access;
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
					Access access=pm.getObjectById(Access.class,Long.parseLong(id));
					req.setAttribute("id", id);
					req.setAttribute("access", access);
					
					String query3 = "select from " + Role.class.getName();
					List<Role> roles = (List<Role>)pm.newQuery(query3).execute();
					req.setAttribute("roles", roles);
					
					String query2 = "select from " + Resource.class.getName();
					List<Resource> resources = (List<Resource>)pm.newQuery(query2).execute();
					req.setAttribute("resources", resources);
					
					if(req.getParameter("resource")!=null&&req.getParameter("status")!=null&&req.getParameter("role")!=null){
						if(Access.isEquals(Long.parseLong(req.getParameter("role")),Long.parseLong(req.getParameter("resource")),Long.parseLong(req.getParameter("id")))||req.getParameter("resource").equals("0")||req.getParameter("role").equals("0")){
							String mensaje="El objeto ya existe";
							req.setAttribute("mensaje", mensaje);
						}else{	
							access.setIdResource(Long.parseLong(req.getParameter("resource")));
							access.setIdRole(Long.parseLong(req.getParameter("role")));
							access.setStatus(Boolean.parseBoolean(req.getParameter("status")));
							resp.sendRedirect("/access/view?id="+req.getParameter("id"));
						}
					}
					RequestDispatcher rd=getServletContext().getRequestDispatcher("/WEB-INF/Views/Access/update.jsp");
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