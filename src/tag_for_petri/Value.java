package tag_for_petri;

/**
 * Created by 李炆睿 on 2017/10/14.
 */
public class Value extends Tag {
    public Value() {
    }
    public Value(String text) {
        this.setText(text);
    }

    public String getName() {
        return "value";
    }
}
