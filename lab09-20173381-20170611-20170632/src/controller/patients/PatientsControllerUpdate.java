package controller.patients;

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
import model.entity.Patient;
import model.entity.Resource;
import model.entity.UserJava;

@SuppressWarnings("serial")
public class PatientsControllerUpdate extends HttpServlet {
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
					pm.close();
					req.setAttribute("patient", patient);
					RequestDispatcher var = this.getServletContext()
							.getRequestDispatcher("/WEB-INF/Views/Patients/update.jsp");
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
		Patient patient = pm.getObjectById(Patient.class, Long.parseLong(req.getParameter("id")));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date created = null, birth = null;
		try {
			created = sdf.parse(req.getParameter("created"));
			birth = sdf.parse(req.getParameter("birth"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		patient.setName(req.getParameter("name"));
		patient.setDNI(req.getParameter("DNI"));
		patient.setCh_number(req.getParameter("ch_number"));
		patient.setCivil_status(req.getParameter("civil_status"));
		patient.setOccupation(req.getParameter("occupation"));
		patient.setPlace_birth(req.getParameter("place_birth"));
		patient.setDegree_instruction(req.getParameter("degree_instruction"));
		patient.setRace(req.getParameter("race"));
		patient.setGender(Boolean.parseBoolean(req.getParameter("gender")));
		patient.setCreated(created);
		patient.setBirth(birth);

		pm.close();
		req.setAttribute("patient", patient);
		resp.sendRedirect("/patients/view?id=" + req.getParameter("id"));
	}
}
