package pre_process_rule;

import bpmnElement.*;
import org.dom4j.DocumentException;
import xml_operation.BPMN_reader;

import java.util.ArrayList;
import java.util.List;

public class PreRule7 extends PreRule {

    @Override
    public void excute(BPMN_elements bpmn_elements) {
        List<GateWay> gateWays = bpmn_elements.getGateWayList();
        for (int i = 0; i < gateWays.size(); ++i) {
            GateWay gateWay = gateWays.get(i);
            for (int j = 0; j < gateWays.size(); ++j) {
                GateWay gateWay1 = gateWays.get(j);
                List<SequenceFlow> sequenceFlows = bpmn_elements.getSequenceFlowList();
                for (int a = 0; a < sequenceFlows.size(); ++a) {
                    SequenceFlow sequenceFlow = sequenceFlows.get(a);
                    if (sequenceFlow.getSourceRef().equals(gateWay.getId()) && sequenceFlow.getTargetRef().equals(gateWay1.getId())) {
                        ChoreographyTask choreographyTask = new ChoreographyTask();
                        choreographyTask.setId(GenID.getId());
                        choreographyTask.setLoopType("None");
                        choreographyTask.setName("Choreography &#10; Task");

                        sequenceFlow.setTargetRef(choreographyTask.getId());
                        SequenceFlow flow = new SequenceFlow();
                        flow.setId(GenID.getId());
                        flow.setSourceRef(choreographyTask.getId());
                        flow.setTargetRef(gateWay1.getId());
                        sequenceFlows.add(flow);

                        ArrayList<String> coming = new ArrayList<>();
                        coming.add(flow.getId());
                        gateWay1.setIncoming(coming);

                        coming = new ArrayList<>();
                        coming.add(sequenceFlow.getId());
                        choreographyTask.setIncoming(coming);
                        coming = new ArrayList<>();
                        coming.add(flow.getId());
                        choreographyTask.setOutgoing(coming);
                        List<Participant> participants = bpmn_elements.getParticipantList();

                        Participant participant1 = new Participant();
                        participant1.setId(GenID.getId());
                        participant1.setName("Participant A");
                        participant1.setParticipantMultiplicity_maximum("1");
                        participant1.setParticipantMultiplicity_minimum("0");
                        bpmn_elements.getParticipantList().add(participant1);
                        Participant participant2 = new Participant();
                        participant2.setId(GenID.getId());
                        participant2.setName("Participant B");
                        participant2.setParticipantMultiplicity_maximum("1");
                        participant2.setParticipantMultiplicity_minimum("0");
                        bpmn_elements.getParticipantList().add(participant2);

                        choreographyTask.setInitiatingParticipantRef(participant1.getId());
                        List<String> parts = new ArrayList<String>();
                        parts.add(participant1.getId());
                        parts.add(participant2.getId());
                        choreographyTask.setParticipantRef(parts);
                        List<MessageFlow> messageFlows = bpmn_elements.getMessageFlowList();

                        MessageFlow messageFlow1 = new MessageFlow();
                        messageFlow1.setId("_1" + participant1.getId() + participant2.getId());
                        messageFlow1.setSourceRef(participant1.getId());
                        messageFlow1.setTargetRef(participant2.getId());
                        bpmn_elements.getMessageFlowList().add(messageFlow1);
                        MessageFlow messageFlow2 = new MessageFlow();
                        messageFlow2.setId("_1" + participant2.getId() + participant1.getId());
                        messageFlow2.setSourceRef(participant2.getId());
                        messageFlow2.setTargetRef(participant1.getId());
                        bpmn_elements.getMessageFlowList().add(messageFlow2);

                        List<String> flows = new ArrayList<String>();
                        flows.add(messageFlow1.getId());
                        flows.add(messageFlow2.getId());
                        choreographyTask.setMessageFlowRef(flows);
                        bpmn_elements.getChoreographyTaskList().add(choreographyTask);
                    }
                }
            }
        }
    }


    public static void main(String[] args) throws DocumentException {
        BPMN_reader reader = new BPMN_reader();
        BPMN_elements elements = reader.read_bpmn("/home/thinkpad/VirtualBox VMs/共享文件夹/3.bpmn");
        PreRule7 rule = new PreRule7();
        rule.excute(elements);
        System.out.println("------------------------------");
        List<ExclusiveGateway> exclusiveGateways = elements.getExclusiveGatewayList();
        for (ExclusiveGateway gateway : exclusiveGateways) {
            System.out.println(gateway);
        }

        List<SequenceFlow> sequenceFlows = elements.getSequenceFlowList();
        for (SequenceFlow sequenceFlow : sequenceFlows) {
            System.out.println(sequenceFlow);
        }

        List<MessageFlow> messageFlows = elements.getMessageFlowList();
        for (MessageFlow messageFlow : messageFlows) {
            System.out.println(messageFlow);
        }

        List<Participant> participants = elements.getParticipantList();
        for (Participant participant : participants) {
            System.out.println(participant);
        }

        List<ChoreographyTask> choreographyTasks = elements.getChoreographyTaskList();
        for (ChoreographyTask choreographyTask : choreographyTasks) {
            System.out.println(choreographyTask);
        }

        List<GateWay> gateways = elements.getGateWayList();
        for (GateWay gateway : gateways) {
            System.out.println(gateway);
        }
    }

}
