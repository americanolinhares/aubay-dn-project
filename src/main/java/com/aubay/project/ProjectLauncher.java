package com.aubay.project;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aubay.project.model.Report;
import com.aubay.project.utils.Commands;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class ProjectLauncher implements IFileGenerator {

    private static Logger logger = LoggerFactory.getLogger(ProjectLauncher.class);

    public static void main(String[] args) {

	ProjectLauncher program = new ProjectLauncher();
	try {
	    Commands commands = new Commands();
	    List<File> files = commands.validateCommandLine(args);
	    program.run(files);
	} catch (IOException e) {
	    logger.error("Class Main - %s", e.getMessage());
	    e.printStackTrace();
	}
    }

    private void run(List<File> files) throws IOException {
	LogfileScanner scanner = new LogfileScanner();
	Report report = scanner.processFile(files.get(0));

	this.generateOutputFile(files.get(1), report);
    }

    @Override
    public void generateOutputFile(File outputFile, Report report) throws IOException {
	ObjectMapper objectMapper = new ObjectMapper();
	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
	objectMapper.writeValue(outputFile, report);
    }
}