package tszielin.quotes.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserTest {        
    @Test
    public void testUser() {
        User user = new User("Test", "Test", "test@test.com", new BCryptPasswordEncoder().encode("test"));
        assertNotNull(user);
        assertNotNull(user.getFirstName());
        assertEquals("Test", user.getFirstName());
        assertNotNull(user.getLastName());
        assertEquals("Test", user.getLastName());
        assertNotNull(user.getEmail());
        assertEquals("test@test.com", user.getEmail());
        assertNotNull(user.getPassword());
        assertTrue(new BCryptPasswordEncoder().matches("test", String.valueOf(user.getPassword())));
    }
}
