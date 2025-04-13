package org.Shiv.Pom;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;
@Data
@Getter
@Setter
public class MetaAIPage {
    private static final MetaAIPage META_AI_PAGE = new MetaAIPage ();

    public static MetaAIPage getMetaAiPage(){
        return META_AI_PAGE;
    }
    private By continueWithoutLoginCTA = By.xpath ("//div//span[contains(text(),'Continue without logging in')]");
    private By year = By.xpath ("//div//span[contains(text(),'Year')]");
    private By enterYear = By.xpath ("//span[contains(text(),'2000')]");
    private By finishCTA = By.xpath ("//span[contains(text(),'Continue')]");
//    private By finishCTA = By.xpath ("//div//span[contains(text(),'Finish')]");
    private By prompt = By.xpath("//div[contains(@aria-placeholder, 'Meta AI')]");
    private By sendPrompt = By.xpath ("//div[@aria-label='Send message']");
    private By assistantResponse = By.xpath ("//div[contains(@class, 'xe0n8xf')]");
    private By latestAssistantResponse = By.xpath ("(//div[contains(@class, 'xe0n8xf')])[2]");

}
