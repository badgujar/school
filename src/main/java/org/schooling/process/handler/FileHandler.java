package org.schooling.process.handler;

import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.schooling.process.core.CacheProvider;
import org.schooling.process.core.ExecutionContext;
import org.schooling.process.helper.FileHandleHelper;
import org.schooling.process.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FileHandler implements Processor {

	private static final String THIS_CLASS_NAME = FileHandler.class.getName();
	private Logger log = LogManager.getLogger(FileHandler.class);

	@Autowired
	private CacheProvider cacheProvider;

	@Autowired
	private FileHandleHelper fileHandlerHelper;

	@Override
	public void process(Exchange exchange) throws Exception {
		String methodName = "process";
		ExecutionContext ctx = new ExecutionContext(cacheProvider);

		Map<String, Object> camelFileName = exchange.getIn().getHeaders();

		String fileName = (String) camelFileName.get("camelfilenameonly");
		log.debug(THIS_CLASS_NAME, methodName, "Received Inbound file is: " + fileName);

		String filePath = (String) camelFileName.get("CamelFileAbsolutePath");
		log.debug(THIS_CLASS_NAME, methodName, "Received Inbound file from the path: " + filePath);

		String message = exchange.getIn().getBody(String.class);
		ctx.put("Message", message);
		ctx.put("camelfilenameonly", (String) exchange.getIn().getHeader("camelfilenameonly"));

		fileHandlerHelper.validateFileMetaData(ctx);

		if (!"Success".equalsIgnoreCase(message)) {
			exchange.getIn().setHeader(Constant.FILE_FAILED, Constant.TRUE);
		} else {
			fileHandlerHelper.validateRecordLevel(ctx);
		}

		System.out.println(message);
	}

}
