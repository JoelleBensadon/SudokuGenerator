package SudokuPackage;

import java.util.Random;

public class SudokuGenerator {

    private final int[][] board = new int[9][9];
    private final Random random = new Random();

    public int[][] generateBoard() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                board[row][col] = 0;
            }
        }

        fillBoard(0, 0);
        return board;
    }

    private boolean fillBoard(int row, int col) {
        if (row == 9) {
            return true;
        }

        int nextRow = col == 8 ? row + 1 : row;
        int nextCol = col == 8 ? 0 : col + 1;

        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        shuffle(numbers);

        for (int number : numbers) {
            if (isValid(row, col, number)) {
                board[row][col] = number;

                if (fillBoard(nextRow, nextCol)) {
                    return true;
                }

                board[row][col] = 0;
            }
        }

        return false;
    }

    private boolean isValid(int row, int col, int number) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == number || board[i][col] == number) {
                return false;
            }
        }

        int boxRow = row - row % 3;
        int boxCol = col - col % 3;

        for (int r = boxRow; r < boxRow + 3; r++) {
            for (int c = boxCol; c < boxCol + 3; c++) {
                if (board[r][c] == number) {
                    return false;
                }
            }
        }

        return true;
    }

    private void shuffle(int[] numbers) {
        for (int i = numbers.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);

            int temp = numbers[i];
            numbers[i] = numbers[j];
            numbers[j] = temp;
        }
    }

    public void removeNumbers(int cellsToRemove) {
        if (cellsToRemove < 0 || cellsToRemove > 81) {
            throw new IllegalArgumentException(
                    "Number of cells to remove must be between 0 and 81"
            );
        }

        int removed = 0;

        while (removed < cellsToRemove) {
            int row = random.nextInt(9);
            int col = random.nextInt(9);

            if (board[row][col] != 0) {
                board[row][col] = 0;
                removed++;
            }
        }
    }

    public void printBoard() {
        for (int row = 0; row < 9; row++) {
            if (row % 3 == 0 && row != 0) {
                System.out.println("------+-------+------");
            }

            for (int col = 0; col < 9; col++) {
                if (col % 3 == 0 && col != 0) {
                    System.out.print("| ");
                }

                if (board[row][col] == 0) {
                    System.out.print(". ");
                } else {
                    System.out.print(board[row][col] + " ");
                }
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {
        SudokuGenerator generator = new SudokuGenerator();

        generator.generateBoard();

        System.out.println("COMPLETED SUDOKU:");
        generator.printBoard();

        generator.removeNumbers(40);

        System.out.println("\nSUDOKU PUZZLE:");
        generator.printBoard();
    }
}