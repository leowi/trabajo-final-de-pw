package controller.role;

import java.io.IOException;
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
//				RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/Views/Error/registry.jsp");
	//			rd.forward(req, resp);
		//	}
			//else{
//				if(Access.isAccess(account.get(0).getIdRole(),req.getRequestURI())){
					//inicio de codigo
					if(req.getParameter("name")!=null){
						if(Role.isEquals(req.getParameter("name"))){
							String mensaje="El objeto ya existe";
							req.setAttribute("mensaje", mensaje);
						}else{
							Role r = new Role(req.getParameter("name"));
							try {
								pm.makePersistent(r);
							} finally {
								pm.close();
							}
							resp.sendRedirect("/roles");
						}
					}
					
					RequestDispatcher rd=getServletContext().getRequestDispatcher("/WEB-INF/Views/Roles/add.jsp");
				    try {
						rd.forward(req, resp);
					} catch (ServletException e) {
						e.printStackTrace();
					}
				  //final de codigo
				//}else{
					//RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/Views/Error/permission.jsp");
					//rd.forward(req, resp);
				//}
			//}
		//}
	}
}