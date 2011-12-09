/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.chtijbug.drools.runtime.impl;

import java.util.List;

import org.chtijbug.drools.entity.history.FactObject;
import org.chtijbug.drools.entity.history.rule.RuleFiredHistoryEvent;
import org.drools.event.rule.ActivationCancelledEvent;
import org.drools.event.rule.ActivationCreatedEvent;
import org.drools.event.rule.AfterActivationFiredEvent;
import org.drools.event.rule.AgendaEventListener;
import org.drools.event.rule.AgendaGroupPoppedEvent;
import org.drools.event.rule.AgendaGroupPushedEvent;
import org.drools.event.rule.BeforeActivationFiredEvent;
import org.drools.runtime.rule.Activation;
import org.drools.runtime.rule.FactHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author nheron
 */
public class RuleHandlerListener implements AgendaEventListener {

	private final RuleBaseStatefullSession ruleBaseSession;
	static final Logger LOGGER = LoggerFactory.getLogger(RuleHandlerListener.class);

	public RuleHandlerListener(RuleBaseStatefullSession ruleBaseSession) {
		this.ruleBaseSession = ruleBaseSession;
	}

	@Override
	public void activationCreated(ActivationCreatedEvent ace) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void activationCancelled(ActivationCancelledEvent ace) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void beforeActivationFired(BeforeActivationFiredEvent bafe) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void afterActivationFired(AfterActivationFiredEvent event) {
		Activation activation = event.getActivation();
		List<? extends FactHandle> listFact = activation.getFactHandles();
		RuleFiredHistoryEvent newRuleEvent = new RuleFiredHistoryEvent(activation.getRule().getName());

		for (FactHandle h : listFact) {
			FactObject sourceFactObject = ruleBaseSession.getLastFactObjectVersionFromFactHandle(h);
			newRuleEvent.getWhenObjects().add(sourceFactObject);
		}

		LOGGER.debug("AfterActivationFiredEvent. Rule name: {} ", activation.getRule().getName());
		ruleBaseSession.getHistoryContainer().addHistoryElement(newRuleEvent);

		// nbRuleFired++;
		//
		// if (nbRuleFired > 2000) {
		// KnowledgeRuntime runTime = event.getKnowledgeRuntime();
		// runTime.halt();
		// }
		// LOGGER.debug("nbre RDG Fired ", nbRuleFired);

	}

	@Override
	public void agendaGroupPopped(AgendaGroupPoppedEvent agpe) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void agendaGroupPushed(AgendaGroupPushedEvent agpe) {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
