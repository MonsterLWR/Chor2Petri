package tag_for_petri;

/**
 * Created by 李炆睿 on 2017/10/15.
 */
public class Capacity extends Tag{
    public Capacity() {
        this.addChild(new Value("0"));
    }
    @Override
    public String getName() {
        return "capacity";
    }
}
