package some_test;

import bpmnElement.BPMN_elements;
import org.dom4j.DocumentException;
import org.junit.Test;
import pre_process_rule.*;
import xml_operation.BPMN_reader;
import xml_operation.BPMN_writer;

import java.io.IOException;

/**
 * Created by 李炆睿 on 2017/10/21.
 */
public class Test_for_bpmn_write {
    @Test
    public void test_write() throws IOException, DocumentException {
        BPMN_reader reader = new BPMN_reader();
        BPMN_elements bpmn_elements = reader.read_bpmn("case.bpmn");

        PreRules_excecutor preRules_excecutor = new PreRules_excecutor();
        preRules_excecutor.excecute(bpmn_elements);
        System.out.println("-----------------------------after pre--------------------------");
        System.out.println(bpmn_elements);
        System.out.println("-----------------------------after pre--------------------------");

        BPMN_writer bpmn_writer = new BPMN_writer(bpmn_elements);
        bpmn_writer.write("BPMN.bpmn");
    }

    @Test
    public void test_write_1() throws IOException, DocumentException {
        BPMN_reader reader = new BPMN_reader();
        BPMN_elements bpmn_elements = reader.read_bpmn("case.bpmn");

        PreRule preRule = new PreRule1();
        preRule.excute(bpmn_elements);

        BPMN_writer bpmn_writer = new BPMN_writer(bpmn_elements);
        bpmn_writer.write("BPMN-1.bpmn");
    }

    @Test
    public void test_write_2() throws IOException, DocumentException {
        BPMN_reader reader = new BPMN_reader();
        BPMN_elements bpmn_elements = reader.read_bpmn("case.bpmn");

        PreRule preRule = new PreRule2();
        preRule.excute(bpmn_elements);

        BPMN_writer bpmn_writer = new BPMN_writer(bpmn_elements);
        bpmn_writer.write("BPMN-2.bpmn");
    }

    @Test
    public void test_write_3() throws IOException, DocumentException {
        BPMN_reader reader = new BPMN_reader();
        BPMN_elements bpmn_elements = reader.read_bpmn("case.bpmn");

        BPMN_writer bpmn_writer = new BPMN_writer(bpmn_elements);

        PreRule preRule = new PreRule3_1();
        preRule.excute(bpmn_elements);
        bpmn_writer.write("BPMN-3-1.bpmn");

        PreRule preRule2 = new PreRule4_1();
        preRule2.excute(bpmn_elements);
        bpmn_writer.write("BPMN-3-2.bpmn");
    }

    @Test
    public void test_write_4() throws IOException, DocumentException {
        BPMN_reader reader = new BPMN_reader();
        BPMN_elements bpmn_elements = reader.read_bpmn("case.bpmn");

        BPMN_writer bpmn_writer = new BPMN_writer(bpmn_elements);

        PreRule preRule = new PreRule4_1();
        preRule.excute(bpmn_elements);
        bpmn_writer.write("BPMN-4-1.bpmn");

        PreRule preRule2 = new PreRule4_2();
        preRule2.excute(bpmn_elements);
        bpmn_writer.write("BPMN-4-2.bpmn");
    }

    @Test
    public void test_write_5() throws IOException, DocumentException {
        BPMN_reader reader = new BPMN_reader();
        BPMN_elements bpmn_elements = reader.read_bpmn("case.bpmn");

        PreRule preRule = new PreRule5();
        preRule.excute(bpmn_elements);

        BPMN_writer bpmn_writer = new BPMN_writer(bpmn_elements);
        bpmn_writer.write("BPMN-5.bpmn");
    }

    @Test
    public void test_write_6() throws IOException, DocumentException {
        BPMN_reader reader = new BPMN_reader();
        BPMN_elements bpmn_elements = reader.read_bpmn("case.bpmn");

        PreRule preRule = new PreRule6();
        preRule.excute(bpmn_elements);

        BPMN_writer bpmn_writer = new BPMN_writer(bpmn_elements);
        bpmn_writer.write("BPMN-6.bpmn");
    }

    @Test
    public void test_write_7() throws IOException, DocumentException {
        BPMN_reader reader = new BPMN_reader();
        BPMN_elements bpmn_elements = reader.read_bpmn("case.bpmn");

        PreRule preRule = new PreRule7();
        preRule.excute(bpmn_elements);

        BPMN_writer bpmn_writer = new BPMN_writer(bpmn_elements);
        bpmn_writer.write("BPMN-7.bpmn");
    }
}
