/**
 * The BuildIdResponse class represents a response containing one builds.
 * Built for the getSpecificBuildData method
 */
public class BuildIdResponse {
    public Response data;
    public String message;
    public String status;

    /**
     * Gets the message associated with the response.
     * @return message
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Gets the status of the response.
     * @return status
     */
    public String getStatus() {
        return this.status;
    }
}

class BuildIdResponses {
    public Response[] data;
    public String message;
    public String status;

    /**
     * Gets the message associated with the response.
     * @return message
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Gets the status of the response.
     * @return status
     */
    public String getStatus() {
        return this.status;
    }
}
