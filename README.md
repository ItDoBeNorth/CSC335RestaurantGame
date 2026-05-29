For CSC 335 Team 3 Project



---
To Run: 

Install JDK 24
Install JavaFX

Create a new User Library under Eclipse -> Window -> Preferences -> Java -
> Build Path -> User Libraries -> New.
Name it JavaFX and include the jars under the lib folder from JavaFX.

click on Run -> Run Configurations... -> Java Application,
create a new launch configuration for your project named `hellofx` and add these VM
arguments (replace “/path/to/” with the JavaFX install path from before: 
Windows:
--module-path "\path\to\javafx-sdk-24.0.2\lib" --add-modules
javafx.controls,javafx.fxml --enable-native-access=javafx.graphics
Uncheck “Use the -XstartOnFirstThread argument when launching with SWT”

You will also need to add the JavaFX library to the classpath. In the package explorer,
right click on the project (csc335-lab1) and go to Build Path -> Add Libraries
Choose User Library and click Next
Check JavaFX and click Finish
