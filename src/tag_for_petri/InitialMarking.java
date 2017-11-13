package tag_for_petri;

/**
 * Created by 李炆睿 on 2017/10/15.
 */
public class InitialMarking extends Tag{
    public InitialMarking() {
        //        默认子标签
        this.addChild(new Value("Default,0"));

        Graphic graphic_for_initial = new Graphic();
        graphic_for_initial.addChild(new Offset());
        this.addChild(graphic_for_initial);
    }

    @Override
    public String getName() {
        return "initialMarking";
    }
}
