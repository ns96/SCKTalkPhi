/*
 * Copyright (C) 2015 nathan
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

import com.phidgets.PhidgetException;
import com.phidgets.StepperPhidget;
import com.phidgets.event.ErrorEvent;
import com.phidgets.event.ErrorListener;
import com.phidgets.event.StepperVelocityChangeEvent;
import com.phidgets.event.StepperVelocityChangeListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author nathan
 */
public class SCKTalkPhiRaspberryPi extends javax.swing.JPanel {
    private final String VERSION = "SCKTalkPhi v1.2 (01/27/2015)";
            
    // need to make this global since on the rPi touch screen 
    // we don't have a keyboard to enter values
    private JTextField currentRampTextField;

    // this object handle talking to the motor control board
    private StepperPhidget stepperPhidget;

    // The position target to move to. This must be a very big number to 
    // keep motor turning continuesly
    private long targetPosition = 10000000000L;

    // this specifies how fast the motor should accelarate
    private long acceleration = 5000L;

    // the current limit of 1.0
    private double currentLimit = 1.0;

    // this sets how many seconds the motor should spin by default ( 5 min)
    private int targetSpinTime = 300;

    // The spin speed to set
    private int setSpeed = 0;

    // the time object for count seconds
    private Timer spinTimer = null;

    // keep track of the spin time
    private int spinTime = 0;

    // used to exit the ramp sequence thread if the stop button was pressed
    private boolean stopMotor;

    // formatter used to only display one decimal place
    private final DecimalFormat df = new DecimalFormat("0.0");

    /**
     * Creates new form SCKTalkPhiRaspberryPi
     */
    public SCKTalkPhiRaspberryPi() {
        initComponents();

        // create a timer object for count seconds the motor is spining
        spinTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                updateSpinTime();
            }
        });
    }

    /**
     * Method to update the spin time or stop the motor from spinning
     */
    private void updateSpinTime() {
        spinTime++;

        // check to see if spin time is not more that the target time
        // if it is then stop the motor
        if (targetSpinTime > 0 && spinTime > targetSpinTime) {
            stopButtonActionPerformed(null);
        } else {
            spinTimeLabel.setText(spinTime + " sec");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new JPanel();
        startButton = createSimpleButton();
        upButton = createSimpleButton();
        downButton = createSimpleButton();
        stopButton = createSimpleButton();
        mainTabbedPane = new JTabbedPane();
        jPanel2 = new JPanel();
        connectToggleButton = createSimpleToggleButton();
        connectLabel = new JLabel();
        spinSpeedLabel = new JLabel();
        spinTimeLabel = new JLabel();
        jLabel1 = new JLabel();
        incrementComboBox = createSimpleCombobox();
        jLabel2 = new JLabel();
        spinSpeedTextField = new JTextField();
        runRampCheckBox = new JCheckBox();
        rampStepTextField = new JTextField();
        jPanel4 = new JPanel();
        jPanel3 = new JPanel();
        jLabel3 = new JLabel();
        directionComboBox = createSimpleCombobox();
        jLabel10 = new JLabel();
        maxSpeedTextField = new JTextField();
        jLabel11 = new JLabel();
        currentLimitTextField = new JTextField();
        jPanel5 = new JPanel();
        jLabel4 = new JLabel();
        jLabel5 = new JLabel();
        jLabel6 = new JLabel();
        jLabel7 = new JLabel();
        ramp1SpeedTextField = new JTextField();
        ramp1TimeTextField = new JTextField();
        jLabel8 = new JLabel();
        ramp2SpeedTextField = new JTextField();
        ramp2TimeTextField = new JTextField();

        setLayout(new BorderLayout());

        jPanel1.setLayout(new GridLayout(1, 0));

        startButton.setText("START");
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });
        jPanel1.add(startButton);

        upButton.setText("UP");
        upButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                upButtonActionPerformed(evt);
            }
        });
        jPanel1.add(upButton);

        downButton.setText("DOWN");
        downButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                downButtonActionPerformed(evt);
            }
        });
        jPanel1.add(downButton);

        stopButton.setText("STOP");
        stopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                stopButtonActionPerformed(evt);
            }
        });
        jPanel1.add(stopButton);

        add(jPanel1, BorderLayout.SOUTH);

        mainTabbedPane.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent evt) {
                mainTabbedPaneStateChanged(evt);
            }
        });

        jPanel2.setLayout(new GridLayout(5, 2, 2, 2));

        connectToggleButton.setText("CONNECT");
        connectToggleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                connectToggleButtonActionPerformed(evt);
            }
        });
        jPanel2.add(connectToggleButton);

        connectLabel.setFont(new Font("Tahoma", 1, 14)); // NOI18N
        connectLabel.setForeground(new Color(255, 0, 0));
        connectLabel.setText("Not Connected");
        connectLabel.setToolTipText("");
        jPanel2.add(connectLabel);

        spinSpeedLabel.setFont(new Font("Tahoma", 1, 14)); // NOI18N
        spinSpeedLabel.setForeground(Color.blue);
        spinSpeedLabel.setText("0 rpms");
        jPanel2.add(spinSpeedLabel);

        spinTimeLabel.setFont(new Font("Tahoma", 1, 14)); // NOI18N
        spinTimeLabel.setForeground(Color.blue);
        spinTimeLabel.setText("0 sec");
        jPanel2.add(spinTimeLabel);

        jLabel1.setFont(new Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Increment");
        jPanel2.add(jLabel1);

        incrementComboBox.setFont(new Font("Tahoma", 1, 14)); // NOI18N
        incrementComboBox.setModel(new DefaultComboBoxModel(new String[] { "1", "10", "20", "50", "100", "500" }));
        jPanel2.add(incrementComboBox);

        jLabel2.setFont(new Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Speed");
        jPanel2.add(jLabel2);

        spinSpeedTextField.setFont(new Font("Tahoma", 1, 11)); // NOI18N
        spinSpeedTextField.setText("3000");
        jPanel2.add(spinSpeedTextField);

        runRampCheckBox.setFont(new Font("Tahoma", 1, 14)); // NOI18N
        runRampCheckBox.setText("Ramp");
        runRampCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                runRampCheckBoxActionPerformed(evt);
            }
        });
        jPanel2.add(runRampCheckBox);

        rampStepTextField.setEditable(false);
        rampStepTextField.setText("Program Not Running ...");
        jPanel2.add(rampStepTextField);

        mainTabbedPane.addTab("MAIN", jPanel2);

        jPanel4.setLayout(new BorderLayout());

        jPanel3.setLayout(new GridLayout(3, 2, 2, 2));

        jLabel3.setFont(new Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Rotation");
        jLabel3.setToolTipText("");
        jPanel3.add(jLabel3);

        directionComboBox.setFont(new Font("Tahoma", 1, 14)); // NOI18N
        directionComboBox.setModel(new DefaultComboBoxModel(new String[] { "Clockwise", "Counter Clockwise" }));
        directionComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                directionComboBoxActionPerformed(evt);
            }
        });
        jPanel3.add(directionComboBox);

        jLabel10.setFont(new Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Max Speed");
        jPanel3.add(jLabel10);

        maxSpeedTextField.setFont(new Font("Tahoma", 1, 11)); // NOI18N
        maxSpeedTextField.setText("5000");
        maxSpeedTextField.setToolTipText("");
        maxSpeedTextField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                rampTextFieldFocusGained(evt);
            }
        });
        jPanel3.add(maxSpeedTextField);

        jLabel11.setFont(new Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Current Limit");
        jPanel3.add(jLabel11);

        currentLimitTextField.setFont(new Font("Tahoma", 1, 11)); // NOI18N
        currentLimitTextField.setText("1.0");
        currentLimitTextField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                currentLimitTextFieldFocusGained(evt);
            }
        });
        jPanel3.add(currentLimitTextField);

        jPanel4.add(jPanel3, BorderLayout.NORTH);

        jPanel5.setLayout(new GridLayout(3, 3, 2, 2));

        jLabel4.setFont(new Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("STEP");
        jPanel5.add(jLabel4);

        jLabel5.setFont(new Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("RPM");
        jPanel5.add(jLabel5);

        jLabel6.setFont(new Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("DWELL");
        jPanel5.add(jLabel6);

        jLabel7.setText("# 1");
        jPanel5.add(jLabel7);

        ramp1SpeedTextField.setFont(new Font("Tahoma", 1, 11)); // NOI18N
        ramp1SpeedTextField.setText("500");
        ramp1SpeedTextField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                rampTextFieldFocusGained(evt);
            }
        });
        jPanel5.add(ramp1SpeedTextField);

        ramp1TimeTextField.setFont(new Font("Tahoma", 1, 11)); // NOI18N
        ramp1TimeTextField.setText("10");
        ramp1TimeTextField.setToolTipText("");
        ramp1TimeTextField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                rampTextFieldFocusGained(evt);
            }
        });
        jPanel5.add(ramp1TimeTextField);

        jLabel8.setText("# 2");
        jPanel5.add(jLabel8);

        ramp2SpeedTextField.setFont(new Font("Tahoma", 1, 11)); // NOI18N
        ramp2SpeedTextField.setText("3000");
        ramp2SpeedTextField.setToolTipText("");
        ramp2SpeedTextField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                rampTextFieldFocusGained(evt);
            }
        });
        jPanel5.add(ramp2SpeedTextField);

        ramp2TimeTextField.setFont(new Font("Tahoma", 1, 11)); // NOI18N
        ramp2TimeTextField.setText("30");
        ramp2TimeTextField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                rampTextFieldFocusGained(evt);
            }
        });
        jPanel5.add(ramp2TimeTextField);

        jPanel4.add(jPanel5, BorderLayout.CENTER);

        mainTabbedPane.addTab("SETUP", jPanel4);

        add(mainTabbedPane, BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Method to create simple buttons that look OK on the Raspberry Pi
     *
     * @return
     */
    private JButton createSimpleButton() {
        JButton button = new JButton();
        simplifyComponent(button);
        return button;
    }

    /* Method to create simple toogle that look OK on the Raspberry Pi
     *
     * @return
     */
    private JToggleButton createSimpleToggleButton() {
        JToggleButton button = new JToggleButton();
        simplifyComponent(button);
        return button;
    }

    /**
     * Simply the buttons to display OK on a raspberry pi TFT
     *
     * @return
     */
    private JComboBox createSimpleCombobox() {
        JComboBox comboBox = new JComboBox();
        comboBox.setForeground(Color.BLACK);
        comboBox.setBackground(Color.WHITE);
        return comboBox;
    }

    private void simplifyComponent(JComponent button) {
        button.setForeground(Color.BLACK);
        button.setBackground(Color.WHITE);
        Border line = new LineBorder(Color.BLACK);
        Border margin = new EmptyBorder(5, 15, 5, 15);
        Border compound = new CompoundBorder(line, margin);
        button.setBorder(compound);
    }

    /**
     * Method to stop the stepper motor from spinning
     *
     * @param evt
     */
    private void stopButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_stopButtonActionPerformed
        stopMotor = true;

        try {
            if (stepperPhidget != null) {
                stepperPhidget.setEngaged(0, false);
            }

            spinTimer.stop();
            spinTime = 0;
            setSpeed = 0;

            spinSpeedLabel.setText("stopped");
            spinTimeLabel.setText("0");
            startButton.setEnabled(true);
            
            if(mainTabbedPane.getSelectedIndex() == 1) {
                stepperPhidget.close();
                System.exit(0);
            }
        } catch (PhidgetException ex) {
            Logger.getLogger(SCKTalkPhiDesktop.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_stopButtonActionPerformed

    /**
     * Method to handle events from the up button
     *
     * @param evt
     */
    private void upButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_upButtonActionPerformed
        if (mainTabbedPane.getSelectedIndex() == 0) {
            increaseSpeedTextFieldValue();
        } else if (currentRampTextField != null) {
            increaseRampTextFieldValue();
        } else {
            increaseCurrentLimitTextFieldValue();
        }
    }//GEN-LAST:event_upButtonActionPerformed

    /**
     * Used for setting the current ram text field
     *
     * @param evt
     */
    private void rampTextFieldFocusGained(FocusEvent evt) {//GEN-FIRST:event_rampTextFieldFocusGained
        currentRampTextField = (JTextField) evt.getComponent();
    }//GEN-LAST:event_rampTextFieldFocusGained

    /**
     * Method to handle events from the down button
     *
     * @param evt
     */
    private void downButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_downButtonActionPerformed
        if (mainTabbedPane.getSelectedIndex() == 0) {
            decreaseSpeedTextFieldValue();
        } else if (currentRampTextField != null) {
            decreaseRampTextFieldValue();
        } else {
            decreaseCurrentLimitTextFieldValue();
        }
    }//GEN-LAST:event_downButtonActionPerformed

    /**
     * Method to start the motor spinning
     *
     * @param evt
     */
    private void startButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        // if we are in the setup tab we need to just save the settings
        if(mainTabbedPane.getSelectedIndex() == 1) {
            System.out.println("Saving settings ...");
            return;
        }
        
        // check to see if the controller board is connected
        if (stepperPhidget == null) {
            JOptionPane.showMessageDialog(this,
                    "The Stepper Motor is not connected ...\n\n" + VERSION,
                    "Connection Error",
                    JOptionPane.ERROR_MESSAGE);

            return;
        }

        // The baord is connected so move motor
        try {
            startButton.setEnabled(false);

            //Set up some initial acceleration and velocity values
            stepperPhidget.setAcceleration(0, acceleration);

            // set the current limit
            currentLimit = Double.parseDouble(currentLimitTextField.getText());
            stepperPhidget.setCurrentLimit(0, currentLimit);

            // now set the current position
            stepperPhidget.setCurrentPosition(0, 0L);

            // set the target position to some really big number
            stepperPhidget.setTargetPosition(0, targetPosition);

            // power-up the motor now
            System.out.println("\nEngaging Stepper Motor\n");
            stepperPhidget.setEngaged(0, true);

            if (runRampCheckBox.isSelected()) {
                // start a step seqence
                startStepSequence();
            } else {
                // start the timer
                spinTimer.start();

                // set the speed now
                changeMotorSpeedSmoothly();
            }
        } catch (PhidgetException ex) {
            Logger.getLogger(SCKTalkPhiDesktop.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_startButtonActionPerformed

    /**
     * Just set the current ramp text field to null
     *
     * @param evt
     */
    private void currentLimitTextFieldFocusGained(FocusEvent evt) {//GEN-FIRST:event_currentLimitTextFieldFocusGained
        currentRampTextField = null;
    }//GEN-LAST:event_currentLimitTextFieldFocusGained

    /**
     * A toggle button for connecting or disconnecting from the controller
     *
     * @param evt
     */
    private void connectToggleButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_connectToggleButtonActionPerformed
        if (connectToggleButton.isSelected()) {
            if (stepperPhidget != null) {
                return;
            }

            try {
                stepperPhidget = new StepperPhidget();
                stepperPhidget.openAny();

                System.out.println("Waiting for the Phidget Stepper to be attached...\n");

                stepperPhidget.waitForAttachment();

                System.out.println("Phidget Information");
                System.out.println("================================================");
                System.out.println("Version: " + stepperPhidget.getDeviceVersion());
                System.out.println("Name: " + stepperPhidget.getDeviceName());
                System.out.println("Serial #: " + stepperPhidget.getSerialNumber());
                System.out.println("# Steppers: " + stepperPhidget.getMotorCount());

                // update the UI label to indicate successful connection
                connectLabel.setForeground(new Color(0x00, 0xC0, 0x00));
                connectLabel.setText("Connected ...");

                // add some listeners now
                stepperPhidget.addErrorListener(new ErrorListener() {
                    public void error(ErrorEvent ex) {
                        connectLabel.setText("Connection Failed ...");
                        System.out.println("\n--->Error: " + ex.getException());
                    }
                });

                // listener which displays the current rpm
                stepperPhidget.addStepperVelocityChangeListener(new StepperVelocityChangeListener() {
                    @Override
                    public void stepperVelocityChanged(StepperVelocityChangeEvent svce) {
                        int speed = (int) (((svce.getValue() / 16.0) / 96.0) * 60.0);
                        spinSpeedLabel.setText(speed + " rpms");
                    }
                });
            } catch (PhidgetException ex) {
                Logger.getLogger(SCKTalkPhiDesktop.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            connectLabel.setForeground(Color.RED);
            connectLabel.setText("Dis-Connected ...");
            
            if (stepperPhidget != null) {
                try {
                    stepperPhidget.setEngaged(0, false);
                    stepperPhidget.close();
                    stepperPhidget = null;
                } catch (PhidgetException ex) {
                    Logger.getLogger(SCKTalkPhiDesktop.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_connectToggleButtonActionPerformed
    
    /**
     * Method to change the direction of rotation
     * 
     * @param evt 
     */
    private void directionComboBoxActionPerformed(ActionEvent evt) {//GEN-FIRST:event_directionComboBoxActionPerformed
        if(directionComboBox.getSelectedIndex() == 0) {
            targetPosition = Math.abs(targetPosition);
        } else {
            targetPosition = -targetPosition;
        }
    }//GEN-LAST:event_directionComboBoxActionPerformed
    
    /**
     * Method to change the labels of the START and STOP buttons
     * depending on selected tab
     * 
     * @param evt 
     */
    private void mainTabbedPaneStateChanged(ChangeEvent evt) {//GEN-FIRST:event_mainTabbedPaneStateChanged
        if(mainTabbedPane.getSelectedIndex() == 0) {
            startButton.setText("START");
            stopButton.setText("STOP");
        } else {
            startButton.setText("SAVE");
            stopButton.setText("EXIT");
        }
    }//GEN-LAST:event_mainTabbedPaneStateChanged
    
    /**
     * Need to disable the UP/DOWN buttons
     * 
     * @param evt 
     */
    private void runRampCheckBoxActionPerformed(ActionEvent evt) {//GEN-FIRST:event_runRampCheckBoxActionPerformed
        System.out.println("Disable start and stop button");
    }//GEN-LAST:event_runRampCheckBoxActionPerformed

    /**
     * Method to run the step sequence the step sequence
     */
    private void startStepSequence() {
        SwingWorker worker = new SwingWorker<Boolean, Void>() {
            @Override
            public Boolean doInBackground() {
                stopMotor = false;

                // get the step sequences
                String[] stepSeqences = getRampProgramSteps();

                // interate over the lines containing the seqences
                for (int i = 0; i < stepSeqences.length; i++) {
                    String[] stepInfo = stepSeqences[i].split("\\s*,\\s*");
                    String setSpeedString = stepInfo[1];
                    targetSpinTime = Integer.parseInt(stepInfo[2]);

                    rampStepTextField.setText(stepInfo[0] + ", " + setSpeedString + " rpms, " + targetSpinTime + " sec");

                    // start the motor spinning
                    spinSpeedTextField.setText(setSpeedString);
                    changeMotorSpeedSmoothly();

                    // use a loop to keep track of time this step is running
                    int count = 0;
                    while (count < targetSpinTime) {
                        // check to if the motor was stop
                        if (stopMotor) {
                            rampStepTextField.setText("Sequenced Stoped ...");
                            return false;
                        }

                        // update the count timer
                        spinTimeLabel.setText((targetSpinTime - count) + " sec");

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(SCKTalkPhiDesktop.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        count++;
                    }
                }

                // stop the motor now
                stopButtonActionPerformed(null);

                // seqeunce complete so return true
                return true;
            }
        };

        worker.execute();
    }

    /**
     * Method to get the ramp program
     *
     * @return
     */
    private String[] getRampProgramSteps() {
        String[] rampProgramSteps = new String[2];
        rampProgramSteps[0] = "1," + ramp1SpeedTextField.getText() + "," + ramp1TimeTextField.getText();
        rampProgramSteps[1] = "2," + ramp2SpeedTextField.getText() + "," + ramp2TimeTextField.getText();
        return rampProgramSteps;
    }

    /**
     * Increase the value for particular ramp text field
     */
    private void increaseSpeedTextFieldValue() {
        int increment = Integer.parseInt(incrementComboBox.getSelectedItem().toString());
        int value = Integer.parseInt(spinSpeedTextField.getText());
        value += increment;

        int maxSpeed = getMaxSpeed();
        if (value > maxSpeed) {
            value = maxSpeed;
        }

        // update the GUI now
        spinSpeedTextField.setText("" + value);

        changeMotorSpeedSmoothly();
    }

    /**
     * Increase the value for particular ramp text field
     */
    private void increaseRampTextFieldValue() {
        int increment = Integer.parseInt(incrementComboBox.getSelectedItem().toString());
        int value = Integer.parseInt(currentRampTextField.getText());
        value += increment;
        currentRampTextField.setText("" + value);
    }

    /**
     * Increase the value for particular ramp text field
     */
    private void increaseCurrentLimitTextFieldValue() {
        double increment = 0.1;
        double value = Double.parseDouble(currentLimitTextField.getText());
        value += increment;
        currentLimitTextField.setText(df.format(value));
    }

    /**
     * decrease the value for particular ramp text field
     */
    private void decreaseSpeedTextFieldValue() {
        int increment = Integer.parseInt(incrementComboBox.getSelectedItem().toString());
        int value = Integer.parseInt(spinSpeedTextField.getText());
        value -= increment;

        if (value < 0) {
            value = 0;
        }

        spinSpeedTextField.setText("" + value);

        changeMotorSpeedSmoothly();
    }

    /**
     * decrease the value for particular ramp text field
     */
    private void decreaseRampTextFieldValue() {
        int increment = Integer.parseInt(incrementComboBox.getSelectedItem().toString());
        int value = Integer.parseInt(currentRampTextField.getText());
        value -= increment;
        currentRampTextField.setText("" + value);
    }

    /**
     * decrease the current value for the current limit text field
     */
    private void decreaseCurrentLimitTextFieldValue() {
        double increment = 0.1;
        double value = Double.parseDouble(currentLimitTextField.getText());
        value -= increment;
        currentLimitTextField.setText(df.format(value));
    }

    /**
     * change the motor speed smoothly by doing it in small increments
     *
     * @param evt
     */
    private void changeMotorSpeedSmoothly() {
        try {
            int changeBy = 50; // how much to change the speed by

            int newSpeed = Integer.parseInt(spinSpeedTextField.getText());

            if (newSpeed >= setSpeed) {
                // we need to increate is steps of 100 rpms
                int diff = newSpeed - setSpeed;
                while (setSpeed < newSpeed && setSpeed != 0 && diff > changeBy * 10) {
                    setSpeed += changeBy * 2;
                    setMotorSpeed();

                    // pause a bit before increasing speed again
                    Thread.sleep(50);
                }

                if (setSpeed != newSpeed) {
                    setSpeed = newSpeed;
                    setMotorSpeed();
                }
            } else {
                // we need to decrese in steps of 100 rpms
                int diff = setSpeed - newSpeed;
                while (setSpeed > newSpeed && diff > changeBy) {
                    setSpeed -= changeBy;
                    setMotorSpeed();

                    // pause a bit before increasing speed again
                    Thread.sleep(50);
                }

                setSpeed = newSpeed;
                setMotorSpeed();
            }
        } catch (NumberFormatException nfe) {
        } catch (InterruptedException ex) {
            Logger.getLogger(SCKTalkPhiDesktop.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Given an RPM reading set the speed by converting to micro-steps per
     * second
     */
    public void setMotorSpeed() {
        double rps = setSpeed / 60.0; // rounds per second 
        double sps = rps * 96.0;      // steps per second 
        long msps = (long) sps * 16;   // get the microsteps per second needed

        System.out.println("RPM: " + setSpeed + ", Microsteps/sec: " + msps);

        if (stepperPhidget != null) {
            try {
                stepperPhidget.setVelocityLimit(0, msps);
            } catch (PhidgetException ex) {
                Logger.getLogger(SCKTalkPhiDesktop.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Method to return the maximum speed
     *
     * @return
     */
    private int getMaxSpeed() {
        return Integer.parseInt(maxSpeedTextField.getText());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JLabel connectLabel;
    private JToggleButton connectToggleButton;
    private JTextField currentLimitTextField;
    private JComboBox directionComboBox;
    private JButton downButton;
    private JComboBox incrementComboBox;
    private JLabel jLabel1;
    private JLabel jLabel10;
    private JLabel jLabel11;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JPanel jPanel4;
    private JPanel jPanel5;
    private JTabbedPane mainTabbedPane;
    private JTextField maxSpeedTextField;
    private JTextField ramp1SpeedTextField;
    private JTextField ramp1TimeTextField;
    private JTextField ramp2SpeedTextField;
    private JTextField ramp2TimeTextField;
    private JTextField rampStepTextField;
    private JCheckBox runRampCheckBox;
    private JLabel spinSpeedLabel;
    private JTextField spinSpeedTextField;
    private JLabel spinTimeLabel;
    private JButton startButton;
    private JButton stopButton;
    private JButton upButton;
    // End of variables declaration//GEN-END:variables

    /**
     * Main method for testing the UI on the desktop
     *
     * @param args
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                SCKTalkPhiRaspberryPi sckTalkPhiRaspberryPi = new SCKTalkPhiRaspberryPi();

                JFrame frame = new JFrame();
                frame.setSize(320, 240);
                frame.setResizable(false);
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

                // add the GUI panel now
                frame.add(sckTalkPhiRaspberryPi);

                frame.validate();
                frame.setVisible(true);
            }
        });
    }
}
