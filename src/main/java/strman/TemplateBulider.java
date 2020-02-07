package strman;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TemplateBulider {

    private static final String REGEX = "<=(.*?)\\>";

    private final String template;
    private final Map<String, String> properties;

    public TemplateBulider(String template) {
        this.template = template;
        this.properties = buildProperties(template);
    }

    public String execute() {
        String result = template;
        for (Map.Entry<String, String> entry : properties.entrySet()) {
            if (null == entry.getValue())
                continue;
            String key = "<=" + entry.getKey() + ">";
            result = result.replace(key, entry.getValue());
        }
        return result;
    }

    public void add(String key, String value) {
        if (!properties.containsKey(key))
            throw new RuntimeException("No veriable definitation \"" + key + "\"");
        properties.replace(key, value);
    }

    public void add(String key, Number value) {
        add(key, value.toString());
    }

    protected static Map<String, String> buildProperties(String template) {
        Map<String, String> valueMap = new HashMap<>();
        Pattern patten = Pattern.compile(REGEX);
        Matcher m = patten.matcher(template);
        while (m.find()) {
            String key = m.group().substring(2, m.group().length() - 1);
            if (valueMap.containsKey(key))
                throw new RuntimeException("Duplicate value definition \"" + key + "\"");
            valueMap.put(key, null);
        }
        return valueMap;
    }

    @Override
    public String toString() {
        return template;
    }
}
