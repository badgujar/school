package org.schooling.process.file.validation.rules.javaimpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.schooling.process.core.ExecutionContext;
import org.springframework.stereotype.Component;

@Component
public class FilePatternValidationRule extends AbstractFileValidationRule {

	Logger log = LogManager.getLogger(FilePatternValidationRule.class);

	private static final String THIS_CLASS_NAME = FilePatternValidationRule.class.getName();
	private String RULE_EXECUTE = "executeRule";

	@Override
	public boolean executeRule(ExecutionContext ctx) {
		log.info(THIS_CLASS_NAME, RULE_EXECUTE, "Rule Check Started");
		try {
			// File not empty check
			String fileName = (String) ctx.get("camelfilenameonly");
			if (filePatternCheck(fileName)) {
				log.info(THIS_CLASS_NAME, RULE_EXECUTE,
						String.format("Received file  : (%s) with valid file pattern ", fileName));
				return true;
			} else {
				log.error(THIS_CLASS_NAME, RULE_EXECUTE,
						String.format("Received file  : (%s) with invalid file pattern ", fileName));
				// TODO
				ctx.put("", "");
				return false;
			}
		} catch (Exception throwable) {
			log.error(THIS_CLASS_NAME, RULE_EXECUTE,
					String.format("Exception occured while Rule Execution : (%s)", throwable.getMessage()));
			return false;
		}
	}

	private boolean filePatternCheck(String fileName) {
		// TODO file pattern
		return true;
	}

}
