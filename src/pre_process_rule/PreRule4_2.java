package pre_process_rule;

import java.util.ArrayList;
import java.util.List;

import bpmnElement.BPMN_elements;
import bpmnElement.IntermediateThrowEvent;
import bpmnElement.ParallelGateway;
import bpmnElement.SequenceFlow;

public class PreRule4_2 extends PreRule{

	@Override
	public void excute(BPMN_elements bpmn_elements) {
		// TODO Auto-generated method stub
		List<SequenceFlow> sequenceFlowsList = bpmn_elements.getSequenceFlowList();
		List<ParallelGateway> parallelGatewaysList = bpmn_elements.getParallelGatewayList();
		List<IntermediateThrowEvent> intermediateThrowEventsList = bpmn_elements.getIntermediateThrowEventList();		
		
		for(int i = 0; i < intermediateThrowEventsList.size(); i++) {
			IntermediateThrowEvent wating1 = intermediateThrowEventsList.get(i);
			
			if(wating1.getOutgoing().size() >= 2) { //Wating1的输出行数等于2时
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
				newParaOutgoing.add(wating1.getOutgoing().get(0));
				newParaOutgoing.add(wating1.getOutgoing().get(1));
				newParallelGateway.setOutgoing(newParaOutgoing);
				
				parallelGatewaysList.add(newParallelGateway);
				bpmn_elements.getGateWayList().add(newParallelGateway);
				
				//2.更改序列流
				for(int j = 0; j < sequenceFlowsList.size(); j++) {
					if(sequenceFlowsList.get(j).getId().equals(wating1.getOutgoing().get(0)) || sequenceFlowsList.get(j).getId().equals(wating1.getOutgoing().get(1))) {
						sequenceFlowsList.get(j).setSourceRef(newParID);
					}
				}
				
				//3.生成序列流
				SequenceFlow newSequenceFlow = new SequenceFlow();
				newSequenceFlow.setId(newParIncomingID);
				newSequenceFlow.setSourceRef(wating1.getId());
				newSequenceFlow.setTargetRef(newParID);
				sequenceFlowsList.add(newSequenceFlow);
				
				//4.改变Wating1的Outgoing
				ArrayList<String> newTasksOutgoing = new ArrayList<>();
				newTasksOutgoing.add(newParID);
				wating1.setIncoming(newTasksOutgoing);			
			}		
		}
	}

}

