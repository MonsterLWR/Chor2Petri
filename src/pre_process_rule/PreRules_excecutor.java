package pre_process_rule;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.DocumentException;

import bpmnElement.BPMN_elements;
import xml_operation.BPMN_reader;

public class PreRules_excecutor {
	List<PreRule> preRules = new ArrayList<>();
	
	public PreRules_excecutor() {
		preRules.add(new PreRule1());
		preRules.add(new PreRule2());
		preRules.add(new PreRule3_1());
		preRules.add(new PreRule3_2());
		preRules.add(new PreRule4_1());
		preRules.add(new PreRule4_2());
		preRules.add(new PreRule5());
		preRules.add(new PreRule6());
		preRules.add(new PreRule7());
	}
	
	public void excecute(BPMN_elements bpmn_elements) throws DocumentException {
		for (PreRule preRule : preRules) {
			preRule.excute(bpmn_elements);
		}
	}
}
