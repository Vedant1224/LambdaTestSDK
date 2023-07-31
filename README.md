# LambdaTestSDK
SDK For LambdaTest

# Description 
The LambdaTestSDK is a Java SDK designed to interact with LambdaTest's automation API and allow data from API calls to be stored as Java objects. This enables easy access to specific elements within the API responses, providing a structured and convenient way to work with the data.

# Installation
To use the LambdaTestSDK in your Java project, you can include it as a dependency in your project's pom.xml file.

# Usage
To use the LambdaTestSDK in your Java project, you can follow these steps:

1.Add the LambdaTestSDK dependency to your pom.xml file as shown in the installation section.

2.Import the necessary classes from the SDK into your Java files.

3.Create an instance of the relevant API class (e.g., ExtensionsAPI, UserFilesAPI) by providing your LambdaTest credentials.

4.Call the methods of the API class to interact with LambdaTest's automation API and handle the API responses as Java objects.

## Example Code

Below is an example code snippet that demonstrates how to use the LambdaTestSDK to change the name of a build:

```java
 public static void main(String[] args) {
        // Replace the empty strings with your actual username and password
        String userName = "";
        String password = "";

        BuildAPI buildAPI = new BuildAPI(userName, password);
        try {
            // Replace the buildId with the ID of the specific build you want to update
            int buildId = 16648943;

            // Update the name and/or status of the build using patchName() method
            BuildIdResponse buildResponse = buildAPI.getSpecificBuildData(buildId);

            // Access the Response data from the buildResponse, if needed
            Response buildData = buildResponse.getData();

            // Print the updated build data or any other information you want
            System.out.println("Build ID: " + buildData.getBuildId());
            System.out.println("Build Name: " + buildData.getBuildName());
            System.out.println("User ID: " + buildData.getUserId());
            System.out.println("Username: " + buildData.getUserName());
            System.out.println("Status ID: " + buildData.getStatusId());
            System.out.println("Create Timestamp: " + buildData.getCreateTimestamp());
            System.out.println("End Timestamp: " + buildData.getEndTimestamp());
            System.out.println("Tags: " + buildData.getTags());

            // You can access other attributes of Response as well, if needed
        } catch (ValidationException | HTTPException e) {
            e.printStackTrace();
        }
    }
```

# LambdaTest API Documentation

Version: 1.0.1

[OpenAPI Specification (OAS3) - openapi.yaml](https://api.lambdatest.com/automation/api/v1)

## Servers

- https://api.lambdatest.com/automation/api/v1

## Authorize

No specific API endpoints under this section.

## Build

- GET /builds: Fetch all builds of an account. This endpoint allows you to retrieve a list of all builds associated with your LambdaTest account.
- GET /builds/{build_id}: Fetch specified build details. Use this endpoint to get detailed information about a specific build identified by its build ID.
- DELETE /builds/{build_id}: Delete Build. This endpoint lets you delete a specific build using its build ID.
- PATCH /builds/{build_id}: Update Build Name or Status. You can use this endpoint to update the name or status of a specific build identified by its build ID.
- PUT /build/stop: Stop tests by BuildID. This endpoint allows you to stop ongoing tests associated with a specific build using its BuildID.

## Session

- GET /sessions: Fetch list of all sessions. Use this endpoint to retrieve a list of all test sessions recorded in your LambdaTest account.
- GET /sessions/{session_id}: Session specific information. Get detailed information about a specific test session using its unique session ID.
- DELETE /sessions/{session_id}: Delete test session. This endpoint enables you to delete a specific test session using its unique session ID.
- PATCH /sessions/{session_id}: Update session name and status. Update the name or status of a specific test session using its unique session ID.
- PUT /sessions/{session_id}/stop: Stop session by sessionID. This endpoint allows you to stop an ongoing test session using its unique session ID.

## Test

- POST /tests/{test_id}/exceptions: Upload assertion logs to our lambda storage. Use this endpoint to upload assertion logs related to a specific test using its unique test ID.
- GET /test/{test_id}/video: Fetch recorded video of a test id. Retrieve the recorded video of a specific test using its unique test ID.

## Tunnel

- GET /tunnels: Fetch running tunnels of your account. Use this endpoint to retrieve a list of running tunnels associated with your LambdaTest account.
- DELETE /tunnels/{tunnel_id}: Stop a running tunnel. Stop a specific running tunnel using its unique tunnel ID.

## Platforms

- GET /platforms: Fetch platforms. Retrieve information about the available platforms supported by LambdaTest.

## PreRun

- GET /files: Fetch all pre run files uploaded by the user. Get a list of all pre-run files uploaded by the user.
- POST /files: Upload pre run executable file to our lambda storage. Upload a pre-run executable file to LambdaTest storage.
- DELETE /files/delete: Delete pre run from our lambda storage. Delete a pre-run file from LambdaTest storage.
- POST /files/validate: Check if the file is approved by Lambdatest. Check if a file is approved by LambdaTest.
- PUT /files/download: Download pre run executable file. Download a pre-run executable file from LambdaTest storage.

## User Files

- GET /user-files: Fetch all user files uploaded by the user. Retrieve a list of all user files uploaded by the user.
- POST /user-files: Upload files to our lambda storage. Upload files to LambdaTest storage.
- DELETE /user-files/delete: Delete user files from our lambda storage. Delete user files from LambdaTest storage.
- PUT /user-files/download: Download user file from lambda storage. Download a user file from LambdaTest storage.

## Lighthouse

- GET /lighthouse/report/{session_id}: To fetch the Lighthouse performance report data. Retrieve the Lighthouse performance report data for a specific session.

## Organization

- GET /org/concurrency: Get organisation concurrency. Get information about the organization's concurrency.

## Project

- GET /projects: Get a list of Projects. Retrieve a list of all projects associated with your LambdaTest account.
- POST /project: Create a Project at Lambdatest. Create a new project at LambdaTest.
- GET /project/{id}: Get the details of a particular Project. Retrieve detailed information about a specific project using its unique ID.
- PUT /project/{id}: Update a Created Project at Lambdatest. Update the details of an existing project using its unique ID.

## Extensions

- GET /files/extensions: Fetch all extensions uploaded by the user. Retrieve a list of all extensions uploaded by the user.
- POST /files/extensions: Upload extensions in zip format to our lambda storage. Upload extensions in zip format to LambdaTest storage.
- DELETE /files/extensions/delete: Delete extension from our lambda storage. Delete an extension from LambdaTest storage.

## Resolution

- GET /resolutions: Get Resolutions of Platforms.


