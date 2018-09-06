
SDET Example Project.

Objectives: 

1. Write a simple Java SpringBoot/Thymeleaf Web Application.
2. Test the Web Application by writing an example test framework that 
   will concurrently test the web front end on Chrome, Firefox and Edge browsers.
   UI Test Cases must follow the principles of the Page Object Model
   design pattern.
3. Write a test case that exercise a REST API.
4. Generate a report of the test cases that succeeded and failed


This is a Maven, TestNG, Selenium 3, Apache POI, Java 1.8 and 
Spring Boot STS-3.9.3 IDE Project running on Windows 10.

IDE used:

    Spring STS IDE
  --------------------------------------
	Version: STS (3.9.3)

	
Important dependency versions used:

        Software             Version
  ------------------------------  --------------
   Java JDK                         1.8.0_161,
   TestNG                           6.10
   Selenium 3 for Java              3.12.0
   Apache POI                       3.17
   Apache POI OOXML                 3.17
   Selenium Chrome Driver           3.12.0
   Selenium Firefox Driver          3.12.0
   Selenium Edge Driver             3.12.0
   Maven Surefire plugin            2.21.0
   maven-surefire-report-plugin     2.21.0
   Apache Maven                     3.5.0
   Rest Assured                     3.1.0
   Hamcrest all                     1.3
   Hamcrest core matchers           1.8
   Thymeleaf                        latest from repo

   
Selenium Driver Exes used:

  Already included in this project under the webdrivers directory

    Browser      Driver EXE
  -----------  -------------------------------------------------------
   Chrome:      chromedriver.exe
   Firefox:     geckodriver.exe
   Edge:        MicrosoftWebDriver_17134.exe  Windows 10 build 17134.
   IE:          IEDriverServer.exe

TestNG Test Framework files:

   found under the src/test/java/framework directory   
	
TestNG Test Suite xml files:

   found under the src/test/resources directory.
   
     TestNG Suite config files
   -----------------------------------
     TngDemoAppChromeTestSuite.xml  Runs the TngSdetUiTest tests using the Chrome webdriver. 1 active thread.
     TngDemoAppFirefoxTestSuite.xml Runs the TngSdetUiTest tests using the Firefox webdriver. 1 active thread.
     TngDemoAppEdgeTestSuite.xml    Runs the TngSdetUiTest tests using the Edge webdriver. 1 active thread.
     TngDemoAppRestApiTestSuite.xml Runs the TngSdetRestApiTest tests using a RestTemplate. 1 active thread.
	 TngAllDemoAppTestSuite.xml     Runs all test Suites in parallel. 3 active threads.
	
	 Running test suite from Command Line
   ----------------------------------------
	 To run a test suite from the command line using maven surefire do from the project directory:
	    mvn clean test -DsuiteXmlFile=TngDemoAppChromeTestSuite.xml site -DgenerateReports=false surefire-report:report
	
     To run all test suites simultaneously using maven surefire do from the project directory:	
		mvn clean test -DsuiteXmlFile=TngAllDemoAppTestSuite.xml site -DgenerateReports=false surefire-report:report
		
	 Running test suite from STS IDE
   -----------------------------------
	 On the project explorer go to src/test/resources. Right click on one the test suite xml files
	 and then select "Run As -> TestNG Suite"
       	   
		
Generated report files:

   1. Test NG Report files

      found under the test-output directory.

      TestNG Report Filename
    -------------------------
      index.html	
	 
   2. Surefire Report files

      found under the target/site directory.

      TestNG Report Filename
    -------------------------
      surefire-report.html	
	 
	 
	 
   
  