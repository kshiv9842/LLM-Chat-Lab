package org.Shiv.Model;

import org.Shiv.Pom.MetaAIPage;
import org.Shiv.data.DataReader;
import org.Shiv.driver.DriverManager;
import org.Shiv.driver.SeleniumActions;
import org.Shiv.utils.FileUtil;
import org.Shiv.utils.PropertiesUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MetaAITest extends org.Shiv.tests.BaseTest {
    @Test (description = "Verify response of AI model to ask same question in different manner.")
    public void verifyResponseContainingCorrectAnswer(){
        DriverManager.navigateURL (PropertiesUtils.getPropertyValue ("metaAIUrl"));
        SeleniumActions.click (MetaAIPage.getMetaAiPage ().getPrompt ());
        SeleniumActions.sendKeys (MetaAIPage.getMetaAiPage ().getPrompt (), DataReader.readAIProps ()
            .getTest_1_prompt_1 ());
        SeleniumActions.click (MetaAIPage.getMetaAiPage ().getSendPrompt ());
        long responseTime = calculateResponseTime ();
        String response = SeleniumActions.getText (MetaAIPage.getMetaAiPage ()
            .getAssistantResponse ());
        System.out.println ("=== "+response);
        System.out.println ("Response Time "+responseTime);
        FileUtil.updateJsonFile ("MetaAI_test_1_prompt_1_ResponseTime",responseTime+" ms");
        SeleniumActions.sleep (2000);
        SeleniumActions.click (MetaAIPage.getMetaAiPage ().getPrompt ());
        SeleniumActions.sendKeys (MetaAIPage.getMetaAiPage ().getPrompt (),DataReader.readAIProps ()
            .getTest_1_prompt_2 ());
        SeleniumActions.click (MetaAIPage.getMetaAiPage ().getSendPrompt ());
        long startTime = System.currentTimeMillis ();
        SeleniumActions.waitForElementVisibility (MetaAIPage.getMetaAiPage ()
            .getLatestAssistantResponse ());
        long endTime = System.currentTimeMillis ();
        String response1 = SeleniumActions.getText (MetaAIPage.getMetaAiPage ()
            .getLatestAssistantResponse ());
        System.out.println ("=== "+response1);
        responseTime = endTime-startTime;
        FileUtil.updateJsonFile ("MetaAI_test_1_prompt_2_ResponseTime",responseTime+" ms");
        SeleniumActions.sleep (5000);
        Assert.assertTrue (response.contains (DataReader.readAIProps ()
            .getTest_1_expected_answer ()));
        Assert.assertTrue (response1.contains (DataReader.readAIProps ()
            .getTest_1_expected_answer ()));

    }
    private void chooseContinueWithoutLogin(){
       // SeleniumActions.waitForElementVisibility (MetaAIPage.getMetaAiPage ()
         //   .getContinueWithoutLoginCTA ());
       // SeleniumActions.click (MetaAIPage.getMetaAiPage ()
         //   .getContinueWithoutLoginCTA ());
        SeleniumActions.sleep (2000);
        SeleniumActions.alternateClick (MetaAIPage.getMetaAiPage ()
            .getYear ());
        SeleniumActions.sleep (2000);
        SeleniumActions.scrollIntoView (MetaAIPage.getMetaAiPage ()
            .getEnterYear ());
        SeleniumActions.click (MetaAIPage.getMetaAiPage ()
            .getEnterYear ());
        SeleniumActions.click (MetaAIPage.getMetaAiPage ()
            .getFinishCTA ());
        SeleniumActions.sleep (4000);
    }
    @Test (description = "Verify response of AI model to ask question while maintaining context")
    public void verifyResponseMaintainingContext(){
        DriverManager.navigateURL (PropertiesUtils.getPropertyValue ("metaAIUrl"));
        SeleniumActions.click (MetaAIPage.getMetaAiPage ().getPrompt ());
        SeleniumActions.sendKeys (MetaAIPage.getMetaAiPage ().getPrompt (),DataReader.readAIProps ().getTest_2_prompt_1 ());
        SeleniumActions.click (MetaAIPage.getMetaAiPage ().getSendPrompt ());
        long responseTime = calculateResponseTime ();
        String response = SeleniumActions.getText (MetaAIPage.getMetaAiPage ()
            .getAssistantResponse ());
        System.out.println ("=== "+response);
        FileUtil.updateJsonFile ("MetaAI_test_2_prompt_1_ResponseTime",responseTime+" ms");
        SeleniumActions.sleep (2000);
        SeleniumActions.click (MetaAIPage.getMetaAiPage ().getPrompt ());
        SeleniumActions.sendKeys (MetaAIPage.getMetaAiPage ().getPrompt (),DataReader.readAIProps ().getTest_2_prompt_2 ());
        SeleniumActions.click (MetaAIPage.getMetaAiPage ().getSendPrompt ());
        long startTime = System.currentTimeMillis ();
        SeleniumActions.waitForElementVisibility (MetaAIPage.getMetaAiPage ()
            .getLatestAssistantResponse ());
        long endTime = System.currentTimeMillis ();
        String response1 = SeleniumActions.getText (MetaAIPage.getMetaAiPage ()
            .getLatestAssistantResponse ());
        System.out.println ("=== "+response1);
        responseTime = endTime-startTime;
        FileUtil.updateJsonFile ("MetaAI_test_2_prompt_2_ResponseTime",responseTime+" ms");
        SeleniumActions.sleep (5000);
    }
    @Test (description = "Verify response of AI model to ask Surprises and Unusual Questions ")
    public void verifyResponseForUnusualQuestions(){
        DriverManager.navigateURL (PropertiesUtils.getPropertyValue ("metaAIUrl"));
        SeleniumActions.click (MetaAIPage.getMetaAiPage ().getPrompt ());
        SeleniumActions.sendKeys (MetaAIPage.getMetaAiPage ().getPrompt (),DataReader.readAIProps ().getTest_3_prompt_1 ());
        SeleniumActions.click (MetaAIPage.getMetaAiPage ().getSendPrompt ());
        long responseTime = calculateResponseTime ();
        String response = SeleniumActions.getText (MetaAIPage.getMetaAiPage ()
            .getAssistantResponse ());
        System.out.println ("=== "+response);
        Assert.assertFalse(response.isEmpty(), "Response should not be empty but was.");
        FileUtil.updateJsonFile ("MetaAI_test_3_prompt_1_ResponseTime", responseTime+" ms");
    }
    @Test (description = "Verify response of AI model When the AI Doesn’t Understand the ques.")
    public void verifyResponseForUnexpectedQuestions(){
        DriverManager.navigateURL (PropertiesUtils.getPropertyValue ("metaAIUrl"));
        SeleniumActions.click (MetaAIPage.getMetaAiPage ().getPrompt ());
        SeleniumActions.sendKeys (MetaAIPage.getMetaAiPage ().getPrompt (),DataReader.readAIProps ().getTest_4_prompt_1 ());
        SeleniumActions.click (MetaAIPage.getMetaAiPage ().getSendPrompt ());
        long responseTime = calculateResponseTime ();
        String response = SeleniumActions.getText (MetaAIPage.getMetaAiPage ()
            .getAssistantResponse ());
        System.out.println ("=== "+response);
        Assert.assertFalse(response.isEmpty(), "Response should not be empty but was.");
        FileUtil.updateJsonFile ("MetaAI_test_4_prompt_1_ResponseTime", responseTime+" ms");
    }
    @Test (description = "Verify response of AI model that AI keeps personal or sensitive information secure")
    public void verifyAIDoesNotRetainPersonalInfo(){
        DriverManager.navigateURL (PropertiesUtils.getPropertyValue ("metaAIUrl"));
        SeleniumActions.click (MetaAIPage.getMetaAiPage ().getPrompt ());
        SeleniumActions.sendKeys (MetaAIPage.getMetaAiPage ().getPrompt (),DataReader.readAIProps ()
            .getTest_5_prompt_1 ());
        SeleniumActions.click (MetaAIPage.getMetaAiPage ().getSendPrompt ());
        long responseTime = calculateResponseTime ();
        String response = SeleniumActions.getText (MetaAIPage.getMetaAiPage ()
            .getAssistantResponse ());
        System.out.println ("=== "+response);
        Assert.assertFalse(response.isEmpty(), "Response should not be empty but was.");
        //Assert.assertTrue (response.contains ("For security and privacy reasons, I don't have the ability to save"));
        FileUtil.updateJsonFile ("MetaAI_test_5_prompt_1_ResponseTime", responseTime+" ms");
    }
    private long calculateResponseTime() {
        long startTime = System.currentTimeMillis ();
        chooseContinueWithoutLogin();
        SeleniumActions.waitForElementVisibility (MetaAIPage.getMetaAiPage ().getAssistantResponse ());
        long endTime = System.currentTimeMillis ();
        long responseTime = (endTime-startTime)-8000;
        return responseTime;
    }
}
