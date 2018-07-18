package model.entity;

import java.util.Date;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.annotations.*;
import controller.PMF;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Resource {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY) Long id;
	@Persistent private String url;
	@Persistent private boolean status;
	@Persistent private Date created;
	public Resource(String url){
		this.url=url;
		this.status=true;
	    this.created=new Date();
	}
	
	public static boolean isEquals(String url){
		PersistenceManager pm=PMF.get().getPersistenceManager();
		String query = "select from " + Resource.class.getName();
		List<Resource> a = (List<Resource>)pm.newQuery(query).execute();
		for(int i=0;i<a.size();i++)
			if(a.get(i).getUrl().equals(url))
				return true;
		return false;
	}
	public static boolean isEquals(String url,Long id){
		PersistenceManager pm=PMF.get().getPersistenceManager();
		String query = "select from " + Resource.class.getName()+" where id!="+id;
		List<Resource> a = (List<Resource>)pm.newQuery(query).execute();
		for(int i=0;i<a.size();i++){
			if(a.get(i).getUrl().equals(url))
				return true;
		}
		return false;
	}
	
	public static String exist(Long id){
		try{
			PersistenceManager pm=PMF.get().getPersistenceManager();
			String s=pm.getObjectById(Resource.class,id).getUrl();
			if(pm.getObjectById(Resource.class,id).isStatus())
				s+=" (t)";
			else
				s+=" (f)";
			return s;
		}catch(Exception e){
			return "null";
		}
	}
	
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Long getId() {
		return id;
	}
	public Date getCreated() {
		return created;
	}
	
}

