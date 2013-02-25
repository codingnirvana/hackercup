package wordracer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Tester {
    public static void main(String[] args) {
        char letter = (char) ('A' + new Random().nextInt(25));

        char[][] firstBoard = new char[7][7];
        char[][] secondBoard = new char[7][7];

        DemoWordRacer wRacer = new DemoWordRacer();
        DemoWordRacer dRacer = new DemoWordRacer();

        wRacer.initGameBoard(letter);
        dRacer.initGameBoard(letter);
        firstBoard[3][3] = letter;
        secondBoard[3][3] = letter;


        for (int i = 0; i < 24; i++) {
            DemoWordRacer.Result result = wRacer.pickLetter();
            int pos1 = result.Position;
            char letter1 = result.Letter;
            firstBoard[pos1 / 7][pos1 % 7] = letter1;

            int pos2 = dRacer.pickPosition(letter1);
            secondBoard[pos2 / 7][pos2 % 7] = letter1;

            result = dRacer.pickLetter();
            pos2 = result.Position;
            char letter2 = result.Letter;
            secondBoard[pos2 / 7][pos2 % 7] = letter2;

            pos1 = wRacer.pickPosition(letter2);
            firstBoard[pos1 / 7][pos1 % 7] = letter2;
        }


        System.out.println("Opponents board");
        calculateScore(firstBoard);

        System.out.println("Your Board");
        calculateScore(secondBoard);

//        System.out.println("Example Boards");
//        calculateScore(new char[][]{new char[]{'A', 'B', 'A', 'L', 'O', 'N', 'E'},
//                new char[]{'X', 'I', 'B', 'I', 'U', 'L', 'A'},
//                new char[]{'I', 'Q', 'I', 'P', 'T', 'I', 'C'},
//                new char[]{'S', 'N', 'O', 'B', 'W', 'A', 'Y'},
//                new char[]{'E', 'L', 'S', 'A', 'O', 'N', 'M'},
//                new char[]{'S', 'H', 'E', 'B', 'R', 'A', 'A'},
//                new char[]{'W', 'E', 'S', 'Y', 'K', 'Y', 'D'}});
//
//        calculateScore(new char[][]{new char[]{'E', 'P', 'M', 'J', 'J', 'Q', 'J'},
//                new char[]{'E', 'A', 'E', 'U', 'U', 'U', 'U'},
//                new char[]{'Y', 'B', 'J', 'M', 'M', 'A', 'D'},
//                new char[]{'Q', 'U', 'I', 'P', 'P', 'E', 'D'},
//                new char[]{'E', 'L', 'E', 'O', 'O', 'R', 'E'},
//                new char[]{'Q', 'U', 'A', 'F', 'F', 'E', 'R'},
//                new char[]{'O', 'M', 'S', 'F', 'F', 'S', 'S'}});
//
//        calculateScore(new char[][]{new char[]{'R', 'D', 'O', 'U', 'S', 'E', 'S'},
//                new char[]{'E', 'R', 'A', 'J', 'I', 'F', 'U'},
//                new char[]{'S', 'U', 'B', 'Q', 'F', 'O', 'F'},
//                new char[]{'E', 'M', 'O', 'P', 'E', 'D', 'Q'},
//                new char[]{'E', 'L', 'M', 'E', 'M', 'E', 'U'},
//                new char[]{'J', 'Y', 'U', 'P', 'M', 'U', 'A'},
//                new char[]{'P', 'J', 'F', 'A', 'E', 'J', 'Q'}});
//
//        calculateScore(new char[][]{new char[]{'F', 'U', 'T', 'T', 'O', 'C', 'K'},
//                new char[]{'U', 'N', 'E', 'Q', 'U', 'A', 'L'},
//                new char[]{'T', 'R', 'A', 'M', 'M', 'E', 'L'},
//                new char[]{'H', 'O', 'L', 'Y', 'D', 'A', 'Y'},
//                new char[]{'O', 'V', 'E', 'R', 'B', 'I', 'D'},
//                new char[]{'R', 'E', 'B', 'A', 'T', 'E', 'S'},
//                new char[]{'K', 'N', 'O', 'Z', 'J', 'J', 'I'}});
//
//        calculateScore(new char[][]{new char[]{'F', 'U', 'Z', 'Z', 'I', 'L', 'Y'},
//                new char[]{'U', 'N', 'B', 'O', 'W', 'E', 'D'},
//                new char[]{'T', 'R', 'A', 'P', 'P', 'E', 'R'},
//                new char[]{'H', 'A', 'U', 'N', 'T', 'E', 'R'},
//                new char[]{'O', 'V', 'E', 'R', 'L', 'A', 'P'},
//                new char[]{'R', 'E', 'G', 'A', 'L', 'E', 'D'},
//                new char[]{'C', 'L', 'A', 'W', 'G', 'J', 'A'}});

    }

    static void printBoardWithTotal(char[][] board, int[][] score) {
        int points = 0;

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(board[i][j] + "   ");
            }
            System.out.print(score[0][i]);
            points += score[0][i];
            System.out.println();

        }

        for (int i = 0; i < 7; i++) {
            System.out.print(String.format("%1$-" + 4 + "s", score[1][i]));
            points += score[1][i];
        }
        System.out.println(points);

    }

    static void calculateScore(char[][] board) {
        int[] score = new int[]{0, 0, 1, 2, 3, 5, 8, 13};

        List<String> words = new ArrayList<String>();
        try {
            Scanner scanner = new Scanner(new File("/tmp/words.dat"));
            while (scanner.hasNext()) {
                words.add(scanner.nextLine());
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException("Please add the words.dat to the tmp directory .. ");
        }


        int[][] total = new int[2][7];
        boolean[][] isTakenH = new boolean[7][7];
        boolean[][] isTakenV = new boolean[7][7];

        for (int length = 7; length >= 2; length--) {
            for (int row = 0; row < 7; row++)
                for (int start = 0; start < 7; start++) {
                    int end = start + length;
                    if (end > 7) break;

                    boolean quit = false;
                    for (int i = start; i < end; i++) {
                        if (isTakenH[row][i]) {
                            quit = true;
                            break;
                        }
                    }
                    if (quit)
                        continue;

                    String word = "";
                    for (int i = start; i < end; i++)
                        word += board[row][i];

                    if (Collections.binarySearch(words, word) > 0) {
                        for (int i = start; i < end; i++)
                            isTakenH[row][i] = true;
                        total[0][row] += score[word.length()];
                    }
                }

            for (int col = 0; col < 7; col++)
                for (int start = 0; start < 7; start++) {
                    int end = start + length;
                    if (end > 7) break;

                    boolean quit = false;
                    for (int i = start; i < end; i++) {
                        if (isTakenV[i][col]) {
                            quit = true;
                            break;
                        }
                    }
                    if (quit)
                        continue;

                    String word = "";
                    for (int i = start; i < end; i++)
                        word += board[i][col];

                    if (Collections.binarySearch(words, word) > 0) {

                        for (int i = start; i < end; i++)
                            isTakenV[i][col] = true;
                        total[1][col] += score[word.length()];
                    }
                }
        }

        printBoardWithTotal(board, total);
    }
}
