package Test;

import static org.junit.Assert.assertNotNull;

import org.junit.BeforeClass;
import org.junit.Test;

import Model.ProjectManager;

public class ProjectMangerTest {

	private static ProjectManager proj;
	
	@BeforeClass
	public static void setup() {
		proj = new ProjectManager();
	}
	
	@Test
	public void getTest() {
		assertNotNull(proj.getProjects());
	}
}
