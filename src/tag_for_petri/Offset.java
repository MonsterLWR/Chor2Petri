package tag_for_petri;

/**
 * Created by 李炆睿 on 2017/10/14.
 */
public class Offset extends Tag {
    public Offset() {
        this.addAttribute(new Attribute("x","60.0"));
        this.addAttribute(new Attribute("y","50.0"));
    }

    public String getName() {
        return "offset";
    }
}
