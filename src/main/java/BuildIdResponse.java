import java.util.List;
/**
 * The BuildIdResponse class represents a response containing one builds.
 * Built for the getSpecificBuildData method
 */
public class BuildIdResponse {
    private Response data;
    private String message;
    private String status;

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
     * Get the data field
     * @return
     */
    public Response getData() {return data;}
}

class BuildIdResponses {
    private Meta meta;
    private Response[] data;
    private String message;
    private String status;

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

    public Response[] getData() {return data;}

    public Meta getMeta() {return meta;}
}

class Meta{
    private Attributes attributes;
    private ResultSet result_set;
}

class Attributes{
    private int org_id;

    public int getOrgId() {return org_id;}
}

class ResultSet{
    private int count;
    private int limit;
    private int offset;
    private int total;

    public int getCount() {return count;}
    public int getLimit() {return limit;}

    public int getOffset() {return offset;}
    public int getTotal() {return total;}
}


