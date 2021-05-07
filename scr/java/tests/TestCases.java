package tests;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import mainCareHome.CareHome;

public class TestCases {
	
	 @Test
	 public void testCheckCompliance() {
		 CareHome careHome = new CareHome();
		 assertEquals(true, careHome.checkCompliance(8, 4));
		 assertNotEquals(true, careHome.checkCompliance(8, 1));
		 assertEquals(true, careHome.checkCompliance(2, 10));
		 assertNotEquals(true, careHome.checkCompliance(2, 6));
		 
	 }

}
