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
