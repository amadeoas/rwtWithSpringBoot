package de.wolff.rwtonspringboot;

import org.eclipse.rap.rwt.testfixture.TestContext;
import org.junit.Rule;
import org.junit.Test;

import uk.co.bocaditos.rwtwithspringboot.entrypoints.MainRwtOnSpringBootEntryPoint;


public class RwtOnSpringBootApplicationTests {

	@Rule
	public TestContext rwtContext = new TestContext();

	@Test
	public void mainRwtTest() {
		// Create and run the EntryPoint is a unit test environment
		new MainRwtOnSpringBootEntryPoint().createUI();

		System.out.println("I'm an RWT unit test!");
	}

} // end class RwtOnSpringBootApplicationTests
