// full code: https://github.com/SeleniumHQ/seleniumhq.github.io/blob/313d31cc3e4948ac1b43bf86e910cc1138547fca/examples/java/src/test/java/dev/selenium/bidi/cdp/NetworkTest.java
...
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.NetworkInterceptor;
import org.openqa.selenium.devtools.v131.browser.Browser;
import org.openqa.selenium.devtools.v131.network.Network;
import org.openqa.selenium.devtools.v131.performance.Performance;
import org.openqa.selenium.devtools.v131.performance.model.Metric;
...

public class NetworkTest extends BaseTest {
...
    public void setCookie() {
    // initialize ChromeDriver
    ChromeDriver driver = new ChromeDriver();
    // get a DevTools instance
    DevTools devTools = driver.getDevTools();
    // set up WebSocket connection (handshake)
    devTools.createSession();
    
    // send a command
    devTools.send(
              Network.setCookie(
                      "cheese",
                      "gouda",
                      Optional.empty(),
                      Optional.of("www.selenium.dev"),
                      Optional.empty(),
                      Optional.of(true),
                      Optional.empty(),
                      Optional.empty(),
                      Optional.empty(),
                      Optional.empty(),
                      Optional.empty(),
                      Optional.empty(),
                      Optional.empty(),
                      Optional.empty()));
    
    // use WD Classic commands to go to a particular URL, verify the results, and quit the session
    driver.get("https://www.selenium.dev");
    Cookie cheese = driver.manage().getCookieNamed("cheese");
    Assertions.assertEquals("gouda", cheese.getValue());
    driver.quit();
    }
...
}
