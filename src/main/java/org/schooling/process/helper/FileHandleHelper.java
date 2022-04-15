package org.schooling.process.helper;

import org.schooling.process.core.ExecutionContext;
import org.schooling.process.rules.FileMetadataValidationRuleEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FileHandleHelper {

	@Autowired
	private FileMetadataValidationRuleEngine fileMetadataValidationRuleEngine;
	
	public void validateFileMetaData(ExecutionContext ctx) {
		try {
			fileMetadataValidationRuleEngine.executeRules(ctx);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void validateRecordLevel(ExecutionContext ctx) {

	}
}
