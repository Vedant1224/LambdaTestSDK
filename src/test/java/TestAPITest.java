import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class TestAPITest {

    private TestAPI testAPI;
    private String testId;

    @BeforeEach
    public void setUp() {
        // Initialize the TestAPI instance with dummy credentials
        // You can enter your own credentials and try it out
        testAPI = new TestAPI("", "");
        // You can enter your own testId
        testId = "";
    }

    @Test
    public void testUploadExceptionLogs() {
        List<String> exceptions = new ArrayList<>();
        exceptions.add("exception 1");
        exceptions.add("exception 2");
        exceptions.add("exception 3");
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

        try {
            // Invoke the method being tested
            TestResponse response = testAPI.fetchRecordedVideo(testId);
            System.out.println(response);

            // Assertions
            Assertions.assertNotNull(response);
            // Add more assertions to validate the response as needed
        } catch (HTTPException e) {
            e.printStackTrace();
            Assertions.fail("Exception occurred: " + e.getMessage());
        }
    }
}
