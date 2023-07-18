/**
 * A class to store all the constants common to all the API
 * Class also includes fields that store error messages
 */
public class ApiConstants {

    public static final String HEADER_ACCEPT = "accept";
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String HEADER_CONTENT_TYPE = "Content-Type";
    public static final String BASE_URL = "https://api.lambdatest.com/";
    public static final String BASIC = "Basic ";
    public static final String APPLICATION_JSON = "application/json";
    public static final int REQUEST_TIMEOUT = 10;
    public static final String VALIDATION_EXCEPTION_MESSAGE = "Value must be greater than 0";
    public static final int DATE_DIFFERENCE = 90;
    public static final String HTTP_EXCEPTION_MESSAGE = "Connection Unsuccessful. Status Code: ";
    public static final String DATE_EXCEPTION_MESSAGE = "Difference between fromDate and toDate cannot exceed 90 days.";
    public static final String ERROR_500_DESCRIPTION = "The server encountered an unexpected condition or error that prevented it from fulfilling the request. This error is typically caused by a problem on the server-side. Clients receiving a 500 error should retry the request at a later time or contact the server administrators for assistance.";


    public static final String HEADER_VIDEO = "video";
    public static final String HEADER_EXCEPTIONS = "exceptions";
    public static final String TUNNEL_ENDPOINT = "automation/api/v1/tunnels/";
    public static final String PLATFORM_ENDPOINT = "automation/api/v1/platforms";

    public static final String ORGANISATION_ENDPOINT = "automation/api/v1/org/concurrency";

    public static final String RESOLUTION_ENDPOINT = "automation/api/v1/resolutions";

    public static final String SESSION_ENDPOINT = "automation/api/v1/sessions";
}

