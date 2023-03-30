package sk.stuba.fei.uim.oop;

public class BoardCell {
    private final boolean usable;

    public boolean isUsable() {
        return usable;
    }

    public BoardCell(boolean usable){
        this.usable=usable;
    }
}
