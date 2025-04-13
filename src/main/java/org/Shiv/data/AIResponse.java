package org.Shiv.data;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Data
@Getter
@Setter
public class AIResponse {
    private String test_1_prompt_1_ResponseTime;
    private String test_1_prompt_2_ResponseTime;
    private String test_2_prompt_1_ResponseTime;
    private String test_2_prompt_2_ResponseTime;
    private String test_3_prompt_1_ResponseTime;
    private String test_4_prompt_1_ResponseTime;
}
