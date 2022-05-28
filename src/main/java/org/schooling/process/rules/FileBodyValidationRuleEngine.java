package org.schooling.process.rules;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.schooling.process.file.body.validation.rules.javaimpl.CanditateAppearingCodeValidationRule;
import org.schooling.process.file.body.validation.rules.javaimpl.CanditateCategoryCodeValidationRule;
import org.schooling.process.file.body.validation.rules.javaimpl.CanditateCentreCodeValidationRule;
import org.schooling.process.file.body.validation.rules.javaimpl.CanditateCollegeCodeValidationRule;
import org.schooling.process.file.body.validation.rules.javaimpl.CanditateCommunityCodeValidationRule;
import org.schooling.process.file.body.validation.rules.javaimpl.CanditateDistrictCodeValidationRule;
import org.schooling.process.file.body.validation.rules.javaimpl.CanditateEnglishSubValidationRule;
import org.schooling.process.file.body.validation.rules.javaimpl.CanditateGenderValidationRule;
import org.schooling.process.file.body.validation.rules.javaimpl.CanditateMilSubValidationRule;
import org.schooling.process.file.body.validation.rules.javaimpl.CanditateNameValidationRule;
import org.schooling.process.file.body.validation.rules.javaimpl.CanditatePreviousRollNumberValidationRule;
import org.schooling.process.file.body.validation.rules.javaimpl.CanditateRegNumberValidationRule;
import org.schooling.process.file.body.validation.rules.javaimpl.CanditateSerialNumberValidationRule;
import org.schooling.process.file.body.validation.rules.javaimpl.CanditateSt12Opt12ValidationRule;
import org.schooling.process.file.body.validation.rules.javaimpl.CanditateStOpt3ExOptValidationRule;
import org.schooling.process.file.body.validation.rules.javaimpl.CanditateStreamCodeValidationRule;
import org.schooling.process.rule.JavaRule;
import org.schooling.process.rule.RuleEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FileBodyValidationRuleEngine implements RuleEngine {

	@Autowired
	private CanditateDistrictCodeValidationRule canditateDistrictCodeValidationRule;

	@Autowired
	private CanditateCollegeCodeValidationRule canditateCollegeCodeValidationRule;

	@Autowired
	private CanditateStreamCodeValidationRule canditateStreamCodeValidationRule;

	@Autowired
	private CanditateSerialNumberValidationRule canditateSerialNumberValidationRule;
	
	@Autowired
	private CanditateCentreCodeValidationRule canditateCentreCodeValidationRule;

	@Autowired
	private CanditateNameValidationRule canditateNameValidationRule;
	
	@Autowired
	private CanditateRegNumberValidationRule canditateRegNumberValidationRule;
	
	@Autowired
	private CanditateGenderValidationRule canditateGenderValidationRule;

	@Autowired
	private CanditateCommunityCodeValidationRule canditateCommunityCodeValidationRule;
	
	@Autowired
	private CanditateCategoryCodeValidationRule canditateCategoryCodeValidationRule;

	@Autowired
	private CanditateAppearingCodeValidationRule canditateAppearingCodeValidationRule;
	
	@Autowired
	private CanditatePreviousRollNumberValidationRule canditatePreviousRollNumberValidationRule;
	
	@Autowired
	private CanditateEnglishSubValidationRule canditateEnglishSubValidationRule;

	@Autowired
	private CanditateMilSubValidationRule canditateMilSubValidationRule;
	
	@Autowired
	private CanditateSt12Opt12ValidationRule canditateSt12Opt12ValidationRule;
	
	@Autowired
	private CanditateStOpt3ExOptValidationRule canditateStOpt3ExOptValidationRule;


	private List<JavaRule> rules;

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
		rules.add(canditateDistrictCodeValidationRule);
		rules.add(canditateCollegeCodeValidationRule);
		rules.add(canditateStreamCodeValidationRule);
		rules.add(canditateSerialNumberValidationRule);
		rules.add(canditateCentreCodeValidationRule);
		rules.add(canditateNameValidationRule);
		rules.add(canditateGenderValidationRule);
		rules.add(canditateRegNumberValidationRule);
		rules.add(canditateCommunityCodeValidationRule);
		rules.add(canditateCategoryCodeValidationRule);
		rules.add(canditateAppearingCodeValidationRule);
		rules.add(canditatePreviousRollNumberValidationRule);
		rules.add(canditateEnglishSubValidationRule);
		rules.add(canditateMilSubValidationRule);
		rules.add(canditateSt12Opt12ValidationRule);
		rules.add(canditateStOpt3ExOptValidationRule);
	}

}
