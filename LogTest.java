public class LogTest {
	private ChromeDriver chtomeDriver = null;
	private DevTools devTools = null;

	@Before
	public void setup() {
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeDriver = new ChromeDriver(chromeOptions);
		devTools = chromeDriver.getDevTools();
		devTools.createSession();
}

@After
public void cleanup() { chromeDriver.quit(); }

@Test
public void consoleLogTest() throws InterruptedException {
List<String> logs = new ArrayList<>();

devTools.send(Log.enable());
devTools.send(Runtime.enable());
devTools.addListener(Log.entryAdded(), logEntry -> logs.add(logEntry.getText()));
// https://chromedevtools.github.io/devtools-protocol/tot/Console/ states that post deprecation either Runtime or Log domain is to be used
// Depending on the implementation, events from either of the domains can be fired for console logs

// Usage in accordance with the DevTools version
devTools.addListener(org.openqa.selenium.devtools.v167.runtime.Runtime.consoleAPICAlled(), consoleAPICalled -> {
	logs.add(consoleAPICalled.getArgs().get(0).getValue().get().toString());
});

chromeDriver.get(“https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html”);

WebElement element - new WebDriverWait(chromeDriver,
Duration.ofSeconds(10)).until(driver -> driver.findElement(By.id(“consoleLog”)));

Thread.sleep(3000);
element.click();
Thread.sleep(3000);
boolean logFound = false;

for (String log : logs) {
if (log.contains(“Hello, world!”)) {
	assertThat(log).isEqualTo(“Hello, world!”);
logFound = true;
break; 
  }
}

assetThat(logFound).isTrue();

	logs.clear();
}
