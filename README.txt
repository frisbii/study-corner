Study Corner; A cozy companion for your studies.

To compile, use the following command:
    javac src/*.java src/panels/*.java -d ./classes

To run, use the following command:
    java -cp "./classes" Main

Using the program:
    In its current state, Study Corner has a central panel with four subpanels arranged in a grid.
        Top left - a clock showing the current time
        Top right - an editable to-do list
        Bottom left - a timer (counting down from 80s when the program starts)
        Bottom right - a tic-tac-toe game
    
    At the bottom, there is a panel where a creature will soon roam and respond to events. Currently, 
    the creature is a red box, but this will be updated. 
