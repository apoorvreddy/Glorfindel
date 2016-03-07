package tree_match;

import edu.stanford.nlp.ling.IndexedWord;

/**
 * Created by apoorv on 7/3/16.
 */
public class NodePair {

    IndexedWord node1;
    IndexedWord node2;

    public NodePair(IndexedWord node1, IndexedWord node2){
        this.node1 = node1;
        this.node2 = node2;
    }
}
