package pre_process_rule;

import java.util.ArrayList;
import java.util.List;

import bpmnElement.BPMN_elements;
import bpmnElement.ChoreographyTask;
import bpmnElement.ExclusiveGateway;
import bpmnElement.SequenceFlow;

public class PreRule3_1 extends PreRule{

	@Override
	public void excute(BPMN_elements bpmn_elements) {
		// TODO Auto-generated method stub
		List<SequenceFlow> sequenceFlowsList = bpmn_elements.getSequenceFlowList();
		List<ExclusiveGateway> exclusiveGatewaysList = bpmn_elements.getExclusiveGatewayList();
		List<ChoreographyTask> choreographyTasks = bpmn_elements.getChoreographyTaskList();
		
		
		for(int i = 0; i < choreographyTasks.size(); i++) {
			ChoreographyTask choreography = choreographyTasks.get(i);
			
			if(choreography.getIncoming().size() >= 2) { //Tasks的输入行数大于等于2时
				int incomingSize = choreography.getIncoming().size();
				//1.生成选择网关
				ExclusiveGateway newExclusiveGateway = new ExclusiveGateway();
				//设置属性
				newExclusiveGateway.setGatewayDirection("Converging");
				String ExclusID = GenID.getId();  //网关ID
				
				newExclusiveGateway.setId(ExclusID);
				newExclusiveGateway.setName("Exclusive Gateway");
				
				ArrayList<String> newExclusIncoming = new ArrayList<>();
				for(int j = 0; j < choreography.getIncoming().size();j++) {
					newExclusIncoming.add(choreography.getIncoming().get(j));
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
						if(sequenceFlowsList.get(j).getId().equals(choreography.getIncoming().get(a))) {
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
				newSequenceFlow.setTargetRef(choreography.getId());
				sequenceFlowsList.add(newSequenceFlow);
				
				//4.改变Tasks的Incoming
				ArrayList<String> newTasksIncoming = new ArrayList<>();
				newTasksIncoming.add(ExclusOutputID);
				choreography.setIncoming(newTasksIncoming);
			}
		}
	}

}
