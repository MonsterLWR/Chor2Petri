package translate_rule1;

import bpmnElement.BPMN_elements;
import petriElement.Petri_elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李炆睿 on 2017/10/15.
 */
public class Rules_executor_1 {
    List<Rule> rules;
    public Rules_executor_1() {
        rules = new ArrayList<Rule>();
        //gateway_rule需要在basic_rule之前执行，因为gateway_rule中涉及到对bpmn对象的删减
        rules.add(new Gateway_Rule_2());
        rules.add(new Gateway_Rule_1());
        rules.add(new Basic_Rule_1());
        rules.add(new Basic_Rule_2());
        rules.add(new Basic_Rule_3());
        rules.add(new Basic_Rule_4());
        rules.add(new Basic_Rule_5());

    }

    public void excecute(BPMN_elements bpmn_elements, Petri_elements petri_elements) {
        for (Rule rule : rules) {
            rule.excecute(bpmn_elements,petri_elements);
        }
    }
}
