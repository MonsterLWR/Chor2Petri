package bpmnElement;

import layout.Flowable;
import layout.Shapeable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李炆睿 on 2017/10/10.
 * 用于保存BPMN各种元素类型的List
 */
public class BPMN_elements {
    private List<ChoreographyTask> choreographyTaskList = new ArrayList<ChoreographyTask>();
    private List<EndEvent> endEventList = new ArrayList<EndEvent>();
    private List<IntermediateThrowEvent> intermediateThrowEventList = new ArrayList<IntermediateThrowEvent>();
    private List<EventBasedGateway> eventBasedGatewayList = new ArrayList<EventBasedGateway>();
    private List<ExclusiveGateway> exclusiveGatewayList = new ArrayList<ExclusiveGateway>();
    private List<MessageFlow> messageFlowList = new ArrayList<MessageFlow>();
    private List<ParallelGateway> parallelGatewayList = new ArrayList<ParallelGateway>();
    private List<Participant> participantList = new ArrayList<Participant>();
    private List<SequenceFlow> sequenceFlowList = new ArrayList<SequenceFlow>();
    private List<StartEvent> startEventList = new ArrayList<StartEvent>();
    private List<GateWay> gateWayList = new ArrayList<GateWay>();
    private List<Message> messageList = new ArrayList<Message>();
    private List<Association> associationList = new ArrayList<Association>();

    public List<Shapeable> getShapeableList() {
        List<Shapeable> shapeableList = new ArrayList<>();
        shapeableList.addAll(choreographyTaskList);
        shapeableList.addAll(endEventList);
        shapeableList.addAll(intermediateThrowEventList);
        shapeableList.addAll(startEventList);
        shapeableList.addAll(gateWayList);
        shapeableList.addAll(messageList);
        return shapeableList;
    }

    public List<Flowable> getFlowableList() {
        List<Flowable> flowableList = new ArrayList<>();
        flowableList.addAll(sequenceFlowList);
        flowableList.addAll(associationList);
        return flowableList;
    }

    public List<ChoreographyTask> getChoreographyTaskList() {
        return choreographyTaskList;
    }

    public void setChoreographyTaskList(List<ChoreographyTask> choreographyTaskList) {
        this.choreographyTaskList = choreographyTaskList;
    }

    public List<EndEvent> getEndEventList() {
        return endEventList;
    }

    public void setEndEventList(List<EndEvent> endEventList) {
        this.endEventList = endEventList;
    }

    public List<IntermediateThrowEvent> getIntermediateThrowEventList() {
        return intermediateThrowEventList;
    }

    public void setIntermediateThrowEventList(List<IntermediateThrowEvent> intermediateThrowEventList) {
        this.intermediateThrowEventList = intermediateThrowEventList;
    }

    public List<EventBasedGateway> getEventBasedGatewayList() {
        return eventBasedGatewayList;
    }

    public void setEventBasedGatewayList(List<EventBasedGateway> eventBasedGatewayList) {
        this.eventBasedGatewayList = eventBasedGatewayList;
    }

    public List<ExclusiveGateway> getExclusiveGatewayList() {
        return exclusiveGatewayList;
    }

    public void setExclusiveGatewayList(List<ExclusiveGateway> exclusiveGatewayList) {
        this.exclusiveGatewayList = exclusiveGatewayList;
    }

    public List<MessageFlow> getMessageFlowList() {
        return messageFlowList;
    }

    public void setMessageFlowList(List<MessageFlow> messageFlowList) {
        this.messageFlowList = messageFlowList;
    }

    public List<ParallelGateway> getParallelGatewayList() {
        return parallelGatewayList;
    }

    public void setParallelGatewayList(List<ParallelGateway> parallelGatewayList) {
        this.parallelGatewayList = parallelGatewayList;
    }

    public List<Participant> getParticipantList() {
        return participantList;
    }

    public void setParticipantList(List<Participant> participantList) {
        this.participantList = participantList;
    }

    public List<SequenceFlow> getSequenceFlowList() {
        return sequenceFlowList;
    }

    public void setSequenceFlowList(List<SequenceFlow> sequenceFlowList) {
        this.sequenceFlowList = sequenceFlowList;
    }

    public List<StartEvent> getStartEventList() {
        return startEventList;
    }

    public void setStartEventList(List<StartEvent> startEventList) {
        this.startEventList = startEventList;
	}

	public List<GateWay> getGateWayList() {
		return gateWayList;
	}

	public void setGateWayList(List<GateWay> gateWayList) {
		this.gateWayList = gateWayList;
	}

	public List<Message> getMessageList() {
		return messageList;
	}

	public void setMessageList(List<Message> messageList) {
		this.messageList = messageList;
	}

	public List<Association> getAssociationList() {
		return associationList;
	}

	public void setAssociationList(List<Association> associationList) {
		this.associationList = associationList;
	}

    @Override
    public String toString() {
        return "BPMN_elements{" +
                "\n choreographyTaskList=" + choreographyTaskList +
                "\n endEventList=" + endEventList +
                "\n intermediateThrowEventList=" + intermediateThrowEventList +
                "\n eventBasedGatewayList=" + eventBasedGatewayList +
                "\n exclusiveGatewayList=" + exclusiveGatewayList +
                "\n messageFlowList=" + messageFlowList +
                "\n parallelGatewayList=" + parallelGatewayList +
                "\n participantList=" + participantList +
                "\n sequenceFlowList=" + sequenceFlowList +
                "\n startEventList=" + startEventList +
                "\n gateWayList=" + gateWayList +
                "\n messageList=" + messageList +
                "\n associationList=" + associationList +
                '}';
    }
}
