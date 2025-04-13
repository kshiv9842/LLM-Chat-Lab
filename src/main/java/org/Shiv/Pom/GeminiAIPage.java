package org.Shiv.Pom;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;
@Data
@Getter
@Setter
public class GeminiAIPage {
    private static final GeminiAIPage GEMINI_AI_PAGE = new GeminiAIPage ();
    public static GeminiAIPage getGeminiAiPage() {
        return GEMINI_AI_PAGE;
    }
    private By prompt = By.xpath ("//div[@data-placeholder='Ask Gemini']");
    private By sendPrompt = By.xpath ("//button[@aria-label='Send message']");
    private By assistantResponse = By.xpath ("//div[contains(@id,'model-response-message-content')]");
}
