ChromeDriver driver = new ChromeDriver();

DevTools devTools = driver.getDevTools();

devTools.createSession();

devTools.send(
Emulation.setGeolocationOverride(
Optional.of(47.604653),
Optional.of(-122.335461),
Optional.of(1)));

driver.get(“https://my-location.org/”);
driver.quit();
