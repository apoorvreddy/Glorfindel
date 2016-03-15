package app.response;

/**
 * Created by apoorv on 15/3/16.
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;


public class AppResponse {
    private long id;

    @Length(max = 256)
    private String text1;

    @Length(max = 256)
    private String text2;

    private String similarityFunction;

    private double score;

    public AppResponse() {
        // Jackson deserialization
    }

    public AppResponse(long id, String text1, String text2, double score, String similarityFunction) {
        this.id = id;
        this.text1 = text1;
        this.text2 = text2;
        this.score = score;
        this.similarityFunction = similarityFunction;
    }

    public AppResponse(long id, String text1, String text2, double score){
        this(id, text1, text2, score, "DepTreeKernel");
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public String getText1(){
        return text1;
    }

    @JsonProperty
    public String getText2(){
        return text2;
    }

    @JsonProperty
    public String getSimilarityFunction(){
        return similarityFunction;
    }

    @JsonProperty
    public double getScore(){
        return score;
    }
}

