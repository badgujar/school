package org.schooling.process.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.schooling.process.mapper.EntityMapper;
import org.schooling.process.model.StudentTransactionRecord;
import org.schooling.process.transaction.dao.SchoolingTransactionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class SchoolingServiceImpl implements SchoolingService {

	private Logger log = LogManager.getLogger(SchoolingServiceImpl.class);

	@Autowired
	SchoolingTransactionDAO schoolingTransactionDAO;

	@Override
	public Integer saveStudentDetails(StudentTransactionRecord studentTransactionRecord) throws Exception {

		Integer studentMaster;
		try {
			studentMaster = (Integer) schoolingTransactionDAO.save(EntityMapper
					.mapToStudentMaster(studentTransactionRecord, studentTransactionRecord.getStudentPersonalData()));
			schoolingTransactionDAO.save(EntityMapper.mapToRegistration(studentTransactionRecord));

			studentTransactionRecord.getSubject().stream().forEach(sub -> {
				try {
					schoolingTransactionDAO.save(EntityMapper.mapToMarks(studentTransactionRecord, sub));
				} catch (Exception e) {
					throw new RuntimeException(
							"Exception occured while instering Marks records in the DB with message: "
									+ e.getMessage());
				}
			});

			return studentMaster;
		} catch (Exception e) {
			log.fatal("Exception occured while saving into the DB with message: " + e.getMessage());
			throw e;
		}

	}


}
