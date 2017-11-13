package bpmnElement;

import layout.Shapeable;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.ArrayList;

public class IntermediateThrowEvent implements Elementable, Shapeable{
    private String id;
    private String name;
    private ArrayList<String> incoming= new ArrayList<String>();
    private ArrayList<String> outgoing= new ArrayList<String>();
    private String inputSet;

    public IntermediateThrowEvent() {

    }

    public IntermediateThrowEvent(Element element){
        id = element.attributeValue("id");
        name = element.attributeValue("name");
        for (Element e : element.elements()) {
            switch (e.getName()) {
                case "incoming":
                    incoming.add(e.getText());
                    break;
                case "outgoing":
                    outgoing.add(e.getText());
                    break;
                case "inputSet":
                    inputSet = e.getText();
                    break;
            }
        }
    }

    @Override
    public Element toElement() {
        Element intermediateThrowEvent = DocumentHelper.createElement("intermediateThrowEvent");
//		添加属性
        intermediateThrowEvent.addAttribute("id", getId());
        intermediateThrowEvent.addAttribute("name", getName());
//		添加子标签
        Element inputSet = intermediateThrowEvent.addElement("inputSet");
        inputSet.addText(getInputSet());

        for (String s : getOutgoing()) {
            Element outgoing = intermediateThrowEvent.addElement("outgoing");
            outgoing.addText(s);
        }
        for (String s : getIncoming()) {
            Element incoming = intermediateThrowEvent.addElement("outgoing");
            incoming.addText(s);
        }

        //返回标签
        return intermediateThrowEvent;
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
        return "42.0";
    }

    @Override
    public String getHeight() {
        return "42.0";
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

    public ArrayList<String> getOutgoing() {
        return outgoing;
    }

    public void setOutgoing(ArrayList<String> outgoing) {
        this.outgoing = outgoing;
    }

    public String getInputSet() {
        if (inputSet == null) {
            return "";
        }else
            return inputSet;
    }

    public void setInputSet(String inputSet) {
        this.inputSet = inputSet;
    }

    @Override
    public String toString() {
        return "IntermediateThrowEvent{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", incoming=" + incoming +
                ", outgoing=" + outgoing +
                ", inputSet='" + inputSet + '\'' +
                '}';
    }
}
