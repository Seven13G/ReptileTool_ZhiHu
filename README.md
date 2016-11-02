# Seven13T
This is a Selenium Framework Building project. And some of the codes comes from Autotask Corporation, QA Automation team. And I will continue updating this in a long period.If you are interested in Selenium, you can join this project.
And following is the steps to import the project.

#Running
 At first, an eclipse with TestNg and Maven is required. It's recommand using Eclipse IDE for Java. It's coded by Java of course.

 And next, import the project.You can see the details of this project, aha! It's simple, isn't it?

# Introduce

 This is the source codes for the reptile tool.And you can use it to get the questions and answers from zhihu.com for certain keywords. Besides, it can output xml files for the results. It fit all the keywords search. Have a good time!
<br>

#Sample<br>

```java
import java.io.UnsupportedEncodingException;

import org.testng.annotations.Test;

import com.Selenium_FrameWork.LaunchDriver;

public class Selenium_TestNg {
	
@Test

  public void zhihu() throws UnsupportedEncodingException {
  
	SearchZhihu searchZhihu = LaunchDriver.zhihu("https://www.zhihu.com/search");
	
	//set the search keywords
	searchZhihu.searchQuestion("韩春雨");
	
	//expand all the contents
	searchZhihu.clickMore();
	
	//click one question and get in the link
	QuestionZhihu questionZhihu = searchZhihu.clickLink();
	
	//show and output the questions and answers
	questionZhihu.showMessage();
  }
  
}
```

#Related Works

##[zhihu website](www.zhihu.com)<br>

##The first one is Selenium of course!<br>
>[SeleniumHQ/Selenium](https://github.com/SeleniumHQ/selenium)
<br>
>>Selenium  API Documentation: 
<br>
>>> * [C#](http://seleniumhq.github.io/selenium/docs/api/dotnet/)
 <br>
>>>* [Java](http://seleniumhq.github.io/selenium/docs/api/java/index.html)
 <br>
>>> * [Pythson](http://seleniumhq.github.io/selenium/docs/api/py/)
 <br>
>>> * [Ruby](http://seleniumhq.github.io/selenium/docs/api/rb/)

