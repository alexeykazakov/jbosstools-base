/*******************************************************************************
 * Copyright (c) 2014 Red Hat, Inc.
 * Distributed under license by Red Hat, Inc. All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Red Hat, Inc. - initial API and implementation
 *     Zend Technologies Ltd. - JBIDE-18678
 ******************************************************************************/
package org.jboss.tools.usage.event;

import org.jboss.tools.usage.googleanalytics.RequestType;

/**
 * @author Alexey Kazakov
 * @author Andre Dietisheim
 * @author Kaloyan Raev
 */
public class UsageReporter {

	public static final String NOT_APPLICABLE_LABEL = "N/A";

	private static UsageReporter INSTANCE;
	
	private IUsageReporterService service;
	
	public UsageReporter() {
		INSTANCE = this;
	}

	public static UsageReporter getInstance() {
		if (INSTANCE == null) {
			new UsageReporter();
		}
		return INSTANCE;
	}
	
	public synchronized void setService(IUsageReporterService service) {
		this.service = service;
	}
	
	public synchronized void unsetService(IUsageReporterService service) {
		if (this.service == service) {
			this.service = null;
		}
	}

	/**
	 * Registers the event type
	 * 
	 * @param type
	 */
	public void registerEvent(UsageEventType type) {
		if (service != null) {
			service.registerEvent(type);
		}
	}

	/**
	 * Tracks a user's event 
	 * 
	 * @param event
	 */
	public void trackEvent(UsageEvent event) {
		if (service != null) {
			service.trackEvent(event);
		}
	}

	/**
	 * Tracks a user's event
	 * 
	 * @param pagePath
	 * @param title
	 * @param event
	 * @param type
	 * @param startNewVisitSession
	 */
	public void trackEvent(String pagePath, String title, UsageEvent event, RequestType type,
			boolean startNewVisitSession) {
		if (service != null) {
			service.trackEvent(pagePath, title, event, type, startNewVisitSession);
		}
	}

	/**
	 * Doesn't send a tracking request instantly but remembers the event's value for tracking events once a day.
	 * If the type of this event was used for sending or counting events a day before then a new event with a sum (if bigger than 0) of all previously collected events is sent.
	 * Category, action names and labels are taken into account when values are being counted.
	 * For events without labels and/or values the "N/A" is used as a label and "1" is used as the default value.
	 * @param event  
	 */
	public void countEvent(UsageEvent event) {
		if (service != null) {
			service.countEvent(event);
		}
	}

	/**
	 * Sends a tracking request for all daily events if it's time to send them 
	 */
	public void trackCountEvents() {
		if (service != null) {
			service.trackCountEvents();
		}
	}

}