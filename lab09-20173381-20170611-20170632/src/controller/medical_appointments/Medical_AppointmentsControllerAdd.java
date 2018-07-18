
package controller.medical_appointments;

import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.*;

import com.google.appengine.api.users.UserServiceFactory;

import controller.PMF;
import model.entity.Access;
import model.entity.Medical_Appointment;
import model.entity.Patient;
import model.entity.Resource;
import model.entity.Role;
import model.entity.UserJava;

import javax.servlet.*;
import javax.jdo.PersistenceManager;

@SuppressWarnings("serial")
public class Medical_AppointmentsControllerAdd extends HttpServlet {
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

					doPost(req, resp);
					// fin
				} else {
					RequestDispatcher rd = getServletContext()
							.getRequestDispatcher("/WEB-INF/Views/Error/permission.jsp");
					rd.forward(req, resp);

				}
			}
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		String dni = req.getParameter("DNI");
		String query = "select from " + Patient.class.getName();
		List<Patient> patients = (List<Patient>) pm.newQuery(query).execute();
		for (int i = 0; i < patients.size(); i++) {
			Patient p = (Patient) patients.get(i);
			System.out.println(p);
			System.out.println(dni);
			if (dni != null) {
				if (p.getDNI().equals(dni)) {
					System.out.println("prueba1");
					Long IdPatient = p.getId();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String reason = req.getParameter("reason");
					boolean status = Boolean.parseBoolean(req.getParameter("status"));
					if (req.getParameter("reason") != null && req.getParameter("appointment_date") != null
							&& req.getParameter("status") != null && req.getParameter("DNI") != null) {

						try {
							Date appointment_date = null;
							appointment_date = sdf.parse(req.getParameter("appointment_date"));
							Medical_Appointment medical_appointment = new Medical_Appointment(reason, appointment_date,
									status, IdPatient);
							System.out.println(medical_appointment);
							p.getIdMedical_appointments().add(IdPatient);

							pm.makePersistent(medical_appointment);
							pm.makePersistent(p);
							resp.sendRedirect("/medical_appointments");
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}

				}
			}

		}
		RequestDispatcher var = getServletContext()
				.getRequestDispatcher("/WEB-INF/Views/Patients/Medical_Appointments/add.jsp");
		var.forward(req, resp);
	}
}
