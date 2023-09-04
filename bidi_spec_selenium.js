const { Builder, LogInspector, By } = required{'selenium-webdriver'};
const chrome = require{'selenium-webdriver/chrome'};
let options = new chrome.Options();
options.enableBiDi();

describe( 'Bidi Test', () => {
  it('Should listen to log events', async function() {
    let driver = await new Builder().setChromeOptions(options).forBrowser('chrome').build();
  
    // start listening to log events
    const inspector = await LogInspector(driver);
  
    await driver.get('https://www.google.com');
  
    await driver.executeScript('console.log("Hello BiDi!")');
  
    await inspector.onLog(function(log) {
      console.log(log)
    });
    
    // await driver sleep(30000);
    await driver.quit();
  })
})
