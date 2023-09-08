public class NetworkInterceptorTest {
	private ChromeDriver chromeDriver = null;
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
	public void networkInterceptorTest() throws IOException, InterruptedException {
		File fi = new File(“/Users/Lana/Downloads/smiley.webp”);
		Byte[] fileContent = Files.readAllBytes(fi.toPath());
	  
	NetworkInterceptor interceptor = new NetworkInterceptor(chromeDriver.
		Route.matching(req -> req.getUri().contains(“img.ehowcdn.com”) && req.getMethod() == HttpMethod.GET).to(() -> req -> new HttpResponse()
		.setStatus(200)
		.setHeader(“content-type”, “image/webp”)
		.setContent(Contents.bytes(fileContent))));
	  
	chromeDriver.get(“https://www.ehow.com”);
	  
	Thread.sleep(10000);
	  
	chromeDriver.quit();
	}
}
