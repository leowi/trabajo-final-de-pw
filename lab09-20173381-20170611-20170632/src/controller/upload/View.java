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
public class View extends HttpServlet {
 private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

 @Override
 public void doGet(HttpServletRequest req, HttpServletResponse res)
     throws ServletException, IOException {	 	
	 PersistenceManager pm=PMF.get().getPersistenceManager();          
     
     String id=req.getParameter("id");
     Historia historia = pm.getObjectById(Historia.class,Long.parseLong(id));         
     
    	 req.setAttribute("s", historia.getUpload());    	
    	 req.setAttribute("s2", historia.getUpload2());
    	 
    	 RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/Views/Upload/view.jsp");
 			rd.forward(req, res);     
 }
}