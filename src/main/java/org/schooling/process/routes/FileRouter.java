package org.schooling.process.routes;

import org.apache.camel.Predicate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.beanio.BeanIODataFormat;
import org.apache.camel.spi.DataFormat;
import org.schooling.process.handler.FileHandler;
import org.schooling.process.utils.Constant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FileRouter extends RouteBuilder {

	@Value("${input.source.path}")
	public String sourcePath;
	@Value("${input.source.success.path}")
	public String successPath;
	@Value("${input.source.error.path}")
	public String errorPath;
	
	private final String fileComponent = "file://";

	@Override
	public void configure() throws Exception {

		Predicate failPredicate = header(Constant.FILE_FAILED).isEqualTo(Constant.TRUE);
		Predicate processedPredicate = header(Constant.FILE_PROCESSED).isEqualTo(Constant.TRUE);

		DataFormat dataFormat = new BeanIODataFormat("classpath:transformation/request/beanio/studentdatabeanio.xml",
				"studentEnrollmentData");

		from(fileComponent + sourcePath).split(body().tokenize("\\r\\n")).log("${body}").bean(FileHandler.class)
				.choice().when(failPredicate).to(fileComponent + errorPath).end().choice().when(processedPredicate)
				.to(fileComponent + successPath).end().log("${messageHistory}");
	}

}
