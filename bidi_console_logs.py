from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.wait import WebDriverWait

options = webdriver.ChromeOptions()
options.enable_bidi = True
driver = webdriver.Chrome(options=options)
wait = WebDriverWait(driver, 5)
try:
    driver.get('https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html')
    log_entries = []
    driver.script.add_console_message_handler(log_entries.append)
    driver.find_element(By.ID, "consoleLog").click()
    wait.until(lambda _: log_entries)
    assert log_entries[0].text == "Hello, world!"
    log_entries.clear()
except Exception as e:
    print(f"Error: {e}")
finally:
    if driver is not None:
        driver.quit()