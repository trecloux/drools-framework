package org.chtijbug.drools.runner;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: samuel
 * Date: 03/10/12
 * Time: 17:48
 */
public class DroolsRunnerGenerationException extends Throwable {

    public DroolsRunnerGenerationException(String message) {
        super(message);
    }

    public DroolsRunnerGenerationException(String message, IOException cause) {
        super(message, cause);
    }
}
