package translate_rule2;

import bpmnElement.BPMN_elements;
import petriElement.Petri_elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李炆睿 on 2017/10/15.
 */
public class Rules_executor_2 {
    List<Rule> rules;
    public Rules_executor_2() {
        rules = new ArrayList<Rule>();
        //gateway_rule需要在basic_rule之前执行，因为gateway_rule中涉及到对bpmn对象的删减
        rules.add(new Rule_4());
        rules.add(new Rule_5_6());
        rules.add(new Rule_3());
        rules.add(new Rule_7_8());
        rules.add(new Rule_1());
        rules.add(new Rule_2());
    }

    public void excecute(BPMN_elements bpmn_elements, Petri_elements petri_elements) {
        for (Rule rule : rules) {
            rule.excecute(bpmn_elements,petri_elements);
        }
    }
}
