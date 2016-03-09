import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ComputerTerminal {

    private int rows;
    private int cols;
    private char[][] display;

    private int cursorRow;
    private int cursorCol;

    private boolean isOverride;

    public ComputerTerminal(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.display = new char[rows][cols];

        this.cursorRow = 0;
        this.cursorCol = 0;

        this.isOverride = true;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public int getCursorRow() {
        return cursorRow;
    }

    public void setCursorRow(int cursorRow) {
        this.cursorRow = cursorRow;
    }

    public int getCursorCol() {
        return cursorCol;
    }

    public void setCursorCol(int cursorCol) {
        this.cursorCol = cursorCol;
    }

    public boolean isOverride() {
        return isOverride;
    }

    public void setOverride(boolean override) {
        isOverride = override;
    }

    public void setAt(int row, int col, char ch) {
        this.display[row][col] = ch;
    }

    public char getAt(int row, int col) {
        return this.display[row][col];
    }

    public void interpret(String input) {
        CommandReader reader = new CommandReader(input);
        Command command;
        while ((command = reader.nextCommand()) != null) {
            command.execute(this);
        }
    }

    public void print() {
        StringBuilder builder = new StringBuilder();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                builder.append(display[row][col]);
            }
            builder.append('\n');
        }
        System.out.println(builder);
    }

    public static void main(String[] args) throws IOException {
        if (args.length >= 1) {
            try (BufferedReader scanner = new BufferedReader(new FileReader(args[0]))) {
                ComputerTerminal terminal = new ComputerTerminal(10, 10);
                String line;
                while ((line = scanner.readLine()) != null) {
                    terminal.interpret(line);
                }
                terminal.print();
            }
        }
    }

}

class CommandReader {
    private char[] input;
    private int pos;

    public CommandReader(String input) {
        this.input = input.toCharArray();
        this.pos = -1;
    }

    public Command nextCommand() {
        if (advance()) {
            char current = input[pos];
            if (current == '^') {
                if (!advance()) {
                    throw new IllegalArgumentException(String.format("Incomplete command sequence starting at position %d", pos));
                }
                char next = input[pos];
                if (next == '^') {
                    return new EnterCharacterCommand(next);
                } else {
                    if (Character.isDigit(next)) {
                        if (!advance()) {
                            throw new IllegalArgumentException(String.format("Incomplete command sequence starting at position %d", pos));
                        }
                        char nextNext = input[pos];

                        int newRow = Character.digit(next, 10);
                        int newCol = Character.digit(nextNext, 10);

                        return new MoveCursorCommand(newRow, newCol);
                    } else {
                        switch (next) {
                            case 'c':
                                return new ClearCommand();
                            case 'h':
                                return new MoveCursorCommand(0, 0);
                            case 'b':
                                return new MoveCursorAtBeginningOfLineCommand();
                            case 'd':
                                return new MoveCursorDownOneRowCommand();
                            case 'u':
                                return new MoveCursorUpOneRowCommand();
                            case 'l':
                                return new MoveCursorLeftOneColumnCommand();
                            case 'r':
                                return new MoveCursorRightOneColumnCommand();
                            case 'e':
                                return new EraseToTheEndOfLineCommand();
                            case 'i':
                                return new EnterInsertModeCommand();
                            case 'o':
                                return new EnterOverrideModeCommand();
                            default:
                                throw new IllegalArgumentException(String.format("Unknown command at position %d", pos));
                        }
                    }
                }
            } else {
                return new EnterCharacterCommand(current);
            }
        }
        return null;
    }

    private boolean advance() {
        if (pos < input.length - 1) {
            pos++;
            return true;
        }
        return false;
    }
}

abstract class Command {
    public abstract void execute(ComputerTerminal terminal);
}

class ClearCommand extends Command {
    @Override
    public void execute(ComputerTerminal terminal) {
        for (int row = 0; row < terminal.getRows(); row++) {
            for (int col = 0; col < terminal.getCols(); col++) {
                terminal.setAt(row, col, ' ');
            }
        }
    }
}

class MoveCursorCommand extends Command {
    private int newRow;
    private int newCol;

    MoveCursorCommand() {
        this.newRow = 0;
        this.newCol = 0;
    }

    MoveCursorCommand(int newRow, int newCol) {
        this.newRow = newRow;
        this.newCol = newCol;
    }

    @Override
    public void execute(ComputerTerminal terminal) {
        if ((newRow >= 0 && newRow <= terminal.getRows() - 1) &&
                (newCol >= 0 && newCol <= terminal.getCols() - 1)) {
            terminal.setCursorRow(newRow);
            terminal.setCursorCol(newCol);
        }
    }

    public void setCursorPosition(int newRow, int newCol) {
        this.newRow = newRow;
        this.newCol = newCol;
    }
}

class MoveCursorAtBeginningOfLineCommand extends MoveCursorCommand {
    @Override
    public void execute(ComputerTerminal terminal) {
        setCursorPosition(terminal.getCursorRow(), 0);
        super.execute(terminal);
    }
}

class MoveCursorDownOneRowCommand extends MoveCursorCommand {
    @Override
    public void execute(ComputerTerminal terminal) {
        setCursorPosition(terminal.getCursorRow() + 1, terminal.getCursorCol());
        super.execute(terminal);
    }
}

class MoveCursorUpOneRowCommand extends MoveCursorCommand {
    @Override
    public void execute(ComputerTerminal terminal) {
        setCursorPosition(terminal.getCursorRow() - 1, terminal.getCursorCol());
        super.execute(terminal);
    }
}

class MoveCursorLeftOneColumnCommand extends MoveCursorCommand {
    @Override
    public void execute(ComputerTerminal terminal) {
        setCursorPosition(terminal.getCursorRow(), terminal.getCursorCol() - 1);
        super.execute(terminal);
    }
}

class MoveCursorRightOneColumnCommand extends MoveCursorCommand {
    @Override
    public void execute(ComputerTerminal terminal) {
        setCursorPosition(terminal.getCursorRow(), terminal.getCursorCol() + 1);
        super.execute(terminal);
    }
}

class EraseToTheEndOfLineCommand extends Command {
    @Override
    public void execute(ComputerTerminal terminal) {
        for (int col = terminal.getCursorCol(); col < terminal.getCols(); col++) {
            terminal.setAt(terminal.getCursorRow(), col, ' ');
        }
    }
}

class EnterInsertModeCommand extends Command {
    @Override
    public void execute(ComputerTerminal terminal) {
        terminal.setOverride(false);
    }
}

class EnterOverrideModeCommand extends Command {
    @Override
    public void execute(ComputerTerminal terminal) {
        terminal.setOverride(true);
    }
}

class EnterCharacterCommand extends MoveCursorCommand {
    private char charToEnter;

    EnterCharacterCommand(char charToEnter) {
        this.charToEnter = charToEnter;
    }

    @Override
    public void execute(ComputerTerminal terminal) {
        if (terminal.isOverride()) {
            terminal.setAt(terminal.getCursorRow(), terminal.getCursorCol(), charToEnter);
        } else {
            for (int col = terminal.getCols() - 1; col > terminal.getCursorCol(); col--) {
                char previous = terminal.getAt(terminal.getCursorRow(), col - 1);
                terminal.setAt(terminal.getCursorRow(), col, previous);
            }
            terminal.setAt(terminal.getCursorRow(), terminal.getCursorCol(), charToEnter);
        }

        setCursorPosition(terminal.getCursorRow(), terminal.getCursorCol() + 1);
        super.execute(terminal);
    }
}