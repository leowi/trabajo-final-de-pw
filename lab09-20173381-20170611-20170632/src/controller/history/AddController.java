package controller.history;
import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.users.UserServiceFactory;

import controller.PMF;
import java.util.Date;
import java.util.List;

import model.entity.*;

@SuppressWarnings("serial")
public class AddController extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		PersistenceManager pm=PMF.get().getPersistenceManager();
		//if(UserServiceFactory.getUserService().getCurrentUser()==null){
			//RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/Views/Error/account.jsp");
			//rd.forward(req, resp);
		//}else{
			//String query1 = "select from " + UserJava.class.getName()+" where email=='"+UserServiceFactory.getUserService().getCurrentUser()+"' && status==true";
			//List<UserJava> account = (List<UserJava>)pm.newQuery(query1).execute();
			//if(account.isEmpty()){
				//RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/Views/Error/registry.jsp");
				//rd.forward(req, resp);
			//}
			//else{
				//if(Access.isAccess(account.get(0).getIdRole(),req.getRequestURI())){
					Date d=new Date();
					SimpleDateFormat mdyFormat = new SimpleDateFormat("yyyy-MM-dd");
					String dateToday = mdyFormat.format(d);
					try {
						Date x=mdyFormat.parse(dateToday);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					String name=req.getParameter("name");
					String namepro=req.getParameter("namepro");
					String birthdate=req.getParameter("birthdate");
					String sex=req.getParameter("sex");
					String date=req.getParameter("date");
					String rfc=req.getParameter("rfc");
					String fh=req.getParameter("fh");
					String ph=req.getParameter("ph");
					String pd=req.getParameter("pd");
					String dd=req.getParameter("dd");
					String wp=req.getParameter("wp");
					String fore=req.getParameter("fore");
					String recommend=req.getParameter("recommend");
					String spe=req.getParameter("spe");
					String obs=req.getParameter("obs");
					
					if(req.getParameter("name")!=null && req.getParameter("namepro")!=null && req.getParameter("birthdate")!=null && req.getParameter("sex")!=null &&
							req.getParameter("date")!=null && req.getParameter("rfc")!=null && req.getParameter("fh")!=null && req.getParameter("ph")!=null &&
							req.getParameter("fore")!=null && req.getParameter("recommend")!=null && req.getParameter("spe")!=null && req.getParameter("obs")!=null &&
							req.getParameter("pd")!=null && req.getParameter("dd")!=null && req.getParameter("wp")!=null){
						
						Historia u01 = new Historia(name,namepro,birthdate,sex,date,rfc,fh,ph,pd,dd,wp,fore,recommend,spe,obs);
						try{
							pm.makePersistent(u01);
						}
						finally{
							pm.close();
						}	
						resp.sendRedirect("/history");
					}
					req.setAttribute("dateToday", dateToday);
					RequestDispatcher var=getServletContext().getRequestDispatcher("/WEB-INF/Views/History/add.jsp");
				    try {
						var.forward(req, resp);
					} catch (ServletException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				//}else{
					//RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/Views/Error/permission.jsp");
					//rd.forward(req, resp);
			
				//}
		//}
		//}
		
	}
}