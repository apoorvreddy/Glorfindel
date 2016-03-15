package app;

/**
 * Created by apoorv on 15/3/16.
 */

import app.resources.AppResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import text_util.PipelineProvider;

public class App extends Application<AppConfiguration> {
    public static PipelineProvider pipelineProvider = PipelineProvider.getInstance();

    public static void main(String[] args) throws Exception {
        PipelineProvider.getPipeline();
        new App().run(args);
    }

    @Override
    public String getName() {
        return "similarity";
    }

    @Override
    public void initialize(Bootstrap<AppConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(AppConfiguration configuration,
                    Environment environment) {

        AppResource resource = new AppResource(configuration.getDefaultFunction());
        environment.jersey().register(resource);
    }

    public static PipelineProvider getPipelineProvider() {
        return pipelineProvider;
    }
}