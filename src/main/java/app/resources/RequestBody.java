package app.resources;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by apoorv on 15/3/16.
 */
public class RequestBody {

    @JsonProperty
    private String text1;
    @JsonProperty
    private String text2;

    @JsonProperty
    private String similarityFunction;

    public String getText1() {
        return text1;
    }

    public String getText2() {
        return text2;
    }

    public String getSimilarityFunction() {
        return similarityFunction;
    }
}
