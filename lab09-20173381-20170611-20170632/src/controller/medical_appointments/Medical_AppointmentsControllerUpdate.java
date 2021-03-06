package controller.medical_appointments;

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
import model.entity.Access;
import model.entity.Medical_Appointment;
import model.entity.Patient;
import model.entity.Resource;
import model.entity.UserJava;

@SuppressWarnings("serial")
public class Medical_AppointmentsControllerUpdate extends HttpServlet {
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

					Medical_Appointment medical_appointment = pm.getObjectById(Medical_Appointment.class,
							Long.parseLong(req.getParameter("id")));
					String query2 = "select from " + Patient.class.getName();

					List<Patient> patients = (List<Patient>) pm.newQuery(query2).execute();

					pm.close();
					req.setAttribute("medical_appointment", medical_appointment);
					req.setAttribute("patients", patients);
					RequestDispatcher var = this.getServletContext()
							.getRequestDispatcher("/WEB-INF/Views/Patients/Medical_Appointments/update.jsp");
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

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Medical_Appointment medical_appointment = pm.getObjectById(Medical_Appointment.class,
				Long.parseLong(req.getParameter("id")));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date appointment_date = null;
		try {
			appointment_date = sdf.parse(req.getParameter("appointment_date"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		medical_appointment.setReason(req.getParameter("reason"));
		medical_appointment.setAppointment_date(appointment_date);
		medical_appointment.setStatus(Boolean.parseBoolean(req.getParameter("status")));

		pm.close();
		req.setAttribute("medical_appointment", medical_appointment);
		resp.sendRedirect("/medical_appointments/view?id=" + req.getParameter("id"));
	}
}
