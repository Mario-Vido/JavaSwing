package sk.stuba.fei.uim.oop;

public class Player {

    private int mouseXPosition;
    private int mouseYPosition;
    private int xForPlayerRock=1;
    private int yForPlayerRock=1;
    public Player() {

    }

    public int getMouseXPosition() {
        return mouseXPosition;
    }

    public void setMouseXPosition(int mouseXPosition) {
        this.mouseXPosition = mouseXPosition;
    }

    public int getMouseYPosition() {
        return mouseYPosition;
    }

    public void setMouseYPosition(int mouseYPosition) {
        this.mouseYPosition = mouseYPosition;
    }

    public void setxForPlayerRock(int xForPlayerRock) {
        this.xForPlayerRock = xForPlayerRock;
    }

    public void setyForPlayerRock(int yForPlayerRock) {
        this.yForPlayerRock = yForPlayerRock;
    }

    /*
        playersMove works by looking for white rocks and if it surrounded by a black rock and if the space
        opposite of the black rock has a white rock and free space we can place there the players rock
         */
    public void playersMove(int highAndWidthOfBoard, PlayBoard p) {
        for (int x = 1; x < highAndWidthOfBoard; x++) {
            for (int y = 1; y < highAndWidthOfBoard; y++) {
                //left
                for (int i = 1; i <= (highAndWidthOfBoard - x - 1); i++) {
                    if (p.getPlayBoard(x, y) == 2 && p.getPlayBoard(x - 1, y) == 0 && p.getPlayBoard(x + i, y) == 1) {
                        p.setPlayBoard(x - 1, y, 3);
                        break;
                    }
                }//up
                for (int i = 1; i <= highAndWidthOfBoard - y; i++) {
                    if (p.getPlayBoard(x, y) == 2 && p.getPlayBoard(x, y - 1) == 0 && p.getPlayBoard(x, y + i) == 1) {
                        p.setPlayBoard(x, y - 1, 3);

                    }
                }//down
                for (int i = 1; i <= y; i++) {
                    if (p.getPlayBoard(x, y) == 2 && p.getPlayBoard(x, y + 1) == 0 && p.getPlayBoard(x, y - i) == 1) {
                        p.setPlayBoard(x, y + 1, 3);
                        break;
                    }
                }//right
                for (int i = 1; i <= x; i++) {
                    if (p.getPlayBoard(x, y) == 2 && p.getPlayBoard(x + 1, y) == 0 && p.getPlayBoard(x - i, y) == 1) {
                        p.setPlayBoard(x + 1, y, 3);
                        break;
                    }
                }
                horizontalUpLeftPlayer(x, y,p);
                horizontalDownLeftPlayer(x, y,p);
                horizontalUpRightForPlayer(x, y,p);
                horizontalDownRightForPlayer(x, y,p);
            }
        }
    }
    private void horizontalUpLeftPlayer(int x, int y,PlayBoard p) {
        int b = 1;
        for (int i = 1; i <= b; i++) {
            if (p.getPlayBoard(x, y) == 2 && p.getPlayBoard(x - 1, y - 1) == 0 && p.getPlayBoard(x + i, y + i) == 2) {
                b++;
            }
            if (p.getPlayBoard(x, y) == 2 && p.getPlayBoard(x - 1, y - 1) == 0 && p.getPlayBoard(x + b, y + b) == 1) {
                p.setPlayBoard(x - 1, y - 1, 3);
            }
        }
    }

    private void horizontalDownLeftPlayer(int x, int y, PlayBoard p) {
        int b = 1;
        for (int i = 1; i <= b; i++) {
            if (p.getPlayBoard(x, y) == 2 && p.getPlayBoard(x - 1, y + 1) == 0 && p.getPlayBoard(x + i, y - i) == 2) {
                b++;
            }
            if (p.getPlayBoard(x, y) == 2 && p.getPlayBoard(x - 1, y + 1) == 0 && p.getPlayBoard(x + b, y - b) == 1) {
                p.setPlayBoard(x - 1, y + 1, 3);
            }
        }
    }

    private void horizontalDownRightForPlayer(int x, int y,PlayBoard p) {
        int b = 1;
        for (int i = 1; i <= b; i++) {
            if (p.getPlayBoard(x, y) == 2 && p.getPlayBoard(x + 1, y + 1) == 0 && p.getPlayBoard(x - i, y - i) == 2) {
                b++;
            }
            if (p.getPlayBoard(x, y) == 2 && p.getPlayBoard(x + 1, y + 1) == 0 && p.getPlayBoard(x - b, y - b) == 1) {
                p.setPlayBoard(x + 1, y + 1, 3);
            }
        }
    }

   private void horizontalUpRightForPlayer(int x, int y, PlayBoard p) {
        int b = 1;
        for (int i = 1; i <= b; i++) {
            if (p.getPlayBoard(x, y) == 2 && p.getPlayBoard(x + 1, y - 1) == 0 && p.getPlayBoard(x - i, y + i) == 2) {
                b++;
            }
            if (p.getPlayBoard(x, y) == 2 && p.getPlayBoard(x + 1, y - 1) == 0 && p.getPlayBoard(x - b, y + b) == 1) {
                p.setPlayBoard(x + 1, y - 1, 3);
            }
        }
    }
    /*
    flipWhiteStones works bz the principle that i am looking for white rocks that are surrounded by black rocks,
    the input argument is the place where the player put his rock by clicking on the board,
    the player is using this method
     */
    public void flipWhiteStones(int size,PlayBoard p) {
        int yForPlayerRockMinus = yForPlayerRock - 1;
        upToDown(size, yForPlayerRockMinus,p);
        int yForPlayerRockPlus = yForPlayerRock + 1;
        upToDown(size, yForPlayerRockPlus,p);
        int xForPlayerRockMinus = xForPlayerRock - 1;
        leftToRight(size, xForPlayerRockMinus,p);
        int xForPlayerRockPlus = xForPlayerRock + 1;
        leftToRight(size, xForPlayerRockPlus,p);
        if (!(yForPlayerRockMinus == 0)) {
            horizontalLeftPlayer(xForPlayerRockPlus, yForPlayerRockMinus, size,p);
            horizontalLeftPlayer(xForPlayerRockMinus, yForPlayerRockMinus, size,p);
        }
        horizontalLeftPlayer(xForPlayerRockMinus, yForPlayerRockPlus, size,p);
        horizontalLeftPlayer(xForPlayerRockPlus, yForPlayerRockPlus, size,p);
        if (!(xForPlayerRockMinus== 0)) {
            horizontalLeftPlayer(xForPlayerRockMinus, yForPlayerRockPlus, size,p);
            horizontalLeftPlayer(xForPlayerRockMinus, yForPlayerRockMinus, size,p);
        }
        horizontalLeftPlayer(xForPlayerRockPlus, yForPlayerRockMinus, size,p);
        horizontalLeftPlayer(xForPlayerRockPlus, yForPlayerRockPlus, size,p);
        xForPlayerRock=1;
        yForPlayerRock=1;
    }
    private void horizontalLeftPlayer(int xSide, int ySide, int size,PlayBoard p) {
        if (xSide == 0) {
            xSide = xSide + 2;
        } else if (ySide == 0) {
            ySide = ySide + 2;
        }
        for (int i = 0; i < size; i++) {
            if (p.getPlayBoard(xSide, ySide) == 2 && p.getPlayBoard(xSide - i, ySide + i) == 1 && p.getPlayBoard(xSide + 1, ySide - 1) == 1) {
                for (int k = 0; k <= i; k++) {
                    p.setPlayBoard(xSide - k, ySide + k, 1);
                }
                break;
            } else if (ySide + i == size-1 || xSide - i == 0) {
                break;
            }
        }
        for (int i = 0; i < size; i++) {
            if (p.getPlayBoard(xSide, ySide) == 2 && p.getPlayBoard(xSide - i, ySide - i) == 1 && p.getPlayBoard(xSide + 1, ySide + 1) == 1) {
                for (int k = 0; k <= i; k++) {
                    p.setPlayBoard(xSide - k, ySide - k, 1);
                }
                break;
            } else if (ySide - i == 0 || xSide - i == 0) {
                break;
            }
        }
        for (int i = 0; i < size; i++) {
            if (p.getPlayBoard(xSide, ySide) == 2 && p.getPlayBoard(xSide + i, ySide + i) == 1 && p.getPlayBoard(xSide - 1, ySide - 1) == 1) {
                for (int k = 0; k <= i; k++) {
                    p.setPlayBoard(xSide + k, ySide + k, 2);
                }
                break;
            } else if (ySide + i == size-1 || xSide + i == size-1) {
                break;
            }
        }
        for (int i = 0; i < size; i++) {
            if (p.getPlayBoard(xSide, ySide) == 2 && p.getPlayBoard(xSide + i, ySide - i) == 1 && p.getPlayBoard(xSide - 1, ySide + 1) == 1) {
                for (int k = 0; k <= i; k++) {
                    p.setPlayBoard(xSide + k, ySide - k, 1);
                }
                break;
            } else if (ySide - i == 0 || xSide + i == size-1) {
                break;
            }
        }
    }
    private void leftToRight(int size,int xForPlayerRock,PlayBoard p){
        for (int i=0;i<=xForPlayerRock;i++){
            for (int j=0;j<=size-xForPlayerRock;j++){
                if (p.getPlayBoard(xForPlayerRock, yForPlayerRock) == 2 && p.getPlayBoard(xForPlayerRock- i, yForPlayerRock) == 1 && p.getPlayBoard(xForPlayerRock+ j, yForPlayerRock) == 1) {
                    for(int k=0;k<j;k++) {
                        p.setPlayBoard(xForPlayerRock+k, yForPlayerRock, 1);
                    }
                    for (int k = 0; k < i; k++) {
                        p.setPlayBoard(xForPlayerRock-k, yForPlayerRock, 1);
                    }
                }
            }
        }
    }
    private void upToDown(int size, int yForPlayerRock,PlayBoard p){
        for (int i = 0; i <= yForPlayerRock; i++) {
            for (int j = 0; j <= size - yForPlayerRock; j++) {
                if (p.getPlayBoard(xForPlayerRock, yForPlayerRock) == 2 && p.getPlayBoard(xForPlayerRock, yForPlayerRock - i) == 1 && p.getPlayBoard(xForPlayerRock, yForPlayerRock + j) == 1) {
                    for (int k = 0; k < j; k++) {
                        p.setPlayBoard(xForPlayerRock, yForPlayerRock + k, 1);
                    }
                    for (int k = 0; k < i; k++) {
                        p.setPlayBoard(xForPlayerRock, yForPlayerRock - k, 1);
                    }
                }
            }
        }
    }
}
