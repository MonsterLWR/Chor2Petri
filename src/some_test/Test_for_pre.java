package some_test;

import bpmnElement.BPMN_elements;
import org.dom4j.DocumentException;
import org.junit.Test;
import pre_process_rule.PreRules_excecutor;
import xml_operation.BPMN_reader;

/**
 * Created by 李炆睿 on 2017/10/15.
 */
public class Test_for_pre {
    @Test
    public void pre_test() throws DocumentException {
        PreRules_excecutor excecutor = new PreRules_excecutor();
        BPMN_reader reader = new BPMN_reader();
        BPMN_elements bpmn_elements = reader.read_bpmn("case.bpmn");
        excecutor.excecute(bpmn_elements);
        System.out.println(bpmn_elements);
}
}
