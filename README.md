# selenium-java-bot

**Master selenium java automation** project with **all necessary helpers, utilities and dependencies**. Can be packaged as a JAR file and imported as dependency

**1. Browser Factory:**

**Supported browsers:** 1. Chrome 2. Edge 3. Firefox

**Supported platforms:** 1. Selenium grid 2. Selenoid 3. Browser Stack

There are 2 sub packages in browser factory: 1. Managers 2. Suppliers

**Managers:** 
    This is responsible for the initiation and setup of the browser sessions: Local and Remote. Uses "WebDriver Manager" class for detecting the browser versions in the local or remote platforms, and downloading executable files necessary for initiation (No need to download .exe files)

**Suppliers:**
    This is responsible for verifying the browser name provided by the user in the properties file and trigger the respective manager class
    
**Local execution:** User needs to have **property named browser/browser name** and provide any of the following as values

browser/browserName= chrome/edge/firefox


**Remote execution:**  User needs to have properties named 1. browser/browser name 2. platform 3. hub url and provide any of the follwing as vales

browser/browserName= chrome/edge/firefox

platform= selenium_grid/selenoid/browser_stack

hubUrl= http://<localhost/machine IP>:4444

**NOTE:** Every value in the property file needs to be in lowercase


**2. Helpers:**

This package consists of all the classes/methods necessary for:

1. Actions: within browser window and on elements

2. Actions: Keyboard and Mouse actions

3. Find element and elements

4. Java script executors

5. WebDriver waits

6. Snap shots: 1. In build methods 2. Using Shutter bug

7. Generate Random values

8. Read test data: 1. Yml files 2. Json files 3. Excel files

9. Extent Report
    

**3. Utilities:**

This class should contain methods that can be used for:

1. Encode

2. Decode

3. Current date and time

4. Local time

5. Current seconds and milliseconds



**4. ENUMS:**

Theses are mainly used to decide the behaviour of the framework

1. **BrowserTypes:** To decide against which browser execution has to be done

2. **BrowserWindow:** To decide the action to be executed within the browser window like (maximize, minimize, quit, close etc...)

3. **Elements:** To decide the action to be executed on an element like (click, clear, submit etc...)

4. **LocateBy:** To decide the locator by which element/elements to be located in the browser window

5. **LocateRelativelyBy:** To decide the locator by which element/elements to be relatively located in the browser window

6. **PerformActions:** Decide on the keyboard and mouse actions like (right click, double click, mouse over etc...)

7. **RandomValues:** To generate random vales of type (string, numbers, alpha numeric etc...)

8. **RemotePlatforms:** Decide on the remote platform for execution (selenium grid, selenoid, browser stack)

9. **Scripts:** Decide on the java script to be executed (scroll to bottom, scroll to top, page refresh)

10. **WaitConditions:** Decide on the webdriver wait condition (implicitly, pageload, clickable, visible, invisible etc...)

**NOTE:**

1. Place the **test data** files under **src/test/resources**

2. Extent config file: **extent-config.xml** under **src/main/resources**

3. **Test report and snap shots** by default will be created under **src/test/resources**
