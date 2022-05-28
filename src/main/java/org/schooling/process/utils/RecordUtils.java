package org.schooling.process.utils;

import org.apache.commons.lang3.StringUtils;

public class RecordUtils {

	/**
	 * Private constructor
	 */
	private RecordUtils() {

	}

	/**
	 * 
	 * @param inputArray
	 * @param startIndex
	 * @param endIndex
	 * @return char[] as String
	 */
	public static String fetchCharactersByStartIndexAndEndIndex(char[] inputArray, int startIndex, int endIndex) {

		char[] ch = new char[endIndex - startIndex];
		int j = 0;
		for (int i = startIndex; i < endIndex; i++) {
			ch[j++] = inputArray[i];
		}
		return new String(ch);
	}

	/**
	 * 
	 * @param input
	 * @param startIndex
	 * @param endIndex
	 * @return String
	 */
	public static String fetchSubStringByStartAndEndIndex(String input, int startIndex, int endIndex) {

		return StringUtils.substring(input, startIndex, endIndex);
	}

}
