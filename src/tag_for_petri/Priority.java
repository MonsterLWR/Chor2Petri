package tag_for_petri;

/**
 * Created by 李炆睿 on 2017/10/15.
 */
public class Priority extends Tag{
    public Priority() {
        this.addChild(new Value("1"));
    }

    @Override
    public String getName() {
        return "priority";
    }
}
