package bg.petarh.interview.sirma.employees.empmanagement;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProjectHolderTest {

    @Test
    public void projectsTest() {

        assertTrue("no input - should be empty", ProjectHolder.getProjectList().isEmpty());

        int projectId1 = 1;

        ProjectHolder.getOrCreateProject(projectId1);

        assertEquals("size is 1", 1, ProjectHolder.getProjectList().size());
        assertEquals("id of element 1 = $projectId1 ", projectId1, ProjectHolder.getProjectList().get(0).getId());

        ProjectHolder.getOrCreateProject(projectId1);
        assertEquals("size is 1", 1, ProjectHolder.getProjectList().size());

        int projectId2 = 2;

        ProjectHolder.getOrCreateProject(projectId2);
        assertEquals("size is 2", 2, ProjectHolder.getProjectList().size());
        assertEquals("id of element 2 = $projectId2 ", projectId2, ProjectHolder.getProjectList().get(1).getId());


    }
}
