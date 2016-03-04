package text_util;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

import java.util.List;

/**
 * Created by apoorv on 14/8/15.
 */
public class TextAnnotator {

    private String textBlock;
    private StanfordCoreNLP pipeline;
    private Annotation document;
    private List<CoreMap> sentences;


    public TextAnnotator(String text, StanfordCoreNLP pipeline){
        textBlock = text;
        document = new Annotation(textBlock);
        this.pipeline = pipeline;
        pipeline.annotate(document);
        sentences = document.get(CoreAnnotations.SentencesAnnotation.class);
    }

    public StanfordCoreNLP getPipeline() {
        return pipeline;
    }

    public Annotation getDocument() {
        return document;
    }

    public List<CoreMap> getSentences() {
        return sentences;
    }

    public String getTextBlock() {
        return textBlock;
    }


}