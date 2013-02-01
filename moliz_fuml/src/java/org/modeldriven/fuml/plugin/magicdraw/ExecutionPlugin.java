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


import java.io.PrintWriter;
import java.io.StringWriter;

import javax.swing.ImageIcon;

import com.nomagic.magicdraw.actions.ActionsConfiguratorsManager;
import com.nomagic.magicdraw.plugins.Plugin;
import com.nomagic.magicdraw.ui.browser.actions.DefaultBrowserAction;
import com.nomagic.magicdraw.uml.DiagramType;

/**
 */
public class ExecutionPlugin extends Plugin
{

    /**
     * @see com.nomagic.magicdraw.plugins.Plugin#init()
     * Adding actions on plugin init.
     */
    public void init()
    {
        
        try {
            ActionsConfiguratorsManager manager = ActionsConfiguratorsManager.getInstance();
            /*
            // Main toolbar
            MDAction action = new ToolbarExecuteAction();
            // setting icon. Button with icon looks beter than with text in toolbar.
            action.setLargeIcon( new ImageIcon( getClass().getResource("gears.gif") ) );
            action.setDescription("Execute");
            // Adding action to main toolbar
            manager.addMainToolbarConfigurator(new MainToolbarConfigurator(action) );
            */
            
            // context browser
            DefaultBrowserAction browserExecuteAction = new BrowserExecuteAction();
            browserExecuteAction.setLargeIcon( new ImageIcon(getClass().getResource("gears.gif")));
            browserExecuteAction.setDescription("Execute (Alt+Shift+E)");
            BrowserConfigurator configurator = new BrowserConfigurator(browserExecuteAction);
            manager.addContainmentBrowserContextConfigurator( configurator );
            manager.addContainmentBrowserShortcutsConfigurator( configurator );
        
            // Activity diagram toolbar
            DiagramExecuteAction diagramExecuteAction = new DiagramExecuteAction();
            diagramExecuteAction.setLargeIcon( new ImageIcon(getClass().getResource("gears.gif")));
            diagramExecuteAction.setDescription("Execute");
            
            DiagramConfigurator diagramConfigurator = new DiagramConfigurator(diagramExecuteAction);
            manager.addDiagramContextConfigurator(DiagramType.UML_ACTIVITY_DIAGRAM, diagramConfigurator);
            manager.addDiagramShortcutsConfigurator(DiagramType.UML_ACTIVITY_DIAGRAM, diagramConfigurator);
            manager.addDiagramToolbarConfigurator(DiagramType.UML_ACTIVITY_DIAGRAM, diagramConfigurator);
            manager.addDiagramCommandBarConfigurator(DiagramType.UML_ACTIVITY_DIAGRAM, diagramConfigurator);
            
            //DiagramInnerToolbarConfiguration(Icon icon, String name) what's this??
        
        }
        catch (Throwable t) {
            StringWriter writer = new StringWriter();
            t.printStackTrace(new PrintWriter(writer));
            javax.swing.JOptionPane.showMessageDialog( null, writer.toString());
        }
    }


    /**
     * @see com.nomagic.magicdraw.plugins.Plugin#close()
     */
    public boolean close()
    {
        return true;
    }

    /**
     * @see com.nomagic.magicdraw.plugins.Plugin#isSupported()
     */
    public boolean isSupported()
    {
        return true;
    }

}