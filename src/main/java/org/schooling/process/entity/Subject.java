package org.schooling.process.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.schooling.process.base.BaseEntity;

@Entity
@Table(name = "subjects", schema = "schoolproject")
public class Subject extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String code;
	private String description;
	private boolean isTheory;
	private Integer paperNumber;
	private Integer maxMarks;
	private Integer minMarks;
	private Integer passMarks;
	private boolean isOptional;
	private Integer stream;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "code")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "is_theroy")
	public boolean isTheory() {
		return isTheory;
	}

	public void setTheory(boolean isTheory) {
		this.isTheory = isTheory;
	}

	@Column(name = "paper_no")
	public Integer getPaperNumber() {
		return paperNumber;
	}

	public void setPaperNumber(Integer paperNumber) {
		this.paperNumber = paperNumber;
	}

	@Column(name = "max_marks")
	public Integer getMaxMarks() {
		return maxMarks;
	}

	public void setMaxMarks(Integer maxMarks) {
		this.maxMarks = maxMarks;
	}

	@Column(name = "min_marks")
	public Integer getMinMarks() {
		return minMarks;
	}

	public void setMinMarks(Integer minMarks) {
		this.minMarks = minMarks;
	}

	@Column(name = "pass_marks")
	public Integer getPassMarks() {
		return passMarks;
	}

	public void setPassMarks(Integer passMarks) {
		this.passMarks = passMarks;
	}

	@Column(name = "is_optional")
	public boolean isOptional() {
		return isOptional;
	}

	public void setOptional(boolean isOptional) {
		this.isOptional = isOptional;
	}

	@Column(name = "stream")
	public Integer getStream() {
		return stream;
	}

	public void setStream(Integer stream) {
		this.stream = stream;
	}

}
