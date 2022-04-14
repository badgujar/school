package com.sample.prototypesample.file.validation.rules.javaimpl;

import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.sample.prototypesample.utils.Constant;

@Component
public class FilePatternValidationRule extends AbstractFileValidationRule {

	Logger log = LogManager.getLogger(FilePatternValidationRule.class);

	private static final String THIS_CLASS_NAME = FilePatternValidationRule.class.getName();
	private String RULE_EXECUTE = "executeRule";

	@Override
	public boolean executeRule(Exchange exchange) {
		log.info(THIS_CLASS_NAME, RULE_EXECUTE, "Rule Check Started");
		try {
			Map<String, Object> camelFileName = exchange.getIn().getHeaders();
			String fileName = (String) camelFileName.get("camelfilenameonly");
			if (filePatternCheck(fileName)) {
				log.info(THIS_CLASS_NAME, RULE_EXECUTE,
						String.format("Received file  : (%s) with valid file pattern ", fileName));
				return true;
			} else {
				log.error(THIS_CLASS_NAME, RULE_EXECUTE,
						String.format("Received file  : (%s) with invalid file pattern ", fileName));
				exchange.getIn().setHeader(Constant.FILE_FAILED, Constant.TRUE);
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
