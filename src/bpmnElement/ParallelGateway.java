package bpmnElement;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.ArrayList;

// 并发网关
public class ParallelGateway extends GateWay implements Elementable{

	public  ParallelGateway() {

	}

	public ParallelGateway(Element element){
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

	public String getGatewayDirection() {
		return gatewayDirection;
	}

	public void setGatewayDirection(String gatewayDirection) {
		this.gatewayDirection = gatewayDirection;
	}

	@Override
	public Element toElement() {
		Element parallelGateway = DocumentHelper.createElement("parallelGateway");
//		添加属性
		parallelGateway.addAttribute("id", getId());
		parallelGateway.addAttribute("name", getName());
		parallelGateway.addAttribute("gatewayDirection", getGatewayDirection());
//		添加子标签
		for (String s : getIncoming()) {
			Element incoming = parallelGateway.addElement("incoming");
			incoming.addText(s);
		}
		for (String s : getOutgoing()) {
			Element outgoing = parallelGateway.addElement("outgoing");
			outgoing.addText(s);
		}

		//返回标签
		return parallelGateway;
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
		return "ParallelGateway{" +
				"gatewayDirection='" + gatewayDirection + '\'' +
				", id='" + id + '\'' +
				", name='" + name + '\'' +
				", incoming=" + incoming +
				", outgoing=" + outgoing +
				'}';
	}
}
