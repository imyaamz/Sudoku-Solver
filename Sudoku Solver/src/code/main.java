package code;

import java.io.IOException;

public class main {

	public static void main(String[] args) throws IOException {
		linkedGrid lg = new linkedGrid(9);

		lg.populate();
		lg.display();
		System.out.println("hi");
		lg.solveGrid();
	}
}
