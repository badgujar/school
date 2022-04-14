package com.sample.prototypesample.handler;

import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sample.prototypesample.rules.FileMetadataValidationRuleEngine;
import com.sample.prototypesample.utils.Constant;

@Component
public class FileHandler implements Processor {

	private static final String THIS_CLASS_NAME = FileHandler.class.getName();
	private Logger log = LogManager.getLogger(FileHandler.class);

	@Autowired
	private FileMetadataValidationRuleEngine fileMetadataValidationRuleEngine;

	@Override
	public void process(Exchange exchange) throws Exception {

		String methodName = "process";

		Map<String, Object> camelFileName = exchange.getIn().getHeaders();

		String fileName = (String) camelFileName.get("camelfilenameonly");
		log.debug(THIS_CLASS_NAME, methodName, "Received Inbound file is: " + fileName);

		String filePath = (String) camelFileName.get("CamelFileAbsolutePath");
		log.debug(THIS_CLASS_NAME, methodName, "Received Inbound file from the path: " + filePath);

		fileMetadataValidationRuleEngine.executeRules(exchange);

		String message = exchange.getIn().getBody(String.class);

		if ("Success".equalsIgnoreCase(message)) {
			// TODO
			exchange.getIn().setHeader(Constant.FILE_PROCESSED, Constant.TRUE);
		} else {
			exchange.getIn().setHeader(Constant.FILE_FAILED, Constant.TRUE);
		}
		System.out.println(message);
	}

}
