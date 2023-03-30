package sk.stuba.fei.uim.oop;

public class PlayBoard  {
    private final int[][] PlayBoard;

    public PlayBoard(int sizeOfBoard) {
        PlayBoard = new int[15][15];
        CreateBoard createBoard = new CreateBoard();
        BoardCell[][] pole = createBoard.CreateBoard(sizeOfBoard);
        for (int i = 0; i < sizeOfBoard; i++) {
            for (int j = 0; j < sizeOfBoard; j++) {
                if (pole[i][j].isUsable()) {
                    PlayBoard[i][j] = 0;
                    PlayBoard[0][j] = 5;
                    PlayBoard[i][0] = 4;
                    PlayBoard[sizeOfBoard - 1][j] = 7;
                    PlayBoard[i][sizeOfBoard - 1] = 8;
                } else {
                    PlayBoard[i][j] = 1;
                }
            }
        }
        basicLayoutOfField(sizeOfBoard);
        unUsedFields(sizeOfBoard);
    }

    public int getPlayBoard(int xInArray,int yInArray) {
        return PlayBoard[xInArray][yInArray];
    }

    public void setPlayBoard(int xInArray, int yInArray, int usableSpace){
        PlayBoard[xInArray][yInArray]=usableSpace;
    }

    public void unUsedFields(int sizeOfBoard){
        PlayBoard[0][0]=6;
        PlayBoard[0][sizeOfBoard-1]=6;
        PlayBoard[0][sizeOfBoard-1]=6;
        PlayBoard[sizeOfBoard-1][0]=6;
        PlayBoard[sizeOfBoard-1][sizeOfBoard-1]=6;

    }

    public void basicLayoutOfField(int sizeOfBoard){
        PlayBoard[sizeOfBoard / 2 - 1][sizeOfBoard / 2 - 1] = 1; //1 is black
        PlayBoard[sizeOfBoard / 2 - 1][sizeOfBoard / 2] = 2; // 2 is white
        PlayBoard[sizeOfBoard / 2][sizeOfBoard / 2 - 1] = 2;
        PlayBoard[sizeOfBoard / 2][sizeOfBoard / 2] = 1;
    }
}
