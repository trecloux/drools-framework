package org.chtijbug.drools.guvnor.rest;

/**
 * Created by IntelliJ IDEA.
 * Date: 25/04/12
 * Time: 10:12
 * To change this template use File | Settings | File Templates.
 */

import org.chtijbug.drools.guvnor.rest.dt.DecisionTable;
import org.drools.ide.common.client.modeldriven.dt52.GuidedDecisionTable52;

import java.io.InputStream;

public interface RestRepositoryConnector {


    /**
     * This method retrieves the decision table from Guvnor assets repository according to the name argument.
     *
     * @param dtName {@link String} the decision table name into the Guvnor Repository
     * @return {@link GuidedDecisionTable52} the
     * @throws GuvnorConnexionFailedException - if the connexion to the Guvnor repository failed.
     */
    DecisionTable getGuidedDecisionTable(String dtName) throws GuvnorConnexionFailedException, ChtijbugDroolsRestException;

    void commitChanges(DecisionTable guidedDecisionTable52) throws GuvnorConnexionFailedException;

    InputStream getPojoModel();

}

