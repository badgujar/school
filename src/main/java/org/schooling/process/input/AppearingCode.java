package org.schooling.process.input;

public enum AppearingCode {

	ARTS("A"), COMMERCE("C"), SCIENCE("S");

	private String code;

	AppearingCode(String code) {
		this.code = code;
	}

	public String code() {
		return code;
	}
}
