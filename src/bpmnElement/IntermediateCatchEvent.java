package bpmnElement;

import org.dom4j.Element;

import java.util.ArrayList;

public class IntermediateCatchEvent {
    private String id;
    private String name;
    private String parallelMultiple;
    private ArrayList<String> incoming = new ArrayList<String>();
    private ArrayList<String> outgoing = new ArrayList<String>();
    private String outputSet;

    public  IntermediateCatchEvent() {

    }

    public  IntermediateCatchEvent(Element element) {
        id = element.attributeValue("id");
        name = element.attributeValue("name");
        parallelMultiple = element.attributeValue("parallelMultiple");
        for (Element e : element.elements()) {
            switch (e.getName()) {
                case "incoming":
                    incoming.add(e.getText());
                    break;
                case "outgoing":
                    outgoing.add(e.getText());
                    break;
                case "outputSet":
                    outputSet = e.getText();
                    break;
            }
        }
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

    public String getParallelMultiple() {
        return parallelMultiple;
    }

    public void setParallelMultiple(String parallelMultiple) {
        this.parallelMultiple = parallelMultiple;
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

    public String getOutputSet() {
        return outputSet;
    }

    public void setOutputSet(String outputSet) {
        this.outputSet = outputSet;
    }

    @Override
    public String toString() {
        return "IntermediateCatchEvent{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", parallelMultiple='" + parallelMultiple + '\'' +
                ", incoming=" + incoming +
                ", outgoing=" + outgoing +
                ", outputSet='" + outputSet + '\'' +
                '}';
    }
}
