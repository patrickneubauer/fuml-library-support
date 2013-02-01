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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import org.modeldriven.fuml.Fuml;

import com.nomagic.magicdraw.core.Application;
import com.nomagic.uml2.impl.magicdraw.activities.mdfundamentalactivities.ActivityImpl;

class ExecutionThread extends Thread {
    private File projectFile;
    private ActivityImpl activity;

    public ExecutionThread(File projectFile, ActivityImpl activity) {
        this.projectFile = projectFile;
        this.activity = activity;
    }

    private void writeUIMessage(String msg) {
        Application.getInstance().getGUILog().log(msg);
    }

    public void run() {
        PrintStream oldStream = System.out;

        // System.setProperty("log4j.configuration", "file:log4j.properties");
        PrintStream stream = new PrintStream(new MessageWindowOutputStream());
        System.setOut(stream);
        try {
            writeUIMessage("[plugin] launching fUML runtime environment...");
            new Fuml(projectFile, activity.getName());
            writeUIMessage("[plugin] execution complete");
        } catch (Throwable t) {
            writeUIMessage("[error] " + t.getMessage());
        } finally {
            System.setOut(oldStream);
            try {
                stream.close();
            } catch (Throwable t) {
            }
        }
    }

    class MessageWindowOutputStream extends ByteArrayOutputStream {
        public void write(byte[] b, int off, int len) {
            String s = new String(b, off, len);
            if (s.trim().length() > 0)
                writeUIMessage(s);
            super.write(b, off, len);
        }
    }
}
