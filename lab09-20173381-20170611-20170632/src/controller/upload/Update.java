package controller.upload;

//file Upload.java

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

import controller.PMF;
import model.entity.Historia;


@SuppressWarnings("serial")
public class Update extends HttpServlet {
 private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
 PersistenceManager pm=PMF.get().getPersistenceManager();
 @Override
 public void doGet(HttpServletRequest req, HttpServletResponse res)
     throws ServletException, IOException {	 		          	      
     
     String id=req.getParameter("id");
     Historia historia = pm.getObjectById(Historia.class,Long.parseLong(id));
     System.out.println(req.getParameter("id"));
     if(req.getParameter("hidden")!=null){
    	 System.out.println("dentro");
    	 doPost(req,res,id);    	 
     }
     	 req.setAttribute("id", id);
    	 req.setAttribute("s", historia.getUpload());    	
    	 req.setAttribute("s2", historia.getUpload2());
    	 
    	 RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/Views/Upload/update.jsp");
 			rd.forward(req, res);
 }
 public void doPost(HttpServletRequest req, HttpServletResponse res,String id) throws IOException{
	 
	 Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);
     List<BlobKey> blobKeys = blobs.get("myFile");
     List<BlobKey> blobKeys2 = blobs.get("myFile2");
     Historia historia = pm.getObjectById(Historia.class,Long.parseLong(id));
     historia.setUpload(blobKeys.get(0).getKeyString(), blobKeys2.get(0).getKeyString());
     res.sendRedirect("/upload/view?id="+id);
 }
}