import java.util.ArrayList;

public class Shadow
{

    

    public static void shadow(ArrayList<Cell> shadowing, ArrayList<Cell> board, boolean[][] isActive)
    {
        ArrayList<Cell> pieces;
        pieces = new ArrayList<Cell>();

        for(Cell piece : shadowing)
        {
                if (piece.type == Shape.I)
                    pieces.add(new Cell(Shape.ISHADOW, piece.row, piece.column));
                
                else if (piece.type == Shape.T)
                    pieces.add(new Cell(Shape.TSHADOW, piece.row, piece.column));
                
                else if (piece.type == Shape.O)
                    pieces.add(new Cell(Shape.OSHADOW, piece.row, piece.column));
                
                else if (piece.type == Shape.L)
                    pieces.add(new Cell(Shape.LSHADOW, piece.row, piece.column));
                
                else if (piece.type == Shape.J)
                    pieces.add(new Cell(Shape.JSHADOW, piece.row, piece.column));
                
                else if (piece.type == Shape.S)
                    pieces.add(new Cell(Shape.SSHADOW, piece.row, piece.column));
                
                else if (piece.type == Shape.Z)
                    pieces.add(new Cell(Shape.ZSHADOW, piece.row, piece.column));
                
                else
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