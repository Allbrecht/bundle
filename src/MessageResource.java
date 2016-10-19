import java.io.*;
import java.util.HashSet;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;


/**
 * in order to add new supported language add string_new.properties in bundles folder and add
 * supportedLocales.add(new Locale("new"));  in constructor, where 'new' is locale.getLanguage() for new language.
 */
public class MessageResource implements Translator{
    private static MessageResource ourInstance = new MessageResource();
    private PropertyResourceBundle propertyResourceBundle;
    private static HashSet<Locale> supportedLocales; // without default language

    public static MessageResource getInstance() {
        return ourInstance;
    }

    private MessageResource() {
        supportedLocales = new HashSet<>();
        supportedLocales.add(new Locale("pl"));
    }

    /**
     * @param relativePathToDefaultPropertyFile must end with: /strings.properties , where strings.properties is default property file.
     */
    public ResourceBundle getResourceBundle(String relativePathToDefaultPropertyFile, Locale locale) {

        if(supportedLocales.contains(locale)) {
            relativePathToDefaultPropertyFile = relativePathToDefaultPropertyFile.replace(".properties", "_" + locale.getLanguage() + ".properties");
        }
        InputStream is = null;
        try {
            is = new FileInputStream(relativePathToDefaultPropertyFile);
            propertyResourceBundle = new PropertyResourceBundle(new BufferedInputStream(is));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return propertyResourceBundle;
    }

    public ResourceBundle getResourceBundle(String relativePathToDefaultPropertyFile){
        return getResourceBundle(relativePathToDefaultPropertyFile, Locale.getDefault());
    }

    @Override
    public String getText(String id) {
        return null;
    }

    @Override
    public void setLanguage(String language) {

    }
}
