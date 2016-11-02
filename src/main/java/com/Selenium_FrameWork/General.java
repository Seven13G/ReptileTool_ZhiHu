package com.Selenium_FrameWork;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class General {
	 private static String xmlURL = "..\\data.xml";
	    private static String pictureURL = "..\\Attachment\\ImageAttachment.jpg";
	    private static String wordUrl = "..\\Attachment\\WordAttachment.docx";
	    private static String pdfUrl = "..\\Attachment\\PDF.pdf";
	    private Document document;
	    
	    public General() {

	    }
	    
	    public void createXml(String fileName) {
	        Element root = this.document.createElement("scores"); 
	        this.document.appendChild(root); 
	        Element employee = this.document.createElement("employee"); 
	        Element name = this.document.createElement("name"); 
	        name.appendChild(this.document.createTextNode("wangchenyang")); 
	        employee.appendChild(name); 
	        Element sex = this.document.createElement("sex"); 
	        sex.appendChild(this.document.createTextNode("m")); 
	        employee.appendChild(sex); 
	        Element age = this.document.createElement("age"); 
	        age.appendChild(this.document.createTextNode("26")); 
	        employee.appendChild(age); 
	        root.appendChild(employee); 
	        TransformerFactory tf = TransformerFactory.newInstance();
	        try {
	            Transformer transformer = tf.newTransformer();
	            DOMSource source = new DOMSource(document);
	            transformer.setOutputProperty(OutputKeys.ENCODING, "gb2312");
	            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	            PrintWriter pw = new PrintWriter(new FileOutputStream(fileName));
	            StreamResult result = new StreamResult(pw);
	            transformer.transform(source, result);
	            System.out.println("生成XML文件成功!");
	        } catch (TransformerConfigurationException e) {
	            System.out.println(e.getMessage());
	        } catch (IllegalArgumentException e) {
	            System.out.println(e.getMessage());
	        } catch (FileNotFoundException e) {
	            System.out.println(e.getMessage());
	        } catch (TransformerException e) {
	            System.out.println(e.getMessage());
	        }
	    }

	    public static String getAbsolutePath(String type) {
	        File xmlFile = null;
	        switch (type) {
	        case "Picture":
	            xmlFile = new File(pictureURL);
	            break;
	        case "Word":
	            xmlFile = new File(wordUrl);
	            break;
	        case "PDF":
	            xmlFile = new File(pdfUrl);
	            break;
	        }
	        return xmlFile.getAbsolutePath();
	    }

	    // get xml node value based on node name
	    public static String getXML(String nodeName) {
	        String value = "";
	        File xmlFile = new File(xmlURL);
	        try {
	            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	            Document doc = dBuilder.parse(xmlFile);
	            NodeList nList = doc.getChildNodes();
	            Node nNode = nList.item(0);
	            // System.out.println(nNode.getTextContent());
	            Element eElement = (Element) nNode;
	            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	                value = eElement.getElementsByTagName(nodeName).item(0).getTextContent();
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return value;
	    }

	    // write to xml based on its node name
	    public static void setXML(String nodeName, String newValue) {
	        File xmlFile = new File(xmlURL);
	        try {
	            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	            Document doc = dBuilder.parse(xmlFile);
	            NodeList nList = doc.getChildNodes();
	            Node nNode = nList.item(0);
	            Element eElement = (Element) nNode;
	            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	                eElement.getElementsByTagName(nodeName).item(0).setTextContent(newValue);
	            }
	            TransformerFactory transformerFactory = TransformerFactory.newInstance();
	            Transformer transformer = transformerFactory.newTransformer();
	            DOMSource source = new DOMSource(doc);
	            StreamResult result = new StreamResult(xmlFile);
	            transformer.transform(source, result);

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	    }

	    // return a random int number (among the range of min and max number set in
	    // the parameter)
	    public static int randInt(int min, int max) {

	        Random rand = new Random();
	        int randomNum = rand.nextInt((max - min) + 1) + min;

	        return randomNum;
	    }

	    // return the current day of week. sunday = 1; Saturday = 7
	    public static int getDayOfWeek() {
	        Calendar calendar = Calendar.getInstance();
	        return calendar.get(Calendar.DAY_OF_WEEK);
	    }

	    // sleep x seconds
	    public static void sleep(double d) {
	        try {
	            Thread.sleep((long) (d * 1000));
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    // grabs the current date in MM/dd/yyyy format
	    public static String getCurrentDate() {
	        return getFormatDate(getXML("DateFormat"));
	    }

	    public static String getFormatDate(String format) {
	        DateFormat dateFormat = new SimpleDateFormat(format);
	        dateFormat.setTimeZone(TimeZone.getTimeZone("America/New_York"));
	        Date date = new Date();
	        return dateFormat.format(date);
	    }
	    
	    public static String getToday() {
//	    	DateFormat dateFormat = new SimpleDateFormat(getXML("DateFormat"));
//	        Calendar cal = Calendar.getInstance();
//	        String today = null;
//	        try {
//	            // cal.setTime(dateFormat.parse(getCurrentDate()))
//	            cal.setTime(new Date());
//	            cal.add(Calendar.DAY_OF_MONTH, 0);  
//	            today = dateFormat.format(cal.getTime());
//	        } catch (Exception ex) {
//	            ex.printStackTrace();
//	        }
//	        return today;
	    	DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	        Date date = new Date();
	        return dateFormat.format(date);
	    }

	    // get the next date forward by x in MM/dd/yyyy format
	    public static String getNextDate(int noOfDays) {
	        DateFormat dateFormat = new SimpleDateFormat(getXML("DateFormat"));
	        Calendar cal = Calendar.getInstance();
	        String nextDate = null;
	        try {
	            // cal.setTime(dateFormat.parse(getCurrentDate()))
	            cal.setTime(new Date());
	            cal.add(Calendar.DATE, noOfDays);
	            nextDate = dateFormat.format(cal.getTime());
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        return nextDate;
	    }

	    public static String getFirstDateOfCurrentMonth() {
	        DateFormat dateFormat = new SimpleDateFormat(getXML("DateFormat"));
	        Calendar cal = Calendar.getInstance();
	        String firstDay = null;
	        try {
	            cal.add(Calendar.MONTH, 0);
	            cal.set(Calendar.DAY_OF_MONTH, 1);
	            firstDay = dateFormat.format(cal.getTime());
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        return firstDay;
	    }
	    
	    public static String getFirstDateOfNextMonth() {
	        DateFormat dateFormat = new SimpleDateFormat(getXML("DateFormat"));
	        Calendar cal = Calendar.getInstance();
	        String firstDay = null;
	        try {
	            cal.add(Calendar.MONTH, 1);
	            cal.set(Calendar.DAY_OF_MONTH, 1);
	            firstDay = dateFormat.format(cal.getTime());
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        return firstDay;
	    }

	    public static String getLastDateOfCurrentMonth() {
	        DateFormat dateFormat = new SimpleDateFormat(getXML("DateFormat"));
	        Calendar cal = Calendar.getInstance();
	        String lasttDay = null;
	        try {
	            cal.add(Calendar.MONTH, 0);
	            cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
	            lasttDay = dateFormat.format(cal.getTime());
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        return lasttDay;
	    }

	    public static String getLastDateOfCurrentYear() {
	        DateFormat dateFormat = new SimpleDateFormat(getXML("DateFormat"));
	        Calendar cal = Calendar.getInstance();
	        String lasttDay = null;
	        try {
	            cal.add(Calendar.YEAR, -1);
	            cal.set(Calendar.DAY_OF_YEAR, cal.getActualMaximum(Calendar.DAY_OF_YEAR));
	            lasttDay = dateFormat.format(cal.getTime());
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        return lasttDay;
	    }
	    
	    public static String getFirstDateOfCurrentYear() {
	        DateFormat dateFormat = new SimpleDateFormat(getXML("DateFormat"));
	        Calendar cal = Calendar.getInstance();
	        String firstDay = null;
	        try {
	            cal.add(Calendar.YEAR, 0);
	            cal.set(Calendar.DAY_OF_YEAR, cal.getActualMinimum(Calendar.DAY_OF_YEAR));
	            firstDay = dateFormat.format(cal.getTime());
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        return firstDay;
	    }

	    public static String getLastDateOfNextMonth() {
	        DateFormat dateFormat = new SimpleDateFormat(getXML("DateFormat"));
	        Calendar cal = Calendar.getInstance();
	        String lasttDay = null;
	        try {
	            cal.add(Calendar.MONTH, 1);
	            cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
	            lasttDay = dateFormat.format(cal.getTime());
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        return lasttDay;
	    }

	    public static double getDayOfMonth() {
	        Calendar cal = Calendar.getInstance();
	        double days = cal.getActualMaximum(Calendar.DATE);
	        return days;
	    }

	    // grabs the saturday in the week
	    public static String getEndOfWeek() {
	        DateFormat dateFormat = new SimpleDateFormat(getXML("DateFormat"));
	        Calendar cal = Calendar.getInstance();

	        int differnce = -cal.get(Calendar.DAY_OF_WEEK) + 7;
	        cal.add(Calendar.DAY_OF_MONTH, differnce);
	        return dateFormat.format(cal.getTime());
	    }

	    public static String tempFile(String fileName) {
	        File tempFile = null;
	        try {
	            tempFile = File.createTempFile(fileName, ".txt");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        try {
	            FileWriter writer = new FileWriter(tempFile);
	            writer.write("Some data");
	            writer.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        return tempFile.getPath();
	    }

	    // timestamp - can be used for suffix
	    public static String timeStamp() {
	        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	        Date date = new Date();
	        return dateFormat.format(date);
	    }

	    public static String timeStampWithSpecialCharacters() {
	        DateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmss");
	        Date date = new Date();
	        return dateFormat.format(date) + getXML("SpecialChar");
	    }

	    // timestamp - can be used for suffix(Because of the timeStamp is long for
	    // some fields(e.g. favorite > Speed Code), so add this method )
	    public static String secondStamp() {
	        DateFormat dateFormat = new SimpleDateFormat("ddHHmmss");
	        Date date = new Date();
	        return dateFormat.format(date);
	    }

		public static CharSequence timeStampShort() {
	        DateFormat dateFormat = new SimpleDateFormat("MMddYY");
	        Date date = new Date();
	        return dateFormat.format(date);
		}

	    // // copy result from direct to newPath
	    // public static void saveResult() {
	    // String newPath = "C:\\Result\\" + SuiteName;
	    // String oldPath = "C:\\SeleniumWebDriver\\" + SuiteName +
	    // "\\test-output\\html";
	    // // File file = new File(oldPath);
	    // // if (!file.exists()) {
	    // // return "nothing to copy, abort";
	    // // }
	    // File file = new File(newPath);
	    // if (!file.exists()) {
	    // file.mkdirs();
	    // }
	    // try {
	    // String cmd = "xcopy" + " " + oldPath + " " + newPath;
	    // Runtime.getRuntime().exec(cmd);
	    // } catch (Exception ex) {
	    // ex.printStackTrace();
	    // // return "copy failed";
	    // }
	    // // return "Pass";
	    // }
	    // achived old functions

	    // //Changes to a different window by part of its URL, wait for max 10
	    // seconds till it appears
	    // @Deprecated
	    // public static String switchToWindowbyPartURL(String partURL, WebDriver
	    // driver){
	    // int timeout = 1;
	    // String winhandle = null;
	    // do {
	    //
	    // Set<String> handles = driver.getWindowHandles();
	    // for(String s: handles){
	    // driver.switchTo().window(s);
	    // if(driver.getCurrentUrl().contains(partURL)){
	    // winhandle = s;
	    // break;
	    // }
	    //
	    // }
	    // timeout++;
	    //
	    // } while (!driver.getCurrentUrl().contains(partURL) && timeout < 10);
	    //
	    // return winhandle;
	    // }

	    // //Changes to a popup window by it's Title
	    // public static String switchToPopUp(String popUpTitle,WebDriver driver){
	    // String winHandle = null;
	    // Set<String> handles = driver.getWindowHandles();
	    // for(String s: handles){
	    // driver.switchTo().window(s);
	    // if(driver.getTitle().contains(popUpTitle))
	    // winHandle = s;
	    // }
	    // return winHandle;
	    // }

	    // //Changes to the popup if it is instance, rarely used
	    // public static String switchToPopUp(WebDriver driver,WebElement
	    // elementToClick){
	    // Set<String> beforePopUp = driver.getWindowHandles();
	    // elementToClick.click();
	    // Set<String> afterPopUp = driver.getWindowHandles();
	    // afterPopUp.removeAll(beforePopUp);
	    // if(afterPopUp.size() == 1)
	    // return (String)afterPopUp.toArray()[0];
	    // else
	    // return null;
	    // }

	    // handle exceptions
	    // public static void handleException(String testName, Exception e) {
	    //
	    // e.printStackTrace();
	    // Reporter.log(testName + " failed");
	    // SelGeneral.takeScreenshot();
	    // // write the exception to a txt file
	    // try {
	    // File log = new File(direct + "/" + testName, "error.txt");
	    // FileWriter logFile = new FileWriter(log);
	    // PrintWriter out = new PrintWriter(logFile);
	    // e.printStackTrace(out);
	    // out.close();
	    // } catch (Exception ioExceptin) {
	    // ioExceptin.printStackTrace();
	    // }
	    //
	    // Assert.fail(testName + " failed");
	    // }
}
