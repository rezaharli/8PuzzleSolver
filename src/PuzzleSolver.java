import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class PuzzleSolver {
	private static LinkedList<String> listPuzzle = new LinkedList<>();
	private static Map<String, String> rekamanParent = new HashMap<>();
	private static Map<String, String> rekamanPergerakan = new HashMap<>();

	private static String soal;

	public static void doSolve(String soal) {
		PuzzleSolver.soal = soal;
		add(soal, null, "");
		while (!listPuzzle.isEmpty()) {
			Puzzle currentPuzzle = new Puzzle(listPuzzle.removeFirst());
			
			if (currentPuzzle.isSolved()) 
				break;
			
			currentPuzzle.setPosisiNol();
			String nextPuzzle;

			if (currentPuzzle.posisiNol != 0 && currentPuzzle.posisiNol != 1 && currentPuzzle.posisiNol != 2) {
				nextPuzzle = currentPuzzle.geserKeBawah();
				add(nextPuzzle, currentPuzzle.data, nextPuzzle.charAt(currentPuzzle.posisiNol) + " ke bawah");
			}
			if (currentPuzzle.posisiNol != 2 && currentPuzzle.posisiNol != 5 && currentPuzzle.posisiNol != 8) {
				nextPuzzle = currentPuzzle.geserKeKiri();
				add(nextPuzzle, currentPuzzle.data, nextPuzzle.charAt(currentPuzzle.posisiNol) + " ke kiri");
			}
			if (currentPuzzle.posisiNol != 6 && currentPuzzle.posisiNol != 7 && currentPuzzle.posisiNol != 8) {
				nextPuzzle = currentPuzzle.geserKeAtas();
				add(nextPuzzle, currentPuzzle.data, nextPuzzle.charAt(currentPuzzle.posisiNol) + " ke atas");
			}
			if (currentPuzzle.posisiNol != 0 && currentPuzzle.posisiNol != 3 && currentPuzzle.posisiNol != 6) {
				nextPuzzle = currentPuzzle.geserKeKanan();
				add(nextPuzzle, currentPuzzle.data, nextPuzzle.charAt(currentPuzzle.posisiNol) + " ke kanan");
			}
		}
		printSolusi();
	}

	private static void add(String nextPuzzle, String parent, String pergerakan) {
		if (!rekamanParent.containsKey(nextPuzzle)) {
			listPuzzle.add(nextPuzzle);
			rekamanParent.put(nextPuzzle, parent);
			rekamanPergerakan.put(nextPuzzle, pergerakan);
		}
	}

	private static void printSolusi() {
		LinkedList<String> tempList = new LinkedList<>();
		String current = "123456780";
		
		while (current != soal) {
			tempList.addFirst(current);
			current = rekamanParent.get(current);
		}
		
		for (int i = 1; !tempList.isEmpty(); i++) {
			Puzzle currentPuzzle = new Puzzle(tempList.removeFirst());
			System.out.print("Langkah ke " + i + " : "
					+ rekamanPergerakan.get(currentPuzzle.data) + "\n");
			System.out.println(currentPuzzle.getMatriks());
		}
		System.out.println("Solved.");
	}
}
