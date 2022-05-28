package org.schooling.process.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.schooling.process.base.BaseEntity;

@Entity
@Table(name = "marks", schema = "schoolproject")
public class Marks extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String registrationId;
	private String subjectOrder;
	private String subjectCode;
	private String subjectTag;
	private Integer obtainedMark;
	private Integer graceMark;
	private Integer totalMark;
	private boolean isPass;
	private String remark;

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

	@Column(name = "subject_order")
	public String getSubjectOrder() {
		return subjectOrder;
	}

	public void setSubjectOrder(String subjectOrder) {
		this.subjectOrder = subjectOrder;
	}

	@Column(name = "subject_code")
	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	@Column(name = "subject_tag")
	public String getSubjectTag() {
		return subjectTag;
	}

	public void setSubjectTag(String subjectTag) {
		this.subjectTag = subjectTag;
	}

	@Column(name = "obtained_mark")
	public Integer getObtainedMark() {
		return obtainedMark;
	}

	public void setObtainedMark(Integer obtainedMark) {
		this.obtainedMark = obtainedMark;
	}

	@Column(name = "grace_mark")
	public Integer getGraceMark() {
		return graceMark;
	}

	public void setGraceMark(Integer graceMark) {
		this.graceMark = graceMark;
	}

	@Column(name = "total_mark")
	public Integer getTotalMark() {
		return totalMark;
	}

	public void setTotalMark(Integer totalMark) {
		this.totalMark = totalMark;
	}

	@Column(name = "is_pass")
	public boolean getIsPass() {
		return isPass;
	}

	public void setIsPass(boolean isPass) {
		this.isPass = isPass;
	}

	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
