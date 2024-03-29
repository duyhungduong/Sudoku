package sudoku;

public class SudokuSolver {

    public boolean solveSudoku(int[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == 0) {
                    for (int num = 1; num <= 9; num++) {
                        if (isValidMove(board, row, col, num)) {
                            board[row][col] = num;
                            if (solveSudoku(board)) {
                                return true;
                            } else {
                                board[row][col] = 0; // Quay lui nếu không tìm được giải pháp
                            }
                        }
                    }
                    return false; // Không tìm thấy giải pháp cho ô hiện tại
                }
            }
        }
        return true; // Tất cả các ô đã được điền
    }

    private boolean isValidMove(int[][] board, int row, int col, int num) {
        // Kiểm tra hàng và cột
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num || board[i][col] == num) {
                return false;
            }
        }
        // Kiểm tra ô vuông 3x3
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i + startRow][j + startCol] == num) {
                    return false;
                }
            }
        }
        return true;
    }
    

    public boolean solveSudokuElon(int[][] board, SudokuPuzzleType puzzleType) {
//        if (puzzleType != SudokuPuzzleType.ELON) {
//            throw new IllegalArgumentException("Invalid puzzle type");
//        }

        for (int row = 0; row < puzzleType.getRows(); row++) {
            for (int col = 0; col < puzzleType.getColumns(); col++) {
                if (board[row][col] == 0) {
                    for (int num = 1; num <= puzzleType.getValidValues().length; num++) {
                        if (isValidMove(board, row, col, num, puzzleType)) {
                            board[row][col] = num;
                            if (solveSudokuElon(board, puzzleType)) {
                                return true;
                            } else {
                                board[row][col] = 0; // Backtrack
                            }
                        }
                    }
                    return false; // No solution found
                }
            }
        }
        return true; // All cells are filled
    }

    private boolean isValidMove(int[][] board, int row, int col, int num, SudokuPuzzleType puzzleType) {
        // Check row
        for (int i = 0; i < puzzleType.getColumns(); i++) {
            if (board[row][i] == num) {
                return false;
            }
        }

        // Check column
        for (int i = 0; i < puzzleType.getRows(); i++) {
            if (board[i][col] == num) {
                return false;
            }
        }

        // Check box
        int boxRowStart = row - row % puzzleType.getBoxHeight();
        int boxColStart = col - col % puzzleType.getBoxWidth();
        for (int i = 0; i < puzzleType.getBoxHeight(); i++) {
            for (int j = 0; j < puzzleType.getBoxWidth(); j++) {
                if (board[i + boxRowStart][j + boxColStart] == num) {
                    return false;
                }
            }
        }

        return true;
    }
}
