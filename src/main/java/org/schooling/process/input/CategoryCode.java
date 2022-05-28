package org.schooling.process.input;

public enum CategoryCode {

	REGULAR("R"), EX_REGULAR("E"), PRIVATE("P");

	private String code;

	private CategoryCode(String code) {
		this.code = code;
	}

	public String code() {
		return code;
	}

}
