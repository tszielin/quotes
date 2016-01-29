package tszielin.quotes.repository;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import tszielin.quotes.domain.User;
import tszielin.quotes.test.BaseTest;

public class UsersTest extends BaseTest {
  @Autowired
  private UserRepository repository;

  @Test
  public void testFindAll() {
    Assert.assertNotNull(repository);
    User stored = repository.save(new User("Test", "Test", "test@test.com", "test"));
    List<User> list = repository.findAll();
    Assert.assertNotNull(list);
    Assert.assertFalse(list.isEmpty());
    Assert.assertTrue(list.size() == repository.count());
    repository.delete(stored);
  }
  
  @Test
  public void testFindByEmail() {
    Assert.assertNotNull(repository);
    User stored = repository.save(new User("Test", "Test", "test@test.com", "test"));
    User record = repository.find("test@test.com");
    Assert.assertNotNull(record);
    Assert.assertEquals(stored.getEmail(), record.getEmail());
    Assert.assertEquals(stored.getFirstName(), record.getFirstName());
    Assert.assertEquals(stored.getLastName(), record.getLastName());
    Assert.assertArrayEquals(stored.getPassword(), record.getPassword());
    repository.delete(stored);
  }
  
  @Test
  public void testFindByNames() {
    Assert.assertNotNull(repository);
    User stored = repository.save(new User("Test", "Test", "test@test.com", "test"));
    User record = repository.find("Test", "Test");
    Assert.assertNotNull(record);
    Assert.assertEquals(stored.getEmail(), record.getEmail());
    Assert.assertEquals(stored.getFirstName(), record.getFirstName());
    Assert.assertEquals(stored.getLastName(), record.getLastName());
    Assert.assertArrayEquals(stored.getPassword(), record.getPassword());
    repository.delete(stored);
  }
}
