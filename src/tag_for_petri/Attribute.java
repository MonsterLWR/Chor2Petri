package tag_for_petri;

/**
 * Created by 李炆睿 on 2017/10/14.
 */
public class Attribute {
    private String key;
    private String value;

    public Attribute(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Attribute{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
