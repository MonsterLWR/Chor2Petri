package bpmnElement;

import org.dom4j.Element;

/**
 * Created by 李炆睿 on 2017/10/21.
 */
public interface Elementable {
    Element toElement();
    String getId();
}
