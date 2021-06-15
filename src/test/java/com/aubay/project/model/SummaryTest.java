package com.aubay.project.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class SummaryTest {

	private Summary summary;
	
    @Before
    public void init()
    {   	
    	summary = new Summary();
    	summary.setCount(2);
    	summary.setDuplicates(4);
    }
    
    @Test
    public void getValidCount_shouldReturnTheCorrectCount()
    {
        assertEquals(2, summary.getCount());
    }
    
    @Test
    public void getUninitializedUnnecessary_shouldReturnTheCorrectUnnecessary()
    {
        assertEquals(0, summary.getUnnecessary());
    }
    
    @Test
    public void getValidDuplicates_shouldReturnTheCorrectDuplicates()
    {
        assertEquals(4, summary.getDuplicates());
    }

}
