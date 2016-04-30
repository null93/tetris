import java.util.ArrayList;

public class Shadow
{

    

    public static void shadow(ArrayList<Cell> shadowing, ArrayList<Cell> board, boolean[][] isActive)
    {
        ArrayList<Cell> pieces;
        pieces = new ArrayList<Cell>();

        for(Cell piece : shadowing)
        {
            pieces.add(new Cell(Shape.Default, piece.row, piece.column));
        }


        boolean flag = false;
        while(!flag)
        {
            for (Cell piece : pieces)
            {
                    if ((piece.row - 1) < 0)
                    {
                        flag = true;
                        break;
                    }

                    double r = piece.row - 1;
                    double c = piece.column;
                    if (isActive[(int)r][(int)c])
                    {
                        flag = true;
                        break;
                    }
            }

            if(!flag)
                for(Cell piece : pieces)
                {
                    piece.row--;
                }
        }


        for(Cell piece : pieces)
        {
            board.add(piece);
        }
    }

}