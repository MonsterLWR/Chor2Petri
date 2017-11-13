package tag_for_petri;

/**
 * Created by 李炆睿 on 2017/10/15.
 */
public class InfiniteServer extends Tag {
    public InfiniteServer() {
        this.addChild(new Value("false"));
    }

    @Override
    public String getName() {
        return "infiniteServer";
    }
}
