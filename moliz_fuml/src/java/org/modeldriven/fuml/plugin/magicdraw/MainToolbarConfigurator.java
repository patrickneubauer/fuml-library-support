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

import java.util.Iterator;

import com.nomagic.actions.AMConfigurator;
import com.nomagic.actions.ActionsCategory;
import com.nomagic.actions.ActionsManager;
import com.nomagic.magicdraw.actions.ActionsID;
import com.nomagic.magicdraw.actions.MDAction;

/**
 * Class for configuring main toolbar. It adds action to File toolbar.
 */
public class MainToolbarConfigurator implements AMConfigurator {

    /**
     * Action which will be added to main toolbar.
     */
    private MDAction action;

    /**
     * Creats configurator with given action.
     * 
     * @param action
     *            action to be added to main toolbar.
     */
    public MainToolbarConfigurator(MDAction action) {
        this.action = action;
    }

    /**
     * @see com.nomagic.actions.AMConfigurator#configure(ActionsManager) Method
     *      adds action to File category.
     */
    public void configure(ActionsManager mngr) {
        // searching for Help action category
        for (Iterator iter = mngr.getCategories().iterator(); iter.hasNext();) {
            ActionsCategory category = (ActionsCategory) iter.next();
            // javax.swing.JOptionPane.showMessageDialog( null,
            // category.getID());
            // adding action to found category.
            if (category.getID().equals(ActionsID.DIAGRAMS)) {
                category.addAction(action);
            }
        }
    }

    public int getPriority() {
        return AMConfigurator.MEDIUM_PRIORITY;
    }

}