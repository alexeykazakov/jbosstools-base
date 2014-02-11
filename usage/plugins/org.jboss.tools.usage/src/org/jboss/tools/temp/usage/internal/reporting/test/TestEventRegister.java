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

import org.jboss.tools.usage.internal.event.EventRegister;

/**
 * @author Alexey Kazakov
 */
public class TestEventRegister extends EventRegister {

	private static TestEventRegister INSTANCE = new TestEventRegister();
	private long currentTime;
	private boolean reset = true;

	protected TestEventRegister() {
	}

	public static TestEventRegister getInstance() {
		return INSTANCE;
	}

	public void reset() {
		eventPreferences = null;
	}

	@Override
	protected void init() {
		if(reset) {
			reset();
		}
		super.init();
	}

	public boolean setReset(boolean reset) {
		boolean old = this.reset;
		this.reset = reset;
		return old;
	}

	@Override
	protected long getCurrentTime() {
		return this.currentTime;
	}

	public void setCurrentTime(long time) {
		this.currentTime = time;
	}
}