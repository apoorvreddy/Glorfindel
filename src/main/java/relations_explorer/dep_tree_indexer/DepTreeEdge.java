package relations_explorer.dep_tree_indexer;

/**
 * Created by apoorv on 18/3/16.
 */
public class DepTreeEdge {
    private int sentenceID;
    private int governorNodeID;
    private int dependentNodeID;
    private String modifier;


    public DepTreeEdge(int sentenceID, int governorNodeID, int dependentNodeID, String modifier) {
        this.sentenceID = sentenceID;
        this.governorNodeID = governorNodeID;
        this.dependentNodeID = dependentNodeID;
        this.modifier = modifier;
    }

    public int getSentenceID() {
        return sentenceID;
    }

    public void setSentenceID(int sentenceID) {
        this.sentenceID = sentenceID;
    }

    public int getGovernorNodeID() {
        return governorNodeID;
    }

    public void setGovernorNodeID(int governorNodeID) {
        this.governorNodeID = governorNodeID;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public int getDependentNodeID() {
        return dependentNodeID;
    }

    public void setDependentNodeID(int dependentNodeID) {
        this.dependentNodeID = dependentNodeID;
    }
}

