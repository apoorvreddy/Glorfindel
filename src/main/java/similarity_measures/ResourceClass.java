package similarity_measures;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by apoorv on 16/3/16.
 */
public class ResourceClass {

    private static ResourceClass instance = new ResourceClass();

    private Set<String> stopWords;

    private ResourceClass(){

        stopWords = new HashSet<String>(Arrays.asList("i", "me", "my", "myself", "we", "our", "ours",
                "ourselves", "you", "your", "yours", "yourself", "yourselves", "he", "him",
                "his", "himself", "she", "her", "hers", "herself", "it", "it's", "its", "itself",
                "they", "them", "their", "theirs", "themselves", "what", "which", "who",
                "whom", "this", "that", "these", "those", "am", "is", "are", "was", "were",
                "be", "been", "being", "have", "has", "had", "having", "do", "does", "did",
                "doing", "a", "an", "the", "and", "but", "if", "or", "because", "as", "until",
                "while", "of", "at", "by", "for", "with", "about", "against", "between", "into",
                "through", "during", "before", "after", "above", "below", "to", "from", "up",
                "down", "in", "out", "on", "off", "over", "under", "again", "further", "then",
                "once", "here", "there", "when", "where", "why", "how", "all", "any", "both",
                "each", "few", "more", "most", "other", "some", "such", "no", "nor",
                "only", "own", "same", "so", "than", "too", "very", "s", "t", "can", "will",
                "just", "don", "should", "now"));
    }

    public static ResourceClass getInstance() {
        return instance;
    }

    public Set<String> getStopWords() {
        return stopWords;
    }
}
