import java.util.Base64;

/**
 * Credentials class represents a user's login credentials.
 */
public class Credentials {
    private String userName;
    private String password;

    /**
     * Constructor to construct a new Credentials object.
     *
     * @param userName the user's username
     * @param password the user's password
     */
    public Credentials(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    /**
     * Gets the user's username.
     *
     * @return username
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * Gets the user's password.
     *
     * @return password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Sets the user's password.
     *
     * @param password the new password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets the user's username.
     *
     * @param userName the new password to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Generates an encoded key using the user's username and password.
     *
     * @return key as a String
     */
    public String generateKey() {
        String key = getUserName() + ":" + getPassword();
        byte[] encodedBytes = Base64.getEncoder().encode(key.getBytes());
        return new String(encodedBytes);
    }
}
