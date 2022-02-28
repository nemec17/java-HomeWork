package com.gb;

import com.sun.xml.internal.ws.api.pipe.NextAction;
import javafx.beans.binding.When;

import java.util.Random;
import java.util.Scanner;

public class tiktaktoe {
    public static void main(String[] args) {
        String[][] map = FildStar(5);

        boolean NextGame = true;

        while (NextGame) {

            PrintMap(map);
            MovePlayer(map);

            if (WhoWinner("X", map) || noWinner(map)) {
                NextGame = false;
                break;
            }

            MovePC(map);

            if (WhoWinner("0", map) || noWinner(map)) {
                NextGame = false;
                PrintMap(map);
                break;
            }
        }

    }

    public static void MovePlayer(String[][] map) {
        Scanner sc = new Scanner(System.in);

        boolean PlayerActive = true;

        while (PlayerActive) {
            System.out.println("Ходит игрок, введите сначало номер строкими после номер столбца");

            int x = sc.nextInt();
            int y = sc.nextInt();

            if ((x > 0 && x <= map.length) && (y > 0 && y <= map.length)) {
                if (map[x - 1][y - 1].equals("*")) {
                    map[x - 1][y - 1] = "X";
                    PlayerActive = false;
                } else System.out.println("Эта клетка занята!");
            } else System.out.println("Давай выбирать поля внутри поля");
        }
    }

    public static void MovePC(String[][] map) {

        boolean ActivePC = true;
        System.out.println("Хожу Я");

        while (ActivePC) {
            Random pc = new Random();

            int x = pc.nextInt(map.length);
            int y = pc.nextInt(map.length);

            for (int i = 0; i < map.length; i++) {

                if (
                        (map[i][0].equals("X") && map[i][1].equals("X") && map[i][2].equals("X")) ||
                        (map[i][1].equals("X") && map[i][2].equals("X") && map[i][3].equals("X")) ||
                        (map[i][2].equals("X") && map[i][3].equals("X") && map[i][4].equals("X")) ||
                        (map[0][i].equals("X") && map[1][i].equals("X") && map[2][i].equals("X")) ||
                        (map[1][i].equals("X") && map[2][i].equals("X") && map[3][i].equals("X")) ||
                        (map[2][i].equals("X") && map[3][i].equals("X") && map[4][i].equals("X"))
                ) {
                    if (map[i][x].equals("*"))  {
                        map[i][x] = "0";
                        break;
                    } else if ((map[x][i].equals("*"))) {
                        map[x][i] = "0";
                        break;
                    } else {
                        if (map[x][y].equals("*")) {
                            map[x][y] = "0";
                            ActivePC = false;
                        }
                    }
                }  else if (map[x][y].equals("*")) {
                    map[x][y] = "0";
                    ActivePC = false;
                }
            }
        }
        System.out.println("Попробуй обыграть меня");
    }

    public static boolean WhoWinner(String remaning, String[][] map) {

        for (int i = 0; i < map.length; i++) {
            if (
                    (map[i][0].equals(remaning) && map[i][1].equals(remaning) && map[i][2].equals(remaning) && map[i][3].equals(remaning)) ||
                    (map[i][1].equals(remaning) && map[i][2].equals(remaning) && map[i][3].equals(remaning) && map[i][4].equals(remaning)) ||
                    (map[0][i].equals(remaning) && map[1][i].equals(remaning) && map[2][i].equals(remaning) && map[3][i].equals(remaning)) ||
                    (map[1][i].equals(remaning) && map[2][i].equals(remaning) && map[3][i].equals(remaning) && map[4][i].equals(remaning))
            ) {
                        System.out.println("Победил " + remaning);
                        return true;
            } else if (
                    (map[0][0].equals(remaning) && map[1][1].equals(remaning) && map[2][2].equals(remaning) && map[3][3].equals(remaning)) ||
                    (map[1][1].equals(remaning) && map[2][2].equals(remaning) && map[3][3].equals(remaning) && map[4][4].equals(remaning)) ||
                    (map[1][0].equals(remaning) && map[2][1].equals(remaning) && map[3][2].equals(remaning) && map[4][3].equals(remaning)) ||
                    (map[0][1].equals(remaning) && map[1][2].equals(remaning) && map[2][3].equals(remaning) && map[3][4].equals(remaning))

            ) {
                System.out.println("Победил " + remaning);
                return true;
            } else if (
                    (map[0][4].equals(remaning) && map[1][3].equals(remaning) && map[2][2].equals(remaning) && map[3][1].equals(remaning)) ||
                    (map[1][3].equals(remaning) && map[2][2].equals(remaning) && map[3][1].equals(remaning) && map[4][0].equals(remaning)) ||
                    (map[0][3].equals(remaning) && map[1][2].equals(remaning) && map[2][1].equals(remaning) && map[3][0].equals(remaning)) ||
                    (map[1][4].equals(remaning) && map[2][3].equals(remaning) && map[3][2].equals(remaning) && map[4][1].equals(remaning))
            ) {
                System.out.println("Победил " + remaning);
                return true;
            } else return false;
        } return false;
    }

    public static boolean noWinner(String[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                if (map[i][j] == "*") {
                    return false;
                }
            }
        }
        System.out.println("Ну что, ты не плох");
        return true;
    }

    public static void PrintMap(String[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                System.out.print(map[i][j] + " ");
            }
        System.out.println();
        }
    }

    public static String[][] FildStar(int size){

        String[][] map = new String[size][size];

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                map[i][j] = "*";
            }
        }
        return map;
    }
}