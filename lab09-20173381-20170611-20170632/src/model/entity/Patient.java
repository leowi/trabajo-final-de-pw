package model.entity;

import java.util.ArrayList;
import java.util.Date;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)

public class Patient {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	@Persistent
	private String name;
	@Persistent
	private String ch_number;
	@Persistent
	private String DNI;
	@Persistent
	private String place_birth;
	@Persistent
	private String degree_instruction;
	@Persistent
	private String race;
	@Persistent
	private String occupation;
	@Persistent
	private String civil_status;
	@Persistent
	private Date created;
	@Persistent
	private Date birth;
	@Persistent
	private boolean gender;
	@Persistent
	private int age;
	@Persistent
	private ArrayList<Long> IdMedical_appointments = new ArrayList<Long>();

	public Patient(String name, String ch_number, String dNI, String place_birth, String degree_instruction,
			String race, String occupation, String civil_status, Date created, Date birth, boolean gender) {
		this.name = name;
		this.ch_number = ch_number;
		DNI = dNI;
		this.place_birth = place_birth;
		this.degree_instruction = degree_instruction;
		this.race = race;
		this.occupation = occupation;
		this.civil_status = civil_status;
		this.created = created;
		this.birth = birth;
		this.gender = gender;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCh_number() {
		return ch_number;
	}

	public void setCh_number(String ch_number) {
		this.ch_number = ch_number;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public String getPlace_birth() {
		return place_birth;
	}

	public void setPlace_birth(String place_birth) {
		this.place_birth = place_birth;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getDegree_instruction() {
		return degree_instruction;
	}

	public void setDegree_instruction(String degree_instruction) {
		this.degree_instruction = degree_instruction;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getCivil_status() {
		return civil_status;
	}

	public void setCivil_status(String civil_status) {
		this.civil_status = civil_status;
	}

	public ArrayList<Long> getIdMedical_appointments() {
		return IdMedical_appointments;
	}

	public void setIdMedical_appointments(ArrayList<Long> idMedical_appointments) {
		IdMedical_appointments = idMedical_appointments;
	}



}
