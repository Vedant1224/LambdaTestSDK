/**
 * The BuildIdResponse class represents a response containing one builds.
 * Built for the getSpecificBuildData method
 */
class BuildIdResponse {
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

/**
 * The BuildIdResponse class represents a response containing several builds.
 * Built for the fetchAllBuilds method
 */
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

/**
 * Represents a single build response.
 */
public class Response {
    private int build_id;
    private String name;
    private int user_id;
    private String username;
    private String status_ind;
    private String create_timestamp;
    private String end_timestamp;

    /**
     * Gets the build ID.
     * @return build ID
     */
    public int getBuildId() {
        return this.build_id;
    }

    /**
     * Gets the user ID.
     * @return user ID
     */
    public int getUserId() {
        return this.user_id;
    }

    /**
     * Gets the build name.
     * @return build name
     */
    public String getBuildName() {
        return this.name;
    }

    /**
     * Gets the username.
     * @return username
     */
    public String getUserName() {
        return this.username;
    }

    /**
     * Gets the status ID.
     * @return status ID
     */
    public String getStatusId() {
        return this.status_ind;
    }

    /**
     * Gets the  timestamp (at time of creation).
     *
     * @return timestamp
     */
    public String getCreateTimestamp() {
        return this.create_timestamp;
    }

    /**
     * Gets the timestamp (at end time).
     *
     * @return timestamp
     */
    public String getEndTimestamp() {
        return this.end_timestamp;
    }
}

class TestResponse{
    public boolean data;
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
