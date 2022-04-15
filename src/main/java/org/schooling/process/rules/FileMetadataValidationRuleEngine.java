package org.schooling.process.rules;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.schooling.process.file.validation.rules.javaimpl.FilePatternValidationRule;
import org.schooling.process.rule.JavaRule;
import org.schooling.process.rule.RuleEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FileMetadataValidationRuleEngine implements RuleEngine {

	private List<JavaRule> rules;

	@Autowired
	private FilePatternValidationRule filePatternValidationRule;

	@Override
	public void executeRules(Object... params) throws Exception {
		for (JavaRule javaRule : rules) {
			boolean nextRule = javaRule.execute(params);
			if (!nextRule) {
				break;
			}
		}
	}

	@PostConstruct
	private void init() {
		rules = new LinkedList<>();
		rules.add(filePatternValidationRule);
	}
}
