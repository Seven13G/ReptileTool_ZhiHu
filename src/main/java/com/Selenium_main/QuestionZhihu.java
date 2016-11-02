package com.Selenium_main;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;  
import org.w3c.dom.Text;

import com.Selenium_FrameWork.General;
import com.Selenium_FrameWork.Page;
import com.Selenium_FrameWork.SelGeneral;

public class QuestionZhihu extends Page{

	@FindBy(css = ".count")
	private List<WebElement> agreeAmount;
	
	@FindBy(css = ".zm-item-answer-author-info")
	private List<WebElement> userName;
	
	@FindBy(css = ".answer-date-link.meta-item")
	private List<WebElement> editTime;
	
	@FindBy(css = ".zm-editable-content.clearfix")
	private List<WebElement> answerLists;
	
	public QuestionZhihu(WebDriver driver) {
		super(driver);
		SelGeneral.switchToWindow("www.zhihu.com/question");
	}
	
	public void showMessage(){
		General.sleep(2);
		
		printXml(questionNumber,questionName,questionLink,userName,agreeAmount,editTime,answerLists,answerLists.size());
		
		for(int i = 0;i<(answerLists.size()-1);i++)
		{
			System.out.println("User:"+userName.get(i).getText()+"--赞:"+agreeAmount.get(i).getText()+"--time:"+editTime.get(i).getText());	
		}
		SelGeneral.closeAWindowAndSwitchToAnotherByURL("question", "search");
	}
	
	 public static void printXml(int q_Number, String questionName, String questionLink, List<WebElement> user, List<WebElement> agreeNumber, List<WebElement> editTime, List<WebElement> content, int amount) 
	 {
		 try{
			 Document doc = null;  
			 DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
			 DocumentBuilder docBuilder = dbf.newDocumentBuilder();  
			 doc = docBuilder.newDocument(); 
			 Element root = doc.createElement("question"+q_Number+"");
			 doc.appendChild(root);  
			 // Element root = new Element("question"+q_Number+""); 
			 // Document Doc = new Document(root);   
			 for (int j = 0; j <= amount-1; j++) { 
		      Element answer = doc.createElement("Answer");   

		      //elements.setAttribute("Number", "" + j);
			  Attr id = doc.createAttribute("Number");//创建Id属性节点
			  id.setValue(j+"");//给属性赋值
			  answer.setAttributeNode(id);//把id属性节点追加到student
			  root.appendChild(answer);//追加到root节点
			  
			  //创建用户元素节点
			  Element link = doc.createElement("link");
			  Text linkValue = doc.createTextNode(questionLink);//创建文本节点
			  link.appendChild(linkValue);
			  answer.appendChild(link);
			  
			  //创建用户元素节点
			  Element users = doc.createElement("user");
			  Text nameValue = doc.createTextNode(user.get(j).getText());//创建文本节点
			  users.appendChild(nameValue);
			  answer.appendChild(users);
			  
			  //创建赞同数元素节点
			  Element agree = doc.createElement("agree");
			  Text agree_txt = doc.createTextNode(agreeNumber.get(j).getText());//创建文本节点
			  agree.appendChild(agree_txt);
			  answer.appendChild(agree);
			  
			  //创建时间元素节点
			  Element time = doc.createElement("time");
			  Text time_txt = doc.createTextNode(editTime.get(j).getText());//创建文本节点
			  time.appendChild(time_txt);
			  answer.appendChild(time);
			  
			  //创建内容元素节点
			  Element contents = doc.createElement("content");
			  Text contents_txt = doc.createTextNode(content.get(j).getText());//创建文本节点
			  contents.appendChild(contents_txt);
			  answer.appendChild(contents);	
			  
			  TransformerFactory tf = TransformerFactory.newInstance();  
	          Transformer transformer = tf.newTransformer();
	          File file = new File("C:\\Users\\jqi\\Desktop\\result\\"+checkFileName(questionName)+".xml");  
	          FileOutputStream out = new FileOutputStream(file);
	          StreamResult xmlResult = new StreamResult(out);  
	          transformer.setOutputProperty(OutputKeys.INDENT, "yes");  
	          transformer.transform(new DOMSource(doc), xmlResult);  
			 }
		 }catch(Exception e){}
		 
	}
	 
	 public static String checkFileName(String name){
		 
		 Pattern pattern = Pattern.compile("[\\\\/:*\"<>?|]");  
         Matcher   m   =   pattern.matcher(name);     
         return   m.replaceAll("").trim();  
	 }

		 
	
}
