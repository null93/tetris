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
        while ( !gui.board.gameOver () ) {
            
            //gui.renderNext(gui.board.next.pieces.get(1).type);
            //gui.renderNext(board.setCurrent());
            if ( !gui.board.skipLoop ) {
                //if(!gui.board.moveDown())
                //  gui.board.setCurrent();
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
       // board.finshedGame ();
    }

}