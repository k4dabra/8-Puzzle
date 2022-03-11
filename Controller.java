import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Controller {
    static Scanner scan = new Scanner(System.in);

    public static Integer[][] fillPuzzle() {
        Integer puzzle[][] = {{9,9,9},{9,9,9},{9,9,9}};
        Integer chosenNumber = 0;
        
        for (int i = 0; i < puzzle.length; i++) {
            for (int j = 0; j < puzzle[i].length; j++) {
                do {
                    System.out.print("Enter the "+(j+1)+"° number of the "+(i+1)+"° line: ");
                    chosenNumber = scan.nextInt();
                    if (!validateChosenNumber(puzzle, chosenNumber)) {
                        System.out.println("\n---> ENTER A VALID NUMBER!\n");
                    }
                } while (!validateChosenNumber(puzzle, chosenNumber));
                puzzle[i][j] = chosenNumber;
            }
            System.out.println();
        }

        return puzzle;
    }

    public static boolean validateChosenNumber(Integer puzzle[][], Integer chosenNumber) {
        for (int i = 0; i < puzzle.length; i++) {
            for (int j = 0; j < puzzle[i].length; j++) {
                if (chosenNumber == puzzle[i][j] || chosenNumber < 0 || chosenNumber > 8) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void printPuzzle(Integer puzzle[][]) {
        System.out.println();
        for (int i = 0; i < puzzle.length; i++) {
            for (int j = 0; j < puzzle[i].length; j++) {
                if (puzzle[i][j] != 0) {
                    System.out.print("\t"+puzzle[i][j]);
                } else {
                    System.out.print("\t");
                }
            }
            System.out.println("\n\n");
        }
    }

    public static HashMap<Character, Integer> getBlankPositionXY(Integer puzzle[][]) {
        HashMap<Character, Integer> xyBlankPosition = new HashMap<Character, Integer>();

        for (int i = 0; i < puzzle.length; i++) {
            xyBlankPosition.put('x', i);
            xyBlankPosition.put('y', Arrays.asList(puzzle[i]).indexOf(0));
            if (xyBlankPosition.get('y') != -1) {
                break;
            }
        }

        return xyBlankPosition;
    }

    public static Character readMove() {
        Character move;

        do {
            System.out.println("w - up");
            System.out.println("s - down");
            System.out.println("a - left");
            System.out.println("d - righ");
            System.out.print("Choose your movement: ");
            move = scan.next().charAt(0);
            move = Character.toLowerCase(move);
            if (!validateMoveOption(move)) {
                System.out.println("\n---> CHOOSE A VALID MOVEMENT!\n");
            }
        } while (!validateMoveOption(move));

        return move;
    }

    public static boolean validateMoveOption(Character move) {
        String possibleMoves = "wasd";

        if (possibleMoves.contains(move.toString())) {
            return true;
        } else {
            return false;
        }
    }

    public static void movePieces(Character move, HashMap<Character, Integer> xyBlankPosition, Integer[][] puzzle) {
        Integer valueMoved;

        switch (move) {
            case 'w':
                if (validateEdges(xyBlankPosition.get('x') - 1)) {
                    xyBlankPosition.put('x', xyBlankPosition.get('x') - 1);
                    valueMoved = puzzle[xyBlankPosition.get('x')][xyBlankPosition.get('y')];
                    puzzle[xyBlankPosition.get('x')][xyBlankPosition.get('y')] = 0;
                    puzzle[xyBlankPosition.get('x') + 1][xyBlankPosition.get('y')] = valueMoved;
                } else {
                    System.out.println("\n---> YOU HIT THE EDGE!\n");
                }
                break;
            case 'a':
                if (validateEdges(xyBlankPosition.get('y') - 1)) {
                    xyBlankPosition.put('y', xyBlankPosition.get('y') - 1);
                    valueMoved = puzzle[xyBlankPosition.get('x')][xyBlankPosition.get('y')];
                    puzzle[xyBlankPosition.get('x')][xyBlankPosition.get('y')] = 0;
                    puzzle[xyBlankPosition.get('x')][xyBlankPosition.get('y') + 1] = valueMoved;
                } else {
                    System.out.println("\n---> YOU HIT THE EDGE!\n");
                }
                break;
            case 's':
                if (validateEdges(xyBlankPosition.get('x') + 1)) {
                    xyBlankPosition.put('x', xyBlankPosition.get('x') + 1);
                    valueMoved = puzzle[xyBlankPosition.get('x')][xyBlankPosition.get('y')];
                    puzzle[xyBlankPosition.get('x')][xyBlankPosition.get('y')] = 0;
                    puzzle[xyBlankPosition.get('x') - 1][xyBlankPosition.get('y')] = valueMoved;
                } else {
                    System.out.println("\n---> YOU HIT THE EDGE!\n");
                }
                break;
            case 'd':
                if (validateEdges(xyBlankPosition.get('y') + 1)) {
                    xyBlankPosition.put('y', xyBlankPosition.get('y') + 1);
                    valueMoved = puzzle[xyBlankPosition.get('x')][xyBlankPosition.get('y')];
                    puzzle[xyBlankPosition.get('x')][xyBlankPosition.get('y')] = 0;
                    puzzle[xyBlankPosition.get('x')][xyBlankPosition.get('y') - 1] = valueMoved;
                } else {
                    System.out.println("\n---> YOU HIT THE EDGE!\n");
                }
                break;
        }
    }

    public static boolean validateEdges(Integer position) {
        if (position < 0 || position > 2) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean checkWinner(Integer puzzle[][]) {
        Integer previousNumber = 0;
        Integer currentNumber = 0;

        for (int i = 0; i < puzzle.length; i++) {
            if (i != 0) {
                previousNumber = puzzle[i-1][puzzle[i-1].length-1];
            }
            for (int j = 0; j < puzzle[i].length; j++) {
                currentNumber = puzzle[i][j];
                if (currentNumber == 0 && previousNumber == 8) {
                    return true;
                } else if (currentNumber != previousNumber+1) {
                    return false;
                } else {
                    previousNumber++;
                }
            }
        }
        return true;
    }

    public static Integer addMovement(Integer movementsCount) {
        return movementsCount+1;
    }

    public static void puzzleCompletion(Integer movementsCount) {
        System.out.println("### CONGRATULATIONS!");
        System.out.println("### YOU FINISHED THE PUZZLE!\n");
        System.out.println("### TOTAL MOVES: "+movementsCount+"\n");
    }
}
