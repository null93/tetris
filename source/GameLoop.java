import javax.swing.JOptionPane;
class GameLoop implements Runnable {
    
    protected GUI gui;
    private Thread thread;
    private Thread timerThread;
    private GameTimer timer;
    public GameLoop ( ) {
        gui = new GUI();
        thread = new Thread();
        timer = new GameTimer(gui);
        timerThread = new Thread(timer);
        timerThread.start();
        // Render the gui
        gui.render();
        gui.renderNext(gui.board.next.pieces.get(1).type);
    }

    public synchronized void run () 
    {
    //     gui.renderNext(gui.board.next.pieces.get(1).type);
    //     gui.render();
    //     gui.renderBoard();
    //     while ( !gui.board.isGameOver) {
    //         gui.board.printBoard();
    //         gui.updateScore(Board.score);
    //         gui.updateLines(Board.lines);
    //         gui.updateLevel(Board.level);
    //         //gui.renderNext(gui.board.next.pieces.get(1).type);
    //         //gui.renderNext(board.setCurrent());
    //         if ( !gui.board.skipLoop ) {
    //             if(!gui.board.moveDown())
    //             {
    //                 gui.board.update();
    //                 //gui.board.setCurrent();
    //                 gui.renderNext(gui.board.next.pieces.get(1).type);
    //             }
    //             gui.render();
    //             gui.renderBoard();
    //         }
    //         gui.board.skipLoop = false;
            
    //         try
    //         {
    //             thread.sleep((long)gui.board.delay);
    //             //thread.sleep(3000);
    //         }
    //         catch(Exception e)
    //         {
    //             e.printStackTrace();
    //         }
    
    //         //while ( board.pause ) {}
    //     }
    //     // gui.board = new Board(20, 10);
    //     // gui.render();
    //     // try
    //     // {
    //     //     thread.sleep(3000);
    //     // }
    //     // catch(Exception e)
    //     // {
    //     //     e.printStackTrace();
    //     // }
    //     timer.seconds = 0;
    //     gui.board.isGameOver = false;
    //     gui.restart();
    //     try
    //     {
    //         thread.sleep(3000);
    //     }
    //     catch(Exception e)
    //     {
    //         e.printStackTrace();
    //     }
    //     System.out.println("Game Over!");
    //     run();
    //    // board.finshedGame ();
    // }

        while(true)
        {
            while(!gui.board.isGameOver)
            {
                // DEBUG - printing isActive
               // gui.board.printBoard();

                // Render the board
                gui.renderBoard();

               

                // Update Display Panel
                gui.updateScore(gui.board.score);
                gui.updateLines(gui.board.lines);
                gui.updateLevel(gui.board.level);
                if(!gui.board.moveDown())
                {
                    gui.board.update();
                    // Render the next piece
                    //if(gui.board.next.pieces.size() != 0)
                        gui.renderNext(gui.board.next.pieces.get(0).type);
                }

                while ( gui.pause ) { System.out.println(gui.pause);}

                // Gravity - delay
                try
                {
                    thread.sleep((long)gui.board.delay);
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }

            
            System.out.println("Game Over!");
            String name = JOptionPane.showInputDialog(null, "Enter your name: ");

            gui.highscoresManager.addPlayer(name, gui.board.score);

            //JOptionPane.showMessageDialog(null, )
            gui.restart();
            try
            {
                thread.sleep(3000);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
            Board.isGameOver = false;


            timerThread = new Thread(timer);
            timerThread.start();
        }
    }

}