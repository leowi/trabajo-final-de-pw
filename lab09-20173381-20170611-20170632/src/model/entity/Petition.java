package model.entity;

import java.util.*;

import javax.jdo.PersistenceManager;
import javax.jdo.annotations.*;

import controller.PMF;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Petition {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY) Long id;
	@Persistent private String name;
	@Persistent private String email;
	@Persistent private Date birth;
	@Persistent private String gender;
	@Persistent private String DNI;
	@Persistent private String phone;
	@Persistent private String address;	
	@Persistent private Date created;
	
	public Petition(String name, String email, Date birth, String gender, String dNI, String phone, String address) {		
		this.name = name;
		this.email = email;
		this.birth = birth;
		this.gender = gender;
		DNI = dNI;
		this.phone = phone;
		this.address = address;
		this.created=new Date();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public Long getId() {
		return id;
	}
	public Date getCreated() {
		return created;
	}
	
}
