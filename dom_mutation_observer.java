import java.time.Duration;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.devtools.events.CdpEventTypes.domMutation;

public class DomMutationObserverTest {
  private ChromeDriver chromeDriver = null;
  private DevTools devTools = null;
  
  @Before
	public void setup() {
    ChromeOptions chromeOptions = new chromeOptions();
    chromeDriver = new ChromeDriver(chromeOptions);
    devTools = chromeDriver.getDevTools();
    devTools.createSession();
  }
  
  @After
  public void cleanup() { chromedriver.quit(); }
  
  @Test
  public void domMutationTest() throws InterruptedException {
    CountDownLatch latch = new CountDownLatch(1);
    ((HasLogEvents) chromeDriver).onLogEvent(domMutation(mutation -> latch.countDown())
    );
  }
}
