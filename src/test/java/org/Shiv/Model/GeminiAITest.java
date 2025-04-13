package org.Shiv.Model;

import java.util.HashMap;
import java.util.Map;

import org.Shiv.Pom.GeminiAIPage;
import org.Shiv.data.AIProps;
import org.Shiv.data.AIResponse;
import org.Shiv.data.DataReader;
import org.Shiv.driver.DriverManager;
import org.Shiv.driver.SeleniumActions;
import org.Shiv.utils.FileUtil;
import org.Shiv.utils.PropertiesUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GeminiAITest extends org.Shiv.tests.BaseTest {
    private String sendPromptAndGetResponse(String promptText, String responseTimeKey) {
        SeleniumActions.click(GeminiAIPage.getGeminiAiPage().getPrompt());
        SeleniumActions.sendKeys(GeminiAIPage.getGeminiAiPage().getPrompt(), promptText);

        long startTime = System.currentTimeMillis();
        SeleniumActions.click(GeminiAIPage.getGeminiAiPage().getSendPrompt());
        SeleniumActions.waitForElementVisibility(GeminiAIPage.getGeminiAiPage().getAssistantResponse());
        long endTime = System.currentTimeMillis();

        SeleniumActions.sleep(2000); // Ensure complete response
        String response = SeleniumActions.getText(GeminiAIPage.getGeminiAiPage().getAssistantResponse());
        FileUtil.updateJsonFile(responseTimeKey, (endTime - startTime) + " ms");

        return response;
    }
    @Test (description = "Verify response of AI model to ask same question in different ways.")
        public void testSameQuestionDifferentWays(){
        DriverManager.navigateURL(PropertiesUtils.getPropertyValue("geminiAIUrl"));
        AIProps aiData = DataReader.readAIProps();
        String response = sendPromptAndGetResponse(aiData.getTest_1_prompt_1(), "Gemini_test_1_prompt_1_ResponseTime");
        System.out.println("-- " + response);

        String response1 = sendPromptAndGetResponse (aiData.getTest_1_prompt_2 (),"Gemini_test_1_prompt_2_ResponseTime");
        System.out.println ("-- "+response1);

        Assert.assertTrue (response.contains (DataReader.readAIProps ()
            .getTest_1_expected_answer ()));
        Assert.assertTrue (response1.contains (DataReader.readAIProps ()
            .getTest_1_expected_answer ()));
        }

        @Test (description = "Verify response of AI model to ask question while maintaining context")
        public void testMaintainsContext(){
            DriverManager.navigateURL(PropertiesUtils.getPropertyValue("geminiAIUrl"));
            AIProps aiData = DataReader.readAIProps();
            String response = sendPromptAndGetResponse(aiData.getTest_2_prompt_1 (), "Gemini_test_2_prompt_1_ResponseTime");
            System.out.println("-- " + response);

            String response1 = sendPromptAndGetResponse (aiData.getTest_2_prompt_2 (),"Gemini_test_2_prompt_2_ResponseTime");
            System.out.println ("-- "+response1);
        }
        @Test (description = "Verify response of AI model to ask Surprises and Unusual Questions ")
        public void verifyResponseForUnusualQuestions(){
            DriverManager.navigateURL(PropertiesUtils.getPropertyValue("geminiAIUrl"));
            AIProps aiData = DataReader.readAIProps();
            String response = sendPromptAndGetResponse(aiData.getTest_3_prompt_1 (), "Gemini_test_3_prompt_1_ResponseTime");
            System.out.println("-- " + response);
            Assert.assertFalse(response.isEmpty(), "AI response should not be empty for unusual questions.");
        }
        @Test (description = "Verify response of AI model When the AI Doesn’t Understand the ques.")
        public void verifyResponseForUnexpectedQuestions(){
            DriverManager.navigateURL(PropertiesUtils.getPropertyValue("geminiAIUrl"));
            AIProps aiData = DataReader.readAIProps();
            String response = sendPromptAndGetResponse(aiData.getTest_4_prompt_1 (), "Gemini_test_4_prompt_1_ResponseTime");
            System.out.println("-- " + response);
            Assert.assertFalse(response.isEmpty(), "AI response should not be empty for unusual questions.");
        }
        @Test (description = "Verify response of AI model that AI keeps personal or sensitive information secure")
        public void verifyAIDoesNotStoreSensitiveInformation(){
            DriverManager.navigateURL(PropertiesUtils.getPropertyValue("geminiAIUrl"));
            AIProps aiData = DataReader.readAIProps();
            String response = sendPromptAndGetResponse(aiData.getTest_5_prompt_1 (), "Gemini_test_5_prompt_1_ResponseTime");
            System.out.println ("response "+response);
        }
    }

