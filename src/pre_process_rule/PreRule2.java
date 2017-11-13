package pre_process_rule;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.DocumentException;

import bpmnElement.BPMN_elements;
import bpmnElement.EndEvent;
import bpmnElement.ExclusiveGateway;
import bpmnElement.SequenceFlow;
import xml_operation.BPMN_reader;

public class PreRule2 extends PreRule {

	@Override
	public void excute(BPMN_elements bpmn_elements) {
		List<EndEvent> endEventList = bpmn_elements.getEndEventList();
		for (int i = 0; i < endEventList.size(); ++i) {
			EndEvent endEvent = endEventList.get(i);
			if (endEvent.getIncoming().size() >= 2) {
				ExclusiveGateway gateway = new ExclusiveGateway();
				gateway.setId(endEvent.getId());
				gateway.setGatewayDirection("Converging");
				gateway.setName("Exclusive Gateway");
				gateway.setIncoming(endEvent.getIncoming());
				
				endEvent.setId(GenID.getId());
				
				SequenceFlow sequenceFlow = new SequenceFlow();
				sequenceFlow.setId(GenID.getId());
				sequenceFlow.setSourceRef(gateway.getId());
				sequenceFlow.setTargetRef(endEvent.getId());
				bpmn_elements.getSequenceFlowList().add(sequenceFlow);
				
				ArrayList<String> comings = new ArrayList<>();
				comings.add(sequenceFlow.getId());
				endEvent.setIncoming(comings);
				
				comings = new ArrayList<>();
				comings.add(sequenceFlow.getId());
				gateway.setOutgoing(comings);
				bpmn_elements.getExclusiveGatewayList().add(gateway);
				bpmn_elements.getGateWayList().add(gateway);
			}
		}
	}
	
	public static void main(String[] args) throws DocumentException {
		BPMN_reader reader = new BPMN_reader();
		BPMN_elements elements = reader.read_bpmn("/home/thinkpad/VirtualBox VMs/共享文件夹/1.bpmn");
		PreRule2 rule = new PreRule2();
		rule.excute(elements);
		System.out.println("------------------------------");
		List list = elements.getEndEventList();
		for (Object object : list) {
			System.out.println(object);
		}
		list = elements.getExclusiveGatewayList();
		for (Object object : list) {
			System.out.println(object);
		}
		list = elements.getSequenceFlowList();
		for (Object object : list) {
			System.out.println(object);
		}
	}
	
}
