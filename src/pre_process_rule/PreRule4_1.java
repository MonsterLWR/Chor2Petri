package pre_process_rule;

import java.util.ArrayList;
import java.util.List;

import bpmnElement.BPMN_elements;
import bpmnElement.ChoreographyTask;
import bpmnElement.ParallelGateway;
import bpmnElement.SequenceFlow;

public class PreRule4_1 extends PreRule{

	@Override
	public void excute(BPMN_elements bpmn_elements) {
		// TODO Auto-generated method stub
		List<SequenceFlow> sequenceFlowsList = bpmn_elements.getSequenceFlowList();
		List<ParallelGateway> parallelGatewaysList = bpmn_elements.getParallelGatewayList();
		List<ChoreographyTask> choreographyTasks = bpmn_elements.getChoreographyTaskList();		
		
		for(int i = 0; i < choreographyTasks.size(); i++) {
			ChoreographyTask choreography = choreographyTasks.get(i);
			
			if(choreography.getOutgoing().size() >= 2) { //Tasks的输出行数大于等于2时
				int outgoingSize = choreography.getOutgoing().size();
				//1.创建并行网关
				ParallelGateway newParallelGateway = new ParallelGateway();
				  //定义属性
				newParallelGateway.setGatewayDirection("Diverging");
				newParallelGateway.setName("Parallel Gateway");	
				
				String newParID = GenID.getId(); //获取网关ID
				newParallelGateway.setId(newParID);
				
				ArrayList<String> newParaIncoming = new ArrayList<>();//定义输入
				String newParIncomingID = GenID.getId(); //获取输入ID，也是序列流的ID
				newParaIncoming.add(newParIncomingID);
				newParallelGateway.setIncoming(newParaIncoming);
				
				ArrayList<String> newParaOutgoing = new ArrayList<>();//定义输出
				for(int j = 0; j < outgoingSize; j++) {
					newParaOutgoing.add(choreography.getOutgoing().get(j));
				}
				newParallelGateway.setOutgoing(newParaOutgoing);
				
				parallelGatewaysList.add(newParallelGateway);
				bpmn_elements.getGateWayList().add(newParallelGateway);
				
				//2.更改序列流
				boolean mid = false;
				for(int j = 0; j < sequenceFlowsList.size(); j++) {
					for(int a = 0; a < outgoingSize; a++) {
						if(sequenceFlowsList.get(j).getId().equals(choreography.getOutgoing().get(a))) {
							mid =true;
						}
					}
					if(mid) {
						sequenceFlowsList.get(j).setSourceRef(newParID);
						mid = false;
					}
				}
				
				//3.生成序列流
				SequenceFlow newSequenceFlow = new SequenceFlow();
				newSequenceFlow.setId(newParIncomingID);
				newSequenceFlow.setSourceRef(choreography.getId());
				newSequenceFlow.setTargetRef(newParID);
				sequenceFlowsList.add(newSequenceFlow);
				
				//4.改变Tasks的Outgoing
				ArrayList<String> newTasksOutgoing = new ArrayList<>();
				newTasksOutgoing.add(newParID);
				choreography.setIncoming(newTasksOutgoing);			
			}		
		}
	}

}
