SCKTalkPhi
==========

This is the control software the Stepper Motor based Spin Coater making use of the excellent Phidgets library (http://www.phidgets.com/).

It's a Netbeans based project with two main classes containing the motor control code for either running on a Desktop computer (SCKTalkPhiDesktop.java), or running on a Raspbery Pi (SCKTalkPhiRaspberryPi.java) with an Small Touch Screen.  This approach is taken to make it easier for programmers of all skill levels to modify the code, without having to work through numerous source code files. As a matter of fact both files share almost identical code, except the Raspberry Pi version has a streamlined GUI to work on the tiny LCD touch screen without a keyboard.

### Installation
1. Install the JRE for your particular operating system
2. Install the Phigets drivers for your particular operating system (http://www.phidgets.com/docs/Operating_System_Support) 
3. Download the zip archieve to a convient location and unpack

### Running On Desktop
1. From the cmd line change into the SCKTalkPhi directory
2. Run the following command to launch the application: java -jar SCKTalkPhi.jar

### Running On RaspberryPi with PiTFT LCD Touch Screen
Running the program in this setup is similar to running on the desktop, except that all commands need to executed using sudo.

1. From the cmd line change into the SCKTalkPhi directory
2. Start X in supper user mode: sudo FRAMEBUFFER=/dev/fb1 startx &
3. Run the startup script to launch the program: sudo ./run.sh

