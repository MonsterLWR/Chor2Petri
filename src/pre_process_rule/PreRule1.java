package pre_process_rule;

import java.util.ArrayList;
import java.util.List;

import bpmnElement.BPMN_elements;
import bpmnElement.ParallelGateway;
import bpmnElement.SequenceFlow;
import bpmnElement.StartEvent;

public class PreRule1 extends PreRule{ //规则一

	@Override
	public void excute(BPMN_elements bpmn_elements) {
		// TODO Auto-generated method stub
		List<StartEvent> startEventList = bpmn_elements.getStartEventList();
		List<ParallelGateway> parallelGateways = bpmn_elements.getParallelGatewayList();
		List<SequenceFlow> sequenceFlows = bpmn_elements.getSequenceFlowList();
		
		if(startEventList.size() <= 0) {
			System.out.println("No startEvent");
		}
		else {
			for(int i = 0; i<startEventList.size();i++) {
				StartEvent startEvent = startEventList.get(i); //只考虑一个startEvent
				if (startEvent.getOutgoing().size() >= 2) {  //如果输出行数大于等于2			
					//1.添加新的网关
					ParallelGateway newParallelGateway = new ParallelGateway();
					  //将ID改为之前的statEvent的ID，这样就不需要更改两条序列流，简化代码
					String oldStartId = startEvent.getId();
					newParallelGateway.setId(oldStartId);
					  //属性
					newParallelGateway.setGatewayDirection("Diverging");
					newParallelGateway.setName("Parallel Gateway");
					
					String newSequenceID = GenID.getId();
					ArrayList<String> newParallelIncoming = new ArrayList<>();
					newParallelIncoming.add(newSequenceID);
					newParallelGateway.setIncoming(newParallelIncoming);
					
					ArrayList<String> newParallelOutcoming = new ArrayList<>();
					for(int j =0 ; j <startEvent.getOutgoing().size(); j++) {
						newParallelOutcoming.add(startEvent.getOutgoing().get(j));
					}
					newParallelGateway.setOutgoing(newParallelOutcoming);
					
					parallelGateways.add(newParallelGateway);
					bpmn_elements.getGateWayList().add(newParallelGateway);
					
					//2.添加新的序列流
					SequenceFlow newSequenceFlow = new SequenceFlow();
					newSequenceFlow.setId(newSequenceID);
					String newStartID = GenID.getId();
					newSequenceFlow.setSourceRef(newStartID);
					newSequenceFlow.setTargetRef(oldStartId);
					sequenceFlows.add(newSequenceFlow);
					
					//3.更换新的startEvent，改为只有一个outgoing,更改新的ID		
					ArrayList<String> startOutgoing = new ArrayList<>();
					startOutgoing.add(newSequenceID);
					startEvent.setOutgoing(startOutgoing);			
					startEvent.setId(newStartID);			
			    }
			}
		}
	}
}
