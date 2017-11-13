package tag_for_petri;

/**
 * Created by 李炆睿 on 2017/10/15.
 */
public class Tagged extends Tag{
    public Tagged() {
        this.addChild(new Value("false"));
    }

    @Override
    public String getName() {
        return "tagged";
    }
}
