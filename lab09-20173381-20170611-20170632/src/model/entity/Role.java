package model.entity;

import java.util.*;
import javax.jdo.PersistenceManager;
import javax.jdo.annotations.*;
import controller.PMF;

@PersistenceCapable(identityType = IdentityType.APPLICATION)

public class Role {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY) Long id;
	@Persistent private String name;
	@Persistent private boolean status;
	@Persistent private Date created;
	
	public Role(String name){
		this.name=name;
		this.status=true;
		this.created=new Date();
	}
	
	public static boolean isEquals(String name){
		PersistenceManager pm=PMF.get().getPersistenceManager();
		String query = "select from " + Role.class.getName();
		List<Role> a = (List<Role>)pm.newQuery(query).execute();
		for(int i=0;i<a.size();i++)
			if(a.get(i).getName().equals(name))
				return true;
		return false;	
	}
	public static boolean isEquals(String name,Long id){
		PersistenceManager pm=PMF.get().getPersistenceManager();
		String query = "select from " + Role.class.getName()+" where id!="+id;
		List<Role> a = (List<Role>)pm.newQuery(query).execute();
		for(int i=0;i<a.size();i++){
			if(a.get(i).getName().equals(name))
				return true;
		}
		return false;
	}
	public static String exist(Long id){
		try{
			PersistenceManager pm=PMF.get().getPersistenceManager();
			String s=pm.getObjectById(Role.class,id).getName();
			if(pm.getObjectById(Role.class,id).isStatus())
				s+=" (t)";
			else
				s+=" (f)";
			return s;
		}catch(Exception e){
			return "null";
		}
	}

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
