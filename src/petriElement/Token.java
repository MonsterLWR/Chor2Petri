package petriElement;

import tag_for_petri.Attribute;
import tag_for_petri.Tag;

/**
 * Created by 李炆睿 on 2017/10/15.
 */
public class Token extends Tag{
    public Token() {
        this.addAttribute(new Attribute("id", "Default"));
        this.addAttribute(new Attribute("enabled", "true"));
        this.addAttribute(new Attribute("red", "0"));
        this.addAttribute(new Attribute("green", "0"));
        this.addAttribute(new Attribute("blue", "0"));
    }

    @Override
    public String getName() {
        return "token";
    }
}
