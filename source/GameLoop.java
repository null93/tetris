class GameLoop implements Runnable {
    
    protected GUI gui;
    private Thread thread;
    public GameLoop ( ) {
        gui = new GUI();
        thread = new Thread();
    }

    public void run () {

        gui.renderNext(gui.board.next.pieces.get(1).type);
        gui.render();
        gui.renderBoard();
        while ( !gui.board.isGameOver) {
            gui.board.printBoard();
            gui.updateScore(Board.score);
            gui.updateLines(Board.lines);
            gui.updateLevel(Board.level);
            //gui.renderNext(gui.board.next.pieces.get(1).type);
            //gui.renderNext(board.setCurrent());
            if ( !gui.board.skipLoop ) {
                if(!gui.board.moveDown())
                {
                    gui.board.update();
                    //gui.board.setCurrent();
                }
                gui.render();
                gui.renderBoard();
            }
            gui.board.skipLoop = false;
            
            try
            {
                thread.sleep((long)gui.board.delay);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
    
            //while ( board.pause ) {}
        }
        // gui.board = new Board(20, 10);
        // gui.render();
        // try
        // {
        //     thread.sleep(3000);
        // }
        // catch(Exception e)
        // {
        //     e.printStackTrace();
        // }

        gui.restart();
        System.out.println("Game Over!");
        run();
       // board.finshedGame ();
    }

}