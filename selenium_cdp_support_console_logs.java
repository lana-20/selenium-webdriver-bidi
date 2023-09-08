ChromeDriver driver = new ChromeDriver();
DevTools devTools = driver.getDevTools();
devTools.createSession();
devTools.send(Log.enable());
devTools.addListener(Log.entryAdded(),
		     logEntry -> (
			     System.out.println(“log: ” + logEntry.getText());
			     System.out.println(“level: ” + logEntry.getLevel());
			     ));
driver.get(“http://example.com”);
// Check the terminal output for the browser console messages.
driver.quit();
