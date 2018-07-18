package controller.patients;

import java.io.IOException;

import java.util.List;

import javax.servlet.http.*;

import com.google.appengine.api.users.UserServiceFactory;

import controller.PMF;
import model.entity.Access;
import model.entity.Patient;
import model.entity.Resource;
import model.entity.UserJava;

import javax.servlet.*;
import javax.jdo.PersistenceManager;

@SuppressWarnings("serial")
public class PatientsControllerView extends HttpServlet {
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

					Patient patient = pm.getObjectById(Patient.class, Long.parseLong(req.getParameter("id")));

					// pasar la lista al jsp
					req.setAttribute("patient", patient);

					// reenviar la solicitud al jsp
					RequestDispatcher var = getServletContext()
							.getRequestDispatcher("/WEB-INF/Views/Patients/view.jsp");

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
