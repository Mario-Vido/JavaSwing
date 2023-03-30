package sk.stuba.fei.uim.oop;

public class CreateBoard {

    public BoardCell[][] CreateBoard(int sizeOfTheBoard){
        BoardCell[][] boardOfCells = new BoardCell[sizeOfTheBoard][sizeOfTheBoard];
        for (int i=0;i<sizeOfTheBoard;i++){
            for (int j=0;j<sizeOfTheBoard;j++){
                boardOfCells[i][j] = new BoardCell(true);
            }
        }

        return boardOfCells;}
}
