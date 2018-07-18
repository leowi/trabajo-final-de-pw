package controller.user;

import java.io.IOException;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import controller.PMF;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.servlet.*;
import javax.servlet.http.*;
import dlince.GmailServlet;
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
					String query2 = "select from " + Role.class.getName();
					List<Role> roles = (List<Role>)pm.newQuery(query2).execute();
					req.setAttribute("roles", roles);
					if(req.getParameter("name")!=null&&req.getParameter("position")!=null&&req.getParameter("specialty")!=null&&req.getParameter("email")!=null&&req.getParameter("birth")!=null&&req.getParameter("gender")!=null&&req.getParameter("DNI")!=null&&req.getParameter("phone")!=null&&req.getParameter("address")!=null&&req.getParameter("role")!=null){
						if(UserJava.isEquals(req.getParameter("email"), req.getParameter("DNI"), req.getParameter("phone"))||req.getParameter("role").equals("0")){
							String mensaje="El objeto ya existe";
							req.setAttribute("mensaje", mensaje);
						}else{
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							Date birth=null;
							try {
								birth=sdf.parse(req.getParameter("birth"));
								String s=sdf.format(birth);
							} catch (ParseException e) {
								e.printStackTrace();
							}
							UserJava u = new UserJava(req.getParameter("name"),req.getParameter("position"),req.getParameter("specialty"),req.getParameter("email"),birth,req.getParameter("gender"),req.getParameter("DNI"),req.getParameter("phone"),req.getParameter("address"),Long.parseLong(req.getParameter("role")));
							try {
								pm.makePersistent(u);
							} finally {
								pm.close();
							}
							resp.sendRedirect("/users");
						}
					}
					RequestDispatcher rd=getServletContext().getRequestDispatcher("/WEB-INF/Views/Users/add.jsp");
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