import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class TestAPITest {

    private TestAPI testAPI;

    @BeforeEach
    public void setUp() {
        // Initialize the TestAPI instance with dummy credentials
        // You can enter your own credentials and try it out
        testAPI = new TestAPI("vedantg", "oqqNJaT8rZL0C3wuMTIlIhujqogN1yh5DB56pCNTSdO5b7bJjH");
    }

    @Test
    public void testUploadExceptionLogs() {
        String testId = "YAAS1-TG8LA-UZOVZ-2X3CU";
        List<String> exceptions = new ArrayList<>();
        exceptions.add("exception 1");
        exceptions.add("exception 2");
       // exceptions.add("expection 3");
        try {
            // Invoke the method being tested
            TestResponse response = testAPI.uploadExceptionLogs(testId, exceptions);

            // Assertions
            Assertions.assertNotNull(response);
            // Add more assertions to validate the response as needed
        } catch (HTTPException e) {
            e.printStackTrace();
            Assertions.fail("Exception occurred: " + e.getMessage());
        }
    }

    @Test
    public void testFetchRecordedVideo() {
        String testId = "YAAS1-TG8LA-UZOVZ-2X3CU";

        try {
            // Invoke the method being tested
            BuildIdResponse response = testAPI.fetchRecordedVideo(testId);

            // Assertions
            Assertions.assertNotNull(response);
            // Add more assertions to validate the response as needed
        } catch (HTTPException e) {
            e.printStackTrace();
            Assertions.fail("Exception occurred: " + e.getMessage());
        }
    }
}
