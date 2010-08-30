package com.assa.test;

import org.junit.Before;
import org.mockito.MockitoAnnotations;


public abstract class BaseMockitoTest {
	@Before 
	public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }
}
