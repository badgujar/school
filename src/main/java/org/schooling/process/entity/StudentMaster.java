package org.schooling.process.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.schooling.process.base.BaseEntity;

@Entity
@Table(name = "student_master", schema = "schoolproject")
public class StudentMaster extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String registrationId;
	private String studentName;
	private String motherName;
	private String fatherName;
	private String sex;
	private String stream;
	private String streamCategory;
	private String communityCode;
	private String appearingCode;
	private String previousRegistrationId;
	private Integer totalSubjects;
	private boolean isPass;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "registration_ID")
	public String getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}

	@Column(name = "student_name")
	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	@Column(name = "mother_name")
	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	@Column(name = "father_name")
	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	@Column(name = "sex")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "stream")
	public String getStream() {
		return stream;
	}

	public void setStream(String stream) {
		this.stream = stream;
	}

	@Column(name = "stream_category")
	public String getStreamCategory() {
		return streamCategory;
	}

	public void setStreamCategory(String streamCategory) {
		this.streamCategory = streamCategory;
	}

	@Column(name = "appearing_code")
	public String getAppearingCode() {
		return appearingCode;
	}

	public void setAppearingCode(String appearingCode) {
		this.appearingCode = appearingCode;
	}

	@Column(name = "previous_registration_ID")
	public String getPreviousRegistrationId() {
		return previousRegistrationId;
	}

	public void setPreviousRegistrationId(String previousRegistrationId) {
		this.previousRegistrationId = previousRegistrationId;
	}

	@Column(name = "total_subjects")
	public Integer getTotalSubjects() {
		return totalSubjects;
	}

	public void setTotalSubjects(Integer totalSubjects) {
		this.totalSubjects = totalSubjects;
	}

	@Column(name = "is_pass")
	public boolean getIsPass() {
		return isPass;
	}

	public void setIsPass(boolean isPass) {
		this.isPass = isPass;
	}

	@Column(name = "community")
	public String getCommunityCode() {
		return communityCode;
	}

	public void setCommunityCode(String communityCode) {
		this.communityCode = communityCode;
	}


}
