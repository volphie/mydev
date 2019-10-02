package my.exercise.java.properties;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.Set;

/**
 * JavaProperties
 *
 * Source copied from https://www.mkyong.com/java/java-properties-file-examples/
 * I changed the original code to be able to read the property file according to system encoding
 *
 */

public class JavaPropertiesEncoding  {

    public static void main(String[] args) {
        JavaPropertiesEncoding app = new JavaPropertiesEncoding();
        app.printAll("properties/config.properties");
    }

    private void printAll(String filename) {

        try (InputStream input = getClass().getClassLoader().getResourceAsStream(filename)) {

            Properties prop = new Properties();

            if (input == null) {
                System.out.println("Sorry, unable to find " + filename);
                return;
            }
			
			String enc = System.getProperty("file.encoding");
			
			System.out.println("System Encoding : " + enc);

			Reader isr = new InputStreamReader(input, enc);

            prop.load(isr);

            // Java 8 , print key and values
            prop.forEach((key, value) -> System.out.println("Key : " + key + ", Value : " + value));

            // Get all keys
            prop.keySet().forEach(x -> System.out.println(x));
  
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
