package petriElement;

import layout.Flowable;
import tag_for_petri.*;

/**
 * Created by 李炆睿 on 2017/10/15.
 */
public class Arc extends Tag implements Flowable {
    public Arc(String id,String source,String target) {
        this.addAttribute(new Attribute("id",id));
        this.addAttribute(new Attribute("source",source));
        this.addAttribute(new Attribute("target",target));

        this.addChild(new Graphic());

        this.addChild(new Inscription());

        this.addChild(new Tagged());

        this.addChild(new Arcpath("000"));
        this.addChild(new Arcpath("001"));

        this.addChild(new Type());
    }

    @Override
    public String getName() {
        return "arc";
    }

    @Override
    public String getSourceRef() {
        for (Attribute attribute : getAttributes()) {
            if (attribute.getKey().equals("source")) {
                return attribute.getValue();
            }
        }
        return null;
    }

    @Override
    public String getTargetRef() {
        for (Attribute attribute : getAttributes()) {
            if (attribute.getKey().equals("target")) {
                return attribute.getValue();
            }
        }
        return null;
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
