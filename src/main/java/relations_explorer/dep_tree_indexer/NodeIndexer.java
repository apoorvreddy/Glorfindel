package relations_explorer.dep_tree_indexer;

import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.*;

/**
 * Created by apoorv on 25/3/16.
 */
public class NodeIndexer {


    private static String indexName = "node_index";

    public static List<Document> getDocumentList(List<DepTreeNode> depTreeNodeList){

        List<Document> documentList = new ArrayList<>();
        for(DepTreeNode depTreeNode: depTreeNodeList){
            documentList.add(getDocument(depTreeNode));
        }
        return documentList;
    }

    public static Document getDocument(DepTreeNode depTreeNode){

        Document document = new Document();
        document.add(new IntField("nodeID", depTreeNode.getNodeID(), Field.Store.YES));
        document.add(new IntField("sentenceID", depTreeNode.getSentenceID(), Field.Store.YES));
        document.add(new StringField("text", depTreeNode.getText(), Field.Store.YES));
        document.add(new StringField("lemma", depTreeNode.getLemma(), Field.Store.YES));
        document.add(new StringField("posTag ", depTreeNode.getPosTag(), Field.Store.YES));
        document.add(new StringField("entityType", depTreeNode.getEntityType(), Field.Store.YES));

        for(Integer parentID: depTreeNode.getParentNodeIDList()){
            document.add(new IntField("parentNodeIDList", parentID, Field.Store.YES));

        }
        return document;
    }

}
