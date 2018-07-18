package controller.upload;
import java.io.IOException;
import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

@SuppressWarnings("serial")
public class Create extends HttpServlet {
private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

@Override
public void doGet(HttpServletRequest req, HttpServletResponse res)
   throws IOException, ServletException {
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
		String id=req.getParameter("id");
		req.setAttribute("id", id);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/Views/Upload/add.jsp");
       rd.forward(req, res);
     //}else{
		//RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/Views/Error/permission.jsp");
		//rd.forward(req, resp);
	//}
//}
//}
   }
}