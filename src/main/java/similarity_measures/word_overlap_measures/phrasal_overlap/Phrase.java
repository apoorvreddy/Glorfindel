package similarity_measures.word_overlap_measures.phrasal_overlap;

/**
 * Created by apoorv on 16/3/16.
 */
public class Phrase {

    public int ngram;
    public int count;

    public Phrase(int ngram, int count){
        this.ngram = ngram;
        this.count = count;
    }
}
