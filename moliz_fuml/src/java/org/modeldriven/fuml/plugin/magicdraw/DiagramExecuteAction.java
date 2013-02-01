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
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.swing.KeyStroke;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.core.project.ProjectsManager;
import com.nomagic.magicdraw.ui.actions.DefaultDiagramAction;
import com.nomagic.magicdraw.uml.symbols.DiagramPresentationElement;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Namespace;
import com.nomagic.uml2.impl.magicdraw.activities.mdfundamentalactivities.ActivityImpl;
import com.nomagic.uml2.impl.magicdraw.classes.mdkernel.DiagramImpl;

/**
*/
public class DiagramExecuteAction extends DefaultDiagramAction {

    private static Log log = LogFactory.getLog(BrowserExecuteAction.class);
    
    /**
     * Creates action with name "BrowserAction Example"
     */
    public DiagramExecuteAction() {
        super("Execute", "Execute", 
                KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.SHIFT_MASK+KeyEvent.ALT_MASK), null);
    }

    /**
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent e) {
        try {
            DiagramPresentationElement presElement = this.getDiagram();
            Element elem = presElement.getElement();
            //JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogParent(), 
            //        elem.getClass().getName());
            DiagramImpl diagram = (DiagramImpl)elem;
            Namespace owner = diagram.getOwnerOfDiagram();
            ActivityImpl activity = (ActivityImpl)owner;  
            
            ProjectsManager manager = Application.getInstance().getProjectsManager();
            Project project = manager.getActiveProject();
    
            ExecutionThread executionThread = new ExecutionThread(
                    new File(project.getFileName()),
                    activity);
            executionThread.start();        
        }
        catch (Throwable t) {
            StringWriter writer = new StringWriter();
            t.printStackTrace(new PrintWriter(writer));
            javax.swing.JOptionPane.showMessageDialog( null, writer.toString());
        }
    }
    
}