package pre_process_rule;

import java.util.ArrayList;
import java.util.List;

import bpmnElement.BPMN_elements;
import bpmnElement.ExclusiveGateway;
import bpmnElement.IntermediateThrowEvent;
import bpmnElement.SequenceFlow;

public class PreRule3_2 extends PreRule{
	public void excute(BPMN_elements bpmn_elements) {
		List<SequenceFlow> sequenceFlowsList = bpmn_elements.getSequenceFlowList();
		List<ExclusiveGateway> exclusiveGatewaysList = bpmn_elements.getExclusiveGatewayList();
		List<IntermediateThrowEvent> intermediateThrowEventsList = bpmn_elements.getIntermediateThrowEventList();
		
		for(int i = 0; i < intermediateThrowEventsList.size(); i++) {
			IntermediateThrowEvent wating1 = intermediateThrowEventsList.get(i);
			
			if(wating1.getIncoming().size() >= 2) {  //输入等于2时
				
				//1.生成选择网关
				ExclusiveGateway newExclusiveGateway = new ExclusiveGateway();
				//设置属性
				newExclusiveGateway.setGatewayDirection("Converging");
				String ExclusID = GenID.getId();  //网关ID
				
				newExclusiveGateway.setId(ExclusID);
				newExclusiveGateway.setName("Exclusive Gateway");
				
				ArrayList<String> newExclusIncoming = new ArrayList<>();
				newExclusIncoming.add(wating1.getIncoming().get(0));
				newExclusIncoming.add(wating1.getIncoming().get(1));
				newExclusiveGateway.setIncoming(newExclusIncoming);
				
				String ExclusOutputID = GenID.getId(); //网关输出ID，也是序列流ID
				ArrayList<String> newExclusOutgoing = new ArrayList<>();
				newExclusOutgoing.add(ExclusOutputID);
				newExclusiveGateway.setOutgoing(newExclusOutgoing);
				//添加选择网关
				exclusiveGatewaysList.add(newExclusiveGateway);
				bpmn_elements.getGateWayList().add(newExclusiveGateway);
				
				//2.改变之前序列流的targetRef
				for(int j = 0; j < sequenceFlowsList.size(); j++) {
					if(sequenceFlowsList.get(j).getId().equals(wating1.getIncoming().get(0)) || sequenceFlowsList.get(j).getId().equals(wating1.getIncoming().get(1))) {
						sequenceFlowsList.get(j).setTargetRef(ExclusID);
					}
				}
				
				//3.生成新的序列流
				SequenceFlow newSequenceFlow = new SequenceFlow();
				newSequenceFlow.setId(ExclusOutputID);
				newSequenceFlow.setSourceRef(ExclusID);
				newSequenceFlow.setTargetRef(wating1.getId());
				sequenceFlowsList.add(newSequenceFlow);
				
				//4.改变wating1的Incoming
				ArrayList<String> newWatingIncoming = new ArrayList<>();
				newWatingIncoming.add(ExclusOutputID);
				wating1.setIncoming(newWatingIncoming);
			}
		}
	}
}
