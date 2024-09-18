package Minesweeper;

public class Element {
    private boolean isMine;
    private int number;
    private boolean isHidden;
    private boolean isFlagged;
    public Element(boolean _isMine, int num){
        isMine = _isMine;
        isHidden = true;
        number = num;
        isFlagged = false;
    }

    public boolean flag(){
        if(isHidden)
            isFlagged = !isFlagged;
        return isFlagged;
    }

    public int reveal(){
        if(!isFlagged)
            isHidden = false;
        return number;
    }

    public boolean getFlag(){
        return isFlagged;
    }
    public boolean getHidden(){
        return isHidden;
    }
    public boolean getMine(){
        return isMine;
    }
    public int getNum(){
        return number;
    }

    public String toString(){
        if(isFlagged) return "P";
        if(isHidden) return ".";
        if(isMine) return "#";
        return String.valueOf(number);
    }
}
