package controller.medical_appointments;

import java.io.IOException;

import javax.servlet.http.*;

import com.google.appengine.api.users.UserServiceFactory;

import controller.PMF;
import model.entity.Access;
import model.entity.Medical_Appointment;
import model.entity.Patient;

import model.entity.Resource;
import model.entity.Role;
import model.entity.UserJava;

import java.util.List;
import javax.servlet.*;

import javax.jdo.PersistenceManager;

@SuppressWarnings("serial")
public class Medical_AppointmentsControllerView extends HttpServlet {
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

					Medical_Appointment m_a = pm.getObjectById(Medical_Appointment.class,
							Long.parseLong(req.getParameter("id")));
					String query = "select from " + Patient.class.getName();
					List<Patient> patients = (List<Patient>) pm.newQuery(query).execute();

					// pasar la lista al jsp
					req.setAttribute("medical_appointment", m_a);
					req.setAttribute("patients", patients);

					// reenviar la solicitud al jsp
					RequestDispatcher var = getServletContext()
							.getRequestDispatcher("/WEB-INF/Views/Patients/Medical_Appointments/view.jsp");

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