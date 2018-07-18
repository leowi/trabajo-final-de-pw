package model.entity;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Medical_Appointment {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY) private Long
	id;
	@Persistent private String reason;
	@Persistent private Date Appointment_date;
	@Persistent private boolean status;
	@Persistent private Long IdPatient;
	public Medical_Appointment(String reason, Date appointment_date, boolean status, Long idPatient) {
		this.reason = reason;
		Appointment_date = appointment_date;
		this.status = status;
		IdPatient = idPatient;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Date getAppointment_date() {
		return Appointment_date;
	}
	public void setAppointment_date(Date appointment_date) {
		Appointment_date = appointment_date;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Long getIdPatient() {
		return IdPatient;
	}
	public void setIdPatient(Long idPatient) {
		IdPatient = idPatient;
	}
	
}
