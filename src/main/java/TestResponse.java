public class TestResponse {
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

    /**
     * Gets the data associated with the response.
     * @return data
     */
    public boolean getData() {
        return this.data;
    }
}
