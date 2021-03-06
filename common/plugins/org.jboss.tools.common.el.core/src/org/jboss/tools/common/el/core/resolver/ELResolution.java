/******************************************************************************* 
 * Copyright (c) 2009 Red Hat, Inc. 
 * Distributed under license by Red Hat, Inc. All rights reserved. 
 * This program is made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html 
 * 
 * Contributors: 
 * Red Hat, Inc. - initial API and implementation 
 ******************************************************************************/ 
package org.jboss.tools.common.el.core.resolver;

import java.util.List;

import org.eclipse.jdt.core.IJavaElement;
import org.jboss.tools.common.el.core.model.ELExpression;

/**
 * Describes a result of EL resolving action.
 * @author Alexey Kazakov
 */
public interface ELResolution {

	/**
	 * @return all segments of this EL operand.
	 */
	List<ELSegment> getSegments();

	/**
	 * @return source EL operand.
	 */
	ELExpression getSourceOperand();

	/**
	 * @return EL context.
	 */
	ELContext getContext();

	/**
	 * Finds the segments which are resolved to given variable.
	 * @param element
	 * @return
	 */
	List<ELSegment> findSegmentsByVariable(IVariable variable);

	/**
	 * Finds the segments which are resolved to given java element.
	 * @param element
	 * @return
	 */
	List<ELSegment> findSegmentsByJavaElement(IJavaElement element);

	/**
	 * Finds the segments which are resolved to given baseName and propertyName.
	 * @param baseName
	 * @param propertyName
	 * @return
	 */
	List<ELSegment> findSegmentsByMessageProperty(String baseName, String propertyName);

	/**
	 * Finds the segment which is located at given offset.
	 * @param offcet relative source EL operand.
	 * @return
	 */
	ELSegment findSegmentByOffset(int offcet);

	/**
	 * @return unresolved segment. May return null.
	 */
	ELSegment getUnresolvedSegment();

	/**
	 * @return true if there is any unresolved segment.
	 */
	boolean isResolved();

	/**
	 * Returns false if EL validator should ignore this EL and don't mark it as a problem even if the EL is not resolved.
	 * @return
	 */
	boolean isValidatable();

	/**
	 * @return last segment. May return null.
	 */
	ELSegment getLastSegment();

	/**
	 * @return the number of resolved segments
	 */
	int getNumberOfResolvedSegments();

	/**
	 * @return the resolved value of this EL as String.
	 * In case of message bundle properties it will be an value from properties file.
	 * If the value is not available for this EL then source EL String will be returned.
	 */
	String getValue();
}