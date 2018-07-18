package model.entity;

import java.util.Date;

import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.annotations.*;
import controller.PMF;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Access {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY) Long id;
	@Persistent private Long idRole;
	@Persistent private Long idResource;
	@Persistent private boolean status;
	@Persistent private Date created;
	
	
	public Access(Long idRole,Long idResource){
		this.idRole=idRole;
		this.idResource=idResource;
		this.status=true;
		this.created=new Date();
	}
	public static boolean isEquals(Long idRole,Long idResource){
		PersistenceManager pm=PMF.get().getPersistenceManager();
		String query = "select from " + Access.class.getName()+" where idResource=="+idResource+" && idRole=="+idRole;
		List<Access> a = (List<Access>)pm.newQuery(query).execute();
		if(a.isEmpty())
			return false;
		return true;
	}
	public static boolean isEquals(Long idRole,Long idResource,Long id){
		PersistenceManager pm=PMF.get().getPersistenceManager();
		String query = "select from " + Access.class.getName()+" where id!="+id;
		List<Access> a = (List<Access>)pm.newQuery(query).execute();
		for(int i=0;i<a.size();i++){
			if(a.get(i).getIdResource().equals(idResource)&&a.get(i).getIdRole().equals(idRole))
				return true;
		}
		return false;
	}
	
	public static boolean isAccess(Long idRole,String url){
		PersistenceManager pm=PMF.get().getPersistenceManager();
		
		String query1 = "select from " + Resource.class.getName()+" where url=='"+url+"' && status==true";
		List<Resource> r = (List<Resource>)pm.newQuery(query1).execute();
		
		String query2 = "select from " + Role.class.getName()+" where id=="+idRole+" && status==true";	
		List<Role> ro = (List<Role>)pm.newQuery(query2).execute();
		
		if(r.isEmpty()==false&&ro.isEmpty()==false){
			String query3 = "select from " + Access.class.getName()+" where idResource=="+r.get(0).getId()+" && idRole=="+idRole+" && status==true";
			List<Access> a = (List<Access>)pm.newQuery(query3).execute();
			if(a.isEmpty()==false)
				return true;
		}
		return false;
		
	}
	
	
	public Long getIdRole() {
		return idRole;
	}
	public void setIdRole(Long idRole) {
		this.idRole = idRole;
	}
	public Long getIdResource() {
		return idResource;
	}
	public void setIdResource(Long idResource) {
		this.idResource = idResource;
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