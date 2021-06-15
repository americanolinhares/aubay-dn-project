package com.aubay.project.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ReportTest {

    private Report report;
    private Summary summary;
    private List<String> startTimestamp;
    private List<String> getTimestamp;
    private Rendering rendering1;
    private Rendering rendering2;

    @Before
    public void init() {
	summary = new Summary();
	summary.setCount(3);
	summary.setDuplicates(4);
	startTimestamp = new ArrayList<>();
	getTimestamp = new ArrayList<>();
	rendering1 = new Rendering();
	rendering2 = new Rendering();
	report = new Report();
	report.setSummary(summary);
	report.setRenderings(Arrays.asList(rendering1, rendering2));
    }

    @Test
    public void getValidReport_shouldReturnTheCorrectReport() {
	assertNotNull(report);
	assertEquals(2, report.getRenderings().size());
	assertEquals(3, report.getSummary().getCount());
    }

    @Test
    public void getVoidReport_shouldReturnTheCorrectReport() {
	report = new Report();

	assertNull(report.getSummary());
    }
}
