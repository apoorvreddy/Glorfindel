package relations_explorer.dep_tree_indexer;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.StringField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by apoorv on 25/3/16.
 */
public class EdgeIndexer extends Indexer {

    private static String indexName = "edge_index";

    public EdgeIndexer(String indexDirectoryPath) throws IOException {
        super(indexDirectoryPath);
    }

    public static List<Document> getDocumentList(List<DepTreeEdge> depTreeEdgeList){

        List<Document> documentList = new ArrayList<>();
        for(DepTreeEdge edge: depTreeEdgeList){
            documentList.add(getDocument(edge));

        }
        return documentList;
    }


    public static Document getDocument(DepTreeEdge depTreeEdge){

        Document document = new Document();
        document.add(new StringField("modifier", depTreeEdge.getModifier(), Field.Store.YES));
        document.add(new IntField("sentenceID", depTreeEdge.getSentenceID(), Field.Store.YES));
        document.add(new IntField("governorNodeID", depTreeEdge.getGovernorNodeID(), Field.Store.YES));
        document.add(new IntField("dependentNodeID", depTreeEdge.getDependentNodeID(), Field.Store.YES));

        return document;
    }

}
