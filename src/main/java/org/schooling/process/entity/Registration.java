package org.schooling.process.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.schooling.process.base.BaseEntity;

@Entity
@Table(name = "registration", schema = "schoolproject")
public class Registration extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String registrationId;
	private String districtCode;
	private String collegeCode;
	private String streamCode;
	private String centerCode;
	private String registrationYear;
	private String registrationNumber;
	private String serialNumber;

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

	@Column(name = "district_code")
	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	@Column(name = "college_code")
	public String getCollegeCode() {
		return collegeCode;
	}

	public void setCollegeCode(String collegeCode) {
		this.collegeCode = collegeCode;
	}

	@Column(name = "stream_code")
	public String getStreamCode() {
		return streamCode;
	}

	public void setStreamCode(String streamCode) {
		this.streamCode = streamCode;
	}

	@Column(name = "center_code")
	public String getCenterCode() {
		return centerCode;
	}

	public void setCenterCode(String centerCode) {
		this.centerCode = centerCode;
	}

	@Column(name = "registration_year")
	public String getRegistrationYear() {
		return registrationYear;
	}

	public void setRegistrationYear(String registrationYear) {
		this.registrationYear = registrationYear;
	}

	@Column(name = "registration_number")
	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	@Column(name = "serial_no")
	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

}
