class TimerThread implements Runnable {
    


    static int seconds;
    private Thread thread;
    public TimerThread ( ) {

        seconds = 0;
        thread = new Thread();
    }

    public void run () {

        
        try
        {
            thread.sleep(1000);
            seconds++;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        }
    }

