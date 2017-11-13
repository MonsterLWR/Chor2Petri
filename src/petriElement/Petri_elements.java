package petriElement;

import layout.Flowable;
import layout.Shapeable;
import tag_for_petri.Tag;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李炆睿 on 2017/10/15.
 */
public class Petri_elements {
    List<Arc> arc_list = new ArrayList<Arc>();
    List<Place> place_list = new ArrayList<Place>();
    List<Transition> transition_list = new ArrayList<Transition>();

    public List<Arc> getArc_list() {
        return arc_list;
    }

    public void setArc_list(List<Arc> arc_list) {
        this.arc_list = arc_list;
    }

    public List<Place> getPlace_list() {
        return place_list;
    }

    public void setPlace_list(List<Place> place_list) {
        this.place_list = place_list;
    }

    public List<Transition> getTransition_list() {
        return transition_list;
    }

    public void setTransition_list(List<Transition> transition_list) {
        this.transition_list = transition_list;
    }

    public List<Shapeable> getShapeableList() {
        List<Shapeable> shapeableList = new ArrayList<>();
        shapeableList.addAll(place_list);
        shapeableList.addAll(transition_list);
        return shapeableList;
    }

    public List<Flowable> getFlowableList() {
        List<Flowable> flowableList = new ArrayList<>();
        flowableList.addAll(arc_list);
        return flowableList;
    }
}
