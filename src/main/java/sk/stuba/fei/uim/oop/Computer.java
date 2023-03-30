package sk.stuba.fei.uim.oop;

public class Computer {
    private final int[] xArray;
    private final int[] yArray;
    private int a = 0;
    public Computer() {
        xArray = new int[144];
        yArray = new int[144];
    }
    /*
    computers move works by looking for black rocks and if it surraunded by a white rock and if the space
    opposite of the white rock has a white rock and free space after the white rock we can place there the computers rock
    and after that we save the position in to an Array.
     */
    public void computersMove(int highAndWidthOfBoard,PlayBoard p) {
        for (int x = 1; x < highAndWidthOfBoard; x++) {
            for (int y = 1; y < highAndWidthOfBoard; y++) {
                //to left
                for (int i = 0; i < highAndWidthOfBoard - x - 1; i++) {
                    if (p.getPlayBoard(x, y) == 1 && p.getPlayBoard(x - 1, y) == 0 && p.getPlayBoard(x + i, y) == 2) {
                        a++;
                        xArray[a] = x - 1;
                        yArray[a] = y;
                        break;
                    }
                }//up
                for (int i = 0; i < highAndWidthOfBoard - y - 1; i++) {
                    if (p.getPlayBoard(x, y) == 1 && p.getPlayBoard(x, y - 1) == 0 && p.getPlayBoard(x, y + i) == 2) {
                        a++;
                        xArray[a] = x;
                        yArray[a] = y - 1;
                        break;
                    }
                }//down
                for (int i = 0; i < y; i++) {
                    if (p.getPlayBoard(x, y) == 1 && p.getPlayBoard(x, y + 1) == 0 && p.getPlayBoard(x, y - i) == 2) {
                        a++;
                        xArray[a] = x;
                        yArray[a] = y + 1;
                        break;
                    }
                }//right
                for (int i = 0; i < x; i++) {
                    if (p.getPlayBoard(x, y) == 1 && p.getPlayBoard(x + 1, y) == 0 && p.getPlayBoard(x - i, y) == 2) {
                        a++;
                        xArray[a] = x + 1;
                        yArray[a] = y;
                        break;
                    }
                }
                horizontalUpLeftComputer(x, y,p);
                horizontalDownLeftComputer(x, y,p);
                horizontalDownRightForComputer(x, y,p);
                horizontalUpRightForComputer(x, y,p);
            }
        }
        /*
        we are using a random number from array to determine where will be placed the white rock
         */
        int randomNumber = getRandomNumber(a);
        a = 0;
        p.setPlayBoard(xArray[randomNumber], yArray[randomNumber], 2);
        flipBlackStones(xArray, yArray, randomNumber, highAndWidthOfBoard,p);
    }
    private void horizontalUpLeftComputer(int x, int y,PlayBoard p) {
        int b = 1;
        for (int i = 1; i <= b; i++) {
            if (p.getPlayBoard(x, y) == 1 && p.getPlayBoard(x - 1, y - 1) == 0 && p.getPlayBoard(x + i, y + i) == 1) {
                b++;
            }
            if (p.getPlayBoard(x, y) == 1 && p.getPlayBoard(x - 1, y - 1) == 0 && p.getPlayBoard(x + b, y + b) == 2) {
                a++;
                xArray[a] = x - 1;
                yArray[a] = y - 1;
            }
        }
    }
    private void horizontalDownLeftComputer(int x, int y, PlayBoard p) {
        int b = 1;
        for (int i = 1; i <= b; i++) {
            if (p.getPlayBoard(x, y) == 1 && p.getPlayBoard(x - 1, y + 1) == 0 && p.getPlayBoard(x + i, y - i) == 1) {
                b++;
            }
            if (p.getPlayBoard(x, y) == 1 && p.getPlayBoard(x - 1, y + 1) == 0 && p.getPlayBoard(x + b, y - b) == 2) {
                a++;
                xArray[a] = x - 1;
                yArray[a] = y + 1;
            }
        }
    }
    private void horizontalDownRightForComputer(int x, int y,PlayBoard p) {
        int b = 1;
        for (int i = 1; i <= b; i++) {
            if (p.getPlayBoard(x, y) == 1 && p.getPlayBoard(x + 1, y + 1) == 0 && p.getPlayBoard(x - i, y - i) == 1) {
                if (x - i == 0 || y - i == 0) {
                    break;
                } else {
                    b++;
                }
            }
            if (p.getPlayBoard(x, y) == 1 && p.getPlayBoard(x + 1, y + 1) == 0 && p.getPlayBoard(x - b, y - b) == 2) {
                a++;
                xArray[a] = x + 1;
                yArray[a] = y + 1;
            }
        }
    }
    private void horizontalUpRightForComputer(int x, int y,PlayBoard p) {
        int b = 1;
        for (int i = 1; i <= b; i++) {
            if (p.getPlayBoard(x, y) == 1 && p.getPlayBoard(x + 1, y - 1) == 0 && p.getPlayBoard(x - i, y + i) == 1) {
                if (x - i == 0 || y - i == 0) {
                    break;
                } else {
                    b++;
                }
            }
            if (p.getPlayBoard(x, y) == 1 && p.getPlayBoard(x + 1, y - 1) == 0 && p.getPlayBoard(x - b, y + b) == 2) {
                a++;
                xArray[a] = x + 1;
                yArray[a] = y - 1;
            }
        }
    }
    private int getRandomNumber(int max){
        return (int) (Math.random() * (max - 1 + 1) + 1);

    }
    private void flipBlackStones(int[] xArray, int[] yArray, int randomNumber, int size,PlayBoard p) {
        int x = xArray[randomNumber];
        int y = yArray[randomNumber];
        int xPlus = x + 1;
        int xMinus = x - 1;
        int yMinus = y - 1;
        int yPlus = y + 1;

        //up and down is blocked
        upToDown(xArray, randomNumber, size, yPlus,p);
        upToDown(xArray, randomNumber, size, yMinus,p);
        leftToRight(yArray, randomNumber, size, xMinus,p);
        leftToRight(yArray, randomNumber, size, xPlus,p);
        if (!(yMinus == 0)) {
            horizontalLeftDown(xPlus, yMinus, size,p);
            horizontalLeftDown(xMinus, yMinus, size,p);
        }
        horizontalLeftDown(xMinus, yPlus, size,p);
        horizontalLeftDown(xPlus, yPlus, size,p);
        if (!(xMinus == 0)) {
            horizontalLeftDown(xMinus, yPlus, size,p);
            horizontalLeftDown(xMinus, yMinus, size,p);
        }
        horizontalLeftDown(xPlus, yMinus, size,p);
        horizontalLeftDown(xPlus, yPlus, size,p);
    }
    private void horizontalLeftDown(int xSide, int ySide, int size,PlayBoard p) {
        horizontalLeft(xSide, ySide, size,p);
    }
    private void horizontalLeft(int xSide, int ySide, int size,PlayBoard p) {
        if (xSide == 0) {
            xSide = xSide + 2;
        } else if (ySide == 0) {
            ySide = ySide + 2;
        }
        for (int i = 0; i < size; i++) {
            if (p.getPlayBoard(xSide, ySide) == 1 && p.getPlayBoard(xSide - i, ySide + i) == 2 && p.getPlayBoard(xSide + 1, ySide - 1) == 2) {
                for (int k = 0; k <= i; k++) {
                    p.setPlayBoard(xSide - k, ySide + k, 2);
                }
                break;
            } else if (ySide + i == size-1 || xSide - i == 0) {
                break;
            }
        }
        for (int i = 0; i < size; i++) {
            if (p.getPlayBoard(xSide, ySide) == 1 && p.getPlayBoard(xSide - i, ySide - i) == 2 && p.getPlayBoard(xSide + 1, ySide + 1) == 2) {
                for (int k = 0; k <= i; k++) {
                    p.setPlayBoard(xSide - k, ySide - k, 2);
                }
                break;
            } else if (ySide - i == 0 || xSide - i == 0) {
                break;
            }
        }
        for (int i = 0; i < size; i++) {
            if (p.getPlayBoard(xSide, ySide) == 1 && p.getPlayBoard(xSide + i, ySide + i) == 2 && p.getPlayBoard(xSide - 1, ySide - 1) == 2) {
                for (int k = 0; k <= i; k++) {
                    p.setPlayBoard(xSide + k, ySide + k, 2);
                }
                break;
            } else if (ySide + i == size-1 || xSide + i == size-1) {
                break;
            }
        }
        for (int i = 0; i < size; i++) {
            if (p.getPlayBoard(xSide, ySide) == 1 && p.getPlayBoard(xSide + i, ySide - i) == 2 && p.getPlayBoard(xSide - 1, ySide + 1) == 2) {
                for (int k = 0; k <= i; k++) {
                    p.setPlayBoard(xSide + k, ySide - k, 2);
                }
                break;
            } else if (ySide - i == 0 || xSide + i == size-1) {
                break;
            }
        }
    }
    private void upToDown(int[] xArray, int randomNumber, int size, int side,PlayBoard p) {
        for (int i = 0; i <= side; i++) {
            for (int j = 0; j <= size - side; j++) {
                if (p.getPlayBoard(xArray[randomNumber], side) == 1 && p.getPlayBoard(xArray[randomNumber], side - i) == 2 && p.getPlayBoard(xArray[randomNumber], side + j) == 2) {
                    for (int k = 0; k < j; k++) {
                        p.setPlayBoard(xArray[randomNumber], side + k, 2);
                    }
                    for (int k = 0; k < i; k++) {
                        p.setPlayBoard(xArray[randomNumber], side - k, 2);
                    }
                }
            }
        }
    }
    private void leftToRight(int[] yArray, int randomNumber, int size, int side,PlayBoard p) {
        for (int i=0;i<=side;i++){
            for (int j=0;j<=size-side;j++){
                if (p.getPlayBoard(side, yArray[randomNumber]) == 1 && p.getPlayBoard(side - i, yArray[randomNumber]) == 2 && p.getPlayBoard(side + j, yArray[randomNumber]) == 2) {
                    for(int k=0;k<j;k++) {
                        p.setPlayBoard(side+k, yArray[randomNumber], 2);
                    }
                    for (int k = 0; k < i; k++) {
                        p.setPlayBoard(side-k, yArray[randomNumber], 2);
                    }
                }
            }
        }
    }
}
