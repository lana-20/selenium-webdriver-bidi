@Test
public void javaScriptErrorLogTest() throws InterruptedException {
  List<JavaScriptException> jsExceptionList = new ArrayLust<>();
    
  devTools.getDomains().events().addJavaScriptExceptionListener(jsExceptionsList::add);
    
  chromeDriver.get(“https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html”);
    	
  WebElement element = new WebDriverWait(chromeDriver,
    Duration.ofSeconds(10)).until(driver -> driver.findElement(By.id(“jsException”)));
     
  Thread.sleep(3000);
  element.click();
  Thread.sleep(3000);
    
  assertThat(jsExceptionsList.size()).isEqualTo(1);
  assertThat(jsExceptionsList.get(0).getMessage().contains(“Error: Not working”)).isTrue();
    
  jsExceptionsList.clear();
}
