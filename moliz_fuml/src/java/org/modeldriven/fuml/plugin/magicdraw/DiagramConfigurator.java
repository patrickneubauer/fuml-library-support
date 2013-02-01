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
import com.nomagic.magicdraw.actions.DiagramContextAMConfigurator;
import com.nomagic.magicdraw.actions.MDActionsCategory;
import com.nomagic.magicdraw.ui.actions.DefaultDiagramAction;
import com.nomagic.magicdraw.uml.symbols.DiagramPresentationElement;
import com.nomagic.magicdraw.uml.symbols.PresentationElement;

/**
 * Class for configuring diagram context menu actions, diagram shortcuts and
 * diagram toolbar.
 * 
 */
public class DiagramConfigurator implements DiagramContextAMConfigurator, AMConfigurator {
    /**
     * Action will be added to diagram.
     */
    private DefaultDiagramAction action;

    /**
     * Creates configurator wich adds given action.
     * 
     * @param action
     *            action to be added to manager.
     */
    public DiagramConfigurator(DefaultDiagramAction action) {
        this.action = action;
    }

    public void configure(ActionsManager mngr, DiagramPresentationElement diagram,
            PresentationElement[] selected, PresentationElement requestor) {
        ActionsCategory category = new MDActionsCategory(null, null);
        category.addAction(action);
        mngr.addCategory(category);
    }

    /**
     * @see com.nomagic.actions.AMConfigurator#configure(com.nomagic.actions.ActionsManager)
     *      Configuring toolbar and shortcuts.
     */
    public void configure(ActionsManager mngr) {
        if (mngr.getActionFor(action.getID()) == null) {
            ActionsCategory category = (ActionsCategory) mngr
                    .getActionFor(ActionsID.ACTIVITY_DIAGRAM_ELEMENTS);
            if (category != null) {
                category.addAction(action);
            }
        }
        for (Iterator iter = mngr.getCategories().iterator(); iter.hasNext();) {
            ActionsCategory category = (ActionsCategory) iter.next();
            // javax.swing.JOptionPane.showMessageDialog( null,
            // category.getID());
            if (category.getID().equals(ActionsID.VIEW)) {
                category.addAction(action);
            }
        }
    }

    public int getPriority() {
        return AMConfigurator.MEDIUM_PRIORITY;
    }

}
