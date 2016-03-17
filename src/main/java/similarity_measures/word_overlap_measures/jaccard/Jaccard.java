package similarity_measures.word_overlap_measures.jaccard;

import similarity_measures.ResourceClass;
import similarity_measures.SimilarityMeasure;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by apoorv on 16/3/16.
 */
public class  Jaccard implements SimilarityMeasure{

    static Set<String> stopWordList = ResourceClass.getInstance().getStopWords();

    public static double computeJaccard(Set<String> s1, Set<String> s2){
        double jaccard = (double)intersectionSize(s1, s2)/unionSize(s1, s2);
        return jaccard;
    }

    public static Set<String> addToSet(String[] tokenizedSentence){

        Set<String> wordSet = new HashSet<String>();

        for(String token: tokenizedSentence){
            if(!stopWordList.contains(token))
            {
                wordSet.add(token);
            }
        }
        return wordSet;
    }

    public static int intersectionSize(Set<String> A, Set<String> B){

        Set<String> C = new HashSet<String>();
        C.addAll(A);
        C.retainAll(B);
        return C.size();
    }

    public static int unionSize(Set<String> A, Set<String> B){

        Set<String> C = new HashSet<String>();
        C.addAll(A);
        C.addAll(B);
        return C.size();
    }

    public double exec(String text1, String text2, List<Object> args) {

        String[] text1Tokens = text1.split(" ");
        String[] text2Tokens = text2.split(" ");

        Set<String> text1TokenSet = addToSet(text1Tokens);
        Set<String> text2TokenSet = addToSet(text2Tokens);

        return computeJaccard(text1TokenSet, text2TokenSet);

    }
}
