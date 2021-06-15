package com.aubay.project.utils;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class CommandsTest {

    private Commands commands;

    @Before
    public void init() {
	commands = new Commands();
    }

    @Test(expected = FileNotFoundException.class)
    public void commandsWithoutExistingInputFile_shouldThrowException() throws IOException {

	String[] fileNames = { "-output", "output.json" };

	commands.validateCommandLine(fileNames);
    }

}
