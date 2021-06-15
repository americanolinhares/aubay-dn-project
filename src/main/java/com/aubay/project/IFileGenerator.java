package com.aubay.project;

import java.io.File;
import java.io.IOException;

import com.aubay.project.model.Report;

public interface IFileGenerator {

    void generateOutputFile(File outputFile, Report report) throws IOException;

}
