import java.util.List;

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
    private List<String> tags;

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

    public List<String> getTags(){return tags;}
}
