package tag_for_petri;

/**
 * Created by 李炆睿 on 2017/10/15.
 */
public class Rate extends Tag {
    public Rate() {
        this.addChild(new Value("1.0"));
    }

    @Override
    public String getName() {
        return "rate";
    }
}
