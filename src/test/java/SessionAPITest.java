import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SessionAPITest {

    private SessionAPI sessionAPI;
    private String sessionId;

    @BeforeEach
    public void setUp() {
        // Initialize the SessionAPI instance with dummy credentials
        // You can enter your own credentials and try it out
        sessionAPI = new SessionAPI("vedantg", "oqqNJaT8rZL0C3wuMTIlIhujqogN1yh5DB56pCNTSdO5b7bJjH");
        // You can enter your own sessionId
        sessionId = "YAAS1-TG8LA-UZOVZ-2X3CU";
    }

    @Test
    public void testGetSessionDetails() {
        try {
            // Invoke the method being tested
            SessionResponse response = sessionAPI.getSessionDetails(sessionId);

            // Assertions
            Assertions.assertNotNull(response);
            // Add more assertions to validate the response as needed
        } catch (HTTPException e) {
            e.printStackTrace();
            Assertions.fail("Exception occurred: " + e.getMessage());
        }
    }

    @Test
    public void testDeleteSession() {
        try {
            // Invoke the method being tested
            SessionResponse response = sessionAPI.deleteSession(sessionId);

            // Assertions
            Assertions.assertNotNull(response);
            // Add more assertions to validate the response as needed
        } catch (HTTPException e) {
            e.printStackTrace();
            Assertions.fail("Exception occurred: " + e.getMessage());
        }
    }

    @Test
    public void testPatchName() {
        String newName = "New Name";
        String newStatus = "completed";

        try {
            // Invoke the method being tested
            SessionResponse response = sessionAPI.patchName(sessionId, newName, newStatus);

            // Assertions
            Assertions.assertNotNull(response);
            // Add more assertions to validate the response as needed
        } catch (ValidationException | HTTPException e) {
            e.printStackTrace();
            Assertions.fail("Exception occurred: " + e.getMessage());
        }
    }

    @Test
    public void testStopSession() {
        try {
            // Invoke the method being tested
            SessionResponse response = sessionAPI.stopSession(sessionId);

            // Assertions
            Assertions.assertNotNull(response);
            // Add more assertions to validate the response as needed
        } catch (HTTPException e) {
            e.printStackTrace();
            Assertions.fail("Exception occurred: " + e.getMessage());
        }
    }
}
