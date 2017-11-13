package tag_for_petri;

/**
 * Created by 李炆睿 on 2017/10/15.
 */
public class Type extends Tag{
    public Type() {
        this.addAttribute(new Attribute("value","normal"));
    }
    @Override
    public String getName() {
        return "type";
    }
}
