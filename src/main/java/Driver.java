/**
 * Created by apoorv on 3/3/16.
 */

import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;
import similarity_measures.word_overlap_measures.phrasal_overlap.PhrasalOverlap;
import text_util.TextAnnotator;
import text_util.PipelineProvider;
import similarity_measures.tree_match.TreeMatch;

import java.util.Arrays;


public class Driver {

    static PipelineProvider pipelineProvider = PipelineProvider.getInstance();

    public static void main(String[] args){

        String test1 = "The lion chased the mouse in the savannah";
        String test2 = "The angry lion chased the squeaky mouse";

        TextAnnotator annotator1 = new TextAnnotator(test1, pipelineProvider.getPipeline());
        TextAnnotator annotator2 = new TextAnnotator(test2, pipelineProvider.getPipeline());

        CoreMap sentence1 = annotator1.getSentences().get(0);
        CoreMap sentence2 = annotator2.getSentences().get(0);

        SemanticGraph depGraph1 = sentence1.get(
                SemanticGraphCoreAnnotations.CollapsedCCProcessedDependenciesAnnotation.class);

        System.out.println(depGraph1.toString());

        SemanticGraph depGraph2 = sentence2.get(
                SemanticGraphCoreAnnotations.CollapsedCCProcessedDependenciesAnnotation.class);

        System.out.println(depGraph2.toString());

        long startTime = System.nanoTime();
        System.out.println("normKernel: " + TreeMatch.computeNormalizedKernel(depGraph1, depGraph2));
        long endTime = System.nanoTime();

        long duration = (endTime - startTime)/1000000;
        System.out.println("Time: " + duration);


        PhrasalOverlap match = new PhrasalOverlap();
        double score = match.exec(test1, test2, Arrays.asList());
        System.out.println("Score: " + score);
    }

}
