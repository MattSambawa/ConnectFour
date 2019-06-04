/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectfour;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSlider;

/**
 *
 * @author sambawam3161
 */

public class Grid extends JPanel {

    public char[][] mat;
    
    private final int ROWS;
    private final int COLUMNS;
    
    private int playerTurn;
    
    public String pOne;
    public String pTwo;
    
    public boolean win;

    public Grid() {
        mat = new char[6][7];
        
        ROWS = 6;
        COLUMNS = 7;
        
        playerTurn = 1;
        
        win = false;
        
        //---------------------
        
        playerOneName();
        playerTwoName();
        
        createBoard();
        
        printBoard();
        System.out.println("  0    1    2    3    4    5    6");
        
        gamePlay();

    }
    
    public void startGame() {
        while (CheckWin() == false) {
            gamePlay();
        }
        
        if (playerTurn % 2 == 1) {
		System.out.println(get_playerOne() + " wins!!");
            }
            else {
                System.out.println(get_playerTwo() + " wins!!");
            }
    }
    
    public void gamePlay() {
        playerTurn++;
        
        Scanner scan = new Scanner(System.in);
        
        if(playerTurn % 2 == 1){
                System.out.print(get_playerOne() + " please select a column: ");
        }
        else {
            System.out.print(get_playerTwo() + " please select a column: ");
        }
				
        int input = scan.nextInt();
        while(input < 0 || input > 6 || mat[0][input] != '_'){
            System.out.print(input + " is not a valid column." + " Please select a valid column: ");
            input = scan.nextInt();
        }
        
            int selection = input;
        
            placetoken:

            for(int row = mat.length - 1; row >= 0; row--){
                if(mat[row][selection] == '_'){
                    if (playerTurn % 2 == 1) {
                        mat[row][selection] = 'O';
                            if (CheckWin()){
                                win = true;
                            }
				break placetoken;
                                
                            }
                    else {
                            mat[row][selection] = 'X';
                            if (CheckWin()){
                                win = true;
                            }
                            break placetoken;
                            }
                        }
                    }
                          
        for (int row = 0; row < mat.length; row++) {
            for(int col = 0; col < mat[0].length; col++){
                System.out.print("| " + mat[row][col] + " |");
            }
            System.out.println();
        }
        System.out.println("  0    1    2    3    4    5    6");
    }
    
    public void createBoard() {
        for (int c = 0; c < get_rows(); c++) {
            for (int r = 0; r < get_columns(); r++) {
                mat[c][r] = '_';
            }
        }
    }
    
    public void printBoard() {
        for (int r = 0; r < get_rows(); r++) {
            for (int c = 0; c < get_columns(); c++) {
                System.out.print("| " + mat[r][c] + " |");
            }
            System.out.println();
        }
    }
    
    //returns rows
    public int get_rows() {
        return ROWS;
    }
    
    //returns columns
    public int get_columns() {
        return COLUMNS;
    }
    
    public void playerOneName() {
        System.out.print("Enter Player One's name: ");
        
        Scanner name = new Scanner(System.in);
        String playerOne = name.nextLine();
        
        if (playerOne == "") {
            System.out.print("Please enter a name: ");
            playerOne = name.nextLine();
            pOne = playerOne;
        }
        else {
            pOne = playerOne;
        }
    }
    
    public void playerTwoName() {
        System.out.print("Enter Player Two's name: ");
        
        Scanner name = new Scanner(System.in);
        String playerTwo = name.nextLine();
        
        if (playerTwo == "") {
            System.out.print("Please enter a name: ");
            playerTwo = name.nextLine();
            pTwo = playerTwo;
        }
        else {
            pTwo = playerTwo;
        }
    }
    
    public String get_playerOne() {
        return pOne;
    }
    
    public String get_playerTwo() {
        return pTwo;
    }

    //checks for room in collumn and returns free spot.
    public int find_height(int x) {
        int y = -1;
        for (int i = 0; i < ROWS; i++) {
            if (mat[x][i] == 0) {
                y = i;
            }
        }
        return y;
    }
    
    
    //------------------------
    //CHECKER METHODS
    //------------------------
    
    
    //Checks for winner
    public boolean CheckWin(){
		if(CheckHorizontal() || CheckVertical() || CheckDiagonal()){
			return true;
		}
		return false;
	}
    
    
    //Horizontal Checker
	public boolean CheckHorizontal(){
		for(int row = 0; row < mat.length; row ++){
			for(int col = 0; col <= 3; col ++){
				if(mat[row][col] == 'O' && mat[row][col] == mat[row][col+1] && mat[row][col] == mat[row][col+2] && mat[row][col] == mat[row][col+3]){
					return true;
				}
				if(mat[row][col] == 'X' && mat[row][col] == mat[row][col+1] && mat[row][col] == mat[row][col+2] && mat[row][col] == mat[row][col+3]){
					return true;
				}
			}
		}
		return false;
	}
	
  //Vertaical Checker
	public boolean CheckVertical(){
		for(int col = 0; col < mat[0].length; col ++){
			for(int row = 0; row <= 2; row ++){
				if(mat[row][col] == 'O' && mat[row][col] == mat[row+1][col] && mat[row][col] == mat[row+2][col] && mat[row][col] == mat[row+3][col]){
					return true;
				}
				if(mat[row][col] == 'X' && mat[row][col] == mat[row+1][col] && mat[row][col] == mat[row+2][col] && mat[row][col] == mat[row+3][col]){
					return true;
				}
			}
		}
		return false;
	}
	
  //Diagonal Checker
	public boolean CheckDiagonal(){
		for(int row = 0; row <= 2; row ++){
			for(int col = 0; col <= 3; col ++){
				if(mat[row][col] == 'O' && mat[row][col] == mat[row+1][col+1] && mat[row][col] == mat[row+2][col+2] && mat[row][col] == mat[row+3][col+3]){
					return true;
				}
				if(mat[row][col] == 'X' && mat[row][col] == mat[row+1][col+1] && mat[row][col] == mat[row+2][col+2] && mat[row][col] == mat[row+3][col+3]){
					return true;
				}
			}
		}

		for(int row = mat.length-1; row >=3; row --){
			for(int col = 0; col <=3; col ++){
				if(mat[row][col] == 'O' && mat[row][col] == mat[row-1][col+1] && mat[row][col] == mat[row-2][col+2] && mat[row][col] == mat[row-3][col+3]){
					return true;
				}
				if(mat[row][col] == 'X' && mat[row][col] == mat[row-1][col+1] && mat[row][col] == mat[row-2][col+2] && mat[row][col] == mat[row-3][col+3]){
					return true;
				}
			}
		}
		return false;
	}
    
    //------------------------------
    //Graphics Implemented Methods
    //------------------------------
        
    public boolean CheckBlueHorizontal(){
        boolean check = true;
        int counter = 0;
        
        while(check){
            for(int w = 0; w < get_columns(); w++){
                for(int h = 0; h < get_rows(); h++){
                    if(mat[w][h] == 'X'){
                        counter++;
                    }else{
                        counter = 0; // if next piece is not a BLUE CHIP, set counter to 0
                    }
                    if(counter >= 4){
                        System.out.println("Player 1 wins"); //if counter is greater or equal to 4, player wins
                        check = false;
                    }
                }
            }
            break;
        }
        return check;
    }
    
    
    public boolean CheckBlueVertical(){
        boolean check = true;
        int counter = 0;
        
        while(check){
            for(int w = 0; w < get_columns(); w += 1){
                for(int h = 0; h < get_rows(); h += 1){
                    if(mat[w][h] == 'X'){
                        counter++;
                    }else{
                        counter = 0; // if next piece is not a BLUE CHIP, set counter to 0
                    }
                    if(counter >= 4){
                        System.out.println("Player 1 wins");
                        check = false;
                    }
                }
            }
            break;
        }
        return check;
    }
    
    
    public boolean CheckRedVertical(){
        boolean check = true;
        int counter = 0;
        
        while(check){
            for(int w = 0; w < get_columns(); w += 1){
                for(int h = 0; h < get_rows(); h += 1){
                    if(mat[w][h] == 'O'){
                        counter += 1;
                    }else{
                        counter = 0; // if next piece is not a RED CHIP, set counter to 0
                    }
                    if(counter >= 4){
                        System.out.println("Player 2 wins");
                        check = false;
                    }
                }
            }
            break;
        }
        return check;
    }
    
    
    public boolean CheckRedHorizontal(){
        boolean check = true;
        int counter = 0;
        
        while(check){
            for(int w = 0; w < get_columns(); w += 1){
                for(int h = 0; h < get_rows(); h += 1){
                    if(mat[w][h] == 'O'){
                        counter += 1;
                    }else{
                        counter = 0; //if next piece is not a RED CHIP, set counter to 0
                    }
                    if(counter >= 4){
                        System.out.println("Player 2 wins");
                        check = false;
                    }
                }
            }
            break;
        }
        return check;
    }
    
    
    public boolean CheckBlueDiagonalForward(){
        boolean check = true;
        int counter = 0;
        //boolean find = false;
        int checkColumn = 1;
        int checkRow = 1;

        while(check){ //goes through until a BLUE CHIP is found
            for(int w = 0; get_columns() > w; w += 1){
                for(int h = 0; get_rows() > h; h += 1){
                    if(mat[w][h] == 'X'){
                        counter += 1;
                        //find = true;
                        while(check){
                            if(checkColumn + w <= get_columns() - 1 && checkRow + h <= get_rows() - 1){
                                if(mat[w + checkColumn][h + checkRow] == 'X'){
                                    counter += 1;
                                }
                            }

                            checkColumn++;
                            
                            checkRow++;

                            if(checkColumn == get_columns() -1 || checkRow == get_rows() -1){
                                //find = false;
                                break;
                            }

                            if(counter >= 4){
                                System.out.println("Player 2 wins");
                                //find = false;
                                check = false;
                                break;
                            }
                        }
                    }
                    if(counter >= 4){
                        check = false;
                        break;
                    }
                    
                    counter = 0;
                    checkColumn = 1;
                    checkRow = 1;
                }
            }
            break;
        }
        return check;
    }
    
    
    public boolean CheckREDDiagonalForward(){
        boolean check = true;
        int counter = 0;
        boolean find = false;
        int checkColumn = 1;
        int checkRow = 1;

        while(check){ //goes through until a RED CHIP is found
            for(int w = 0; get_columns() > w; w += 1){
                for(int h = 0; get_rows() > h; h += 1){
                    if(mat[w][h] == 'O'){
                        counter += 1;
                        find = true;
                        while(find == true){ //goes through diagonally looking for RED CHIPS
                            if(checkColumn + w <= get_columns() - 1 && checkRow + h <= get_rows() - 1){
                                if(mat[w + checkColumn][h + checkRow] == 'O'){
                                    counter += 1;
                                }
                            }

                            checkColumn++;
                            
                            checkRow++;

                            if(checkColumn == get_columns() -1 || checkRow == get_rows() -1){ 
                                find = false;
                                break;
                            }

                            if(counter >= 4){
                                System.out.println("Player 2 wins"); 
                                find = false;
                                check = false;
                                break;
                            }
                        }
                    }
                    if(counter >= 4){
                        check = false;
                        break;
                    }
                    
                    counter = 0;
                    checkColumn = 1;
                    checkRow = 1;
                }
            }
            break;
        }
        return check;
    }
    
    
    public boolean CheckBLUEDiagonalBack(){
        boolean check = true;
        int counter = 0;
        boolean find = false;
        int checkColumn = 1;
        int checkRow = 1;

        while(check){ //goes through until a BLUE CHIP is found
            for(int w = 0; get_columns() > w; w += 1){
                for(int h = 0; get_rows() > h; h += 1){
                    if(mat[w][h] == 'X'){
                        counter += 1;
                        find = true;
                        while(find == true){ //goes through diagonally looking for BLUE CHIPS
                            if(w - checkColumn >= 0 && h - checkRow >= 0){
                                if(mat[w - checkColumn][h - checkRow] == 'X'){
                                    counter += 1;
                                }
                            }
                            checkColumn += 1;
                            checkRow += 1;

                            if(checkColumn == 0 || checkRow == get_columns() -1){
                                find = false;
                                break;
                            }

                            if(counter >= 4){
                                System.out.println("Player 1 wins");
                                find = false;
                                check = false;
                                break;
                            }
                        }
                    }
                    if(counter >= 4){
                        check = false;
                        break;
                    }

                    counter = 0;
                    checkColumn = 1;
                    checkRow = 1;
                }
            }
            break;
        }
        return check;
    }
    
    
    public boolean CheckREDDiagonalBack(){
        boolean check = true;
        int counter = 0;
        boolean find = false;
        int checkColumn = 1;
        int checkRow = 1;

        while(check){ //goes through until a RED CHIP is found
            for(int w = 0; get_columns() > w; w += 1){
                for(int h = 0; get_rows() > h; h += 1){
                    if(mat[w][h] == 'O'){
                        counter += 1;
                        find = true;
                        while(find == true){ //goes through diagonally looking for RED CHIPS
                            if(w - checkColumn >= 0 && h - checkRow >= 0){
                                if(mat[w - checkColumn][h - checkRow] == 'O'){
                                    counter += 1;
                                }
                            }
                            checkColumn += 1;
                            checkRow += 1;

                            if(checkColumn == 0 || checkRow == get_rows() -1){
                                find = false;
                                break;
                            }

                            if(counter >= 4){
                                System.out.println("Player 2 wins");
                                find = false;
                                check = false;
                                break;
                            }
                        }
                    }
                    if(counter >= 4){
                        check = false;
                        break;
                    }

                    counter = 0;
                    checkColumn = 1;
                    checkRow = 1;
                }
            }
            break;
        }
        return check;
    }

}