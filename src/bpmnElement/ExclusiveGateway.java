package bpmnElement;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.ArrayList;

//这是第一种普通的选择网关
public class ExclusiveGateway extends GateWay implements Elementable{

    public ExclusiveGateway() {

    }

    public ExclusiveGateway(Element element) {
        gatewayDirection = element.attributeValue("gatewayDirection");
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
            }
        }
    }

    //get,set方法
    public String getGatewayDirection() {
        return gatewayDirection;
    }

    public void setGatewayDirection(String gatewayDirection) {
        this.gatewayDirection = gatewayDirection;
    }

    @Override
    public Element toElement() {
        Element exclusiveGateway = DocumentHelper.createElement("exclusiveGateway");
//		添加属性
        exclusiveGateway.addAttribute("id", getId());
        exclusiveGateway.addAttribute("name", getName());
        exclusiveGateway.addAttribute("gatewayDirection", getGatewayDirection());
//		添加子标签
        for (String s : getIncoming()) {
            Element incoming = exclusiveGateway.addElement("incoming");
            incoming.addText(s);
        }
        for (String s : getOutgoing()) {
            Element outgoing = exclusiveGateway.addElement("outgoing");
            outgoing.addText(s);
        }

        //返回标签
        return exclusiveGateway;
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

    @Override
    public String toString() {
        return "ExclusiveGateway{" +
                "gatewayDirection='" + gatewayDirection + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", incoming=" + incoming +
                ", outgoing=" + outgoing +
                '}';
    }

}
