package tag_for_petri;

import tag_for_petri.Attribute;
import tag_for_petri.Tag;

/**
 * Created by 李炆睿 on 2017/10/15.
 */
public class Net extends Tag{
    public Net() {
        this.addAttribute(new Attribute("id","Net-One"));
        this.addAttribute(new Attribute("type","P/T net"));
    }
    @Override
    public String getName() {
        return "net";
    }
}
