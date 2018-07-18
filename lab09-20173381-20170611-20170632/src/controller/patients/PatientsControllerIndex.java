package controller.patients;

import java.io.IOException;

import javax.servlet.http.*;

import com.google.appengine.api.users.UserServiceFactory;

import controller.PMF;
import model.entity.Access;
import model.entity.Historia;
import model.entity.Patient;
import model.entity.Resource;
import model.entity.Role;
import model.entity.UserJava;

import java.util.List;
import javax.servlet.*;

import javax.jdo.PersistenceManager;

@SuppressWarnings("serial")
public class PatientsControllerIndex extends HttpServlet {
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

		PersistenceManager pm = PMF.get().getPersistenceManager();
		if (UserServiceFactory.getUserService().getCurrentUser() == null) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/Views/Error/account.jsp");
			rd.forward(req, resp);
		} else {
			String query1 = "select from " + UserJava.class.getName() + " where email=='"
					+ UserServiceFactory.getUserService().getCurrentUser() + "' && status==true";
			List<UserJava> account = (List<UserJava>) pm.newQuery(query1).execute();
			if (account.isEmpty()) {
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/Views/Error/registry.jsp");
				rd.forward(req, resp);
			} else {
				if (Access.isAccess(account.get(0).getIdRole(), req.getRequestURI())) {
					String query = "select from " + Patient.class.getName();

					List<Patient> patients = (List<Patient>) pm.newQuery(query).execute();

					// pasar la lista al jsp
					req.setAttribute("patients", patients);
					
					String query2 = "select from " + Historia.class.getName();					
					 
					 List<Historia> historia = (List<Historia>)
							 pm.newQuery(query2).execute();
					 					
					 req.setAttribute("historia", historia);

					RequestDispatcher var = getServletContext()
							.getRequestDispatcher("/WEB-INF/Views/Patients/index.jsp");
					var.forward(req, resp);
					// fin
				} else {
					RequestDispatcher rd = getServletContext()
							.getRequestDispatcher("/WEB-INF/Views/Error/permission.jsp");
					rd.forward(req, resp);

				}
			}
		}
	}
}