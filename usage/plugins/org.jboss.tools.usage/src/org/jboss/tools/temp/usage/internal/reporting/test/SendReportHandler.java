/*******************************************************************************
 * Copyright (c) 2014 Red Hat, Inc.
 * Distributed under license by Red Hat, Inc. All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Red Hat, Inc. - initial API and implementation
 ******************************************************************************/
package org.jboss.tools.temp.usage.internal.reporting.test;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.jboss.tools.usage.event.UsageEventType;
import org.jboss.tools.usage.googleanalytics.RequestType;

/**
 * @author Alexey Kazakov
 */
public class SendReportHandler implements IHandler {

	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.IHandler#addHandlerListener(org.eclipse.core.commands.IHandlerListener)
	 */
	@Override
	public void addHandlerListener(IHandlerListener handlerListener) {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.IHandler#dispose()
	 */
	@Override
	public void dispose() {
	}


	@Override
	public Object execute(ExecutionEvent e) throws ExecutionException {
		TestUsageReporter reporter = new TestUsageReporter();

		UsageEventType type = new UsageEventType("usage", "1.2.100", "central", "showOnStartup", "true/false");
		reporter.registerEvent(type);

//		/tools/usage/action/wsstartup/1.2.2.Final-v20131204-1734-B141
		reporter.trackEvent("/tools/usage/action/wsstartup/1.2.100", "Usage Test", type.event(), RequestType.PAGE, true);
		try {Thread.sleep(3000);} catch (InterruptedException e1) {e1.printStackTrace();}

		for (int i = 0; i < 3; i++) {
			type = new UsageEventType("server", "3.5.0", null, "new", "Server runtime type. e.g. AS-6.2", "1 - success, 0 - failure");
			if(i==0) {
				reporter.registerEvent(type);
			}
			reporter.trackEvent(type.event("AS7.1", 1));
			try {Thread.sleep(3000);} catch (InterruptedException e1) {e1.printStackTrace();}

			type = new UsageEventType("server", "3.5.0", null, "start", "Server runtime type. e.g. AS-6.2", "How many times started a day");
			reporter.registerEvent(type);
			reporter.trackEvent(type.event("AS7.2", 1));
			try {Thread.sleep(3000);} catch (InterruptedException e1) {e1.printStackTrace();}
		}

		for (int i = 0; i < 10; i++) {
			type = new UsageEventType("central", "2.2.0", null, "COUNT-start-from-scratch", "Example project name. e.g. HTML5Example", "1 - success, 0 - failure");
			reporter.registerEvent(type);
			reporter.countEvent(type.event("HTML5-project", 1));
			try {Thread.sleep(3000);} catch (InterruptedException e1) {e1.printStackTrace();}
		}

		for (int i = 0; i < 5; i++) {
			type = new UsageEventType("jst", "3.3.0", null, "COUNT-palette", "JQM Widget name. e.g. Button");
			reporter.registerEvent(type);
			reporter.countEvent(type.event("JQM-page"));
			try {Thread.sleep(3000);} catch (InterruptedException e1) {e1.printStackTrace();}
		}

		for (int i = 0; i < 7; i++) {
			type = new UsageEventType("browsersim", "3.2.0", null, "COUNT-browsersim", "webkit/javafx");
			if(i==0) {
				reporter.registerEvent(type);
			}
			reporter.countEvent(type.event("webkit"));
			try {Thread.sleep(3000);} catch (InterruptedException e1) {e1.printStackTrace();}
		}

		for (int i = 0; i < 8; i++) {
			if(i==0) {
				reporter.registerEvent(type);
			}
			type = new UsageEventType("browsersim", "3.2.0", null, "COUNT-browsersim", "webkit/javafx");
			reporter.countEvent(type.event("javafx"));
			try {Thread.sleep(3000);} catch (InterruptedException e1) {e1.printStackTrace();}
		}

		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
/*
	public Object execute(ExecutionEvent e) throws ExecutionException {
		TestUsageReprorter reporter = new TestUsageReprorter();
		//Session 1
//		/tools/usage/action/wsstartup/1.2.2.Final-v20131204-1734-B141
		reporter.sendRequest("/tools/usage/action/wsstartup/2.0.0", "Usage Test", null, RequestType.PAGE, true, false);
		try {Thread.sleep(3000);} catch (InterruptedException e1) {e1.printStackTrace();}

		UsageEventType type = new UsageEventType("central", "2.2.0", null, "start-from-scratch", "label-description", "value-description");
		UsageEvent event = new UsageEvent(type, "HTML5-project", 1);
		reporter.sendRequest("/tools/central/2.2.0", "Usage Test", event, RequestType.PAGE, false, false);
		try {Thread.sleep(3000);} catch (InterruptedException e1) {e1.printStackTrace();}
		type = new UsageEventType("central", "2.2.0", null, "start-from-scratch", "label-description", "value-description");
		event = new UsageEvent(type, "HTML5-project", 0);
		reporter.sendRequest( "/tools/central/2.2.0", "Usage Test", event, RequestType.PAGE, false, false);
		try {Thread.sleep(3000);} catch (InterruptedException e1) {e1.printStackTrace();}
		type = new UsageEventType("central", "2.2.0", null, "start-from-scratch", "label-description", "value-description");
		event = new UsageEvent(type, "HTML5-project", 1);
		reporter.sendRequest("/tools/central/2.2.0", "Usage Test", event, RequestType.PAGE, false, false);
		try {Thread.sleep(3000);} catch (InterruptedException e1) {e1.printStackTrace();}

		type = new UsageEventType("jst", "4.5.0", null, "palette-click", "label-description");
		event = new UsageEvent(type, "JQM-page");
		reporter.sendRequest("/tools/jst/4.5.0", "Usage Test", event, RequestType.PAGE, false, false);
		try {Thread.sleep(3000);} catch (InterruptedException e1) {e1.printStackTrace();}
		type = new UsageEventType("jst", "4.5.0", null, "palette-click", "label-description");
		event = new UsageEvent(type, "JQM-page");
		reporter.sendRequest("/tools/jst/4.5.0", "Usage Test", event, RequestType.PAGE, false, false);
		try {Thread.sleep(3000);} catch (InterruptedException e1) {e1.printStackTrace();}
		type = new UsageEventType("jst", "4.5.0", null, "palette-click", "label-description");
		event = new UsageEvent(type, "JQM-dialog");
		reporter.sendRequest("/tools/jst/4.5.0", "Usage Test", event, RequestType.PAGE, false, false);
		try {Thread.sleep(3000);} catch (InterruptedException e1) {e1.printStackTrace();}

		type = new UsageEventType("browsersim", "1.3.0", null, "launch", "label-description");
		event = new UsageEvent(type, "webkit");
		reporter.sendRequest("/tools/browsersim/1.3.0", "Usage Test", event, RequestType.PAGE, false, false);
		try {Thread.sleep(3000);} catch (InterruptedException e1) {e1.printStackTrace();}

		type = new UsageEventType("jst", "4.5.0", null, "palette-click", "label-description");
		event = new UsageEvent(type, "JQM-footer");
		reporter.sendRequest("/tools/jst/4.5.0", "Usage Test", event, RequestType.PAGE, false, false);
		try {Thread.sleep(3000);} catch (InterruptedException e1) {e1.printStackTrace();}

		type = new UsageEventType("browsersim", "1.3.0", null, "launch", "label-description");
		event = new UsageEvent(type, "javafx");
		reporter.sendRequest("/tools/browsersim/1.3.0", "Usage Test", event, RequestType.PAGE, false, false);
		try {Thread.sleep(3000);} catch (InterruptedException e1) {e1.printStackTrace();}

		//Session 2
		

//		UsageEventType type;
//		UsageEvent event;

		reporter.sendRequest("/tools/usage/action/wsstartup/2.0.0", "Usage Test", null, RequestType.PAGE, true, false);
		try {Thread.sleep(3000);} catch (InterruptedException e1) {e1.printStackTrace();}

		type = new UsageEventType("central", "2.2.0", null, "start-from-sample", "label-description", "value-description");
		event = new UsageEvent(type, "kitchensink", 1);
		reporter.sendRequest("/tools/central/2.2.0", "Usage Test", event, RequestType.PAGE, false, false);
		try {Thread.sleep(3000);} catch (InterruptedException e1) {e1.printStackTrace();}
		type = new UsageEventType("server", "3.1.0", null, "create-server", "label-description");
		event = new UsageEvent(type, "AS7.1");
		reporter.sendRequest("/tools/server/3.1.0", "Usage Test", event, RequestType.PAGE, false, false);
		try {Thread.sleep(3000);} catch (InterruptedException e1) {e1.printStackTrace();}
		type = new UsageEventType("openshift", "2.4.0", null, "new-application-wizard", "label-description");
		event = new UsageEvent(type);
		reporter.sendRequest("/tools/openshift/2.4.0", "Usage Test", event, RequestType.PAGE, false, false);
		try {Thread.sleep(3000);} catch (InterruptedException e1) {e1.printStackTrace();}
		type = new UsageEventType("server", "3.1.0", null, "start-server", "label-description");
		event = new UsageEvent(type, "AS7.1");
		reporter.sendRequest("/tools/server/3.1.0", "Usage Test", event, RequestType.PAGE, false, false);

//		GoogleAnalyticsEvent event = new GoogleAnalyticsEvent("testCategory", "testAction", "testLabel");
//		reporter.sendRequest(environment, "/tools/usage/test/send_request/event", "Usage Test", event, RequestType.EVENT, true);
		return null;
	}
*/
	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.IHandler#isEnabled()
	 */
	@Override
	public boolean isEnabled() {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.IHandler#isHandled()
	 */
	@Override
	public boolean isHandled() {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.IHandler#removeHandlerListener(org.eclipse.core.commands.IHandlerListener)
	 */
	@Override
	public void removeHandlerListener(IHandlerListener handlerListener) {
	}
}