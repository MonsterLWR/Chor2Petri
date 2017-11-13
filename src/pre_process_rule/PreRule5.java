package pre_process_rule;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.DocumentException;

import bpmnElement.Association;
import bpmnElement.BPMN_elements;
import bpmnElement.ChoreographyTask;
import bpmnElement.Message;
import bpmnElement.MessageFlow;
import bpmnElement.Participant;
import bpmnElement.SequenceFlow;
import xml_operation.BPMN_reader;
import xml_operation.BPMN_writer;

public class PreRule5 extends PreRule {

	@Override
	public void excute(BPMN_elements bpmn_elements) {
		List<ChoreographyTask> choreographyTasks = bpmn_elements.getChoreographyTaskList();
		int length = choreographyTasks.size();
		for (int i = 0; i < length; ++i) {
			ChoreographyTask choreographyTask = choreographyTasks.get(i);
			List<String> participants = choreographyTask.getParticipantRef();
			String participantRef1 = participants.get(0);
			String participantRef2 = participants.get(1);
			if (participantRef2.equals(choreographyTask.getInitiatingParticipantRef())) {
				String temp = participantRef1;
				participantRef1 = participantRef2;
				participantRef2 = temp;
			}
			List<Association> associations = bpmn_elements.getAssociationList();
			int num = 0;
			Association association1 = null;
			Association association2 = null;
			for (int j = 0; j < associations.size(); ++j) {
				Association association = associations.get(j);
				if (association.getSourceRef().equals(participantRef1) || association.getTargetRef().equals(participantRef1)) {
					++num;
				} else if (association.getSourceRef().equals(participantRef2)) {
					++num;
					association1 = association;
				} else if (association.getTargetRef().equals(participantRef2)) {
					++num;
					association2 = association;
				}
				if (num == 2) {
					break;
				}
			}
			if (num == 2) {
				ChoreographyTask choreographyTask2 = new ChoreographyTask();
				choreographyTask2.setId(GenID.getId());
				choreographyTask2.setLoopType("None");
				choreographyTask2.setName("Choreography &#10; Task");
				
				List<SequenceFlow> sequenceFlows = bpmn_elements.getSequenceFlowList();
				for (int k = 0; k < sequenceFlows.size(); ++k) {
					SequenceFlow sf = sequenceFlows.get(k);
					if (sf.getSourceRef().equals(choreographyTask.getId())) {
						sf.setSourceRef(choreographyTask2.getId());
					}
				}
				
				SequenceFlow sequenceFlow = new SequenceFlow();
				sequenceFlow.setId(GenID.getId());
				sequenceFlow.setSourceRef(choreographyTask.getId());
				sequenceFlow.setTargetRef(choreographyTask2.getId());
				bpmn_elements.getSequenceFlowList().add(sequenceFlow);
				
				choreographyTask2.setOutgoing(choreographyTask.getOutgoing());
				List<String> coming = new ArrayList<>();
				coming.add(sequenceFlow.getId());
				choreographyTask.setOutgoing(coming);
				
				coming = new ArrayList<>();
				coming.add(sequenceFlow.getId());
				choreographyTask2.setIncoming(coming);
				
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
				
				choreographyTask2.setInitiatingParticipantRef(participant1.getId());
				coming = new ArrayList<>();
				coming.add(participant1.getId());
				coming.add(participant2.getId());
				choreographyTask2.setParticipantRef(coming);
				coming = new ArrayList<>();
				coming.add(messageFlow1.getId());
				coming.add(messageFlow2.getId());
				choreographyTask2.setMessageFlowRef(coming);
				choreographyTasks.add(choreographyTask2);
				
				if (association1 != null) {
					association1.setSourceRef(participant2.getId());
				} else {
					association2.setTargetRef(participant2.getId());
				}
			}
		}
	}
	
	public static void main(String[] args) throws DocumentException, IOException {
		BPMN_reader reader = new BPMN_reader();
		BPMN_elements elements = reader.read_bpmn("/home/thinkpad/VirtualBox VMs/共享文件夹/5.bpmn");
		PreRule5 rule = new PreRule5();
		rule.excute(elements);
		BPMN_writer writer = new BPMN_writer(elements);
		writer.write("/home/thinkpad/VirtualBox VMs/共享文件夹/5to.bpmn");
	}

}
