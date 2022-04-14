package com.sample.prototypesample.routes;

import org.apache.camel.Predicate;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sample.prototypesample.handler.FileHandler;
import com.sample.prototypesample.utils.Constant;

@Component
public class FileRouter extends RouteBuilder {

	@Value("${prototype.sample.input.source.path}")
	public String sourcePath;
	@Value("${prototype.sample.input.source.success.path}")
	public String successPath;
	@Value("${prototype.sample.input.source.error.path}")
	public String errorPath;
	
	private final String fileComponent = "file://";

	@Override
	public void configure() throws Exception {

		Predicate failPredicate = header(Constant.FILE_FAILED).isEqualTo(Constant.TRUE);
		Predicate processedPredicate = header(Constant.FILE_PROCESSED).isEqualTo(Constant.TRUE);

		from(fileComponent + sourcePath).bean(FileHandler.class).tracing().choice().when(failPredicate)
				.to(fileComponent + errorPath).end().choice().when(processedPredicate).to(fileComponent + successPath)
				.end().log("${body}").log("${messageHistory}");
	}

}
