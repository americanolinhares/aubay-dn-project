package com.aubay.project.model;

import java.util.ArrayList;
import java.util.List;

public class Report {

	private List<Rendering> renderings;
	private Summary summary;
	
	public Report() {
		this.renderings = new ArrayList<>();		
	}

	public List<Rendering> getRenderings() {
		return renderings;
	}

	public void setRenderings(List<Rendering> renderings) {
		this.renderings = renderings;
	}

	public Summary getSummary() {
		return summary;
	}

	public void setSummary(Summary summary) {
		this.summary = summary;
	}
}