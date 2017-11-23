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
			
			if(wating1.getIncoming().size() >= 2) {  //输入大于等于2时
				int incomingSize = wating1.getIncoming().size();
				//1.生成选择网关
				ExclusiveGateway newExclusiveGateway = new ExclusiveGateway();
				//设置属性
				newExclusiveGateway.setGatewayDirection("Converging");
				String ExclusID = GenID.getId();  //网关ID
				
				newExclusiveGateway.setId(ExclusID);
				newExclusiveGateway.setName("Exclusive Gateway");
				
				ArrayList<String> newExclusIncoming = new ArrayList<>();
				for(int j = 0; j < incomingSize; j++) {
					newExclusIncoming.add(wating1.getIncoming().get(j));
				}
				newExclusiveGateway.setIncoming(newExclusIncoming);
				
				String ExclusOutputID = GenID.getId(); //网关输出ID，也是序列流ID
				ArrayList<String> newExclusOutgoing = new ArrayList<>();
				newExclusOutgoing.add(ExclusOutputID);
				newExclusiveGateway.setOutgoing(newExclusOutgoing);
				//添加选择网关
				exclusiveGatewaysList.add(newExclusiveGateway);
				bpmn_elements.getGateWayList().add(newExclusiveGateway);
				
				//2.改变之前序列流的targetRef
				boolean mid = false;
				for(int j = 0; j < sequenceFlowsList.size(); j++) {
					for(int a = 0; a < incomingSize; a++) {
						if(sequenceFlowsList.get(j).getId().equals(wating1.getIncoming().get(a))) {
							mid =true;
						}
					}
					if(mid) {
						sequenceFlowsList.get(j).setTargetRef(ExclusID);
						mid = false;
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
