package tszielin.quotes.domain;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * User's information.
 * @author Thomas Zielinski
 */
@Document(collection = "users")
public class User extends Base {
    private static final long serialVersionUID = 3659953000594163629L;
    private String firstName;
    private String lastName;
    private String email;
    private char[] password;

    /**
     * Create an object
     */
    protected User() {
    }

    /**
     * Create an {@code User} object
     * @param firstName the first name
     * @param lastName the last name
     * @param email the e-mail address
     * @param password the user's password
     */
    public User(String firstName, String lastName, String email, String password) {
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        this.password = new BCryptPasswordEncoder().encode(password).toCharArray();
    }

    /**
     * Set first name
     * @param firstName the first name
     */
    protected void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Set last name
     * @param lastName the last name
     */
    protected void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Set an e-mail address
     * @param email the e-mail address
     */
    protected void setEmail(String email) {
        this.email = email;
    }

    /**
     * Set an user's password
     * @param password the user's password
     */
    protected void setPassword(String password) {
        setPassword(password == null ? new char[0] : password.toCharArray());
    }

    /**
     * Set a user's password
     * @param password the user's password
     */
    protected void setPassword(char[] password) {
        this.password = password;
    }

    /**
     * Get a first name
     * @return the first name
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Get a last name
     * @return the last name
     */    
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Get an e-mail address
     * @return the e-mail address
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Get an user's password
     * @return the user's password
     */
    public char[] getPassword() {
        return this.password;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((this.email == null) ? 0 : this.email.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User)obj;
        if (this.email == null) {
            if (other.email != null)
                return false;
        } else if (!this.email.equals(other.email))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "firstName=" + this.firstName + ", lastName=" + this.lastName + ", email=" + this.email;
    }
}
