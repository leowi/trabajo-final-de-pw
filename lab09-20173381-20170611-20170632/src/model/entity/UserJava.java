package model.entity;

import java.util.*;

import javax.jdo.PersistenceManager;
import javax.jdo.annotations.*;

import controller.PMF;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class UserJava {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY) Long id;
	@Persistent private String name;
	@Persistent private String position;
	@Persistent private String specialty;
	@Persistent private String email;
	@Persistent private Date birth;
	@Persistent private String gender;
	@Persistent private String DNI;
	@Persistent private String phone;
	@Persistent private String address;
	@Persistent private Long idRole;
	@Persistent private boolean status;
	@Persistent private Date created;
	
	public UserJava(String name, String position, String specialty,String email,Date birth,String gender,String DNI, String phone,String address,Long idRole){
		this.name=name;
		this.position=position;
		this.specialty=specialty;
		this.email=email;
		this.birth=birth;
		this.gender=gender;
		this.DNI=DNI;
		this.phone=phone;
		this.address=address;
		this.idRole=idRole;
		this.status=true;
		this.created=new Date();
	}
	
	public UserJava(String name, String email, Date birth, String gender, String dNI, String phone, String address) {		
		this.name = name;
		this.email = email;
		this.birth = birth;
		this.gender = gender;
		DNI = dNI;
		this.phone = phone;
		this.address = address;
		this.created=new Date();
	}

	public static boolean isEquals(String email,String DNI, String phone){
		PersistenceManager pm=PMF.get().getPersistenceManager();
		String query1 = "select from " + UserJava.class.getName()+" where email=='"+email+"'";
		List<UserJava> a = (List<UserJava>)pm.newQuery(query1).execute();
		String query2 = "select from " + UserJava.class.getName()+" where DNI=="+DNI;
		List<UserJava> b = (List<UserJava>)pm.newQuery(query2).execute();
		String query3 = "select from " + UserJava.class.getName()+" where phone=="+phone;
		List<UserJava> c = (List<UserJava>)pm.newQuery(query3).execute();
		if(a.isEmpty()&&b.isEmpty()&&c.isEmpty())
			return false;
		return true;
	}
	public static boolean isEquals(String email,String DNI, String phone,Long id){
		PersistenceManager pm=PMF.get().getPersistenceManager();
		String query = "select from " + UserJava.class.getName()+" where id!="+id;
		List<UserJava> a = (List<UserJava>)pm.newQuery(query).execute();
		for(int i=0;i<a.size();i++){
			if(a.get(i).getEmail().equals(email)||a.get(i).getDNI().equals(DNI)||a.get(i).getPhone().equals(phone))
				return true;
		}
		return false;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getSpecialty() {
		return specialty;
	}
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDNI() {
		return DNI;
	}
	public void setDNI(String dNI) {
		DNI = dNI;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Long getIdRole() {
		return idRole;
	}
	public void setIdRole(Long idRole) {
		this.idRole = idRole;
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
