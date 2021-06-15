package com.aubay.project;

import static org.junit.Assert.assertEquals;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.aubay.project.model.Report;

public class LogFileScannerTest {

    @Before
    public void init() throws IOException {
    }

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void processExistingFileWithOneRendering_shouldReturnTheCorrectReport() throws IOException {

	File testFile = folder.newFile("someTestFile.log");
	FileWriter fw = new FileWriter(testFile, true);
	BufferedWriter bw = new BufferedWriter(fw);
	bw.write(
		"2010-10-06 09:03:05,869 [WorkerThread-17] INFO  [ServiceProvider]: Executing request startRendering with arguments [114466, 0] on service object { ReflectionServiceObject -> com.dn.gaverzicht.dms.services.DocumentService@4a3a4a3a }");
	bw.newLine();
	bw.write(
		"2010-10-06 09:03:05,873 [WorkerThread-17] INFO  [ServiceProvider]: Service startRendering returned 1286373785873-3536");
	bw.newLine();
	bw.write(
		"2010-10-06 09:03:06,547 [WorkerThread-15] INFO  [ServiceProvider]: Executing request getRendering with arguments [1286373785873-3536] on service object { ReflectionServiceObject -> com.dn.gaverzicht.dms.services.DocumentService@4a3a4a3a }");
	bw.close();

	LogfileScanner logfileScanner = new LogfileScanner();
	Report report = logfileScanner.processFile(testFile);

	assertEquals(1, report.getSummary().getCount());
	assertEquals(0, report.getSummary().getUnnecessary());
	assertEquals(0, report.getSummary().getDuplicates());
	assertEquals("114466", report.getRenderings().get(0).getDocument());
	assertEquals("0", report.getRenderings().get(0).getPage());
    }

    @Test
    public void processExistingFileWithTwoRenderings_shouldReturnTheCorrectReport() throws IOException {

	File testFile = folder.newFile("someTestFile.log");
	FileWriter fw = new FileWriter(testFile, true);
	BufferedWriter bw = new BufferedWriter(fw);
	bw.write(
		"2010-10-06 09:03:05,869 [WorkerThread-17] INFO  [ServiceProvider]: Executing request startRendering with arguments [114466, 0] on service object { ReflectionServiceObject -> com.dn.gaverzicht.dms.services.DocumentService@4a3a4a3a }");
	bw.newLine();
	bw.write(
		"2010-10-06 09:03:26,774 [WorkerThread-12] INFO  [ServiceProvider]: Executing request startRendering with arguments [114466, 0] on service object { ReflectionServiceObject -> com.dn.gaverzicht.dms.services.DocumentService@4a3a4a3a }");
	bw.newLine();
	bw.write(
		"2010-10-06 09:03:05,873 [WorkerThread-17] INFO  [ServiceProvider]: Service startRendering returned 1286373785873-3536");
	bw.newLine();
	bw.write(
		"2010-10-06 09:03:26,777 [WorkerThread-12] INFO  [ServiceProvider]: Service startRendering returned 1286373806777-5552");
	bw.newLine();
	bw.write(
		"2010-10-06 09:03:06,547 [WorkerThread-15] INFO  [ServiceProvider]: Executing request getRendering with arguments [1286373785873-3536] on service object { ReflectionServiceObject -> com.dn.gaverzicht.dms.services.DocumentService@4a3a4a3a }");
	bw.newLine();
	bw.write(
		"2010-10-06 09:03:27,985 [WorkerThread-13] INFO  [ServiceProvider]: Executing request getRendering with arguments [1286373806777-5552] on service object { ReflectionServiceObject -> com.dn.gaverzicht.dms.services.DocumentService@4a3a4a3a }");
	bw.close();

	LogfileScanner logfileScanner = new LogfileScanner();
	Report report = logfileScanner.processFile(testFile);

	assertEquals(2, report.getSummary().getCount());
	assertEquals(0, report.getSummary().getUnnecessary());
	assertEquals(0, report.getSummary().getDuplicates());
	assertEquals("114466", report.getRenderings().get(0).getDocument());
	assertEquals("0", report.getRenderings().get(0).getPage());
	assertEquals("1286373785873-3536", report.getRenderings().get(0).getUid());
	assertEquals("114466", report.getRenderings().get(1).getDocument());
	assertEquals("0", report.getRenderings().get(1).getPage());
	assertEquals("1286373806777-5552", report.getRenderings().get(1).getUid());
    }
}
