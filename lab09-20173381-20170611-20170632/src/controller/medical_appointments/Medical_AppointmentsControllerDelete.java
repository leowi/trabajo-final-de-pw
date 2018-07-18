package controller.medical_appointments;

import java.io.IOException;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserServiceFactory;

import controller.PMF;
import model.entity.Access;
import model.entity.Medical_Appointment;
import model.entity.Patient;
import model.entity.Resource;
import model.entity.UserJava;

@SuppressWarnings("serial")
public class Medical_AppointmentsControllerDelete extends HttpServlet {
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

					String id = req.getParameter("id");
					Medical_Appointment m_a = pm.getObjectById(Medical_Appointment.class, Long.parseLong(id));
					String query = "select from " + Patient.class.getName();
					List<Patient> patients = (List<Patient>) pm.newQuery(query).execute();

					for (int i = 0; i < patients.size(); i++) {
						Patient p = (Patient) patients.get(i);
						for (int j = 0; j < p.getIdMedical_appointments().size(); j++) {
							if (p.getIdMedical_appointments().get(j) == m_a.getId()) {
								p.getIdMedical_appointments().remove(j);
								pm.makePersistent(p);
							}
						}

					}
					pm.deletePersistent(m_a);

					resp.sendRedirect("/medical_appointments");
				} else {
					RequestDispatcher rd = getServletContext()
							.getRequestDispatcher("/WEB-INF/Views/Error/permission.jsp");
					rd.forward(req, resp);

				}
			}
		}
	}
}