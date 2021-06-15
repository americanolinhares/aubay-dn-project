package com.aubay.project;

import com.aubay.project.model.Rendering;
import com.aubay.project.model.Report;
import com.aubay.project.model.Summary;
import com.aubay.project.utils.Constants;

import org.apache.commons.lang3.StringUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class LogfileScanner {

	int count = 0;
	int duplicates = 0;

	public Report processFile(File inputFile) throws IOException {

		Map<String, Rendering> renderingWithThread = new HashMap<>();
		Map<String, Rendering> renderingWithUid = new HashMap<>();
		List<Rendering> renderings = new ArrayList<>();

		try (  Scanner sc = new Scanner(new FileInputStream(inputFile), Constants.UTF_8)) {

			while (sc.hasNextLine()) {

				String line = sc.nextLine();
				processLine(renderingWithThread, renderingWithUid, renderings, line);

			}

			if (sc.ioException() != null) {
				throw sc.ioException();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return populateReport(renderings, count, duplicates);
	}

	private void processLine(Map<String, Rendering> renderingWithThread, Map<String, Rendering> renderingWithUid,
			List<Rendering> renderings, String line) {

		if (line.matches(Constants.REGEX_START_NUMBER)
				&& (line.contains(Constants.START_RENDERING) || line.contains(Constants.GET_RENDERING))) {

			String[] splittedLine = line.replaceAll(Constants.TWO_OR_MORE_SPACES, Constants.SINGLE_SPACE)
					.split(Constants.SINGLE_SPACE, Constants.SIX);
			String timestamp = splittedLine[0] + Constants.SINGLE_SPACE + splittedLine[1];
			String thread = StringUtils.substringBetween(splittedLine[2], Constants.DASH, Constants.CLOSING_BRACKET);
			String logMessage = splittedLine[5];

			if (logMessage.contains(Constants.START_RENDERING)) {

				if (logMessage.contains(Constants.EXECUTING_REQUEST_START_RENDERING)) {
					processRequestStartRendering(renderingWithThread, timestamp, thread, logMessage);
				} else {
					if (logMessage.contains(Constants.SERVICE_START_RENDERING_RETURNED)
							&& renderingWithThread.containsKey(thread)) {
						processReturnedStartRedering(renderingWithThread, renderingWithUid, renderings, thread,
								logMessage);
					}
				}
			} else if (logMessage.contains(Constants.EXECUTING_REQUEST_GET_RENDERING)) {
				processRequestGetRendering(renderingWithUid, timestamp, logMessage);
			}
		}
	}

	private void processRequestGetRendering(Map<String, Rendering> renderingWithUid, String timestamp,
			String logMessage) {
		String uid = StringUtils.substringBetween(logMessage, Constants.OPENING_BRACKET, Constants.CLOSING_BRACKET);

		if (renderingWithUid.containsKey(uid)) {
			renderingWithUid.get(uid).getGetRenderingTimestamp().add(timestamp);
		}
	}

	private void processReturnedStartRedering(Map<String, Rendering> renderingWithThread,
			Map<String, Rendering> renderingWithUid, List<Rendering> renderings, String thread, String logMessage) {
		String uid = logMessage.split(Constants.SINGLE_SPACE, Constants.SIX)[3];

		Rendering rendering = renderingWithThread.get(thread);
		if (renderingWithUid.containsKey(uid)) {
			renderingWithUid.get(uid).getStartRenderingTimestamp().addAll(rendering.getStartRenderingTimestamp());
			duplicates++;
		} else {
			rendering.setUid(uid);
			renderingWithUid.put(uid, rendering);
			renderings.add(rendering);
			count++;
		}
		renderingWithThread.remove(thread);
	}

	private void processRequestStartRendering(Map<String, Rendering> renderingWithThread, String timestamp,
			String thread, String logMessage) {
		String document = StringUtils.substringBetween(logMessage, Constants.OPENING_BRACKET, Constants.COMMA);
		String page = StringUtils.substringBetween(logMessage, Constants.COMMA, Constants.CLOSING_BRACKET);

		Rendering rendering = new Rendering();
		rendering.setDocument(document);
		rendering.setPage(page);
		rendering.getStartRenderingTimestamp().add(timestamp);

		renderingWithThread.put(thread, rendering);
	}

	private static Report populateReport(List<Rendering> renderings, int count, int duplicates) {

		int unnecessary = renderings.stream().mapToInt(rendering -> rendering.getStartRenderingTimestamp().size()
				- rendering.getGetRenderingTimestamp().size()).map(i -> i < 0 ? 0 : i).sum();

		Summary summary = new Summary();
		summary.setCount(count);
		summary.setDuplicates(duplicates);
		summary.setUnnecessary(unnecessary);

		Report report = new Report();

		Iterator<Rendering> linesIterator = renderings.iterator();

		while (linesIterator.hasNext()) {
			Rendering rendering = linesIterator.next();
			report.getRenderings().add(rendering);
		}

		report.setSummary(summary);
		return report;
	}
}
