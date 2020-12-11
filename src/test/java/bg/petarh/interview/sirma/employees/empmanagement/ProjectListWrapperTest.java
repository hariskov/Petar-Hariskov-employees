package bg.petarh.interview.sirma.employees.empmanagement;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProjectListWrapperTest {

    @Test
    public void projectsTest() {

        ProjectListWrapper holder = new ProjectListWrapper();

        assertTrue("no input - should be empty", holder.getProjectList().isEmpty());

        int projectId1 = 1;

        holder.getOrCreateProject(projectId1);

        assertEquals("size is 1", 1, holder.getProjectList().size());
        assertEquals("id of element 1 = $projectId1 ", projectId1, holder.getProjectList().get(0).getId());

        holder.getOrCreateProject(projectId1);
        assertEquals("size is 1", 1, holder.getProjectList().size());

        int projectId2 = 2;

        holder.getOrCreateProject(projectId2);
        assertEquals("size is 2", 2, holder.getProjectList().size());
        assertEquals("id of element 2 = $projectId2 ", projectId2, holder.getProjectList().get(1).getId());


    }
}
