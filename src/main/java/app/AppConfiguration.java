package app;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by apoorv on 15/3/16.
 */
public class AppConfiguration extends io.dropwizard.Configuration {

    @NotEmpty
    private String defaultFunction = "DepTreeKernel";

    @JsonProperty
    public String getDefaultFunction() {
        return defaultFunction;
    }

    @JsonProperty
    public void setDefaultNameString(String function) {
        this.defaultFunction = function;
    }

}
