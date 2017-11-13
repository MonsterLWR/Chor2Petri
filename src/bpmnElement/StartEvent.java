package bpmnElement;

import layout.Shapeable;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.ArrayList;

// 开始事件
public class StartEvent implements Elementable, Shapeable{
	private String id;
	private String isInterrupting;
	private String name;
	private String parallelMultiple;
	private String outputSet;
	private ArrayList<String> outgoing = new ArrayList<String>();

	public StartEvent() {

	}

	public StartEvent(Element element) {
		id = element.attributeValue("id");
		isInterrupting = element.attributeValue("isInterrupting");
		name = element.attributeValue("name");
		parallelMultiple = element.attributeValue("parallelMultiple");
		for (Element e : element.elements()) {
			switch (e.getName()) {
				case "outgoing":
					outgoing.add(e.getText());
					break;
				case "outputSet":
					outputSet = e.getText();
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

	public String getIsInterrupting() {
		return isInterrupting;
	}

	public void setIsInterrupting(String isInterrupting) {
		this.isInterrupting = isInterrupting;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParallelMultiple() {
		return parallelMultiple;
	}

	public void setParallelMultiple(String parallelMultiple) {
		this.parallelMultiple = parallelMultiple;
	}

	public String getOutputSet() {
		if (outputSet==null)
			return "";
		return outputSet;
	}

	public void setOutputSet(String outputSet) {
		this.outputSet = outputSet;
	}

	public ArrayList<String> getOutgoing() {
		return outgoing;
	}

	public void setOutgoing(ArrayList<String> outgoing) {
		this.outgoing = outgoing;
	}

	public Element toElement() {
		Element startEvent = DocumentHelper.createElement("startEvent");
//		添加属性
		startEvent.addAttribute("id", getId());
		startEvent.addAttribute("isInterrupting", getIsInterrupting());
		startEvent.addAttribute("name", getName());
		startEvent.addAttribute("parallelMultiple", getParallelMultiple());
//		添加子标签
		Element outputSet = startEvent.addElement("outputSet");
		outputSet.addText(getOutputSet());

		for (String s : getOutgoing()) {
			Element outgoing = startEvent.addElement("outgoing");
			outgoing.addText(s);
		}

		//返回标签
		return startEvent;
	}

	@Override
	public String toString() {
		return "StartEvent{" +
				"id='" + id + '\'' +
				", isInterrupting='" + isInterrupting + '\'' +
				", name='" + name + '\'' +
				", parallelMultiple='" + parallelMultiple + '\'' +
				", outputSet='" + outputSet + '\'' +
				", outgoing=" + outgoing +
				'}';
	}
}
