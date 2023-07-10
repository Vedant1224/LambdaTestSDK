import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Junit Class
 */
public class BuildAPITest {

    private BuildAPI buildAPI;
    private static final int buildId = 15869773;// Enter a Build ID

    @BeforeEach
    public void setUp() {
        // Initialize the BuildAPI instance with dummy credentials
        // You can enter you own credentials and try it out
        buildAPI = new BuildAPI("vedantg", "oqqNJaT8rZL0C3wuMTIlIhujqogN1yh5DB56pCNTSdO5b7bJjH");
    }

    /**
     * Test case for the getSpecificBuildData method.
     */
    @Test
    public void testGetSpecificBuildData() {
        try {
            // Invoke the method being tested
            BuildIdResponse response = buildAPI.getSpecificBuildData(buildId);
            // Assertions
            Assertions.assertNotNull(response.data);
            Assertions.assertEquals(buildId, response.data.getBuildId());
            // Add more assertions to validate the response as needed
        } catch ( ValidationException | HTTPException e) {
            e.printStackTrace();
            Assertions.fail("Exception occurred: " + e.getMessage());
        }
    }

    /**
     * Test case for the deleteBuild method.
     */
    @Test
    public void testDeleteBuild() {
        try {
            // Invoke the method being tested
            BuildIdResponse response = buildAPI.deleteBuild(buildId);

            // Assertions
            Assertions.assertNotNull(response);
            // Add more assertions to validate the response as needed
        } catch (ValidationException | HTTPException e) {
            e.printStackTrace();
            Assertions.fail("Exception occurred: " + e.getMessage());
        }
    }

    /**
     * Test case for the patchName method.
     */
    @Test
    public void testPatchName() {
        // Test case setup
        String newName = "Test";
        String status = "Passed";

        try {
            // Invoke the method being tested
            BuildIdResponse response = buildAPI.patchName(buildId, newName, status);

            // Assertions
            Assertions.assertNotNull(response);
            // Add more assertions to validate the response as needed
        } catch (ValidationException | HTTPException e) {
            e.printStackTrace();
            Assertions.fail("Exception occurred: " + e.getMessage());
        }
    }

    /**
     * Test case for the fetchAllBuilds method.
     */
    @Test
    public void testFetchAllBuilds() {
        // Test case setup
        int offset = 0;
        int limit = 3;// Enter the number of builds you want to retrieve
        String status = "passed"; // Please provide a status from valid status options
        String fromDate = "2023-06-01"; // Please provide create date in YYYY-MM-DD
        String toDate = "2023-06-27"; // Please provide end date in YYYY-MM-DD
        String sort = ""; // Please provide sort from valid options

        try {
            // Invoke the method being tested
            BuildIdResponses responses = buildAPI.fetchAllBuilds(offset, limit, status, fromDate, toDate, sort);

            // Assertions
            Assertions.assertNotNull(responses);
            // Add more assertions to validate the response as needed
        } catch (ValidationException | HTTPException e) {
            e.printStackTrace();
            Assertions.fail("Exception occurred: " + e.getMessage());
        }
    }
}
