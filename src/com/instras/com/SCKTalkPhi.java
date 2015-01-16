/*
 * Copyright (C) 2015 Instras Scientific LLC
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.instras.com;

import java.awt.EventQueue;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Nathan Stevens
 */
public class SCKTalkPhi {

    /**
     * Main method where either the desktop or frame for the Raspberry PiTFT is
     * called.
     *
     * @param args
     */
    public static void main(final String[] args) {
        try {
            // set the system look and feel
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(SCKTalkPhi.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                // launch the desktop UI
                if (args.length == 0) {
                    SCKTalkPhiDesktop sckTalkPhiDesktop = new SCKTalkPhiDesktop();
                    sckTalkPhiDesktop.pack();
                    sckTalkPhiDesktop.setVisible(true);
                } else {
                    // we must be on Raspberry Pi to go into full screen mode
                    SCKTalkPhiRaspberryPi sckTalkPhiRaspberryPi = new SCKTalkPhiRaspberryPi();

                    JFrame frame = new JFrame();
                    frame.setResizable(false);

                    if (!frame.isDisplayable()) {
                        // Can only do this when the frame is not visible
                        frame.setUndecorated(true);
                    }

                    GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

                    // add the GUI panel now
                    frame.add(sckTalkPhiRaspberryPi);
                    
                    if (gd.isFullScreenSupported()) {
                        gd.setFullScreenWindow(frame);
                        System.out.println("In full screen mode");
                    } else {
                        // Can't run fullscreen, need to bodge around it (setSize to screen size, etc)
                    }

                    sckTalkPhiRaspberryPi.validate();
                    sckTalkPhiRaspberryPi.setVisible(true);
                }
            }
        });
    }

}