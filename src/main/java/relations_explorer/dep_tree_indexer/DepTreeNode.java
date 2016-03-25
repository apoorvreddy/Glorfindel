package relations_explorer.dep_tree_indexer;

import java.util.List;

/**
 * Created by apoorv on 18/3/16.
 */
public class DepTreeNode {

    private int nodeID;
    private List<Integer> parentNodeIDList;
    private int sentenceID;
    private String text;
    private String lemma;
    private String posTag;
    private String entityType;

    public DepTreeNode(int nodeID, List<Integer> parentNodeIDList, int sentenceID, String text, String lemma, String posTag, String entityType) {
        this.nodeID = nodeID;
        this.parentNodeIDList = parentNodeIDList;
        this.sentenceID = sentenceID;
        this.text = text;
        this.lemma = lemma;
        this.posTag = posTag;
        this.entityType = entityType;
    }

    public DepTreeNode(int nodeID, List<Integer> parentNodeIDList, int sentenceID, String text, String lemma, String posTag) {
        this( nodeID, parentNodeIDList, sentenceID, text, lemma, posTag, "");
    }

    public int getSentenceID() {
        return sentenceID;
    }

    public void setSentenceID(int sentenceID) {
        this.sentenceID = sentenceID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLemma() {
        return lemma;
    }

    public void setLemma(String lemma) {
        this.lemma = lemma;
    }

    public String getPosTag() {
        return posTag;
    }

    public void setPosTag(String posTag) {
        this.posTag = posTag;
    }

    public int getNodeID() {
        return nodeID;
    }

    public void setNodeID(int nodeID) {
        this.nodeID = nodeID;
    }

    public List<Integer> getParentNodeIDList() {
        return parentNodeIDList;
    }

    public void setParentNodeIDList(List<Integer> parentNodeID) {
        this.parentNodeIDList = parentNodeID;
    }

    public String getEntityType() {
        return entityType;
    }
}
