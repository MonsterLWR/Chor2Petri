package bpmnElement;

import layout.Shapeable;
import org.dom4j.Element;

import java.util.ArrayList;

public class GateWay implements Cloneable, Elementable, Shapeable {
    protected String gatewayDirection;
    protected String id;
    protected String name;
    protected ArrayList<String> incoming = new ArrayList<String>();
    protected ArrayList<String> outgoing = new ArrayList<String>();

    @Override
    public String toString() {
        return "GateWay [gatewayDirection=" + gatewayDirection + ", id=" + id + ", name=" + name + ", incoming="
                + incoming + ", outgoing=" + outgoing + "]";
    }

    public String getGatewayDirection() {
        return gatewayDirection;
    }

    public void setGatewayDirection(String gatewayDirection) {
        this.gatewayDirection = gatewayDirection;
    }

    @Override
    public Element toElement() {
        return null;
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

    public GateWay clone() throws CloneNotSupportedException {
        GateWay gateWay = (GateWay) super.clone();
        gateWay.incoming = (ArrayList<String>) this.incoming.clone();
        gateWay.outgoing = (ArrayList<String>) this.outgoing.clone();
        return gateWay;
    }
}
