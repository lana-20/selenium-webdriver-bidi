[UNDER CONSTRUCTION]


# Selenium WebDriver BiDi (WD BiDi) - BlackOps-Flavored Codified DevTools - Kismet Child of WD Classic and CDP

### Evolution of WD Classic
### DevTools Overview
### Chrome DevTools Protocol (CDP)
### WD Classic vs CDP
### WD BiDi and Its Advantages
### WD BiDi Implementation Status
### BiDi API Code Samples

----

### Evolution of WD Classic

WD Classic was created in 2005. It's been a 18+ year long journey.

#### WD Classic - History:

2004 -- Selenium RC
- Jason Huggins introduces the fist automation tool in 2004. RC worked by installing a remote control server in the browser machines. It sent a command to a JS engine called Selenium Core. And the Selenium Core, residing in the browser engine, executed that command. RC used to be quite popular, but came to a disadvantage because of its complex architecture. Moreover, its API was not purely object oriented.

2005 -- WebDriver
- Simon Stewart introduces the WedDriver protocol in 2005. The driver communicates to browsers via JSON wire protocol WD API.

2009 -- Selenium WebDriver
- Selenium and WebDriver merge into a new tool called Selenium WebDriver. JSON wire protocol is still used to communicate with the browsers.

2018 -- WD becomes a W3C standard
- W3C makes WD a standard specification. Now, Selenium WD communicates with the browsers via the W3C protocol, not JSON wire. Selenium v4 and other tools that use WD communicate via W3C protocol.
- The protocol was introduced to provide consistency and stability for test cases when using the WD.

#### WD Classic - Automation Tools:
- Selenium WD
    - Open-source suite of tools (ecosystem) for automating web apps.
    - Used for testing and simulating user interactions in the browsers.
- WebDriverIO
    - A test automation tool for web apps.
    - Offers a simple syntax and built-in commands.
    - Supports multiple browsers and devices for efficient and effective testing.
- NightWatchJS
    - An automated testing tool based on Node.js.
    - Offers a simple syntax and built-in WD support.
    - Supports E2E testing and browser automation.
- Appium
    - Open-source ecosystem for mobile automation.
    - Allows testing of native, hybrid and mobile web apps on iOS and Android.
    - Uses WD protocol.
- Katalon, SeleniumBase, etc.
    - Other tools use the Selenium WD under the hood.
    - E.g., Katalon uses the Selenium jar file in the back-end, but it still comminicates to the browsers via the web DevTools protocol.
 


----

### DevTools Overview

- Tools to test and debug code for web developers.
- When you develop a web app in an IDE, you are able to step through the code. But what if you want to debug the UI part of it? Let’s say you want to check the look and feel, e.g., verify that the correct styles were applied.
- This is where the DevTools come into play. They are integrated into all modern-day browsers, such as Chrome, Edge, Safari and Firefox.
- You can use DevTools to verify how your web app looks. I.e., you can view the HTML and DOM structure at a very high level and then drill down into details as required.
<img src="https://github.com/lana-20/selenium-webdriver-bidi/assets/70295997/2f481d75-8c32-4967-96e1-aa17bf8e5a68" width=680>

----

### Chrome DevTools Protocol (CDP) - Automation Tools

Playwright and Puppeteer use CDP to control Chromium-based browsers (Chrome, Edge, Opera) programmatically for web automation and testing purposes.

For example, in order to Click a button, WD Classic would identify the button element, move the mouse cursor over the element (it injects a mouse event) and perform the click action on that element. CDP simulates the analogous Click action on the button in a different way. CDP has a different implementation in the back-end, when we perform the Click action.

In short, CDP performs 3 actions in the back-end:

1. <code>DOM.performSearch</code> -- performs the search operation to identify the element
2. <code>DOM.querySelector</code> -- uses querySelect to select the element
3. <code>DOM.dispatchEvent</code> -- dispatches an event to simulate the Click action on that element

Here is a specific example of CDP implementation by Puppeteer:

    await page._client.send('DOM.performSearch', { query: buttonSelector });
    // id for search results
    await  page._client.send('DOM.performSearch', { query: ' ', searchId });
    await  page._client.send('Input.dispatchMouseEvent', { type: 'mousePressed', ... });
    await  page._client.send('Input.dispatchMouseEvent', { type: 'mouseReleased', ... });




----

### WD Classic vs CDP

In recent years, CDP has been a big hype in the automation industry.

----

### WD BiDi and Its Advantages

Once we have a solid understanding of the WD Classic and CDP, it's easier to understand why BiDi was created and came into play.

<img src="https://github.com/lana-20/selenium-webdriver-bidi/assets/70295997/0b1181b6-af35-4950-b678-33217172a02c" width=460>

WD BiDi is a cross-browser automation protocol. It's an open standard that works across browsers, fast by default, and comes packed with all the features you need for test automation. How? It takes the best of <code>Chrome Dev Tools</code> (e.g., fast bidirectional messaging & low level control) and <code>Classic WebDriver</code> (e.g., best x-browser support, W3C Standard, testing-oriented), and combines them into the extraordinary WebDriver BiDi protocol. The vision behind BiDi is to give you full flexibility and let you write tests using any of your favorite tools and automate them in any browser or driver.

The WebDriver BiDi collaborators are a diverse group. It includes:

- Browser Vendors: Chrome, Edge, Firefox, Safari
- Open Source Automation Projects: Selenium, WebDriverIO
- Companies offering Browser Automation Solutions: BrowserStack, SauceLabs.

This is certainly an exciting future for test automation. It takes a huge effort from various vendors working together to ensure this future.

Selenium, WebDriverIO, Puppeteer and Playwright have already introduced initial support for WebDriver BiDi.

One of the main features is Low Level Control. I.e., monitoring console messages to help verify the uncaught exceptions and intended logs. There's also full interoperability between WebDriver Classic and BiDi.

WD BiDi is undoubtedly the future of browser automation!
____

### WD BiDi Implementation Status

- BiDi specification is a WIP
- A few [modules](https://www.selenium.dev/documentation/webdriver/bidirectional/bidirectional_w3c/) have already been implemented for Selenium and WebDriverIO

WD BiDi is still a work in progress, it's not been fully implemented yet. It's been under development for the past 2 years. A few features are currently available.

![image](https://github.com/lana-20/selenium-webdriver-bidi/assets/70295997/e205ab4d-a0b6-4775-a18b-d316ab0a1ca7)

Domains/Modules:
- <code>session/</code> - is a basic module where you can start a BiDi session
- <code>script/</code> - use this module to inject JS code when using WD BiDi
- <code>network/</code> - a module where you can perform network interception
- <code>log/</code> - can read actual console or JS logs
- <code>input/</code> - events that happen when, for example, you submit a form. WD BiDi supports input events such as the keyboard strokes you perform.
- <code>errors/</code> and - <code>browsing_context/</code> are high level modules

We can understand the content by examining the domain. When we open the <code>browsing_context/</code> module, we observer various sub-modules such as:
- <code>capture_screenshot/</code>
- <code>classic_interop/</code>
- <code>close/</code>
- <code>context-created/</code>
- <code>create/</code>
- <code>dom_content_loaded/</code>
- <code>fragment_navigated/</code>
- <code>get_tree/</code>
- <code>load/</code>
- <code>navigate/</code>
- <code>print/</code>
- <code>reload/</code>
- <code>set_viewport/</code>
We can capture a screenshot, open a new browser, close it, create a new tab/window, navigate to a URL, print pages, or save to PDF, etc.


![image](https://github.com/lana-20/selenium-webdriver-bidi/assets/70295997/3a498c85-59bd-4649-b357-ab65c8c0a4f7)


----

### WebDriver Bidi (BiDirectional) Protocol - Code Samples

#### Sample 1 - Register Basic Authentication - Java

Basic Authentication is a common way to safeguard your resources on the internet.
If you request something from the server, the request goes to the browser, the browser passes it to the server. The request is basically stating “I want authentication” and yields a dialogue box with username and password fields to fill. Once you enter the basic username and password, you’ll be able to easily access the resources. It’s a rather straightforward concept. 

We’ll use AUT: http://httpbin.org/basic-auth/foo/bar
It shows you a small prompt, a dialogue box asking for the username and password. When you enter username “foo” and password “bar”, you’ll get correctly authenticated.
    
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

____

References:
- https://developer.chrome.com/articles/webdriver-bidi/





