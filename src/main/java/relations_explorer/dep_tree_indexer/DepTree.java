package relations_explorer.dep_tree_indexer;

import java.util.List;

/**
 * Created by apoorv on 18/3/16.
 */
public class DepTree {

    private List<DepTreeNode> depTreeNodeList;
    private List<DepTreeEdge> depTreeEdgeList;

    public DepTree(List<DepTreeNode> depTreeNodeList, List<DepTreeEdge> depTreeEdgeList) {
        this.depTreeNodeList = depTreeNodeList;
        this.depTreeEdgeList = depTreeEdgeList;
    }

    public List<DepTreeNode> getDepTreeNodeList() {
        return depTreeNodeList;
    }

    public List<DepTreeEdge> getDepTreeEdgeList() {
        return depTreeEdgeList;
    }
}
