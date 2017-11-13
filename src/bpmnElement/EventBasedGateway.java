package bpmnElement;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.ArrayList;

public class EventBasedGateway extends GateWay implements Elementable{
    //属性
    private String eventGatewayType; //网关类型， 只考虑Exclusive时
    private String instantiate;      //实例

    public EventBasedGateway() {

    }

    public EventBasedGateway(Element element) {
        eventGatewayType = element.attributeValue("eventGatewayType");
        gatewayDirection = element.attributeValue("gatewayDirection");
        id = element.attributeValue("id");
        instantiate = element.attributeValue("instantiate");
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
    public String getEventGatewayType() {
        return eventGatewayType;
    }

    public void setEventGatewayType(String eventGatewayType) {
        this.eventGatewayType = eventGatewayType;
    }

    public String getGatewayDirection() {
        return gatewayDirection;
    }

    public void setGatewayDirection(String gatewayDirection) {
        this.gatewayDirection = gatewayDirection;
    }

    @Override
    public Element toElement() {
        Element eventBasedGateway = DocumentHelper.createElement("eventBasedGateway");
//		添加属性
        eventBasedGateway.addAttribute("id", getId());
        eventBasedGateway.addAttribute("name", getName());
        eventBasedGateway.addAttribute("gatewayDirection", getGatewayDirection());
        eventBasedGateway.addAttribute("eventGatewayType", getEventGatewayType());
        eventBasedGateway.addAttribute("instantiate", getInstantiate());
//		添加子标签
        for (String s : getIncoming()) {
            Element incoming = eventBasedGateway.addElement("incoming");
            incoming.addText(s);
        }
        for (String s : getOutgoing()) {
            Element outgoing = eventBasedGateway.addElement("outgoing");
            outgoing.addText(s);
        }

        //返回标签
        return eventBasedGateway;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInstantiate() {
        return instantiate;
    }

    public void setInstantiate(String instantiate) {
        this.instantiate = instantiate;
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
        return "EventBasedGateway{" +
                "eventGatewayType='" + eventGatewayType + '\'' +
                ", gatewayDirection='" + gatewayDirection + '\'' +
                ", id='" + id + '\'' +
                ", instantiate='" + instantiate + '\'' +
                ", name='" + name + '\'' +
                ", incoming=" + incoming +
                ", outgoing=" + outgoing +
                '}';
    }


}
