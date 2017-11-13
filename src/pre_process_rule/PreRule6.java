package pre_process_rule;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.DocumentException;

import bpmnElement.BPMN_elements;
import bpmnElement.EventBasedGateway;
import bpmnElement.ExclusiveGateway;
import bpmnElement.GateWay;
import bpmnElement.ParallelGateway;
import bpmnElement.SequenceFlow;
import xml_operation.BPMN_reader;

public class PreRule6 extends PreRule {
	
	@Override
	public void excute(BPMN_elements bpmn_elements) {
		List<GateWay> gateWays = bpmn_elements.getGateWayList();
		for (int i = 0; i < gateWays.size(); ++i) {
			GateWay gateWay = gateWays.get(i);
			if (gateWay.getIncoming().size() >= 2 && gateWay.getOutgoing().size() >= 2) {
				try {
					GateWay gateWay1 = (GateWay) gateWay.clone();
					gateWay1.setId(GenID.getId());
					
					SequenceFlow sequenceFlow = new SequenceFlow();
					sequenceFlow.setId(GenID.getId());
					sequenceFlow.setSourceRef(gateWay.getId());
					sequenceFlow.setTargetRef(gateWay1.getId());
					
					ArrayList<String> coming = new ArrayList<>();
					coming.add(sequenceFlow.getId());
					gateWay1.setIncoming(coming);
					if (gateWay1 instanceof ExclusiveGateway) {
						bpmn_elements.getExclusiveGatewayList().add((ExclusiveGateway) gateWay1);
					} else if (gateWay1 instanceof EventBasedGateway) {
						bpmn_elements.getEventBasedGatewayList().add((EventBasedGateway) gateWay1);
					} else if (gateWay1 instanceof ParallelGateway) {
						bpmn_elements.getParallelGatewayList().add((ParallelGateway) gateWay1);
					}
					gateWays.add(gateWay1);
					
					gateWay.setGatewayDirection("Converging");
					coming = new ArrayList<>();
					coming.add(sequenceFlow.getId());
					gateWay.setOutgoing(coming);
					
					List<SequenceFlow> sequenceFlows = bpmn_elements.getSequenceFlowList();
					for (int j = 0; j < sequenceFlows.size(); ++j) {
						SequenceFlow flow = sequenceFlows.get(j);
						if (flow.getSourceRef().equals(gateWay.getId())) {
							flow.setSourceRef(gateWay1.getId());
						}
					}
					
					sequenceFlows.add(sequenceFlow);
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) throws DocumentException {
		BPMN_reader reader = new BPMN_reader();
		BPMN_elements elements = reader.read_bpmn("/home/thinkpad/VirtualBox VMs/共享文件夹/2.bpmn");
		PreRule6 rule = new PreRule6();
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
		
		List<GateWay> gateways = elements.getGateWayList();
		for (GateWay gateway : gateways) {
			System.out.println(gateway);
		}
	}

}
