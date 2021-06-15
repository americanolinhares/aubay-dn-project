package com.aubay.project.utils;

import java.io.File;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.converters.FileConverter;

public class Arguments {

	@Parameter(names = { "-input", "-i" }, converter = FileConverter.class, description = "Input file path", required = false)
	private File input;

	@Parameter(names = { "-output", "-o" }, converter = FileConverter.class, description = "Output file path", required = false)
	private File output;

	public File getInput() {
		return input;
	}

	public void setInput(File input) {
		this.input = input;
	}

	public File getOutput() {
		return output;
	}

	public void setOutput(File output) {
		this.output = output;
	}

}
