package relations_explorer.dep_tree_indexer;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.IndexedWord;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphEdge;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by apoorv on 18/3/16.
 */

public class DepTreeMaker {

    public static DepTree makeDepTree(SemanticGraph graph, int sentenceID) {

        List<DepTreeEdge> depTreeEdgeList = new ArrayList<>();
        List<DepTreeNode> depTreeNodeList = new ArrayList<>();

        Set<Integer> nodeIndexSet = new HashSet<>();

        for (SemanticGraphEdge graphEdge : graph.edgeIterable()) {

            IndexedWord governor = graphEdge.getGovernor();
            if(!nodeIndexSet.contains(governor.index())){
                depTreeNodeList.add(makeNode(governor, graph, sentenceID));
            }
            IndexedWord dependent = graphEdge.getDependent();
            if(!nodeIndexSet.contains(dependent.index())){
                depTreeNodeList.add(makeNode(dependent, graph, sentenceID));
            }

            DepTreeEdge edge = new DepTreeEdge(sentenceID, governor.index(), dependent.index(), graphEdge.getRelation().getShortName());
            depTreeEdgeList.add(edge);
        }

        return new DepTree(depTreeNodeList, depTreeEdgeList);
    }


    private static DepTreeNode makeNode(IndexedWord node, SemanticGraph graph, int sentenceID){

        int nodeID = node.index();

        List<IndexedWord> parentList = graph.getParentList(node);
        List<Integer> parentNodeIDList = new ArrayList<>();

        for(IndexedWord indexedWord: parentList){
            parentNodeIDList.add(indexedWord.index());
        }

        DepTreeNode depTreeNode = new DepTreeNode(nodeID, parentNodeIDList, sentenceID, node.word(), node.lemma(),
                node.tag(), node.get(CoreAnnotations.NamedEntityTagAnnotation.class));

        return depTreeNode;
    }
}
