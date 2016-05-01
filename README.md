FOR THE GRADERS:
    The two design patterns used in our project are Factory and Singleton design patterns.
        Factory Pattern:
            Factory pattern occurs in Tetromino (Tetromino.java) and all the Sub TetrominoX classes.
            We implemented our tetrominoes such that the parent Tetromino class deals with 
            all the movements and the modifications we make to the tetrominoes and the subclasses
            just deal with creating a tetromino with a specific spawning coordinates. 
        Singleton Pattern:
            Singleton Pattern occurs in Shadow (Shadow.java) class. This class, whose job is to
            spawn a shadow of the current piece, is implemented in such a way that an instance of
            shadow can not be made and has static methods.
            
To compile:
    1. Go to the source folder
    2. Type "make" and enter to compile
    3. Type "make run" and enter to run

To show instructions on how to play the game:
    1. Press P to pause 
    2. Press H to see the Help Window, which shows all the key
       mappings and instructions
       