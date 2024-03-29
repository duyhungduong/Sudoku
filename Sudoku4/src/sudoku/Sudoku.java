package sudoku;

import javax.swing.JOptionPane;

public class Sudoku {

    private static SudokuHomePage home;
    private static SudokuFrame game;
    private static SudokuSavedGame save;

    public static void startGame() {
        save.setVisible(false);
        home.setVisible(false);
        game.setVisible(true);
    }

    public static void showHomePage() {
        save.setVisible(false);
        game.setVisible(false);
        home.setVisible(true);
    }

    public static void showSavedGame() {
        home.setVisible(false);
        game.setVisible(false);
        save.setVisible(true);
    }

    public static void saveGame(String state, String mode) {
        String playerName = JOptionPane.showInputDialog("!!!\nPlease enter your name:");
        game.setVisible(false);
        showHomePage();
        save.addPlayer(playerName, state, mode);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                home = new SudokuHomePage();
                game = new SudokuFrame();
                save = new SudokuSavedGame();
                home.setVisible(true);
            }
        });
    }
}
