package model.entity;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Historia {
	@PrimaryKey
	 @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY) private Long
	id;
	 @Persistent private String name;
	 @Persistent private String birthdate;
	 @Persistent private String namepro;
	 @Persistent private String sex;
	 //date of consultation
	 @Persistent private String date;
	 //reason for consultation
	 @Persistent private String rfc;
	 //Family history
	 @Persistent private String fh;
	 //personal antecedents
	 @Persistent private String ph;
	 //presumptive diagnosis
	 @Persistent private String pd;
	 //definitive diagnosis
	 @Persistent private String dd;
	 //WORKPLAN
	 @Persistent private String wp;
	 //forecast
	 @Persistent private String fore;
	 //treatment / recommendations
	 @Persistent private String recommend;
	 //Specifications
	 @Persistent private String spe;
	 //observations
	 @Persistent private String obs;
	 @Persistent private String upload;
	 @Persistent private String upload2;	 

	public Historia(String name, String namepro, String birthdate, String sex,String date,String rfc,String fh,String ph,String pd,String dd,String wp,String fore,
			 String recommend,String spe,String obs) {
		 this.name = name;
		 this.birthdate=birthdate;
		 this.namepro =namepro;
		 this.sex = sex;
		 this.date = date;
		 this.rfc =rfc;
		 this.fh=fh;
		 this.ph = ph;
		 this.pd = pd;
		 this.dd =dd;
		 this.wp=wp;
		 this.fore = fore;
		 this.recommend =recommend;
		 this.spe=spe;
		 this.obs = obs;
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
	public String getNamepro() {
		return namepro;
	}
	public void setNamepro(String namepro) {
		this.namepro = namepro;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	public String getFh() {
		return fh;
	}
	public void setFh(String fh) {
		this.fh = fh;
	}
	public String getPh() {
		return ph;
	}
	public void setPh(String ph) {
		this.ph = ph;
	}
	public String getPd() {
		return pd;
	}
	public void setPd(String pd) {
		this.pd = pd;
	}
	public String getDd() {
		return dd;
	}
	public void setDd(String dd) {
		this.dd = dd;
	}
	public String getWp() {
		return wp;
	}
	public void setWp(String wp) {
		this.wp = wp;
	}
	public String getFore() {
		return fore;
	}
	public void setFore(String fore) {
		this.fore = fore;
	}
	public String getRecommend() {
		return recommend;
	}
	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}
	public String getSpe() {
		return spe;
	}
	public void setSpe(String spe) {
		this.spe = spe;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	 public String getUpload() {
		return upload ;
	}
	public String getUpload2() {
		return upload2;
	}
	public void setUpload(String upload, String upload2) {
		this.upload = upload;
		this.upload2 = upload2;
	}
}
