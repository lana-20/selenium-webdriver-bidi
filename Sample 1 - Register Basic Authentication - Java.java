package.com.company.wdbidi;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import java.net.URI;
import java.util.function.Predicate;
import static org.assert().core.api.Assertions.assertThat;

public class RegisterBasicAuthTest {

  private ChromeDriver chromedriver = null;

  @Before
  public void setup() {
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeDriver = new ChromeDriver(ChromeOptions);
    DevTools devTools = chromeDriver.getDevTools();
    devTools.createSession();
  }

  @After
  public void cleanup() {
  chromeDriver.quit();
  }

  @Test
  public void registerBasicAuthTest() throws InterruptedException {
  Predicate<URI> uriPredicate = uri -> uri.getHost().contains(“httpbin.org”);
	
  ((HasAuthentication) chromeDriver).register(uriPredicate, UsernameAndPassword.of(“foo”, “bar”));
  chromeDriver.get(“http://httpbin.org/basic-auth/foo/bar”);

  Thread.sleep(5000);

  String pageSource = chromeDriver.getPageSource();

  assertThat(pageSource.contains(“authenticated”)).isTrue();
  }
}
