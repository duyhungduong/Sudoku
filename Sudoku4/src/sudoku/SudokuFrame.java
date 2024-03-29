package sudoku;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SudokuFrame extends JFrame {

    private JPanel buttonSelectionPanel;
    private SudokuPanel sPanel;
    private SudokuPuzzleType type;

    public SudokuFrame() {
        //initComponent();

        this.setTitle("Sudoku");
        JPanel windowPanel = new JPanel();
        windowPanel.setLayout(new FlowLayout());
        windowPanel.setPreferredSize(new Dimension(900, 750));

        buttonSelectionPanel = new JPanel();
        buttonSelectionPanel.setPreferredSize(new Dimension(1000, 600));

        sPanel = new SudokuPanel();

        windowPanel.add(sPanel);
        windowPanel.add(buttonSelectionPanel);
        this.add(windowPanel);

        rebuildInterface(SudokuPuzzleType.NINEBYNINE, 26);
    }

    public SudokuFrame(SudokuPuzzle puzzle) {
        //initComponent();

        this.setTitle("Sudoku");
        JPanel windowPanel = new JPanel();
        windowPanel.setLayout(new FlowLayout());
        windowPanel.setPreferredSize(new Dimension(900, 750));

        buttonSelectionPanel = new JPanel();
        buttonSelectionPanel.setPreferredSize(new Dimension(1000, 600));

        sPanel = new SudokuPanel(puzzle);

        windowPanel.add(sPanel);
        windowPanel.add(buttonSelectionPanel);
        this.add(windowPanel);

        rebuildInterface(SudokuPuzzleType.NINEBYNINE, 26);

    }

    public void rebuildInterface(SudokuPuzzleType puzzleType, int fontSize) {
        initComponent(puzzleType);
        SudokuPuzzle generatedPuzzle = new SudokuGenerator().generateRandomSudoku(puzzleType);
        sPanel.newSudokuPuzzle(generatedPuzzle);
        sPanel.setFontSize(fontSize);
        buttonSelectionPanel.removeAll();
        for (String value : generatedPuzzle.getValidValues()) {
            JButton b = new JButton(value);
            int buttonSize = (puzzleType == SudokuPuzzleType.TWELVEBYTWELVE) ? 43 : 50;
            b.setPreferredSize(new Dimension(buttonSize, buttonSize));
            b.addActionListener(sPanel.new NumActionListener());
            buttonSelectionPanel.add(b);
        }
        sPanel.repaint();
        buttonSelectionPanel.revalidate();
        buttonSelectionPanel.repaint();
    }

    private class NewGameListener implements ActionListener {

        private SudokuPuzzleType puzzleType;
        private int fontSize;

        public NewGameListener(SudokuPuzzleType puzzleType, int fontSize) {
            this.puzzleType = puzzleType;
            this.fontSize = fontSize;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            rebuildInterface(puzzleType, fontSize);
        }
    }

    private void solveSudoku() {
        SudokuPuzzle puzzle = sPanel.getPuzzle(); // Get the current puzzle from the panel
        int[][] board = new int[puzzle.getNumRows()][puzzle.getNumColumns()];

        // Convert the puzzle to a 2D integer array
        for (int row = 0; row < puzzle.getNumRows(); row++) {
            for (int col = 0; col < puzzle.getNumColumns(); col++) {
                String value = puzzle.getValue(row, col);
                if (!value.isEmpty()) {
                    board[row][col] = Integer.parseInt(value);
                } else {
                    board[row][col] = 0;
                }
            }
        }

        SudokuSolver solver = new SudokuSolver();
        if (solver.solveSudoku(board)) {
            // Display the solution using a JOptionPane
            StringBuilder solution = new StringBuilder("Solution:\n");
            for (int row = 0; row < puzzle.getNumRows(); row++) {
                for (int col = 0; col < puzzle.getNumColumns(); col++) {
                    solution.append(board[row][col]).append(" ");
                }
                solution.append("\n");
            }
            JOptionPane.showMessageDialog(this, solution.toString(), "Sudoku Solution", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "No solution found.", "Sudoku Solution", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void solveSudoku1() {
        SudokuPuzzle puzzle = sPanel.getPuzzle(); // Get the current puzzle from the panel
        int[][] board = new int[puzzle.getNumRows()][puzzle.getNumColumns()];

        // Convert the puzzle to a 2D integer array
        for (int row = 0; row < puzzle.getNumRows(); row++) {
            for (int col = 0; col < puzzle.getNumColumns(); col++) {
                String value = puzzle.getValue(row, col);
                if (!value.isEmpty()) {
                    board[row][col] = Integer.parseInt(value);
                } else {
                    board[row][col] = 0;
                }
            }
        }

        SudokuSolver solver = new SudokuSolver();
        if (solver.solveSudokuElon(board, SudokuPuzzleType.SIXBYSIX)) {
            // Display the solution using a JOptionPane
            StringBuilder solution = new StringBuilder("Solution:\n");
            for (int row = 0; row < puzzle.getNumRows(); row++) {
                for (int col = 0; col < puzzle.getNumColumns(); col++) {
                    solution.append(board[row][col]).append(" ");
                }
                solution.append("\n");
            }
            JOptionPane.showMessageDialog(this, solution.toString(), "Sudoku Solution", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "No solution found.", "Sudoku Solution", JOptionPane.INFORMATION_MESSAGE);
        }
    }


    private void initComponent(SudokuPuzzleType puzzleType) {
        type = puzzleType;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(900, 750));
        this.setMaximumSize(new Dimension(900, 750));

        setLocationRelativeTo(null);
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("Game");
        JMenu help = new JMenu("Help");

        JMenuItem homepage = new JMenuItem("Home Page");
        JMenuItem exit = new JMenuItem("Exit");

        JMenu newGame = new JMenu("New Game");
        JMenu solve = new JMenu("Solve");
        JMenuItem sixBySixGame = new JMenuItem("Easy");
        sixBySixGame.addActionListener(new NewGameListener(SudokuPuzzleType.SIXBYSIX, 30));
        JMenuItem nineByNineGame = new JMenuItem("Normal");
        nineByNineGame.addActionListener(new NewGameListener(SudokuPuzzleType.NINEBYNINE, 26));
        JMenuItem twelveByTwelveGame = new JMenuItem("Hard");
        twelveByTwelveGame.addActionListener(new NewGameListener(SudokuPuzzleType.TWELVEBYTWELVE, 12));

        JMenuItem savegame = new JMenuItem("Save");

        help.add(solve);

        help.add(homepage);
        help.add(exit);

        newGame.add(sixBySixGame);
        newGame.add(nineByNineGame);
        newGame.add(twelveByTwelveGame);
        //newGame.add(sixteenBySizteenGame);
        file.add(newGame);
        file.add(savegame);
        menuBar.add(file);
        menuBar.add(help);
        this.setJMenuBar(menuBar);

        JMenuItem six = new JMenuItem("6x6");
        JMenuItem nine = new JMenuItem("9x9");


        if (puzzleType == SudokuPuzzleType.SIXBYSIX) {
            solve.add(six);
        } else if (puzzleType == SudokuPuzzleType.NINEBYNINE) {
            solve.add(nine);
        }

        nine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                solveSudoku();
            }
        });

        six.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                solveSudoku1();
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        homepage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Sudoku.showHomePage();
            }
        });

        savegame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mode = "Normal 9x9";
                if(type == SudokuPuzzleType.NINEBYNINE) mode = "Nomarl 9x9";
                else if (type == SudokuPuzzleType.SIXBYSIX) mode = "Easy 6x6";
                else mode = "Hard";
                String state = sPanel.checkIfFilled();
                Sudoku.saveGame(state, mode);
            }
        });

    }

    public SudokuPuzzle returnPuzzle() {
        return sPanel.getPuzzle();
    }

    public String[][] returnBoard() {
        return sPanel.getBoard();
    }
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(900, 750));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SudokuFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
