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
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import javax.swing.KeyStroke;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modeldriven.fuml.Fuml;

import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.core.project.ProjectsManager;
import com.nomagic.magicdraw.ui.browser.Node;
import com.nomagic.magicdraw.ui.browser.actions.DefaultBrowserAction;
import com.nomagic.task.ProgressStatus;
import com.nomagic.task.RunnableWithProgress;
import com.nomagic.uml2.impl.magicdraw.activities.mdfundamentalactivities.ActivityImpl;

/**
     */
public class BrowserExecuteAction extends DefaultBrowserAction {

    private static Log log = LogFactory.getLog(BrowserExecuteAction.class);
    
    /**
     * Creates action with name "BrowserAction Example"
     */
    public BrowserExecuteAction() {
        super("", "Execute", KeyStroke.getKeyStroke(KeyEvent.VK_E,
                KeyEvent.ALT_MASK + KeyEvent.SHIFT_MASK), null);
    }

    /**
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent e) {
        
        ProjectsManager manager = Application.getInstance()
                .getProjectsManager();
        Project project = manager.getActiveProject();
        
        Node node = getTree().getSelectedNode();
        ActivityImpl activity = (ActivityImpl)node.getUserObject();  

        
        ExecutionThread executionThread = new ExecutionThread(
                new File(project.getFileName()),
                activity);
        executionThread.start();        
        
        /*
        ExecutionProgress executionProgress = new ExecutionProgress(
                new File(project.getFileName()),
                activity);
        
        ProgressMonitorHelper monitorHelper = new ProgressMonitorHelper();
        monitorHelper.executeWithProgress(executionProgress, "executing...", false, 200); 
        */
        
    }
    
    class ExecutionProgress implements RunnableWithProgress
    {
        private File projectFile;
        private ActivityImpl activity;
        
        public ExecutionProgress(File projectFile, ActivityImpl activity) {
            this.projectFile = projectFile;
            this.activity = activity;
        }
        
        public void run(ProgressStatus progressStatus)
        {
            PrintStream oldStream = System.out;
            
            //System.setProperty("log4j.configuration", "file:log4j.properties");
            PrintStream stream = new PrintStream(new MessageWindowOutputStream()); 
            System.setOut(stream);
            try {
                writeUIMessage("[plugin] launching fUML runtime environment...");
                new Fuml(projectFile, activity.getName());            
                writeUIMessage("[plugin] execution complete");
            }
            catch (Throwable t) {
                writeUIMessage("[error] " + t.getMessage());
            }
            finally {
                System.setOut(oldStream);
            }    
            
            int max = Integer.MAX_VALUE;
            progressStatus.init("Counting...", 0, max);
            for (int i = 0; i < max; ++i)
            {
                if (progressStatus.isCancel())
                {
                    // cancel clicked
                    return;
                }
                progressStatus.increase();
            }
        }
    }   
        
    private void writeUIMessage(String msg) {
        Application.getInstance().getGUILog().log(msg);
    }
    
    class MessageWindowOutputStream extends ByteArrayOutputStream
    {
        public void write(byte[] b, int off, int len) {
            String s = new String(b, off, len);
            if (s.trim().length() > 0)
                writeUIMessage(s);
            super.write(b, off, len);
        }        
    }
    
    public void updateState() {
        Node node = getTree().getSelectedNode();
        if (node != null) {
            Object object = node.getUserObject();
            if (object instanceof ActivityImpl)
                setEnabled(true);
            else
                setEnabled(false);
        }
    }
}