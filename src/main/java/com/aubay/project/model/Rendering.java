package com.aubay.project.model;

import java.util.ArrayList;
import java.util.List;

public class Rendering {

	private String document;
	private String page;
	private String uid;
	private List<String> startRenderingTimestamp;
	private List<String> getRenderingTimestamp;
	
	public Rendering(){
		this.startRenderingTimestamp = new ArrayList<>();
		this.getRenderingTimestamp = new ArrayList<>();		
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public List<String> getStartRenderingTimestamp() {
		return startRenderingTimestamp;
	}

	public void setStartRenderingTimestamp(List<String> startRenderingTimestamp) {
		this.startRenderingTimestamp = startRenderingTimestamp;
	}

	public List<String> getGetRenderingTimestamp() {
		return getRenderingTimestamp;
	}

	public void setGetRenderingTimestamp(List<String> getRenderingTimestamp) {
		this.getRenderingTimestamp = getRenderingTimestamp;
	}
}
