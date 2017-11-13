package layout;

import java.util.HashMap;
import java.util.List;

import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.swing.util.mxMorphing;
import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import com.mxgraph.view.mxGraph;

public class Layoutor {
	// 自动生成XY坐标
	public void layout(List<Shapeable> shapesList,List<Flowable> flowsList) {
		//创建hashmaps
		HashMap<String , Object> map = new HashMap<String , Object>();
		
        //定义元素对象
        Object[] Sv = new Object[shapesList.size()];
        Object[] Fv = new Object[flowsList.size()];
        //定义mxGrap对象
        final mxGraph graph = new mxGraph();

        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        //f.getContentPane().add(graphComponent, BorderLayout.CENTER);
        //f.setVisible(true);
        
        Object parent = graph.getDefaultParent();
        graph.getModel().beginUpdate();
        try {
        	// *2.获取ID:shapesList.get(i).getId(); 获取Width和Height：shapesList.get(i).getWidth() 和 shapesList.get(i).getHeight()
                for (int i = 0; i < shapesList.size(); i++) {
					Sv[i] = graph.insertVertex(parent, null, shapesList.get(i).getId(), 100, 100, (Double.parseDouble(shapesList.get(i).getWidth())*3), (Double.parseDouble(shapesList.get(i).getHeight())*2.5));
					//System.out.println("i:");
					//System.out.println("Width:"+Double.parseDouble(shapesList.get(i).getWidth()));
					//System.out.println("height:"+Double.parseDouble(shapesList.get(i).getHeight()));
					map.put(shapesList.get(i).getId(), Sv[i]);
                	}
           // *3.获取ID：flowsList.get(j).getId(); 获取source和tar: flowsList.get(j).getSourceRef() 和 flowsList.get(j).getTargetRef()
                for (int j = 0; j < flowsList.size(); j++) {
                	Fv[j] = graph.insertEdge(parent, null, flowsList.get(j).getId(), map.get(flowsList.get(j).getSourceRef()), map.get(flowsList.get(j).getTargetRef()));
                }
                
        } finally {
                graph.getModel().endUpdate();
        }

        morphGraph(graph, graphComponent); //自动布局
        
        // *4.set方法，生成新的X,Y坐标:shapesList.get(z).setX(); shapesList.get(z).setY(); 
        // flowsList.get(n).setX(); flowsList.get(n).setY();
        //设置shaps的坐标
        for (int m = 0; m < shapesList.size(); m++) {
        	shapesList.get(m).setX(String.valueOf(((mxCell)graph.addCell(Sv[m])).getGeometry().getCenterY()));
        	shapesList.get(m).setY(String.valueOf(((mxCell)graph.addCell(Sv[m])).getGeometry().getCenterX()));
        }
        
        //设置flows的坐标
//        for (int n = 0; n < flowsList.size(); n++) {
//        	flowsList.get(n).setX(String.valueOf(((mxCell)graph.addCell(Sv[n])).getGeometry().getCenterX()));
//        	flowsList.get(n).setY(String.valueOf(((mxCell)graph.addCell(Sv[n])).getGeometry().getCenterY()));
//        }
	}
	
	
	private static void morphGraph(final mxGraph graph, mxGraphComponent graphComponent) 
	{ 
		//自动布局方法
		// define layout
		mxIGraphLayout layout = new mxHierarchicalLayout(graph);//层次布局
       
        // layout using morphing
        graph.getModel().beginUpdate();
        try {
        	layout.execute(graph.getDefaultParent());
        	} finally {
        		mxMorphing morph = new mxMorphing(graphComponent, 6, 1.5, 20);
        		morph.addListener(mxEvent.DONE, new mxIEventListener() {
        			public void invoke(Object arg0, mxEventObject arg1) {
        				graph.getModel().endUpdate();
        				// fitViewport();
        				}
        			});
        		morph.startAnimation();
        		}
        }
}
