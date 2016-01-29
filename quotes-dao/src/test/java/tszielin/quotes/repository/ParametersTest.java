package tszielin.quotes.repository;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import tszielin.quotes.domain.NBPParameter;
import tszielin.quotes.repository.NBPParameterRepository;
import tszielin.quotes.test.BaseTest;

public class ParametersTest extends BaseTest {
  @Autowired
  private NBPParameterRepository repository;

  @Test
  public void testFindAll() {
    Assert.assertNotNull(repository);
    NBPParameter stored = repository.save(new NBPParameter());
    List<NBPParameter> list = repository.findAll();
    Assert.assertNotNull(list);
    Assert.assertFalse(list.isEmpty());
    Assert.assertTrue(list.size() == repository.count());
    repository.delete(stored);
  }
}
