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
public class Upload extends HttpServlet {
 private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

 @Override
 public void doPost(HttpServletRequest req, HttpServletResponse res)
     throws ServletException, IOException {	 	
	 PersistenceManager pm=PMF.get().getPersistenceManager();
     Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);
     List<BlobKey> blobKeys = blobs.get("myFile");
     List<BlobKey> blobKeys2 = blobs.get("myFile2");
     
     
     String id=req.getParameter("id");
     Historia historia = pm.getObjectById(Historia.class,Long.parseLong(id));
     historia.setUpload(blobKeys.get(0).getKeyString(), blobKeys2.get(0).getKeyString());
     
     System.out.println(blobKeys.get(0).getKeyString());
     System.out.println(blobKeys2.get(0).getKeyString());
     
     if (blobKeys == null || blobKeys.isEmpty()) {
         res.sendRedirect("/");
     } else {    	     	 
    	 res.sendRedirect("/upload/view?id="+id);
     }
 }
}