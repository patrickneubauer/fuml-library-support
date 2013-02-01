/*
 * Copyright 2008 Lockheed Martin Corporation, except as stated in the file 
 * entitled Licensing-Information. All modifications copyright 2009 Data Access Technologies, Inc. Licensed under the Academic Free License 
 * version 3.0 (http://www.opensource.org/licenses/afl-3.0.php), except as stated 
 * in the file entitled Licensing-Information. 
 *
 * Contributors:
 *   MDS - initial API and implementation
 *
 */

package org.modeldriven.fuml.plugin.magicdraw;

import com.nomagic.actions.AMConfigurator;
import com.nomagic.actions.ActionsManager;
import com.nomagic.magicdraw.actions.BrowserContextAMConfigurator;
import com.nomagic.magicdraw.actions.MDActionsCategory;
import com.nomagic.magicdraw.ui.browser.Tree;
import com.nomagic.magicdraw.ui.browser.actions.DefaultBrowserAction;

/**
 */
public class BrowserConfigurator implements BrowserContextAMConfigurator, AMConfigurator
{

	/**
	 * Action which should be added to the tree.
	 */
	private DefaultBrowserAction action;

	/**
	 * Creates configurator for adding given action.
	 * @param action action to be added to manager.
	 */
	public BrowserConfigurator( DefaultBrowserAction action)
	{
		this.action = action;
	}

	/**
	 * @see com.nomagic.magicdraw.actions.BrowserContextAMConfigurator#configure(com.nomagic.actions.ActionsManager, com.nomagic.magicdraw.ui.browser.Tree)
	 */
	public void configure(ActionsManager mngr, Tree tree)
	{
		configure(mngr);
	}

	/**
	 * @see com.nomagic.actions.AMConfigurator#configure(com.nomagic.actions.ActionsManager)
	 */
	public void configure(ActionsManager mngr)
	{
		// adding action separator
		MDActionsCategory cat= new MDActionsCategory("BrowserConfiguratorExample",
		        "BrowserConfiguratorExample");
		cat.addAction(action);
		mngr.addCategory(cat);
	}

	public int getPriority()
	{
		return AMConfigurator.MEDIUM_PRIORITY;
	}
}
