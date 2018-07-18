package controller.access;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.servlet.*;
import javax.servlet.http.*;

import com.google.appengine.api.users.UserServiceFactory;

import controller.PMF;
import model.entity.*;

public class ControllerAdd extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		//if(UserServiceFactory.getUserService().getCurrentUser()==null){
			//RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/Views/Error/account.jsp");
			//rd.forward(req, resp);
		//}else{
			//String query1 = "select from " + UserJava.class.getName()+" where email=='"+UserServiceFactory.getUserService().getCurrentUser()+"' && status==true";
			//List<UserJava> account = (List<UserJava>)pm.newQuery(query1).execute();
			//if(account.isEmpty()){
				//RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/Views/Error/registry.jsp");
				//rd.forward(req, resp);
			//}
			//else{
				//if(Access.isAccess(account.get(0).getIdRole(),req.getRequestURI())){
					//inicio de codigo
		
					String query3 = "select from " + Role.class.getName();
					List<Role> roles = (List<Role>)pm.newQuery(query3).execute();
					req.setAttribute("roles", roles);
					
					String query2 = "select from " + Resource.class.getName();
					List<Resource> resources = (List<Resource>)pm.newQuery(query2).execute();
					req.setAttribute("resources", resources);
					
					if(req.getParameter("resource")!=null&&req.getParameter("role")!=null){
						if(Access.isEquals(Long.parseLong(req.getParameter("role")), Long.parseLong(req.getParameter("resource")))||req.getParameter("resource").equals("0")||req.getParameter("role").equals("0")){
							String mensaje="El objeto ya existe";
							req.setAttribute("mensaje", mensaje);
						}else{
							Access a = new Access(Long.parseLong(req.getParameter("role")),Long.parseLong(req.getParameter("resource")));
							try {
								pm.makePersistent(a);
							} finally {
								pm.close();
							}
							resp.sendRedirect("/access");
						}
					}
					RequestDispatcher rd=getServletContext().getRequestDispatcher("/WEB-INF/Views/Access/add.jsp");
				    
					rd.forward(req, resp);
					
				  //final de codigo
				//}else{
					//RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/Views/Error/permission.jsp");
					//rd.forward(req, resp);
				//}
			//}
		//}
	}
}