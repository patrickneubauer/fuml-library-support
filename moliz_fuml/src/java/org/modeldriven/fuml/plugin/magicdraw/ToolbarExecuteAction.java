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

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import com.nomagic.actions.NMAction;
import com.nomagic.magicdraw.actions.MDAction;
import com.nomagic.magicdraw.ui.dialogs.MDDialogParentProvider;

public class ToolbarExecuteAction  extends MDAction {
    /**
     */
    public ToolbarExecuteAction(String id, String name, KeyStroke stroke, String group)
    {
        super(id, name, stroke, group);
    }

    /**
     * Creates action with name "ExampleAction", and with key stroke E +CTRL+SHIFT
     */
    public ToolbarExecuteAction()
    {
        super( "Execute", "Execute",  
                KeyStroke.getKeyStroke( KeyEvent.VK_E, 
                        NMAction.MENU_SHORTCUT_MASK+KeyEvent.SHIFT_MASK), null);
    }



    /**
     * Method is called when action should be performed. Showing simple message.
     * @param e event causes action call.
     */
    public void actionPerformed(ActionEvent e)
    {
        JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogParent(), "This is:"+getName());
    }


}
