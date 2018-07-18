package controller.petition;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.users.UserServiceFactory;

import controller.PMF;
import model.entity.*;
@SuppressWarnings("serial")
public class ControllerAccept extends HttpServlet {
	
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
					Petition user=pm.getObjectById(Petition.class,Long.parseLong(id));
					req.setAttribute("id", id);
					req.setAttribute("user", user);									
					String query2 = "select from " + Role.class.getName();
					List<Role> roles = (List<Role>)pm.newQuery(query2).execute();
					req.setAttribute("roles", roles);
					
					if(req.getParameter("name")!=null&&req.getParameter("email")!=null&&req.getParameter("birth")!=null&&req.getParameter("gender")!=null&&req.getParameter("DNI")!=null&&req.getParameter("phone")!=null&&req.getParameter("address")!=null){
						
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
							resp.sendRedirect("/petitions/delete?id="+id);
						}
					}
					RequestDispatcher rd=getServletContext().getRequestDispatcher("/WEB-INF/Views/Petitions/update.jsp");
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