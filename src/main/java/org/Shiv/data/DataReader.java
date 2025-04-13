package org.Shiv.data;

import static java.lang.System.getProperty;
import static java.lang.System.getenv;
import static java.util.Optional.ofNullable;

import java.nio.file.Path;

import lombok.Getter;
import org.Shiv.utils.JsonUtil;

@Getter
public class DataReader {

    public static AIProps readAIProps () {
        AIProps aiProps = null;
        if (aiProps == null) {
            String defaultPath = Path.of (getProperty ("user.dir"), "src/main/resources/")
                    .toString ();
            String configDirectory = ofNullable (getenv ("LOGIN_PROPS_PATH")).orElse (
                    ofNullable (getProperty ("login.props.path")).orElse (defaultPath));
            String configPath = Path.of (configDirectory, "AI_props.json")
                    .toString ();
            aiProps = JsonUtil.fromFile (configPath, AIProps.class);
        }
        return aiProps;
    }
    public static AIResponse aiResponseProps () {
        AIResponse aiResponse = null;
        if (aiResponse == null) {
            String defaultPath = Path.of (getProperty ("user.dir"), "src/main/resources/")
                .toString ();
            String configDirectory = ofNullable (getenv ("LOGIN_PROPS_PATH")).orElse (
                ofNullable (getProperty ("login.props.path")).orElse (defaultPath));
            String configPath = Path.of (configDirectory, "AIResponse.json")
                .toString ();
            aiResponse = JsonUtil.fromFile (configPath, AIResponse.class);
        }
        return aiResponse;
    }
}