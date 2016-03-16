package tree_match;

import edu.stanford.nlp.ling.IndexedWord;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;
import text_util.PipelineProvider;
import text_util.TextAnnotator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * Created by apoorv on 3/3/16.
 */
public class TreeMatch {


    public static int computeCDPMatrix(IndexedWord node1, IndexedWord node2, SemanticGraph tree1, SemanticGraph tree2,
                                       int[][] cdpMatrix){

        int node1Pos = node1.index() - 1;
        int node2Pos = node2.index() - 1;

        if (cdpMatrix[node1Pos][node2Pos] == -1){

            cdpMatrix[node1Pos][node2Pos] = nodeScore(node1, node2);

            if(testWordEquality(node1, node2)){
                cdpMatrix[node1Pos][node2Pos] = nodeScore(node1, node2);
                for(IndexedWord child1: tree1.getChildList(node1)){
                    for(IndexedWord child2: tree2.getChildList(node2)){
                        if(testWordEquality(child1, child2))
                            cdpMatrix[node1Pos][node2Pos] += 1 + computeCDPMatrix(child1, child2, tree1, tree2, cdpMatrix);
                    }
                }
            }

        }

        return cdpMatrix[node1Pos][node2Pos];
    }

    private static int nodeScore(IndexedWord node1, IndexedWord node2){

        int score = 0;
        if(testWordEquality(node1, node2))
            score++;
        if(testLemmaEquality(node1, node2))
            score++;
        if(testStrictPosEquality(node1, node2))
            score++;
        return score;
    }


    private static boolean testWordEquality(IndexedWord node1, IndexedWord node2){
        return node1.word().toLowerCase().equals(node2.word().toLowerCase());
    }

    private static boolean testStrictPosEquality(IndexedWord node1, IndexedWord node2){
        return node1.tag().equals(node2.tag());
    }

    private static boolean testLemmaEquality(IndexedWord node1, IndexedWord node2){
        return node1.lemma().equals(node2.lemma());
    }

    public static  void computeCPPMatrix(SemanticGraph tree1, SemanticGraph tree2,
                                                        int[][] cdpMatrix, int[][] cppMatrix){

        for(int i=0; i<tree1.size(); i++){
            for(int j=0; j<tree2.size(); j++){
                IndexedWord nodei = tree1.getNodeByIndex(i+1);
                IndexedWord nodej = tree2.getNodeByIndex(j+1);

                cppMatrix[i][j] = cdpMatrix[i][j];
                List<NodePair> childPairList = generateNodePairList(nodei, nodej, tree1, tree2);

                for(int k=0; k<childPairList.size() - 1; k++){
                    for(int l=k+1; l<childPairList.size(); l++){
                        NodePair nodePair1 = childPairList.get(k);
                        NodePair nodePair2 = childPairList.get(l);

                        int x, y;
                        x = cdpMatrix[nodePair1.node1.index() - 1][nodePair1.node2.index() - 1];
                        y = cdpMatrix[nodePair2.node1.index() - 1][nodePair2.node2.index() - 1];

                        cppMatrix[i][j] += 1 + x + y + x*y;
                    }
                }
            }
        }
    }


    private static List<NodePair> generateNodePairList(IndexedWord node1, IndexedWord node2,
                                                       SemanticGraph tree1, SemanticGraph tree2){

        List<NodePair> nodePairList = new ArrayList<NodePair>();

        for(IndexedWord child1: tree1.getChildList(node1))
            for(IndexedWord child2: tree2.getChildList(node2)){

                if (child1.word().equals(child2.word())){
                    NodePair nodePair = new NodePair(child1, child2);
                    nodePairList.add(nodePair);
                }
            }

        return nodePairList;
    }


    public static int[][] initializeCDPMatrix(SemanticGraph tree1, SemanticGraph tree2){

        int[][] cdpMatrix = new int[tree1.size()][tree2.size()];
        for(int i=0; i<tree1.size(); i++){
            for(int j=0; j<tree2.size(); j++){
                IndexedWord nodei = tree1.getNodeByIndex(i+1);
                IndexedWord nodej = tree2.getNodeByIndex(j+1);

                if(nodei.word().equals(nodej.word())){

                    IndexedWord nodeiParent = tree1.getParent(nodei);
                    IndexedWord nodejParent = tree2.getParent(nodej);
                    if(nodeiParent!=null && nodejParent!=null){
                        if(nodeiParent.word().equals(nodejParent.word())){
                            cdpMatrix[i][j] = -1;
                        }
                        else
                            cdpMatrix[i][j] = 0;
                    }
                    else
                        cdpMatrix[i][j] = -1;
                }
                else{
                    cdpMatrix[i][j] = 0;
                }
            }
        }
        return cdpMatrix;
    }

    public static double computeKernel(SemanticGraph tree1, SemanticGraph tree2){

        double kernel = 0;

        int[][] cdpMatrix = TreeMatch.initializeCDPMatrix(tree1, tree2);
        TreeMatch.computeCDPMatrix(tree1.getFirstRoot(),
                tree2.getFirstRoot(),
                tree1,
                tree2,
                cdpMatrix);

        int[][] cppMatrix = new int[tree1.size()][tree2.size()];
        TreeMatch.computeCPPMatrix(tree1, tree2, cdpMatrix, cppMatrix);

        for(int i=0; i<cppMatrix.length; i++){
            for(int j=0; j<cppMatrix[0].length; j++){

                if(cppMatrix[i][j] != 0){
                    kernel += 1 + cppMatrix[i][j];
                }
            }
        }
        return kernel;
    }

    public static double computeNormalizedKernel(SemanticGraph tree1, SemanticGraph tree2){

        double kernel1, kernel2, normKernel;

        kernel1 = computeKernel(tree1, tree1);
        kernel2 = computeKernel(tree2, tree2);
        normKernel = computeKernel(tree1, tree2);
        normKernel = normKernel/(Math.sqrt(1 + kernel1 * kernel2));

        return normKernel;
    }


    public static double computeNormalizedKernel(String text1, String text2, PipelineProvider pipelineProvider){

        TextAnnotator annotator1 = new TextAnnotator(text1, pipelineProvider.getPipeline());
        TextAnnotator annotator2 = new TextAnnotator(text2, pipelineProvider.getPipeline());

        CoreMap sentence1 = annotator1.getSentences().get(0);
        CoreMap sentence2 = annotator2.getSentences().get(0);

        SemanticGraph depGraph1 = sentence1.get(
                SemanticGraphCoreAnnotations.CollapsedCCProcessedDependenciesAnnotation.class);

        SemanticGraph depGraph2 = sentence2.get(
                SemanticGraphCoreAnnotations.CollapsedCCProcessedDependenciesAnnotation.class);

        return computeNormalizedKernel(depGraph1, depGraph2);
    }
}