package controller.history;
import java.io.IOException;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import com.google.appengine.api.datastore.Transaction;
import com.google.appengine.api.users.UserServiceFactory;
import controller.PMF;
import model.entity.*;

@SuppressWarnings("serial")
public class DeleteController extends HttpServlet {
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
				if(Access.isAccess(account.get(0).getIdRole(),req.getRequestURI())){ // paso de parametros al access para que comprube si el usuario tiene ese permiso
					// de doy el ID del rol y la URL de este servlet pero solo si es true o sino pasa al else
					
					// codigo de la operacion
					String id=req.getParameter("id");
					Historia u = pm.getObjectById(Historia.class,Long.parseLong(id));
					 pm.deletePersistent(u);
					 resp.sendRedirect("/history");
					 // fin del codigo
					
				}else{
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/Views/Error/permission.jsp");
					rd.forward(req, resp);
			
				}
			}			
		}				
	}
}
