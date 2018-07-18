package controller.history;

import java.io.IOException;
import javax.servlet.http.*;
import java.util.Date;
import java.util.List;
import java.text.DateFormat;
import javax.servlet.*;
import javax.jdo.PersistenceManager;
import model.entity.*;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.UserServiceFactory;

import controller.PMF;


@SuppressWarnings("serial")
public class ViewController extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		PersistenceManager pm=PMF.get().getPersistenceManager();
		if(UserServiceFactory.getUserService().getCurrentUser()==null){ // compruba si as iniciado secsion
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/Views/Error/account.jsp");
			rd.forward(req, resp);
		}else{
			String query1 = "select from " + UserJava.class.getName()+" where email=='"+UserServiceFactory.getUserService().getCurrentUser()+"' && status==true";
			List<UserJava> account = (List<UserJava>)pm.newQuery(query1).execute(); // guarda en el array el usuario con el que me logee como esta en la condicion
			if(account.isEmpty()){ // si encuentra al usuario con ls condiciones no dentra aki pasaria al else
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/Views/Error/registry.jsp");
				rd.forward(req, resp);
			}
			else{
				if(Access.isAccess(account.get(0).getIdRole(),req.getRequestURI())){
					//INICIO
					
					String id=req.getParameter("id");
					Historia historia = pm.getObjectById(Historia.class,Long.parseLong(id));
					
					// pasar la lista al jsp
							 req.setAttribute("historia", historia);
							 
							 // reenviar la solicitud al jsp
							 RequestDispatcher dispatcher =
							getServletContext().getRequestDispatcher("/WEB-INF/Views/History/view.jsp");
							 try {
								dispatcher.forward(req, resp);
							} catch (ServletException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					//FIN
				}else{
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/Views/Error/permission.jsp");
					rd.forward(req, resp);
			
				}
			}
		}				
	}
}
