package tag_for_petri;

/**
 * Created by 李炆睿 on 2017/10/15.
 */
public class Timed extends Tag {
    public Timed() {
        this.addChild(new Value("false"));
    }

    @Override
    public String getName() {
        return "timed";
    }
}
