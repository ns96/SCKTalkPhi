/*
 * Copyright (C) 2015 Nathan Stevens
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

import com.phidget22.*;
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
import java.util.prefs.Preferences;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
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
 * @author Nathan Stevens 
 */
public class SCKTalkPhiRaspberryPi extends javax.swing.JPanel {        
    // need to make this global since on the rPi touch screen 
    // we don't have a keyboard to enter values
    private JTextField currentRampTextField;

    // this object handle talking to the motor control board
    private Stepper stepperPhidget;

    // this specifies how fast the motor should accelarate
    private double acceleration = 500; // rpms per sec

    // the current limit of 1.0
    private double currentLimit = 1.0;

    // this sets how many seconds the motor should spin by default ( 5 min)
    private int targetSpinTime = 300;

    // The spin speed to set
    private int setSpeed = 0;
    
    // used to move the motor in correct direction 1 = CW, -1 = CCW
    private int direction = 1;
    
    // the time object for count seconds
    private Timer spinTimer = null;

    // keep track of the spin time
    private int spinTime = 0;

    // used to exit the ramp sequence thread if the stop button was pressed
    private boolean stopMotor;

    // formatter used to only display one decimal place
    private final DecimalFormat df = new DecimalFormat("0.0");
    
    // used for storing program data
    private final Preferences prefs;
    
    // the current text field
    private JTextField currentTextField;

    /**
     * Creates new form SCKTalkPhiRaspberryPi
     */
    public SCKTalkPhiRaspberryPi() {
        initComponents();
        
        // add the vertical text to the tab panel
        // Create vertical labels to render tab titles
        JLabel labTab1 = new JLabel(" MAIN ");
        labTab1.setFont(new Font("Segoe UI Black", Font.BOLD, 36));
        labTab1.setUI(new VerticalLabelUI(false)); // true/false to make it upwards/downwards
        mainTabbedPane.setTabComponentAt(0, labTab1); // For component1

        JLabel labTab2 = new JLabel(" SETUP/EXIT ");
        labTab2.setFont(new Font("Segoe UI Black", Font.BOLD, 36));
        labTab2.setUI(new VerticalLabelUI(false));
        mainTabbedPane.setTabComponentAt(1, labTab2); // For component2
        
        // set the current textfield to the speed textfield
        currentTextField = spinSpeedTextField;
        
        // create a timer object for count seconds the motor is spinning
        spinTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                updateSpinTime();
            }
        });
        
        // initialize the preferences object
        prefs = Preferences.userRoot().node(this.getClass().getName());
        
        // now load the preferences
        loadPreferences();
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
        rampToggleButton = createSimpleToggleButton();
        rampStepTextField = new JTextField();
        jPanel4 = new JPanel();
        jPanel3 = new JPanel();
        jLabel3 = new JLabel();
        directionComboBox = createSimpleCombobox();
        jLabel10 = new JLabel();
        maxSpeedTextField = new JTextField();
        jLabel11 = new JLabel();
        currentLimitTextField = new JTextField();
        jLabel9 = new JLabel();
        accTextField = new JTextField();
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
        keyPadPanel = new JPanel();
        keyPadButton1 = createSimpleButton();
        keyPadButton2 = createSimpleButton();
        keyPadButton3 = createSimpleButton();
        keyPadButton4 = createSimpleButton();
        keyPadButton5 = createSimpleButton();
        keyPadButton6 = createSimpleButton();
        keyPadButton7 = createSimpleButton();
        keyPadButton8 = createSimpleButton();
        keyPadButton9 = createSimpleButton();
        keyPadButton0 = createSimpleButton();
        keyPadBackButton = createSimpleButton();
        keyPadClearButton = createSimpleButton();

        setLayout(new BorderLayout());

        startButton.setFont(new Font("Segoe UI Black", 1, 48)); // NOI18N
        startButton.setText("START");
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });
        jPanel1.add(startButton);

        upButton.setFont(new Font("Segoe UI Black", 1, 48)); // NOI18N
        upButton.setText("   UP   ");
        upButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                upButtonActionPerformed(evt);
            }
        });
        jPanel1.add(upButton);

        downButton.setFont(new Font("Segoe UI Black", 1, 48)); // NOI18N
        downButton.setText("DOWN");
        downButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                downButtonActionPerformed(evt);
            }
        });
        jPanel1.add(downButton);

        stopButton.setFont(new Font("Segoe UI Black", 1, 48)); // NOI18N
        stopButton.setText("STOP");
        stopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                stopButtonActionPerformed(evt);
            }
        });
        jPanel1.add(stopButton);

        add(jPanel1, BorderLayout.SOUTH);

        mainTabbedPane.setTabPlacement(JTabbedPane.LEFT);
        mainTabbedPane.setFont(new Font("Segoe UI Black", 1, 36)); // NOI18N
        mainTabbedPane.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent evt) {
                mainTabbedPaneStateChanged(evt);
            }
        });

        jPanel2.setLayout(new GridLayout(5, 2, 2, 2));

        connectToggleButton.setFont(new Font("Segoe UI Black", 1, 48)); // NOI18N
        connectToggleButton.setText("CONNECT");
        connectToggleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                connectToggleButtonActionPerformed(evt);
            }
        });
        jPanel2.add(connectToggleButton);

        connectLabel.setFont(new Font("Segoe UI Black", 1, 24)); // NOI18N
        connectLabel.setForeground(new Color(255, 0, 0));
        connectLabel.setText("Not Connected");
        connectLabel.setToolTipText("");
        jPanel2.add(connectLabel);

        spinSpeedLabel.setFont(new Font("Segoe UI Black", 1, 36)); // NOI18N
        spinSpeedLabel.setForeground(Color.blue);
        spinSpeedLabel.setText("0 rpms");
        jPanel2.add(spinSpeedLabel);

        spinTimeLabel.setFont(new Font("Segoe UI Black", 1, 36)); // NOI18N
        spinTimeLabel.setForeground(Color.blue);
        spinTimeLabel.setText("0 sec");
        jPanel2.add(spinTimeLabel);

        jLabel1.setFont(new Font("Segoe UI Black", 1, 36)); // NOI18N
        jLabel1.setText("INCREMENT");
        jPanel2.add(jLabel1);

        incrementComboBox.setFont(new Font("Segoe UI Black", 1, 36)); // NOI18N
        incrementComboBox.setModel(new DefaultComboBoxModel(new String[] { "10", "50", "100", "500" }));
        incrementComboBox.setSelectedItem(new String("100"));
        jPanel2.add(incrementComboBox);

        jLabel2.setFont(new Font("Segoe UI Black", 1, 36)); // NOI18N
        jLabel2.setText("SPEED");
        jPanel2.add(jLabel2);

        spinSpeedTextField.setFont(new Font("Segoe UI Black", 1, 36)); // NOI18N
        spinSpeedTextField.setText("3000");
        spinSpeedTextField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                spinSpeedTextFieldFocusGained(evt);
            }
        });
        jPanel2.add(spinSpeedTextField);

        rampToggleButton.setFont(new Font("Segoe UI Black", 1, 36)); // NOI18N
        rampToggleButton.setText("RUN RAMP");
        jPanel2.add(rampToggleButton);

        rampStepTextField.setEditable(false);
        rampStepTextField.setFont(new Font("Segoe UI", 1, 24)); // NOI18N
        rampStepTextField.setForeground(Color.blue);
        rampStepTextField.setText("Program Not Running ...");
        jPanel2.add(rampStepTextField);

        mainTabbedPane.addTab("", jPanel2);

        jPanel4.setLayout(new BoxLayout(jPanel4, BoxLayout.Y_AXIS));

        jPanel3.setLayout(new GridLayout(4, 2, 2, 6));

        jLabel3.setFont(new Font("Segoe UI Black", 1, 36)); // NOI18N
        jLabel3.setText("ROTATION");
        jLabel3.setToolTipText("");
        jPanel3.add(jLabel3);

        directionComboBox.setFont(new Font("Segoe UI Black", 1, 36)); // NOI18N
        directionComboBox.setModel(new DefaultComboBoxModel(new String[] { "Clockwise", "Counter Clockwise" }));
        directionComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                directionComboBoxActionPerformed(evt);
            }
        });
        jPanel3.add(directionComboBox);

        jLabel10.setFont(new Font("Segoe UI Black", 1, 36)); // NOI18N
        jLabel10.setText("MAX SPEED");
        jPanel3.add(jLabel10);

        maxSpeedTextField.setEditable(false);
        maxSpeedTextField.setFont(new Font("Segoe UI Black", 0, 36)); // NOI18N
        maxSpeedTextField.setText("8000");
        maxSpeedTextField.setToolTipText("");
        maxSpeedTextField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                rampTextFieldFocusGained(evt);
            }
        });
        jPanel3.add(maxSpeedTextField);

        jLabel11.setFont(new Font("Segoe UI Black", 1, 36)); // NOI18N
        jLabel11.setText("CURRENT LIMIT");
        jPanel3.add(jLabel11);

        currentLimitTextField.setEditable(false);
        currentLimitTextField.setFont(new Font("Segoe UI Black", 0, 36)); // NOI18N
        currentLimitTextField.setText("1.0");
        currentLimitTextField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                currentLimitTextFieldFocusGained(evt);
            }
        });
        jPanel3.add(currentLimitTextField);

        jLabel9.setFont(new Font("Segoe UI Black", 1, 36)); // NOI18N
        jLabel9.setText("ACC. (RPM/s)");
        jPanel3.add(jLabel9);

        accTextField.setFont(new Font("Segoe UI Black", 0, 36)); // NOI18N
        accTextField.setText("2000");
        accTextField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                accTextFieldFocusGained(evt);
            }
        });
        jPanel3.add(accTextField);

        jPanel4.add(jPanel3);

        jPanel5.setLayout(new GridLayout(3, 3, 2, 2));

        jLabel4.setFont(new Font("Segoe UI Black", 1, 36)); // NOI18N
        jLabel4.setText("STEP");
        jPanel5.add(jLabel4);

        jLabel5.setFont(new Font("Segoe UI Black", 1, 36)); // NOI18N
        jLabel5.setText("RPM");
        jPanel5.add(jLabel5);

        jLabel6.setFont(new Font("Segoe UI Black", 1, 36)); // NOI18N
        jLabel6.setText("DWELL");
        jPanel5.add(jLabel6);

        jLabel7.setFont(new Font("Segoe UI Black", 1, 36)); // NOI18N
        jLabel7.setText("# 1");
        jPanel5.add(jLabel7);

        ramp1SpeedTextField.setFont(new Font("Segoe UI Black", 0, 36)); // NOI18N
        ramp1SpeedTextField.setText("500");
        ramp1SpeedTextField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                rampTextFieldFocusGained(evt);
            }
        });
        jPanel5.add(ramp1SpeedTextField);

        ramp1TimeTextField.setFont(new Font("Segoe UI Black", 0, 36)); // NOI18N
        ramp1TimeTextField.setText("10");
        ramp1TimeTextField.setToolTipText("");
        ramp1TimeTextField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                rampTextFieldFocusGained(evt);
            }
        });
        jPanel5.add(ramp1TimeTextField);

        jLabel8.setFont(new Font("Segoe UI Black", 1, 36)); // NOI18N
        jLabel8.setText("# 2");
        jPanel5.add(jLabel8);

        ramp2SpeedTextField.setFont(new Font("Segoe UI Black", 0, 36)); // NOI18N
        ramp2SpeedTextField.setText("3000");
        ramp2SpeedTextField.setToolTipText("");
        ramp2SpeedTextField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                rampTextFieldFocusGained(evt);
            }
        });
        jPanel5.add(ramp2SpeedTextField);

        ramp2TimeTextField.setFont(new Font("Segoe UI Black", 0, 36)); // NOI18N
        ramp2TimeTextField.setText("30");
        ramp2TimeTextField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                rampTextFieldFocusGained(evt);
            }
        });
        jPanel5.add(ramp2TimeTextField);

        jPanel4.add(jPanel5);

        mainTabbedPane.addTab("", jPanel4);

        add(mainTabbedPane, BorderLayout.CENTER);

        keyPadPanel.setLayout(new GridLayout(4, 3));

        keyPadButton1.setFont(new Font("Segoe UI Black", 1, 36)); // NOI18N
        keyPadButton1.setText("   1   ");
        keyPadButton1.setActionCommand("1");
        keyPadButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                keyPadButton1ActionPerformed(evt);
            }
        });
        keyPadPanel.add(keyPadButton1);

        keyPadButton2.setFont(new Font("Segoe UI Black", 1, 36)); // NOI18N
        keyPadButton2.setText("2");
        keyPadButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                keyPadButton2ActionPerformed(evt);
            }
        });
        keyPadPanel.add(keyPadButton2);

        keyPadButton3.setFont(new Font("Segoe UI Black", 1, 36)); // NOI18N
        keyPadButton3.setText("3");
        keyPadButton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                keyPadButton3ActionPerformed(evt);
            }
        });
        keyPadPanel.add(keyPadButton3);

        keyPadButton4.setFont(new Font("Segoe UI Black", 1, 36)); // NOI18N
        keyPadButton4.setText("4");
        keyPadButton4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                keyPadButton4ActionPerformed(evt);
            }
        });
        keyPadPanel.add(keyPadButton4);

        keyPadButton5.setFont(new Font("Segoe UI Black", 1, 36)); // NOI18N
        keyPadButton5.setText("5");
        keyPadButton5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                keyPadButton5ActionPerformed(evt);
            }
        });
        keyPadPanel.add(keyPadButton5);

        keyPadButton6.setFont(new Font("Segoe UI Black", 1, 36)); // NOI18N
        keyPadButton6.setText("6");
        keyPadButton6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                keyPadButton6ActionPerformed(evt);
            }
        });
        keyPadPanel.add(keyPadButton6);

        keyPadButton7.setFont(new Font("Segoe UI Black", 1, 36)); // NOI18N
        keyPadButton7.setText("7");
        keyPadButton7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                keyPadButton7ActionPerformed(evt);
            }
        });
        keyPadPanel.add(keyPadButton7);

        keyPadButton8.setFont(new Font("Segoe UI Black", 1, 36)); // NOI18N
        keyPadButton8.setText("8");
        keyPadButton8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                keyPadButton8ActionPerformed(evt);
            }
        });
        keyPadPanel.add(keyPadButton8);

        keyPadButton9.setFont(new Font("Segoe UI Black", 1, 36)); // NOI18N
        keyPadButton9.setText("9");
        keyPadButton9.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                keyPadButton9ActionPerformed(evt);
            }
        });
        keyPadPanel.add(keyPadButton9);

        keyPadButton0.setFont(new Font("Segoe UI Black", 1, 36)); // NOI18N
        keyPadButton0.setText("0");
        keyPadButton0.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                keyPadButton0ActionPerformed(evt);
            }
        });
        keyPadPanel.add(keyPadButton0);

        keyPadBackButton.setFont(new Font("Segoe UI Black", 0, 36)); // NOI18N
        keyPadBackButton.setText("<<");
        keyPadBackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                keyPadBackButtonActionPerformed(evt);
            }
        });
        keyPadPanel.add(keyPadBackButton);

        keyPadClearButton.setFont(new Font("Segoe UI Black", 1, 36)); // NOI18N
        keyPadClearButton.setText("CLEAR");
        keyPadClearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                keyPadClearButtonActionPerformed(evt);
            }
        });
        keyPadPanel.add(keyPadClearButton);

        add(keyPadPanel, BorderLayout.EAST);
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
    
    /**
     * Method to simplify the components for the raspberry pi
     * 
     * @param button 
     */
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
            if(mainTabbedPane.getSelectedIndex() == 1) {
                if(stepperPhidget != null && stepperPhidget.getAttached()) {
                    stepperPhidget.close();
                }
                System.exit(0);
            }
            
            // de-energize the stepper motor
            if (stepperPhidget != null) {
                stepperPhidget.setEngaged(false);
            }

            spinTimer.stop();
            spinTime = 0;
            setSpeed = 0;

            spinSpeedLabel.setText("stopped");
            spinTimeLabel.setText("0");
            startButton.setEnabled(true);
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
        currentTextField = currentRampTextField;
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
        // if we are in the setup tab, we need to save the settings and return
        if(mainTabbedPane.getSelectedIndex() == 1) {
            System.out.println("Saving settings ...");
            setPreferences();
            return;
        }
        
        // check to see if the controller board is connected. Return if its not
        if (stepperPhidget == null) {
            JOptionPane.showMessageDialog(this,
                    "The Stepper Motor is not connected ...\n\n" + SCKTalkPhi.VERSION,
                    "Connection Error",
                    JOptionPane.ERROR_MESSAGE);

            return;
        }

        // The board is connected so move motor
        try {
            startButton.setEnabled(false);
            
            // check the speed and reset if needed
            checkSpeed();
           
            //Set up some initial acceleration and velocity values
            acceleration = Double.parseDouble(accTextField.getText());
            stepperPhidget.setAcceleration(acceleration);

            // set the current limit
            currentLimit = Double.parseDouble(currentLimitTextField.getText());
            stepperPhidget.setCurrentLimit(currentLimit);

            // power-up the motor now
            System.out.println("\nEngaging Stepper Motor\n");
            stepperPhidget.setEngaged(true);

            if (rampToggleButton.isSelected()) {
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
                stepperPhidget = new Stepper();
                System.out.println("Waiting for the Phidget Stepper to be attached...\n");

                stepperPhidget.open(Phidget.DEFAULT_TIMEOUT);
            
                // set the scaling factor and control mode to RUN 
                // so we are in continous rotation mode
                double rescaleFactor = SCKTalkPhi.SCALE_FACTOR;
                stepperPhidget.setRescaleFactor(rescaleFactor);
                stepperPhidget.setControlMode(StepperControlMode.RUN);

                System.out.println("Phidget Information");
                System.out.println("================================================");
                System.out.println("Version: " + stepperPhidget.getDeviceVersion());
                System.out.println("Name: " + stepperPhidget.getDeviceName());
                System.out.println("Serial #: " + stepperPhidget.getDeviceSerialNumber());

                // update the UI label to indicate successful connection
                connectLabel.setForeground(new Color(0x00, 0xC0, 0x00));
                connectLabel.setText("Connected ...");
                
                // update the UI label to indicate successful connection
                connectLabel.setForeground(new Color(0x00, 0xC0, 0x00));
                connectLabel.setText("Connected to SCK-300S+");
            
                // add some listeners now
                stepperPhidget.addErrorListener(new ErrorListener() {
                    public void onError(ErrorEvent ex) {
                        connectLabel.setText("Connection Failed ...");
                        System.out.println("\n--->Error: " + ex.getDescription());
                    }
                });
            
                // listener which displays the current rpm
                stepperPhidget.addVelocityChangeListener(new StepperVelocityChangeListener() {
                    @Override
                    public void onVelocityChange(StepperVelocityChangeEvent svce) {
                        long speed = Math.round(svce.getVelocity());
                        spinSpeedLabel.setText(speed + " rpms");
                    }
                });
                
                /**
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
                */
            } catch (PhidgetException ex) {
                connectLabel.setText("Connection Failed ...");
                Logger.getLogger(SCKTalkPhiDesktop.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            connectLabel.setForeground(Color.RED);
            connectLabel.setText("Dis-Connected ...");
            
            if (stepperPhidget != null) {
                try {
                    stepperPhidget.setEngaged(false);
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
            direction = 1;
        } else {
            direction = -1;
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
    
    private void keyPadButton2ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_keyPadButton2ActionPerformed
        updateCurrentTextField("2");
    }//GEN-LAST:event_keyPadButton2ActionPerformed

    private void spinSpeedTextFieldFocusGained(FocusEvent evt) {//GEN-FIRST:event_spinSpeedTextFieldFocusGained
        currentTextField = spinSpeedTextField;
    }//GEN-LAST:event_spinSpeedTextFieldFocusGained

    private void keyPadButton1ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_keyPadButton1ActionPerformed
        updateCurrentTextField("1");
    }//GEN-LAST:event_keyPadButton1ActionPerformed

    private void keyPadClearButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_keyPadClearButtonActionPerformed
        if(currentTextField != null) {
            currentTextField.setText("");
        }
    }//GEN-LAST:event_keyPadClearButtonActionPerformed

    private void keyPadButton3ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_keyPadButton3ActionPerformed
        updateCurrentTextField("3");
    }//GEN-LAST:event_keyPadButton3ActionPerformed

    private void keyPadButton4ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_keyPadButton4ActionPerformed
        updateCurrentTextField("4");
    }//GEN-LAST:event_keyPadButton4ActionPerformed

    private void keyPadButton5ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_keyPadButton5ActionPerformed
        updateCurrentTextField("5");
    }//GEN-LAST:event_keyPadButton5ActionPerformed

    private void keyPadButton6ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_keyPadButton6ActionPerformed
        updateCurrentTextField("6");
    }//GEN-LAST:event_keyPadButton6ActionPerformed

    private void keyPadButton7ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_keyPadButton7ActionPerformed
        updateCurrentTextField("7");
    }//GEN-LAST:event_keyPadButton7ActionPerformed

    private void keyPadButton8ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_keyPadButton8ActionPerformed
        updateCurrentTextField("8");
    }//GEN-LAST:event_keyPadButton8ActionPerformed

    private void keyPadButton9ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_keyPadButton9ActionPerformed
        updateCurrentTextField("9");
    }//GEN-LAST:event_keyPadButton9ActionPerformed

    private void keyPadButton0ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_keyPadButton0ActionPerformed
        updateCurrentTextField("0");
    }//GEN-LAST:event_keyPadButton0ActionPerformed

    private void keyPadBackButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_keyPadBackButtonActionPerformed
        if(currentTextField != null) {
            String currentText = currentTextField.getText();
            int length = currentText.length();
            if(length >= 1) {
                currentTextField.setText(currentText.substring(0, length - 1));
            }
        }
    }//GEN-LAST:event_keyPadBackButtonActionPerformed

    private void accTextFieldFocusGained(FocusEvent evt) {//GEN-FIRST:event_accTextFieldFocusGained
        currentTextField = accTextField;
    }//GEN-LAST:event_accTextFieldFocusGained
    
    // method to update the text in the selected text field
    private void updateCurrentTextField(String number) {
        if(currentTextField != null) {
            String currentText = currentTextField.getText();
            currentTextField.setText(currentText + number);
        }
    }
    
    
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
                outerLoop:
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
                            rampStepTextField.setText("Sequenced Stopped ...");
                            break outerLoop;
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

                // stop the motor now and set target spin time to zero
                stopButtonActionPerformed(null);
                targetSpinTime = 0;
                rampStepTextField.setText("Program Finished ...");
                
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
            int newSpeed = Integer.parseInt(spinSpeedTextField.getText());
            setSpeed = newSpeed;
            setMotorSpeed();
            
            /* 2/16/2023 Let the stepper motor driver handel the smooth acceleration?    
            int changeBy = 50; // how much to change the speed by
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
                // we need to decrease in steps of 100 rpms
                int diff = setSpeed - newSpeed;
                while (setSpeed > newSpeed && diff > changeBy) {
                    setSpeed -= changeBy;
                    setMotorSpeed();

                    // pause a bit before increasing speed again
                    Thread.sleep(50);
                }

                setSpeed = newSpeed;
                setMotorSpeed();
            }*/
        } catch (NumberFormatException nfe) { }
    }

    /**
     * Given an RPM reading set the speed by converting to micro-steps per
     * second
     */
    public void setMotorSpeed() {
        /**
        double rps = setSpeed / 60.0; // rounds per second 
        double sps = rps * 96.0;      // steps per second 
        long msps = (long) sps * 16;   // get the microsteps per second needed
        * */
        
        System.out.println("RPM: " + setSpeed);

        if (stepperPhidget != null) {
            try {
                double speed = direction*setSpeed;
                stepperPhidget.setVelocityLimit(speed);
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
    
    /**
     * Check the speed value to make sure we not going faster than we should
     */
    private void checkSpeed() {
        int maxSpeed =  getMaxSpeed();
        int currentSpeed = Integer.parseInt(spinSpeedTextField.getText());
        if(currentSpeed > maxSpeed) {
            spinSpeedTextField.setText("" + maxSpeed);
        }
    }
    
    /**
     * Method to store the program preferences
     */
    private void setPreferences() {
        prefs.putInt("direction", directionComboBox.getSelectedIndex());
        prefs.put("maxSpeed", maxSpeedTextField.getText());
        prefs.put("currentLimit", currentLimitTextField.getText());
        prefs.put("acceleration", accTextField.getText());
        prefs.put("ramp1Speed", ramp1SpeedTextField.getText());
        prefs.put("ramp1Time", ramp1TimeTextField.getText());
        prefs.put("ramp2Speed", ramp2SpeedTextField.getText());
        prefs.put("ramp2Time", ramp2TimeTextField.getText());
    }
    
    /**
     * Method to load the preferences and update the UI components
     */
    private void loadPreferences() {
        directionComboBox.setSelectedIndex(prefs.getInt("direction", 0));
        //maxSpeedTextField.setText(prefs.get("maxSpeed", "8000"));
        currentLimitTextField.setText(prefs.get("currentLimit", "1.0"));
        accTextField.setText(prefs.get("acceleration", "500"));
        ramp1SpeedTextField.setText(prefs.get("ramp1Speed", "500"));
        ramp1TimeTextField.setText(prefs.get("ramp1Time", "30"));
        ramp2SpeedTextField.setText(prefs.get("ramp2Speed", "3000"));
        ramp2TimeTextField.setText(prefs.get("ramp2Time", "60"));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JTextField accTextField;
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
    private JLabel jLabel9;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JPanel jPanel4;
    private JPanel jPanel5;
    private JButton keyPadBackButton;
    private JButton keyPadButton0;
    private JButton keyPadButton1;
    private JButton keyPadButton2;
    private JButton keyPadButton3;
    private JButton keyPadButton4;
    private JButton keyPadButton5;
    private JButton keyPadButton6;
    private JButton keyPadButton7;
    private JButton keyPadButton8;
    private JButton keyPadButton9;
    private JButton keyPadClearButton;
    private JPanel keyPadPanel;
    private JTabbedPane mainTabbedPane;
    private JTextField maxSpeedTextField;
    private JTextField ramp1SpeedTextField;
    private JTextField ramp1TimeTextField;
    private JTextField ramp2SpeedTextField;
    private JTextField ramp2TimeTextField;
    private JTextField rampStepTextField;
    private JToggleButton rampToggleButton;
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
                //frame.setSize(320, 240);
                //frame.setResizable(false);
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setTitle("Raspberry Pi GUI");
                
                // add the GUI panel now
                frame.add(sckTalkPhiRaspberryPi);

                frame.validate();
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
}
