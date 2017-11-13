package tag_for_petri;

/**
 * Created by 李炆睿 on 2017/10/15.
 */
public class Inscription extends Tag{
    public Inscription() {
        this.addChild(new Value("Default,1"));
        this.addChild(new Graphic());
    }

    @Override
    public String getName() {
        return "inscription";
    }
}
