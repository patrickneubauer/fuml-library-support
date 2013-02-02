/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.fumldebug.debugger.papyrus.provider;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.modelexecution.fumldebug.debugger.provider.IActivityProvider;
import org.modelexecution.fumldebug.debugger.provider.IActivityProviderFactory;

/**
 * An {@link IActivityProviderFactory} for Papyrus UML2 activity diagram
 * resources.
 * 
 * @author Philip Langer (langer@big.tuwien.ac.at)
 */
public class PapyrusActivityProviderFactory implements IActivityProviderFactory {

	protected static final String FILE_EXT = "di"; //$NON-NLS-1$

	@Override
	public boolean supports(IResource resource) {
		return isExistingFileWithSupportedExtension(resource);
	}

	private boolean isExistingFileWithSupportedExtension(IResource resource) {
		return isIFile(resource) && resource.exists()
				&& FILE_EXT.equals(resource.getFileExtension());
	}

	private boolean isIFile(IResource resource) {
		return resource instanceof IFile;
	}

	@Override
	public IActivityProvider createActivityProvider(IResource resource) {
		if (!supports(resource))
			throw new IllegalArgumentException();
		return new PapyrusActivityProvider((IFile) resource);
	}

}
