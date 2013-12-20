import java.util.Scanner;

public class Main {
	
	private static Scanner scanner;

	public static void main(String[] args) {
		scanner = new Scanner(System.in);
		String puzzle = PuzzleGenerator.doGenerate();
		scanner.nextLine();
		PuzzleSolver.doSolve(puzzle);
	}
	
}
