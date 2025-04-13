package org.Shiv.utils;

import static java.lang.System.getProperty;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Properties;
import org.json.JSONObject;
import org.json.JSONArray;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;

import com.google.gson.JsonObject;

public class FileUtil {
    public static Properties readPropertyFile(String filename) {
        FileInputStream input = null;
        try {
            input = new FileInputStream(filename);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Properties prop = new Properties();
        try {
            prop.load(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return prop;
    }
    public static void updateJsonFile(String key, String value) {
        try {
            // Read the JSON file
            String filePath = Path.of (getProperty ("user.dir"), "src/main/resources/AIResponse.json")
                .toString ();
            File file = new File(filePath);
            JSONObject jsonObject = new JSONObject (readFile(file));

            // Update the value
            jsonObject.put (key, value);

            // Write the updated JSON to the file
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(jsonObject.toString ());
            fileWriter.close();

        } catch (Exception e) {
            System.out.println("Error updating JSON file: " + e.getMessage());
        }
    }

    private static String readFile(File file) throws IOException {
        FileReader fileReader = new FileReader(file);
        StringBuilder stringBuilder = new StringBuilder();
        int character;
        while ((character = fileReader.read()) != -1) {
            stringBuilder.append((char) character);
        }
        fileReader.close();
        return stringBuilder.toString();
    }

}
