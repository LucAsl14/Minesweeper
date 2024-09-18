package Minesweeper;
import javax.swing.*;
import java.awt.event.*;

//make a safe start maybe

public class GraphicDriver extends JFrame implements ActionListener {
    private ImageIcon hidden = new ImageIcon("D:\\coding\\code\\game images\\othello-green.png");
    private ImageIcon empty = new ImageIcon("D:\\coding\\code\\game images\\empty tile 2.png");
    private ImageIcon tile1 = new ImageIcon("D:\\coding\\code\\game images\\tile1 (2).png");
    private ImageIcon tile2 = new ImageIcon("D:\\coding\\code\\game images\\tile2 (2).png");
    private ImageIcon tile3 = new ImageIcon("D:\\coding\\code\\game images\\tile3 (2).png");
    private ImageIcon tile4 = new ImageIcon("D:\\coding\\code\\game images\\tile4 (2).png");
    private ImageIcon tile5 = new ImageIcon("D:\\coding\\code\\game images\\tile5 (2).png");
    private ImageIcon tile6 = new ImageIcon("D:\\coding\\code\\game images\\tile6.png");
    private ImageIcon tile7 = new ImageIcon("D:\\coding\\code\\game images\\tile7.png");
    private ImageIcon tile8 = new ImageIcon("D:\\coding\\code\\game images\\tile8.png");
    private ImageIcon flag = new ImageIcon("D:\\coding\\code\\game images\\flag tile.png");
    private ImageIcon clickHand = new ImageIcon("D:\\coding\\code\\game images\\mine tile.png");

    private final int size = 9;
    private final int rows = size;
    private final int cols = size;
    private final int mines = rows*cols/5;
    JButton slot[][] = new JButton[rows][cols];
    Field f1;
    boolean isHand = true;
    JButton flagButton;
    JButton click;
    boolean firstClick = true;

    public GraphicDriver(){
        super("Minesweeper by Lucas :)");
        setLayout(null);  
        setSize(1200,810);  
        setVisible(true);  
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  
        


        //Buttons
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                slot[i][j] = new JButton();
                slot[i][j].setBounds(5+j*755/cols,i*755/rows,755/cols,755/rows);
                slot[i][j].setIcon(hidden);
                slot[i][j].addActionListener(this);
                add(slot[i][j]);
            }
        }
        click = new JButton(clickHand);
        flagButton = new JButton(flag);
        click.setBounds(1000, 200, 120, 120);
        flagButton.setBounds(1000, 600, 120, 120);
        click.setIcon(clickHand);
        flagButton.setIcon(flag);
        click.addActionListener(this);
        flagButton.addActionListener(this);
        add(click);
        add(flagButton);

        //Game
        // f1 = new Field(rows,cols,mines/4);
        // showBoard(false);
    }

    public void reset(){
        new GraphicDriver();
    }

    public void actionPerformed(ActionEvent e){
        boolean skip = false;
        if(e.getSource()==flagButton){
            isHand = false;
            skip = true;
        }
        if(e.getSource()==click){
            isHand = true;
            skip = true;
        }
        checkloop: for(int i=0; i<rows; i++){
            if(skip) break checkloop;
            for(int j=0; j<cols; j++){
                if(e.getSource()==slot[i][j]){
                    if(firstClick){
                        f1 = new Field(rows,cols,mines,i,j);
                        firstClick = false;
                    }
                    int result;
                    if(isHand) result = GameMaster.makeMove(f1, i, j, true);
                    else result = GameMaster.makeMove(f1, i, j, false);
                    if(result==-1){
                        showBoard(true);
                        JOptionPane.showMessageDialog(GraphicDriver.this,"You lost lmao");
                        dispose();
                        reset();
                        dispose();
                    }
                    setTitle("Minesweeper by Lucas :)"+" There are "+f1.getMines()+" mines left :)");
                    break checkloop;            
                }
            }
        }
        if(!skip) showBoard(false);
        if(!skip) checkWin();
        
    }

    private void checkWin(){
        if(f1.checkWin()){
            // Winner of the game
            JOptionPane.showMessageDialog(GraphicDriver.this,"You Win!");
            dispose();
            reset();
        }
    }

    private void showBoard(boolean losing){
        Element[][] toPrint = f1.returnField();
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                switch(toPrint[i][j].toString()){
                    case "0":
                        slot[i][j].setIcon(empty);
                        break;
                    case "1":
                        slot[i][j].setIcon(tile1);
                        break;
                    case "2":
                        slot[i][j].setIcon(tile2);
                        break;
                    case "3":
                        slot[i][j].setIcon(tile3);
                        break;
                    case "4":
                        slot[i][j].setIcon(tile4);
                        break;
                    case "5":
                        slot[i][j].setIcon(tile5);
                        break;
                    case "6":
                        slot[i][j].setIcon(tile6);
                        break;
                    case "7":
                        slot[i][j].setIcon(tile7);
                        break;
                    case "8":
                        slot[i][j].setIcon(tile8);
                        break;
                    case "P":
                        slot[i][j].setIcon(flag);
                        break;
                    case ".":
                        slot[i][j].setIcon(hidden);
                        break;
                }
                if(losing&&toPrint[i][j].getMine()){
                    slot[i][j].setIcon(clickHand);
                }
            }
        }
        // f1.printField();
        // System.out.println(isHand);
    }

    public static void main(String[] args){
        new GraphicDriver();
    }
}

