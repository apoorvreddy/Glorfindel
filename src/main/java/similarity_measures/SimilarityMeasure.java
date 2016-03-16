package similarity_measures;

import java.util.List;

/**
 * Created by apoorv on 16/3/16.
 */
public interface SimilarityMeasure {

    double exec(String text1, String text2, List<Object> args);
}
