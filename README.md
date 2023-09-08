# Selenium WebDriver BiDi (WD BiDi) - BlackOps-Flavored Codified DevTools - Kismet Child of WebDriver Classic (WD Classic) and Chrome DevTools Protocol (CDP)

Agenda:

### [Evolution of WD Classic](https://github.com/lana-20/selenium-webdriver-bidi/blob/main/README.md#evolution-of-wd-classic-1)
### [Evolution of CDP](https://github.com/lana-20/selenium-webdriver-bidi/blob/main/README.md#evolution-of-cdp-1)
### [WD Classic vs CDP](https://github.com/lana-20/selenium-webdriver-bidi/blob/main/README.md#wd-classic-vs-cdp-1)
### [WD BiDi and Its Advantages](https://github.com/lana-20/selenium-webdriver-bidi/blob/main/README.md#wd-bidi---advantages)
### [WD BiDi Implementation Status](https://github.com/lana-20/selenium-webdriver-bidi/blob/main/README.md#wd-bidi-implementation-status-1)
### [BiDi API Code Samples](https://github.com/lana-20/selenium-webdriver-bidi/blob/main/README.md#webdriver-bidi-bidirectional-protocol---code-samples)

----

### Evolution of WD Classic

WD Classic was created in 2005. It's been a 18+ year long journey.

#### WD Classic - History:

<img src="https://github.com/lana-20/selenium-webdriver-bidi/assets/70295997/49b625d8-03ef-4e8d-850e-c51610257c90" width=880>

We need to understand the evolution of WD Classic for easier understanding of the WD BiDi concept.

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

### Evolution of CDP

#### DevTools Overview

- Tools to test and debug code for web developers.
- When you develop a web app in an IDE, you are able to step through the code. But what if you want to debug the UI part of it? Let’s say you want to check the look and feel, e.g., verify that the correct styles were applied.
- This is where the DevTools come into play. They are integrated into all modern-day browsers, such as Chrome, Edge, Safari and Firefox.
- You can use DevTools to verify how your web app looks. I.e., you can view the HTML and DOM structure at a very high level and then drill down into details as required.
<img src="https://github.com/lana-20/selenium-webdriver-bidi/assets/70295997/2f481d75-8c32-4967-96e1-aa17bf8e5a68" width=680>

#### CDP Definition

Need for protocol:
- Automation support
- Extended debuggability
- Consistency

Why do we need this protocol in the first place? It’s in the browser, the browser understands everything. Why do we need a protocol?

Essentially, the need for a protocol arises in the context of a contract between two parties which communicate with each other. Any entity can act as such a party to the contract. The parties need to be aware of the common shared semantics, what response they receive, as well as the format of the response. The receiver can understand the content they receive, and vice versa - the receiver responds back to the sender, and the sender can interpret what they receive. This forms the foundation of any global protocol that exists.

CDP exists to provide the capacity to communicate between the DevTools and the browser, and vice versa.
E.g.: You have a Chrome browser. It has all the information about any event that happens – when DOM is loaded, network requests are being fired, or even performance-related information, when we download a resource or get more resources. But the browser needs to pipe this information through, to communicate it to the DevTools interface, so that we can actually verify that it’s there. This communication between the browser and the DevTools happens using CDP. It’s a common language they both understand. 

That’s why we need a Protocol. It provides consistency for all Chromium-based browsers, like Edge or Chrome – they understand CDP and know how to work with it. And hence the browsers have the DevTools window available. 

Additionally, since CDP is able to communicate with a browser driver - e.g., the Chrome Driver - the same protocol is used for the driver, which makes it easier to use it for automation purposes as well. Chrome driver is a binary that we use for testing purposes, which knows how to talk to the browser and drive it. 

#### CDP Structure

Concepts:
- Domains
- Commands
- Events

CDP is divided into Domains, Commands, and Events. In the OOP analogy, we have classes which group the related methods and properties together. Similarly, a Domain is a special logical grouping of all the Commands and Events that keeps them all in one place. That is how it’s structured.

Commands give instructions to the browser to do something. We give it a command, instruct it to perform a certain action, or instruct it to retrieve certain data.

An Event is a signal that something has happened in the system. Browsers are intrinsically even-driven in nature. When the DOM is loaded, there are lots of events that are fired, and the browser picks these up and uses this information in the DevTools window. DOM is just one example. The Protocol avails lots of events which the browser is driven by.

Domains, Commands, and Events are the three core concepts of CDP. 

#### Selenium CDP Support

<img src="https://github.com/lana-20/selenium-webdriver-bidi/assets/70295997/d336cd7e-5f83-4077-b16d-358a30eb904b" width=680>

First, ChromeDriver is getting initialized. A simple session is created with the ChromeDriver.

        ChromeDriver driver = new ChromeDriver();

We get the DevTools instance (for later use).

        DevTools devTools = driver.getDevTools();

Then we create a session - that is our initial WebSocket handshake connection that is established. So when you do createSession, the WebSocket connection is established. And there are certain Domains, Events, and Commands that would need to be called for that handshake - that’s what happens in the background as part of it.

        devTools.createSession();

Later we move on to actually sending the commands. 
When we do devTools.send(), we are sending the command.

        devTools.send(
        Emulation.setGeolocationOverride(
        Optional.of(47.604653),
        Optional.of(-122.335461),
        Optional.of(1)));

<img src="https://github.com/lana-20/selenium-webdriver-bidi/assets/70295997/722c4edc-dec4-495e-9232-76119565a4bf" width=680>

After that, we can use Selenium commands to go to a particular URL and verify that the location has changed.

        driver.get(“https://my-location.org/”);
        driver.quit();

#### Geolocation Testing 

Geolocation Testing - a type of testing when we perform tests on our web app from IPs from various countries worldwide. Here are some features that can be tested with geolocation browser testing:

- Geofencing - it’s a method that sends notifications to our users through mobile devices by leveraging the geographical area specified by the user, while using our web app. E.g., when I traveled to Vancouver, Canada, I received a notification from my booking app regarding local places to visit.
- Geotagging - allows us to put a geographical tag over social media elements, such as photos, videos, QR codes, etc. Using geotagging, we can implement geographically-based authentication through QR code identification on our web app.
- Geoblocking - it’s used to apply restrictions to our web app, concerning the laws and regulations of a country. Many online streaming companies - such as Amazon Prime Video, Disney, HBO, Netflix, etc. - use geoblocking to deliver rich media content based on the respective country’ standards. E.g., you may have seen something similar to the below captioned examples, when accessing a video through Prime Video or a thumbnail on Youtube:

<img src="https://github.com/lana-20/selenium-webdriver-bidi/assets/70295997/03d9e1e9-4664-46e9-8508-7176be79dcd3" width=680>

Let's review another Selenium CDP example:

<img src="https://github.com/lana-20/selenium-webdriver-bidi/assets/70295997/c7ff04ac-ceae-4b78-9c65-c50579b24a75" width=680>

This example is very similar to what we saw earlier.

In the first 3 lines - we initialize a driver, get devTools instruments, and create a session.

    ChromeDriver driver = new ChromeDriver();
    DevTools devTools = driver.getDevTools();
    devTools.createSession();

Then we enable the Log-related domain. This is the instruction which does not return any information, it’s a mere instruction to enable the Log domain.

    devTools.send(Log.enable());

Now we add a listener. Adding a listener is what allows us to listen for events.

    devTools.addListener(Log.entryAdded(),
    	logEntry -> (
    		System.out.println(“log: ” + logEntry.getText());
     		System.out.println(“level: ” + logEntry.getLevel());
    ));

We tell the script to inform us when any entry of log events happens on the server.

This covers how Selenium is able to support its domains, events, and commands for almost any protocol version that comes in. But it’s version specific. If we send something today, the code works. Tomorrow it might have more parameters, or an event name changed, some features deprecated. The script may or may not work. We must be extra cautious version-specifics-wise.

We’ve accounted for how Selenium supports raw CDP. As SDETs, we need to understand it, the commands and domains that exist, what they do, why they do it - there are a lot of processes and overhead for any tester to understand and then execute this in the raw form. 
Selenium just provides the support because the maintainers don’t have that BiDi stuff setup fully yet. This is not a recommended way of doing things. Anyone maintaining this code incurs a lot of overhead. But if you really need it, this is the way to get started, because it’s supported in all Selenium language bindings.


One source to check for the Selenium CDP documentation is https://www.selenium.dev/documentation/webdriver/bidirectional/. 

<img src="https://github.com/lana-20/selenium-webdriver-bidi/assets/70295997/60a000ba-55ac-4cce-95c0-ef7af8c9610e" width=680>

Another source is the CDP web site https://chromedevtools.github.io/devtools-protocol/. Be cautious with the experimental features, as those might get deprecated at any time.

<img src="https://github.com/lana-20/selenium-webdriver-bidi/assets/70295997/ab010162-2cf7-4cf3-b12a-908f67394ec3" width=680>

Selenium supports all the CDP domains, events and commands listed in the protocol page above.
Since it’s version-specific, with each new Chrome version the BiDi maintainers download the protocol, which is available in the open source repository, do the mapping, generate all the classes with respect to the binary, which is attached to Selenium libraries and sort of ship it out. It’s a lot of work per version that they try to keep up with. When a new version comes in, they try to release it within a week, so that they can support the latest CDP protocol.

#### CDP - Automation Tools

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

In recent years, CDP has gained a lot of traction in the automation industry. Let's compare how <code>WD Classic</code> and <code>CDP</code> implementation differs side-by-side.

WD Classic | CDP |
---|---|
🔵 Standard protocol, supported by all browsers | 🔵 Supports only Chromium-based browsers |
🔴 Communicates via HTTP requests | 🔴 Communicates via WebSocket |
🟣 Does not support low-level controls | 🟣 Supports low-level controls |

🔵 WD Classic is a standard protocol designed according to the W3C specificiation.

🔵 CDP is a protocol, but it only supports Chromium-based browsers, such as Chrome and Edge.

🔴 WD Classic starts an HTTP server in the back-end and sends the commands to the browser driver. The driver carries these instructions on to the browser. Communication happens via the traditional HTTP response/request protocol.

🔴 CDP uses a WebSocket which is bidirectional in nature. WebSocket has the capacity to send the commands and concurrently listen to the events/messages from the server in real time.

🟣 WD Classic can perform operations in the browser UI, but cannot perform those operations in the DevTools console. It can't control the DevTools programmatically. We cannot access network requests, console components, errors or events that happen in the DevTools.

🟣 CDP has a the power of accessing the browser DevTools. It can get the messages or errors from the console, mock the network requests, or wait until the DOM changes.


#### WD Classic - Disadvantages:
- Synchronous Nature
    - WD commands are generally synchronous in nature. It means that the client sends and HTTP request and waits for a response from the browser server before proceeding to the next command.
    - E.g., if we want to click a button, first we need to vefify that the button is enabled and is clickable, and then perform the click action. To achieve this, WD sends 3 synchronous requests one-by-one in order to make sure that the element is 1) visible and 2) clickable, and 3) performs the click action.
    - Due to its synchronous nature, WD waits until an operation is processed on the server side -- this is a performance concern.
- Limited Low-Level DevTools Control
    - Some of the low-level DevTools controls, such as Performance Profiling, Network Interception, Advanced DOM Inspection, and JavaScript Console Interactions, are not available in WD Classic.
- Unidirectional Communication
    - Traditional HTTP-based communication, while suitable for certain scenarios, is not designed for persistent, low-latency connections. This is where WebSockets come into play. WebSockets provide a bi-directional communication channel between a client and a server, enabling real-time data transfer over a single TCP connection. 
    - WD Classic is slow because it lacks the BiDirectional communication with the browser. It means the users have to poll for element availability or visibility, which leads to delays in test automation.
    - Because WD Classic is synchronous and unidirectional, we can send a request and receive response messages for that request at a later time. But we can't actually know what's happening on the browser server side.

#### CDP - Disadvantages:
- Browser Compatibility - Chromium Only
    - This specific protocol is designed to be consistent only for the Chromium browsers. CDP doesn’t work with other browsers, like Safari and Firefox. Other browsers on the market have their own proprietary protocols and interfaces. Mozilla has done a great job at implementing a subset of CDP, but that is just for Puppeteer support. The support is rather incomplete, so anytime we are using Firefox, there’s a good chance things will break while using CDP, because of this partial half-baked support.
- Version-Specific Dependencies
    - CDP has a caveat - it’s specific to the Chrome browser version. For every Google Chrome version release, there is a respective new release of a CDP version. This might cause a breaking change. Certain features might get deprecated or modified, affecting backward compatibility. We might write our code today and send CDP commands via this code. A test script written for the current browser version might not work for a previous or future browser version. Let’s say we’re sending 4 parameters. And tomorrow with the new Chrome version it might require 5 parameters, and our code will break. It affects the durability. We don’t want the code to break, hence due to frequent Chrome releases, we'll have to deal with overhead.
- Lacks Accommodation for Automation Needs
    - And while CDP supports automation, it’s important to understand that it was not designed with automation in mind. It was designed to provide this physical DevTools experience. It doesn’t keep that need for common automation use cases. It doesn’t necessarily address that in a straightforward manner.

----

### WD BiDi - Advantages

Once we have a solid understanding of the WD Classic and CDP, it's easier to understand why BiDi was created and came into play. At a certain point, Chromium developers wondered why shouldn't they merge both WD Classic and CDP tools into one protocol, so that they could utilize the power of both tools. That's how WD BiDi was born.

WD BiDi is a new standard protocol, but it was not built entirely from scratch. It's developed over WD, allowing us to continue working with WD Classic while utilizing the power of CDP.

WD BiDi is a cross-browser automation protocol. It's an open standard that works across browsers, fast by default, and comes packed with all the features you need for test automation. How? It takes the best of <code>Chrome DevTools Protocol</code> (e.g., fast bidirectional messaging & low level control) and <code>WebDriver Classic</code> (e.g., best cross-browser support, W3C Standard, testing-oriented), and combines them into the extraordinary WebDriver BiDi protocol. The vision behind BiDi is to give you full flexibility and let you write tests using any of your favorite tools and automate them in any browser or driver.

This is certainly an exciting future for test automation. It takes a huge effort from various vendors working together to ensure this future.

Selenium, WebDriverIO, Puppeteer and Playwright have already introduced initial support for WebDriver BiDi.

<img src="https://github.com/lana-20/selenium-webdriver-bidi/assets/70295997/0b1181b6-af35-4950-b678-33217172a02c" width=460>

#### BiDi - Road to Better Automation

Why BiDi? How can we take advantage of WD BiDi in our automation scripts?
- Standardized approach - W3C compliance
    - By adhering to the W3C WD specification, BiDi ensures standardized browser automation for consistency and compatibility with various tools and frameworks.
    - WD BiDi is developed by the W3C committee, which developed the WD protocol. WD is not hosted and maintained by one organization. It’s a standardized approach, all browser vendors have to  implement the same API to allow testing. Any action we perform with WD BiDi is the same action in all the browsers. The approach that BiDi follows is relatively the same as WD, but it uses web sockets. 

- Cross-browser support - not specific to browser versions
    - WD BiDi follows W3C standards, enabling cross-browser compatibility, allowing the same test scripts to automate tests across different browsers without major modifications, saving time and effort.
    - Unlike CDP, as a W3C specification, BiDi is not an individual stable implementation.
    - It’s not browser-specific. Once the BiDi protocol is in place, version specifics become a noise for a browser. Browsers are required to adapt the entire protocol as it is. They might do it incrementally until they complete it. And the protocol itself is something that can evolve (add/delete/modify items), but it will never lead to breaking changes.

- Created with automation scenarios in mind
    - The protocol is developed with keeping automation scenarios in mind. It provides domains, events and commands, but it will never try to increase the burden on someone who’s testing. It tries to keep in mind a person who understands testing, and a person who understands browsers. There’s a difference.
    - For example, because CDP was devised to communicate with the browser, the commands are very specific to how the browser functions. Typically we, as testers, don't know how the browser functions or how these commands map to what we want to do. But instead the aim here is to design BiDi in a way that an end-user/tester understands. Those user needs and scenarios are kept in mind. 

- Complementary to WD protocol, with an option to only have the BiDi connection.
    - There's also full interoperability between WD Classic and BiDi. BiDi is complementary to the WD protocol. The WD protocol kind of sits in parallel with it. Every time a WD protocol session is created, we can also create a BiDi session. The session gives us the information needed to connect to the socket.
    - Along with that, many browser vendors are working toward eliminating the need for the users to download specific drivers. They are trying to implement this BiDi protocol within the browser, so we can directly communicate with the browsers for testing purposes.

- Low latency, bidirectional communication
    - WD BiDi enables snappy bidirectional communication, allowing the browser to send real-time updates to the test script, improving synchronization and making testing faster and more reliable.
    - Our automation scripts will be faster than the previous implementation, because WD BiDi comminicates via a bidirectional web socket. We can be aware of what's happening in the browser in real time without sending a synchronous request.

- Low-level debugging control
    - WD BiDi offers low-level browser control, enabling advanced interactions, complex scenario simulation and thorough testing, especially useful for JS-heavy or browser-specific features in web apps.
    - Even when we use WD Classic in our scripts, we can still add WD BiDi and gain access to the DevTools to monitor console messages to help verify the uncaught exceptions and intended logs. We can also listen to JS exceptions, console logs, mock test data, intercept a network request, etc.
    - It gives you all the low-level debugging capability that you have in CDP. It doesn’t mean that CDP is the same as BiDi, that we just lift and pick the same protocol. A lot of thought process goes into what is required, what is needed, what is not heavy for the browsers. Keep in mind, CDP is very heavy and complex for browsers to implement. BiDi is designed keeping both the browser vendors and the end users in mind.

- Developed by leading browser vendors together, keeping browsers in mind
    - There’s a working group that meets every month to discuss, design, and iterate over this protocol. That group consists of members from Puppeteer, Selenium, all browser vendors coming together to pitch ideas, to discuss what happens. They publish the meeting agenda and minutes publicly. It’s a good collaborative effort keeping the entire automation in mind.

WD BiDi is undoubtedly the future of browser automation!

#### Low Level Debugging Control

- Listen to JS errors
    - WD BiDi listens to JS errors, allowing real-time detection and reporting, enhancing debugging capabilities during test execution.
        - If we have a JS-rich app that throws an error at some point in your automation, we can listen to those errors with WD BiDi and debug them to fix our issue.
- Listen to console logs
    - WD BiDi enables real-time capture and analysis of console logs, aiding in debugging and logging during test execution.
        - We can listen to the browser console in the app and fetch the logs for the debugging purpose.
- DOM Mutation
    - WD BiDi allows monitoring and reacting to changes in the DOM, facilitating dynamic web app testing and validation.
        - We can monitor the DOM structure/tree. BiDi can trigger an event whenever we want to inject any script.
- Network Interception
    - WD BiDi enables capture and manipulation of network requests, facilitating advanced testing and analysis of web app performance.
        - We can listen to the incoming network requests/responses for the browser and intercept them,
     
With BiDi, we can also use the Browser Context module, inject scripts, and perform all sorts of DevTools low-level controls.

----

### WD BiDi Implementation Status

WD BiDi is still a work in progress, it's not been fully implemented yet. It's been under development for the past 2 years.
For the past 6 months (as of August 2023), the WD team has been working hard on implementing BiDi in Selenium and WebDriverIO.

- WD BiDi specification is a WIP, supported by diverse collaborators, such as:
    - Browser Vendors: Chrome, Edge, Firefox, Safari
    - Open Source Automation Projects: Selenium, WebDriverIO
    - Companies offering Browser Automation Solutions: BrowserStack, SauceLabs.
- A few [modules](https://www.selenium.dev/documentation/webdriver/bidirectional/bidirectional_w3c/) have already been implemented for Selenium and WebDriverIO and can be used in automation scripts.

<img src="https://github.com/lana-20/selenium-webdriver-bidi/assets/70295997/e205ab4d-a0b6-4775-a18b-d316ab0a1ca7" width=680>

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

<img src="https://github.com/lana-20/selenium-webdriver-bidi/assets/70295997/3a498c85-59bd-4649-b357-ab65c8c0a4f7" width=680>

WD BiDi Goals |
---|
🟢 Notify of new contexts |
🟢 Listen for JS errors |
🟢 Listen for Console logs/errors |
🟡 Bootstrap scripts |
🟡 Mock back-ends and intercept network requests |
🔴 Input Actions/Form submission |
🔴 Access to native DevTools protocol |
🔴 Dynamic changes to iframes or documents |
🔴 Performance timings |

----

### WebDriver Bidi (BiDirectional) Protocol - Code Samples

#### Register Basic Authentication

Basic Authentication is a common way to safeguard your resources on the internet.
If you request something from the server, the request goes to the browser, the browser passes it to the server. The request is basically stating “I want authentication” and yields a dialogue box with username and password fields to fill. Once you enter the basic username and password, you’ll be able to easily access the resources. It’s a rather straightforward concept. 

We’ll use AUT: http://httpbin.org/basic-auth/foo/bar
It shows you a small prompt, a dialogue box asking for the username and password. When you enter username “foo” and password “bar”, you’ll get correctly authenticated.

![image](https://github.com/lana-20/selenium-webdriver-bidi/assets/70295997/07823db2-2f0a-440b-bca7-b59213d37100)

![image](https://github.com/lana-20/selenium-webdriver-bidi/assets/70295997/53818654-c618-48fe-97e9-b8a30f382e13)
    
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

#### Console Logs

![image](https://github.com/lana-20/selenium-webdriver-bidi/assets/70295997/8f76ca2c-f431-47e9-9aca-c5386743a90c)

#### JavaScript Exception Logs

![image](https://github.com/lana-20/selenium-webdriver-bidi/assets/70295997/dd2878c6-b24c-4c2b-9037-6ba80f971165)

#### Network Interceptor

![image](https://github.com/lana-20/selenium-webdriver-bidi/assets/70295997/c8b3dd85-b445-4d90-aa3b-33efda885783)

![image](https://github.com/lana-20/selenium-webdriver-bidi/assets/70295997/de7c4a99-cb15-49f1-82b0-d850bc88a069)

____

References:
- https://developer.chrome.com/articles/webdriver-bidi/
- https://www.honeybadger.io/blog/websocket-node/
- https://w3c.github.io/webdriver-bidi/
- https://github.com/w3c/webdriver-bidi/blob/master/roadmap.md
- https://github.com/GoogleChromeLabs/chromium-bidi/milestones
- https://wiki.mozilla.org/WebDriver/RemoteProtocol/WebDriver_BiDi
- bit.ly/bidi-demo-2023
- https://youtu.be/3gDWWsc0h3Y
- https://youtu.be/t7SaPlkMvZY?si=lD7rCBemYSfVgqPV
- http://youtu.be/ehPpDgqc2bE
- http://youtu.be/gOsvQNnsVP8
- http://youtu.be/O3_fPgPIvWQ
- https://developer.chrome.com/blog/webdriver-bidi/
- https://youtu.be/8k4-8-mMXs4





