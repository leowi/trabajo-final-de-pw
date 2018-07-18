package controller.petition;

import java.io.IOException;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import controller.PMF;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.servlet.*;
import javax.servlet.http.*;
import dlince.GmailServlet;
import model.entity.*;

public class ControllerSign extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		PersistenceManager pm = PMF.get().getPersistenceManager();		
		
					//inicio de codigo
			if(req.getParameter("name")!=null&&req.getParameter("email")!=null&&req.getParameter("birth")!=null&&req.getParameter("gender")!=null&&req.getParameter("DNI")!=null&&req.getParameter("phone")!=null&&req.getParameter("address")!=null){
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date birth=null;
				try {
					birth=sdf.parse(req.getParameter("birth"));
					String s=sdf.format(birth);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				Petition u = new Petition(req.getParameter("name"),req.getParameter("email"),birth,req.getParameter("gender"),req.getParameter("DNI"),req.getParameter("phone"),req.getParameter("address"));
				try {
					pm.makePersistent(u);
				} finally {
					pm.close();
				}
				resp.sendRedirect("/gmail");
			
				}
					RequestDispatcher rd=getServletContext().getRequestDispatcher("/WEB-INF/Views/Petitions/sign.jsp");
					rd.forward(req, resp);
					//final de codigo						
	}
}