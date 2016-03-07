package tree_match;

import edu.stanford.nlp.ling.IndexedWord;
import edu.stanford.nlp.semgraph.SemanticGraph;

import java.util.Arrays;

/**
 * Created by apoorv on 3/3/16.
 */
public class TreeMatch {


    public static int computeCDPMatrix(IndexedWord node1, IndexedWord node2, SemanticGraph tree1, SemanticGraph tree2,
                                       int[][] cdpMatrix){

        int node1Pos = node1.index() - 1;
        int node2Pos = node2.index() - 1;

        if (cdpMatrix[node1Pos][node2Pos] == -1){

            cdpMatrix[node1Pos][node2Pos] = 0;
            if(node1.word().equals(node2.word())){

                for(IndexedWord child1: tree1.getChildList(node1)){
                    for(IndexedWord child2: tree2.getChildList(node2)){
                        if(child1.word().equals(child2.word()))
                            cdpMatrix[node1Pos][node2Pos] += 1 + computeCDPMatrix(child1, child2, tree1, tree2, cdpMatrix);
                    }
                }
            }

        }

        return cdpMatrix[node1Pos][node2Pos];
    }


    public static <T extends Number> T computeCPPMatrix(IndexedWord node1, IndexedWord node2,
                                                        SemanticGraph tree1, SemanticGraph tree2,
                                                        int[][] cdpMatrix, T[][] cppMatrix){


        int node1Pos = node1.index();
        int node2Pos = node2.index();

        if(node1.word().equals(node2.word())){

            cppMatrix[node1Pos][node2Pos] = (T) Integer.valueOf(cdpMatrix[node1Pos][node2Pos]);

            for(IndexedWord childNode1: tree1.getChildList(node1)){
                for(IndexedWord childNode2: tree2.getChildList(node2)){

//                    cppMatrix

                }
            }

        }

        else{
            cppMatrix[node1Pos][node2Pos] = (T) Integer.valueOf(0);
        }


        return (T) Integer.valueOf(0);
    }



    public static <T extends Number> T[][] initializeMatrix(T[][] matrix){
        Arrays.fill(matrix, 0);
        return matrix;
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

}