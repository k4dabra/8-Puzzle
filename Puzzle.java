import java.util.HashMap;

public class Puzzle {
    public static void main(String[] args) {

        // variables
        Integer puzzle[][];
        HashMap<Character, Integer> xyBlankPosition;
        Character move = 'x';
        Integer movementsCount = 0;

        // filling in the puzzle
        puzzle = Controller.fillPuzzle();

        // printing the puzzle
        Controller.printPuzzle(puzzle);

        // checking if the puzzle is in order
        while (!Controller.checkWinner(puzzle)) {

            // getting blank position x,y
            xyBlankPosition = Controller.getBlankPositionXY(puzzle);

            // reading the movement
            move = Controller.readMove();

            // moving the pieces
            Controller.movePieces(move, xyBlankPosition, puzzle);

            // +1 movement
            movementsCount = Controller.addMovement(movementsCount);

            // printing the puzzle
            Controller.printPuzzle(puzzle);
        }

        // Puzzle completion
        Controller.puzzleCompletion(movementsCount);
    }
}
