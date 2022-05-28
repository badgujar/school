package org.schooling.process.model;

import lombok.Data;

@Data
public class Subject {

	private String code;
	private String subjectTag;
	private String marks;
	private String hcrMarks;
	private boolean isTheory;
	private boolean isPractical;

	public enum MilSubject {
		O("O  "), H("H  "), B("B  "), P("P  "), T("T  "), U("U  "), AE("AE "), S("S  ");

		public final String subject;

		private MilSubject(String subject) {
			this.subject = subject;
		}

		public String getSubject() {
			return subject;
		}

		public static boolean isValidMilSubject(String subject) {

			for (MilSubject milSubject : MilSubject.values()) {
				if (milSubject.getSubject().equals(subject)) {
					return true;
				}
			}
			return false;
		}
	}


}
