import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class query_board {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = null;
        PrintWriter out = null;
        try {
            scanner = new Scanner(new File(args[0]));
            out = new PrintWriter(System.out);
            Interpreter interpreter = new Interpreter(256);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                interpreter.execute(line, out);
            }
        } finally {
            if (scanner != null) {
                scanner.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }

}

class Interpreter {

    private Board board;

    public Interpreter(int boardSize) {
        this.board = new Board(boardSize);
    }

    public void execute(String line, PrintWriter out) {
        String[] tokens = line.split(" ");
        Operation operation = parseOperation(tokens);
        int[] arguments = parseArguments(tokens);
        operation.execute(board, arguments, out);
    }

    private Operation parseOperation(String[] tokens) {
        try {
            return (Operation) Class.forName(tokens[0] + "Operation").newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private int[] parseArguments(String[] tokens) {
        int[] arguments = new int[tokens.length - 1];
        for (int i = 1; i < tokens.length; i++) {
            arguments[i - 1] = Integer.parseInt(tokens[i]);
        }
        return arguments;
    }
}

interface Operation {
    void execute(Board board, int[] args, PrintWriter out);
}

class SetRowOperation implements Operation {

    public void execute(Board board, int[] args, PrintWriter out) {
        int row = args[0];
        int value = args[1];
        board.setRow(row, value);
    }

}

class SetColOperation implements Operation {

    public void execute(Board board, int[] args, PrintWriter out) {
        int col = args[0];
        int value = args[1];
        board.setCol(col, value);
    }
}

class QueryRowOperation implements Operation {

    public void execute(Board board, int[] args, PrintWriter out) {
        int row = args[0];
        out.println(board.queryRow(row));
    }
}


class QueryColOperation implements Operation {

    public void execute(Board board, int[] args, PrintWriter out) {
        int col = args[0];
        out.println(board.queryCol(col));
    }
}

class Board {

    private int[][] matrix;

    public Board(int size) {
        matrix = new int[size][size];
    }

    public void setRow(int row, int value) {
        for (int col = 0; col < matrix.length; col++) {
            matrix[row][col] = value;
        }
    }

    public void setCol(int col, int value) {
        for (int row = 0; row < matrix.length; row++) {
            matrix[row][col] = value;
        }
    }

    public int queryRow(int row) {
        int sum = 0;
        for (int col = 0; col < matrix.length; col++) {
            sum += matrix[row][col];
        }
        return sum;
    }

    public int queryCol(int col) {
        int sum = 0;
        for (int row = 0; row < matrix.length; row++) {
            sum += matrix[row][col];
        }
        return sum;
    }
}

