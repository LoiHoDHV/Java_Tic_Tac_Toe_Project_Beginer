package tictactoe;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // write your code here

        Scanner scanner = new Scanner(System.in);


        char[][] character = new char[3][3];
        printEmptyBattle(character);
//        System.out.print("Enter cells: ");
//        String  stringLine = scanner.nextLine().trim();
//        for(int i = 0 ; i < character.length; i++){
//            for(int j = 0; j < character[i].length; j++){
//                character[i][j] = stringLine.charAt(i*3 + j);
//            }
//        }

//        getUserInput(character, scanner);
//        printBattle(character);

        fight(character,scanner);


    }
    public static boolean drawFight(char[][] arr){
        boolean draw = false;
        if(!hasEmptyCells(arr) && !OWins(arr) && !XWins(arr)){
            draw = true;
        }
        return draw;
    }

    public static void fight(char[][] arr,Scanner scanner){
        boolean stop = false;
        boolean notDraw = true;
        int count = 0;
        while(!stop && notDraw){
            getUserInput(arr,scanner,count % 2);
            ++count;
            if(XWins(arr) || OWins(arr)){
                stop = true;
                break;
            }
            if(drawFight(arr)){
                notDraw = false;
                break;
            }
        }
        if(XWins(arr)){
            System.out.println("X wins");
        }
        if(OWins(arr)){
            System.out.println("O wins");
        }
        if(drawFight(arr)){
            System.out.println("Draw");
        }
    }

    public static void printResult(char[][] character,Scanner scanner){
        System.out.print("Enter cells: ");
        String  stringLine = scanner.nextLine().trim();
        for(int i = 0 ; i < character.length; i++){
            for(int j = 0; j < character[i].length; j++){
                character[i][j] = stringLine.charAt(i*3 + j);
            }
        }

        printBattle(character);

        if(XWins(character) && !OWins(character)){
            System.out.println("X wins");
        }else if (OWins(character) && !XWins(character)){
            System.out.println("O wins");
        }else if (!hasEmptyCells(character) && !OWins(character) && !XWins(character)){
            System.out.println("Draw");
        }else if(hasEmptyCells(character) && !OWins(character) && !XWins(character) && !counting(character)){
            System.out.println("Game not finished");
        }else if((XWins(character) && OWins(character)) || counting(character)){
            System.out.println("Impossible");
        }
    }

    public static void replaceCharacter_(char[][] arr){
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr[i].length; j++){
                if(arr[i][j] == '_'){
                    arr[i][j] = ' ';
                }
            }
        }
    }

    public static void printBattle(char[][] arr){
        replaceCharacter_(arr);
        System.out.println("---------");
        for(int i = 0; i < 3; i++){
            System.out.print("| ");
            for(int j = 0; j < arr[i].length; j++){

                System.out.print(arr[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }
    public static void putBlankIntoArr(char[][] arr){
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr[i].length ; j++){
                arr[i][j] = ' ';
            }
        }
    }
    public static void printEmptyBattle(char[][] arr){
        putBlankIntoArr(arr);
        System.out.println("---------");
        for(int i = 0; i < 3; i++){
            System.out.print("| ");for(int j = 0; j < arr[i].length; j++){

                System.out.print(arr[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }



    /**
     * win on Row
     * @param arr
     * @param c
     * @param row
     * @return
     */
    public static boolean winOnRow(char[][] arr, char c,int row){
        boolean win = false;
        int count = 0;
        for(int i = 0 ; i < arr[row].length ; i++){
            if(arr[row][i] == c){
                ++count;
            }
        }
        if (count == 3){
            win = true;
        }
        return win;
    }

    /**
     * win on Column
     * @param arr
     * @param c
     * @param col
     * @return
     */
    public static boolean winOnColum(char[][] arr, char c, int col){
        boolean win = false;
        int count = 0;
        for(int i = 0; i < arr[col].length; i++){
            if(arr[i][col] == c){
                ++count;
            }
        }
        if(count == 3){
            win = true;
        }
        return win;
    }

    /**
     * win on Left Diagonal Line
     *
     * @param arr
     * @param c
     * @return
     */
    public static boolean winOnLeftDiagonalLine(char[][] arr,char c){
        boolean win = false;
        int count = 0;
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr[i].length; j++){
                if(i == j){
                    if(arr[i][j] != c){
                        return win;
                    }else{
                        ++count;
                    }
                }
            }
        }
        if (count == 3){
            win = true;
        }
        return win;
    }

    /**
     * win on Right Diagonal Line
     *
     * @param arr
     * @param c
     * @return
     */
    public static boolean winOnRightDiagonalLine(char[][] arr, char c){
        boolean win = false;
        int count = 0;
        for(int i = 0; i < arr.length; i++){
            for(int j = 0;j < arr[i].length; j++){
                if(i + j == arr.length - 1){
                    if(arr[i][j] != c){
                        return win;
                    }
                    else{
                        ++count;
                    }
                }
            }
        }
        if(count == 3){
            win = true;

        }
        return win;
    }

    /**
     * EmptyCell?
     * @param arr
     * @return
     */
    public static boolean hasEmptyCells(char[][] arr){
        boolean empty = false;
        for (char[] k : arr){

            for(char e : k){
                if (e == ' '){
                    empty = true;
                    break;
                }
            }
            if(empty == true){
                break;
            }
        }
        return empty;

    }


    public static boolean XWins(char[][] arr){

        char c = 'X';
        if(winOnLeftDiagonalLine(arr,c) || winOnRightDiagonalLine(arr,c)){
            return true;
        }
        for(int i = 0; i < arr.length; i++){
            if(winOnColum(arr,c,i) || winOnRow(arr,c,i)){
                return true;
            }
        }
        return false;
    }

    public static boolean OWins(char[][] arr){
        boolean oWins = false;
        char c = 'O';
        if(winOnLeftDiagonalLine(arr,c) || winOnRightDiagonalLine(arr,c)){
            oWins = true;
        }
        for(int i = 0; i < arr.length; i++){
            if(winOnColum(arr,c,i) || winOnRow(arr,c,i)){
                oWins = true;
            }
        }
        return oWins;
    }

    public static boolean counting(char[][] arr){
        int countX = 0;
        int countO = 0;
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr.length; j++){
                if(arr[i][j] == 'X'){
                    ++countX;
                }
                if(arr[i][j] == 'O'){
                    ++countO;
                }
            }
        }
        boolean imposible = false;
        if((int)Math.abs(countX - countO) >= 2){
            imposible = true;
        }
        return imposible;

    }
    public static void getUserInput(char[][] arr, Scanner scanner, int turn){
        boolean canParse = true;
        boolean notValidInput = true;
        boolean occupied = true;
        int fCoordinate = 0;
        int sCoordinate = 0;
        while(notValidInput && occupied){
            System.out.print("Enter the coordinates: ");
            String[] choice = scanner.nextLine().split(" ");

            for(int i = 0; i < choice.length; i++){
                if(canParseInt(choice[i]) == false){
                    System.out.println("You should enter numbers!");
                    canParse = false;
                    break;
                }

            }

            if(!canParse){
                canParse = true;
                continue;
            }

            fCoordinate = Integer.parseInt(choice[0]);
            sCoordinate = Integer.parseInt(choice[1]);
            if(fCoordinate < 1 || fCoordinate > 3 || sCoordinate < 1 || sCoordinate > 3){
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }

            if(occupied(fCoordinate,sCoordinate,arr)){
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }else{
                notValidInput = false;
                occupied = false;
            }



        }
        if(turn == 0){
            placeX(arr,fCoordinate,sCoordinate);
        }
        if(turn == 1){
            placeO(arr,fCoordinate,sCoordinate);
        }

        printBattle(arr);
    }



    public static boolean canParseInt(String s){
        try{
            Integer.parseInt(s);
            return true;
        }catch ( Exception e){
            return false;
        }
    }
    public static boolean occupied(int first, int second, char[][] arr){
        boolean occupied = false;
        if(arr[first-1][second-1] == 'X' || arr[first-1][second-1] == 'O'){
            occupied = true;
        }
        return occupied;
    }
    public static void placeX(char[][] arr, int fCoordinate, int sCoordinate){
        arr[fCoordinate - 1][sCoordinate -1] = 'X';
    }
    public static void placeO(char[][] arr, int fCoordinate, int sCoordinate){
        arr[fCoordinate - 1][sCoordinate - 1] = 'O';
    }

}
