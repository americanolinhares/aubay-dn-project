package com.aubay.project.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.beust.jcommander.JCommander;

public class Commands {

    private static Logger logger = LoggerFactory.getLogger(Commands.class);

    public List<File> validateCommandLine(String[] args) throws IOException {

	Arguments arguments = new Arguments();
	JCommander cmd = JCommander.newBuilder().addObject(arguments).build();
	cmd.parse(args);

	Optional<File> input = Optional.ofNullable(arguments.getInput());

	if (!input.isPresent()) {
	    throw new FileNotFoundException("Class Commands - FileNotFoundException");
	}

	List<File> fileValidation = addInputFile(input.get());

	File output = arguments.getOutput();
	if (!output.exists()) {

	    try {
		output = new File(output.getAbsoluteFile().toString());
		if (output.createNewFile()) {
		    logger.debug("Class Commands - File %s created ", output.getName());
		}
	    } catch (IOException e) {
		e.printStackTrace();
	    }

	}
	fileValidation.add(output);
	return fileValidation;

    }

    private List<File> addInputFile(File input) throws FileNotFoundException {
	List<File> fileValidation = new ArrayList<>();
	if (input.exists()) {
	    fileValidation.add(input);
	} else {
	    logger.error("Class Commands - FileNotFoundException");
	    throw new FileNotFoundException("Class Commands - Invalid Input file");
	}
	return fileValidation;
    }

}
