package tag_for_petri;

import org.junit.Test;

/**
 * Created by 李炆睿 on 2017/10/14.
 */
public class Position extends Tag {
    public Position() {
        this.addAttribute(new Attribute("x","0.0"));
        this.addAttribute(new Attribute("y","0.0"));
    }

    public String getName() {
        return "position";
    }
    @Test
    public void position_test() {
        Position position = new Position();
        System.out.println(position);
    }
}
