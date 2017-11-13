package petriElement;

import layout.Shapeable;
import tag_for_petri.*;

/**
 * Created by 李炆睿 on 2017/10/15.
 */
public class Transition extends Tag implements Shapeable{
    public Transition(String id,String _name) {
//        初始化默认子标签
        this.addAttribute(new Attribute("id", id));

        Graphic graphic = new Graphic();
        graphic.addChild(new Position());
        this.addChild(graphic);

        Name name = new Name();
        name.getChild("value").setText(_name);
        this.addChild(name);

        this.addChild(new Orientation());

        this.addChild(new Rate());

        this.addChild(new Timed());

        this.addChild(new InfiniteServer());

        this.addChild(new Priority());
    }

    @Override
    public String getName() {
        return "transition";
    }

    private String x = "0.0";
    private String y = "0.0";

    @Override
    public void setX(String x) {
        this.x = x;
        Tag graphics = this.getChild("graphics");
        Tag position = graphics.getChild("position");
        position.setAttribute(new Attribute("x",x));
    }

    @Override
    public String getX() {
        return x;
    }

    @Override
    public void setY(String y) {
        this.y = y;
        Tag graphics = this.getChild("graphics");
        Tag position = graphics.getChild("position");
        position.setAttribute(new Attribute("y",y));
    }

    @Override
    public String getY() {
        return y;
    }

    @Override
    public String getWidth() {
        return "40.0";
    }

    @Override
    public String getHeight() {
        return "40.0";
    }

    @Override
    public String getId() {
        for (Attribute attribute : getAttributes()) {
            if (attribute.getKey().equals("id")) {
                return attribute.getValue();
            }
        }
        return null;
    }
}
