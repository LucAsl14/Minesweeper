package Minesweeper;
import java.util.Random;

public class Field {
    private Element[][] field;
    private int rows, cols, mines;
    private Random rand = new Random();
    public Field(int _rows, int _cols, int _mines, int avoidY, int avoidX){
        rows = _rows;
        cols = _cols;
        mines = _mines;
        field = new Element[rows][cols];

        for(int i=0; i<mines; i++){
            int y = rand.nextInt(rows);
            int x = rand.nextInt(cols);
            boolean canMine = true;
            for(int j=-1; j<=1; j++){
                for(int k=-1; k<=1; k++){
                    if(y+j==avoidY&&x+k==avoidX) canMine = false;
                }
            }
            if(field[y][x]==null&&canMine){
                field[y][x] = new Mine();
            } else i--;
        }

        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                if(field[i][j]==null){
                    field[i][j] = new Number(checkSurrounding(i, j));
                }
            }
        }
    }

    private int checkSurrounding(int row, int col){
        int totalMines = 0;
        for(int i=-1; i<=1; i++){
            for(int j=-1; j<=1; j++){
                if(row+i<0) continue;
                if(row+i>=rows) continue;
                if(col+j<0) continue;
                if(col+j>=cols) continue;
                
                if(field[row+i][col+j]!=null){
                    if(field[row+i][col+j].getMine()==true){
                        totalMines++;
                    }
                }
            }
        }
        return totalMines;
    }

    public int revealClick(int _row, int _col){
        if(field[_row][_col].getFlag()==true){
            return -3;
        }
        if(field[_row][_col].getMine()==true){
            return -1;
        }
        if(field[_row][_col].getHidden()==false){
            return -2;
        }
        int toReturn = field[_row][_col].reveal();
        if(toReturn==0){
            for(int i=-1; i<=1; i++){
                for(int j=-1; j<=1; j++){
                    if(_row+i<0) continue;
                    if(_row+i>=rows) continue;
                    if(_col+j<0) continue;
                    if(_col+j>=cols) continue;

                    revealClick(_row+i, _col+j);
                }
            }            
        }
        return toReturn;
    }

    public void flagClick(int _row, int _col){
        boolean isFlagAfter = field[_row][_col].flag();
        if(isFlagAfter) mines--;
        else mines++;
    }

    public boolean checkWin(){
        for(Element[] a:field){
            for(Element e:a){
                if(e.getHidden()&&!e.getFlag()) return false;
            }
        }
        return mines>=0;
    }

    public Element[][] returnField(){
        return field;
    }

    public int getMines(){
        return mines;
    }

    public void printField(){
        for(Element[] a:field){
            for(Element e:a){
                System.out.print(e+" ");
            }
            System.out.println();
        }
    }
}
