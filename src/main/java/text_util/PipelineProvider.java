package text_util;

/**
 * Created by apoorv on 3/3/16.
 */


import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import java.util.Properties;

public class PipelineProvider {

    private static StanfordCoreNLP pipeline;
    private static PipelineProvider pipelineProvider = new PipelineProvider();

    public PipelineProvider(){

        Properties props = new Properties();

        System.out.println("Setting pipeline for tokenization ...");
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, depparse");
        pipeline = new StanfordCoreNLP(props);
    }

    public static PipelineProvider getInstance(){
        return pipelineProvider;
    }

    public static StanfordCoreNLP getPipeline(){
        return pipeline;
    }

}
