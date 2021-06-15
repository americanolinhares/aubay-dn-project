package com.aubay.project.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class RenderingTest {

	private Rendering rendering;
	private List<String> startTimestamp;
	private List<String> getTimestamp;
	
    @Before
    public void init()
    {
    	startTimestamp = new ArrayList<>();
    	startTimestamp.add("START_TIMESTAMP_ONE");
    	startTimestamp.add("START_TIMESTAMP_TWO");    	
    	getTimestamp = new ArrayList<>();
    	getTimestamp.add("GET_TIMESTAMP_ONE");
    	getTimestamp.add("GET_TIMESTAMP_TWO");
    	
    	rendering = new Rendering();
    	rendering.setDocument("DOCUMENT");
    	rendering.setPage("PAGE");
    	rendering.setUid("UID");
    	rendering.setStartRenderingTimestamp(startTimestamp);
    	rendering.setGetRenderingTimestamp(getTimestamp);
    }
    
    @Test
    public void getDocument_shouldReturnTheCorrectDocument()
    {
        assertEquals("DOCUMENT", rendering.getDocument());
    }
    
    @Test
    public void getUid_shouldReturnTheCorrectUid()
    {
        assertEquals("UID", rendering.getUid());
    }
    
    @Test
    public void getPage_shouldReturnTheCorrectPage()
    {
        assertEquals("PAGE", rendering.getPage());
    }
    
    @Test
    public void getStartTimestampList_shouldReturnTheStartTimestampList()
    {
        assertNotNull(rendering.getStartRenderingTimestamp());
        assertEquals(2,rendering.getStartRenderingTimestamp().size());
    }
    
    @Test
    public void getGetTimestampList_shouldReturnTheGetTimeStampList()
    {
        assertNotNull(rendering.getGetRenderingTimestamp());
        assertEquals(2,rendering.getGetRenderingTimestamp().size());
        assertEquals("GET_TIMESTAMP_ONE",rendering.getGetRenderingTimestamp().get(0));
    }
}
