package co.startupservice.bebold.business;

public  abstract class User {

    private String username;
    private String password;
    private Long id;


    /**
     * User class constructor method
     * @param username User name
     * @param password User password
     * @param id User identifier
     */
    public User(String username, String password, Long id) {
        this.username = username;
        this.password = password;
        this.id = id;
    }

    /**
     * Method for update password
     * @param password
     * @return
     */
    public boolean updatePassword(String password)
    {
        this.password = password;
        return true;
    }

    @Override
    public String toString()
    {
        return this.username + " " + this.password + " " + this.getClass().getName();
    }
}
