package org.schooling.process.service;

import java.io.Serializable;

import org.schooling.process.model.StudentTransactionRecord;

public interface SchoolingService<T> extends Serializable {

	Integer saveStudentDetails(StudentTransactionRecord studentTransactionRecord) throws Exception;

}