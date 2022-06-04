# Generating-SVG-Image-with-Java-Canvas
Java project made for my programming subject which consists of developing an SVG file with content created in a java canvas through a database.

## Java classes Explained

The primary objective of this project is to master the process of generating data in a java project and take that data and sending it in a database to take it again to the java project in order to create the exact same draw in a svg file.

As for the model class, there are methods of insert,select,delete, and generating the svg file, so is the same thing that there is in the alphabet soup or the MVC example.

As for the Drawing class there is not much to say except is the creation itself of the canvas with its size.

In the window class there is all of the instances and creations of the combobox, textfield and buttons of the java window. The combobox is where there is all of the hard part.
If you select "point" it gets the coordinates of where you have clicked in the canvas. The same goes for the "line" except there is something that works as first click and second click and it gets the coordinates of the first click and the second click respectively.
The hard part goes for the polygon part because you need to check where cuadrant you are pointing to and taking that in count the polygon will rotate in a direction or not. To select which polygon you want to create you need first to write it in the textfield.
NOTE: If you put "1" or "2" in the polygon textfield it won't work or do strange things so please don't select it.
The circle part is very easy to implement, the only strange thing is the calculation of the radius but is was not so hard at all.

As for the Java buttons, there is one that deletes everything in the database to clean the data for the SVG file, and the another one creates the SVG File itself.

## Some examples of the execution

![Image text](https://github.com/JohanSantanaGalvanJob/Generating-SVG-Image-with-Java-Canvas/blob/master/canvas.PNG)

![Image text](https://github.com/JohanSantanaGalvanJob/Generating-SVG-Image-with-Java-Canvas/blob/master/svgfile.PNG)
