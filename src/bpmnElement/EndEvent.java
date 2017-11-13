package bpmnElement;

import layout.Shapeable;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.ArrayList;

// 结束事件
public class EndEvent implements Elementable, Shapeable {
    private String id;
    private String name;
    private ArrayList<String> incoming = new ArrayList<String>();
    private String inputSet;

    public EndEvent() {

    }

    public EndEvent(Element element) {
        id = element.attributeValue("id");
        name = element.attributeValue("name");
        for (Element e : element.elements()) {
            switch (e.getName()) {
                case "incoming":
                    incoming.add(e.getText());
                    break;
                case "inputSet":
                    inputSet = e.getText();
                    break;
            }
        }
    }

    private String x;
    private String y;

    @Override
    public String getX() {
        return x;
    }

    @Override
    public void setX(String x) {
        this.x = x;
    }

    @Override
    public String getY() {
        return y;
    }

    @Override
    public void setY(String y) {
        this.y = y;
    }

    @Override
    public String getWidth() {
        return "32.0";
    }

    @Override
    public String getHeight() {
        return "32.0";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getIncoming() {
        return incoming;
    }

    public void setIncoming(ArrayList<String> incoming) {
        this.incoming = incoming;
    }

    public String getInputSet() {
        if (inputSet == null) {
            return "";
        }
        return inputSet;
    }

    public void setInputSet(String inputSet) {
        this.inputSet = inputSet;
    }

    public Element toElement() {
        Element endEvent = DocumentHelper.createElement("endEvent");
//		添加属性
        endEvent.addAttribute("id", getId());
        endEvent.addAttribute("name", getName());
//		添加子标签
        Element inputSet = endEvent.addElement("inputSet");
        inputSet.addText(getInputSet());

        for (String s : getIncoming()) {
            Element incoming = endEvent.addElement("incoming");
            incoming.addText(s);
        }

        //返回标签
        return endEvent;
    }

    @Override
    public String toString() {
        return "EndEvent{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", incoming=" + incoming +
                ", inputSet='" + inputSet + '\'' +
                '}';
    }
}
