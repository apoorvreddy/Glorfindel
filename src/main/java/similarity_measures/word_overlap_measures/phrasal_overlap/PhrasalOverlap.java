package similarity_measures.word_overlap_measures.phrasal_overlap;

import similarity_measures.SimilarityMeasure;

import java.util.*;

/**
 * Created by apoorv on 16/3/16.
 */
public class PhrasalOverlap implements SimilarityMeasure{


    private static Map<String, Phrase> constructCountVector(String[] tokens, int maxNgramLength){

        Map<String, Phrase> countMap = new HashMap<String, Phrase>();

        Vector<String> vector = new Vector(Arrays.asList(tokens));
        for(int i=0; i<maxNgramLength; i++){
            for(int j=0; j<tokens.length-i-1; j++){

                List<String> concatenatedStringList = vector.subList(j, j+i);
                String concatString = "";
                for(String s: concatenatedStringList){
                    concatString += " " + s.toLowerCase();
                }

                if(countMap.containsKey(concatString)){
                    Phrase phrase = countMap.get(concatString);
                    phrase.count ++;
                    countMap.put(concatString, phrase);
                }
                else
                    countMap.put(concatString, new Phrase(i, 1));
            }
        }

        return countMap;
    }


    private static int computeOverlap(String[] text1Tokens, String[] text2Tokens, int maxOverlap){

        Map<String, Phrase> countVector1 = constructCountVector(text1Tokens, maxOverlap);
        Map<String, Phrase> countVector2 = constructCountVector(text2Tokens, maxOverlap);

        Map<String, Phrase> minVector = countVector1.size()<countVector2.size()?countVector1:countVector2;

        int numerator = 0;

        for(Map.Entry<String, Phrase> entry: minVector.entrySet()){

            if(countVector1.containsKey(entry.getKey()) && countVector2.containsKey(entry.getKey())){
                Phrase phrase1 = countVector1.get(entry.getKey());
                Phrase phrase2 = countVector2.get(entry.getKey());
                numerator += Math.min(phrase1.count, phrase2.count) * Math.pow(phrase1.ngram+1, 2);
            }
        }
        return numerator;
    }


    public static double overlap(String text1, String text2){
        String[] text1Tokens = text1.split(" ");
        int text1Length = text1Tokens.length;

        String[] text2Tokens = text2.split(" ");
        int text2Length = text2Tokens.length;

        if (text1.equals(text2)){

            int n = text1Length;
            return (double)(n * (n+1) * (Math.pow(n, 2) + 3*n + 2))/12;
        }

        int maxOverLap = text1Length<text2Length?text1Length:text2Length;

        double denominator = Math.sqrt(
                computeOverlap(text1Tokens, text1Tokens, text1Length) *
        computeOverlap(text2Tokens, text2Tokens, text2Length));
        int numerator = computeOverlap(text1Tokens, text2Tokens, maxOverLap);

        System.out.println("Numerator: " + numerator + ", Denominator: " + denominator);
        return Math.tanh((double) numerator / denominator);
    }


    @Override
    public double exec(String text1, String text2, List<Object> args) {
        return overlap(text1, text2);
    }
}
