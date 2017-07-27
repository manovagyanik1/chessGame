package com.company;

import com.company.model.Coordinate;
import com.company.model.Turn;
import com.company.playerType.Horse;
import com.company.playerType.IPlayer;
import com.company.utils.Gen;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class ChessGame {

    public static int INF = 245234234; // a very big number
    public static void main(String[] args) {
        System.out.println("Chess game started!!");
        System.out.println("Enter players' coordinates(x,y)");
        System.out.println("Enter for player 1 (x,y)");
        Coordinate p1 = readInputCoordinates();
        System.out.println("Enter for player 2 (x,y)");
        Coordinate p2 = readInputCoordinates();
        IPlayer horse = new Horse();
        play(p1, p2, horse);
    }

    public static Coordinate readInputCoordinates() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter x");
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        return new Coordinate(x, y);
    }

    private static int[][] initMoves() {
        int[][] movesNeeded = new int[8][8];
        for(int i =0; i<8; i++) {
            for(int j=0; j<8; j++) {
                movesNeeded[i][j] = INF;
            }
        }
        return movesNeeded;
    }

    public static int[][] initGame(IPlayer player) {
        int[][] movesNeeded = initMoves();
        // do breadth first traversal, start with 0,0
        int count = 64;
        Queue<Coordinate> q = new LinkedList<>();
        movesNeeded[0][0] = 0;
        q.add(new Coordinate(0,0));
        while(count >0 && !q.isEmpty()) {
            Coordinate current = q.remove();
            int movesCurrent = movesNeeded[current.getX()][current.getY()];
            List<Coordinate> list = player.move(current);
            List<Coordinate> curatedList = Gen.curateList(list);
            for(Coordinate c: curatedList){
                int prevMovesNeeded = movesNeeded[c.getX()][c.getY()];
                if(prevMovesNeeded == INF){
                    count--;
                    movesNeeded[c.getX()][c.getY()] = movesCurrent + 1;
                    q.add(c);
                } else {
                    // put minimum
                    if(prevMovesNeeded > movesCurrent + 1){
                        q.add(c);
                    }
                    movesNeeded[c.getX()][c.getY()] = Math.min(prevMovesNeeded, movesCurrent + 1);
                }
            }
        }
        // Now we have array populated
        return movesNeeded;
    }

    public static void play(Coordinate p1, Coordinate p2, IPlayer playerType) {
        int[][] movesNeeded = initGame(playerType);
        Turn turn = Turn.P1;
        IPlayer horse = new Horse();
        while(!isWin(p1, p2)){
            playTurn(p1, p2, turn, horse, movesNeeded);
            turn = getNextTurn(turn);
        }
        // showResult(p1, p2, movesNeeded);
    }

    private static Turn getNextTurn(Turn turn){
        if(turn == Turn.P1) {
            return Turn.P2;
        }
        return Turn.P1;
    }

    public static boolean isWin(Coordinate p1, Coordinate p2) {
        if((p1.getX() == 0 && p1.getY() ==0) || (p2.getX() ==0 && p2.getY() ==0)){
            return true;
        }
        return false;
    }

    private static void playTurn(Coordinate p1, Coordinate p2, Turn turn, IPlayer player, int[][] movesNeeded){
        if(turn == Turn.P1){
            List<Coordinate> list = player.move(p1);
            list = Gen.curateList(list);
            Coordinate minCoord = list.get(0);
            int minMove = movesNeeded[minCoord.getX()][minCoord.getY()];
            for(Coordinate c: list){
                if(p2.getX()!= c.getX() || p2.getY()!=c.getY()){
                    if(minMove>movesNeeded[c.getX()][c.getY()]){
                        minMove = movesNeeded[c.getX()][c.getY()];
                        minCoord = c;
                    }
                }
            }
            System.out.println("p1 moved to " + minCoord.getX() + " , " + minCoord.getY());
            p1.setX(minCoord.getX());
            p1.setY(minCoord.getY());
        } else {
            List<Coordinate> list = player.move(p2);
            list = Gen.curateList(list);
            Coordinate minCoord = list.get(0);
            int minMove = movesNeeded[minCoord.getX()][minCoord.getY()];
            for(Coordinate c: list){
                if(p1.getX()!= c.getX() || p1.getY()!=c.getY()){
                    if(minMove>movesNeeded[c.getX()][c.getY()]){
                        minMove = movesNeeded[c.getX()][c.getY()];
                        minCoord = c;
                    }
                }
            }
            System.out.println("p2 moved to " + minCoord.getX() + " , " + minCoord.getY());

            p2.setX(minCoord.getX());
            p2.setY(minCoord.getY());
        }
    }

    public static void showResult(Coordinate p1, Coordinate p2, int[][] movesNeeded) {
        if(movesNeeded[p1.getX()][p1.getY()] > movesNeeded[p2.getX()][p2.getY()]){
            System.out.println("p2 won the game");
        } else {
            System.out.println("p1 won the game");
        }
        // print the path here.
    }

}
