
package controller.patients;

import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.*;

import com.google.appengine.api.users.UserServiceFactory;

import controller.PMF;
import model.entity.Access;
import model.entity.Patient;
import model.entity.Resource;
import model.entity.Role;
import model.entity.UserJava;

import javax.servlet.*;
import javax.jdo.PersistenceManager;

@SuppressWarnings("serial")
public class PatientsControllerAdd extends HttpServlet {
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

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		PersistenceManager pm = PMF.get().getPersistenceManager();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String name = req.getParameter("name");
		String ch_number = req.getParameter("ch_number");
		String DNI = req.getParameter("DNI");
		String place_birth = req.getParameter("place_birth");
		String degree_instruction = req.getParameter("degree_instruction");
		String race = req.getParameter("race");
		String occupation = req.getParameter("occupation");
		String civil_status = req.getParameter("civil_status");

		boolean gender = Boolean.parseBoolean(req.getParameter("gender"));

		if (req.getParameter("name") != null && req.getParameter("ch_number") != null && req.getParameter("DNI") != null
				&& req.getParameter("place_birth") != null && req.getParameter("degree_instruction") != null
				&& req.getParameter("race") != null && req.getParameter("occupation") != null
				&& req.getParameter("civil_status") != null && req.getParameter("gender") != null
				&& req.getParameter("birth") != null && req.getParameter("created") != null) {
			Patient p;

			try {
				Date created = null, birth = null;
				created = sdf.parse(req.getParameter("created"));
				birth = sdf.parse(req.getParameter("birth"));
				p = new Patient(name, ch_number, DNI, place_birth, degree_instruction, race, occupation, civil_status,
						created, birth, gender);
				try {
					pm.makePersistent(p);
				} finally {
					pm.close();
				}
				System.out.println(p.toString());
				resp.sendRedirect("/patients");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		RequestDispatcher var = getServletContext().getRequestDispatcher("/WEB-INF/Views/Patients/add.jsp");
		try {
			var.forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
