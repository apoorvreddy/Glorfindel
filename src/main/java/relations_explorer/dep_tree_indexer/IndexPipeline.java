package relations_explorer.dep_tree_indexer;

import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;
import org.apache.lucene.document.Document;
import text_util.PipelineProvider;
import text_util.TextAnnotator;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by apoorv on 25/3/16.
 */
public class IndexPipeline {

    static PipelineProvider pipelineProvider = PipelineProvider.getInstance();
    private AtomicInteger count = new AtomicInteger(0);

    private NodeIndexer nodeIndexer;
    private EdgeIndexer edgeIndexer;

    public IndexPipeline(String nodeIndexDirectory, String edgeIndexDirectory) throws IOException{
        nodeIndexer = new NodeIndexer(nodeIndexDirectory);
        edgeIndexer = new EdgeIndexer(edgeIndexDirectory);
    }

    public void indexDocument(String textDocument) throws IOException {

        TextAnnotator annotator = new TextAnnotator(textDocument, pipelineProvider.getPipeline());
        for(CoreMap sentence: annotator.getSentences()){
            SemanticGraph graph = sentence.get(SemanticGraphCoreAnnotations.CollapsedCCProcessedDependenciesAnnotation.class);
            DepTree depTree = DepTreeMaker.makeDepTree(graph, count.get());

            for(Document document: nodeIndexer.getDocumentList(depTree.getDepTreeNodeList())){
                nodeIndexer.index(document);
            }

            for(Document document: edgeIndexer.getDocumentList(depTree.getDepTreeEdgeList())){
                edgeIndexer.index(document);
            }
        }
    }
}
