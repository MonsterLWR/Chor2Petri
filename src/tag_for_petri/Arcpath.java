package tag_for_petri;

import org.junit.Test;

/**
 * Created by 李炆睿 on 2017/10/15.
 */
public class Arcpath extends Tag{
    public Arcpath(String id) {
        this.addAttribute(new Attribute("id", id));
        this.addAttribute(new Attribute("x","0"));
        this.addAttribute(new Attribute("y","0"));
        this.addAttribute(new Attribute("curvePoint","false"));
    }

    @Override
    public String getName() {
        return "arcpath";
    }
}
