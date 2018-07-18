package controller.history;
import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import model.entity.*;
import com.google.appengine.api.datastore.Transaction;
import com.google.appengine.api.users.UserServiceFactory;

import controller.PMF;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
@SuppressWarnings("serial")

public class UpdateController extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		PersistenceManager pm=PMF.get().getPersistenceManager();
		if(UserServiceFactory.getUserService().getCurrentUser()==null){ // compruba si as iniciado secsion
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/Views/Error/account.jsp");
			rd.forward(req, resp);
		}else{
			String query1 = "select from " + UserJava.class.getName()+" where email=='"+UserServiceFactory.getUserService().getCurrentUser()+"' && status==true";
			List<UserJava> account = (List<UserJava>)pm.newQuery(query1).execute(); // guarda en el array el usuario con el que me logee como esta en la condicion
			if(account.isEmpty()){ // si encuentra al usuario con ls condiciones no dentra aki pasaria al else
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/Views/Error/registry.jsp");
				rd.forward(req, resp);
			}
			else{
				if(Access.isAccess(account.get(0).getIdRole(),req.getRequestURI())){
					//INICIO
					
					String id=req.getParameter("id");
					Historia historia=pm.getObjectById(Historia.class,Long.parseLong(id));
					req.setAttribute("ID", id);
					req.setAttribute("historia", historia);
					
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
						
						historia.setName(name);
						historia.setNamepro(namepro);
						historia.setBirthdate(birthdate);
						historia.setSex(sex);
						historia.setDate(date);
						historia.setRfc(rfc);
						historia.setFh(fh);
						historia.setPh(ph);
						historia.setPd(pd);
						historia.setDd(dd);
						historia.setWp(wp);
						historia.setFore(fore);
						historia.setRecommend(recommend);
						historia.setWp(wp);
						historia.setSpe(spe);
						historia.setObs(obs);
						resp.sendRedirect("/history");
					}
					
					RequestDispatcher var=getServletContext().getRequestDispatcher("/WEB-INF/Views/History/update.jsp");
				    try {
						var.forward(req, resp);
					} catch (ServletException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				    //FIN
				}else{
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/Views/Error/permission.jsp");
					rd.forward(req, resp);
			
				}
			}
		}		
	}
}