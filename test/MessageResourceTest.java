import org.junit.Test;

import java.util.Locale;
import java.util.ResourceBundle;

import static org.junit.Assert.*;

public class MessageResourceTest {
    @Test
    public void basicTest() throws Exception {
        MessageResource messageResource = MessageResource.getInstance();
        ResourceBundle resourceBundle = messageResource.getResourceBundle("resource/strings.properties", new Locale("pl"));
        assertEquals("polski", resourceBundle.getString("LANGUAGE"));

        resourceBundle = messageResource.getResourceBundle("resource/strings.properties",new Locale("xxx"));
        assertEquals("default", resourceBundle.getString("LANGUAGE"));

        Locale.setDefault(new Locale("pl"));
        resourceBundle = messageResource.getResourceBundle("resource/strings.properties");
        assertEquals("polski", resourceBundle.getString("LANGUAGE"));
    }

    @Test
    public void countrySignTest() throws Exception {
        MessageResource messageResource = MessageResource.getInstance();
        ResourceBundle resourceBundle = messageResource.getResourceBundle("resource/strings.properties", new Locale("pl"));

        System.out.println(resourceBundle.getString("COUNTRY_SIGNS"));

    }
}