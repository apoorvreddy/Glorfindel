package app.resources;

/**
 * Created by apoorv on 15/3/16.
 */
import app.App;
import app.response.AppResponse;
import com.google.common.base.Optional;


import javax.ws.rs.*;

import com.codahale.metrics.annotation.Timed;
import similarity_measures.tree_match.TreeMatch;

import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;

@Path("/similarity")
@Produces(MediaType.APPLICATION_JSON)
public class AppResource {

    private final String defaultFunction;
    private final AtomicLong counter;

    public AppResource(String defaultFunction) {
        this.defaultFunction = defaultFunction;
        this.counter = new AtomicLong();
    }

    @GET
    @Timed
    public AppResponse getSimilarity(@QueryParam("text1") String text1,
                                  @QueryParam("text2") String text2,
                                  @QueryParam("similarityFunction") Optional<String> similarityFunction) {

        double score = 0;
        return new AppResponse(
                counter.incrementAndGet(),
                text1,
                text2,
                score,
                similarityFunction.or(defaultFunction));
    }

    @POST
    @Timed
    public AppResponse getSimilarity(RequestBody requestBody){

        String text1 = requestBody.getText1();
        String text2 = requestBody.getText2();
        String similarityFunction = requestBody.getSimilarityFunction();



        double score = TreeMatch.computeNormalizedKernel(text1, text2, App.getPipelineProvider());
        return new AppResponse(
                counter.incrementAndGet(),
                text1,
                text2,
                score,
                similarityFunction);
    }

}