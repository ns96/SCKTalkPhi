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
package com.instras;

import java.awt.EventQueue;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Nathan Stevens
 * @version 2.0.0 11/17/2020
 */
public class SCKTalkPhi {
    public static final String VERSION = "SCKTalkPhi v2.1.0 (10/06/2024)";

    // the scaling factor which converts everything into rpm with the SCK-300S+
    // 3.75 deg per step = 0.03906
    // 7.5 deg per step = 0.07812
    public static double SCALE_FACTOR = 0.03906;
    
    public static final String RAMP_SEQUENCE_FILE = "ramp_sequence.txt";
    
    /**
     * Method to read a text file into string
     * @param filePath
     * @return string containing the contents of the file
     */
    public static String readFileAsString(String filePath) {
        String content = null;
 
        try {
            System.out.println("Reading file: " + filePath + " ...");
            content = new String ( Files.readAllBytes( Paths.get(filePath) ) );
        } 
        catch (IOException e) {
            System.out.println("Missing file: " + filePath);
        }
 
        return content;
    }
    
    /**
     * Method to write a string to a file
     * @param content
     * @param filePath
     * @return 
     */
    public static boolean writeStringToFile(String content, String filePath) {
        try {
            System.out.println("Writing file: " + filePath + " ...");
            File output = new File(filePath);
            FileWriter writer = new FileWriter(output);

            writer.write(content);
            writer.flush();
            writer.close();
        } catch (IOException ex) {
            return false;
        }    
        
        return true;
    }
    
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
            @Override
            public void run() {
                // launch the desktop UI
                if (args.length == 0) {
                    SCKTalkPhiDesktop sckTalkPhiDesktop = new SCKTalkPhiDesktop();
                    sckTalkPhiDesktop.pack();
                    sckTalkPhiDesktop.setVisible(true);
                } else {
                    // we must be on Raspberry Pi to go into full screen mode and make sure
                    // to run this program as root by first running startx as root
                    // "sudo FRAMEBUFFER=/dev/fb1 startx"
                    // sudo ./run.sh
                    
                    // check to see if use a different rescale factor
                    if(args.length >= 2) {
                        try {
                            double rf = Double.parseDouble(args[1]);
                            SCKTalkPhi.SCALE_FACTOR = rf;
                            System.out.println("Setting Rescale Factor To: " + rf);
                        } catch(NumberFormatException nfe) {
                            System.out.println("Error Setting Rescale Factor To: " + args[1]);
                        }
                    }
                    
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
                        // Can't run fullscreen, need to dodge around it (setSize to screen size, etc)
                        frame.pack();
                    }

                    frame.validate();
                    frame.setVisible(true);
                }
            }
        });
    }
}
