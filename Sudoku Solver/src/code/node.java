package code;

public class node {

	private int data;
	private int boxID;
	private node up;
	private node down;
	private node left;
	private node right;
	private boolean[] possible = new boolean[10];

	public node(){
			this.data = 0;
			up = null;
			down = null;
			left = null;
			right = null;
			for(int x = 0; x < 10; x ++)
			possible[x] = true;
			boxID = 0;
		}

	public int getBoxID() {
		return boxID;
	}

	public void setBoxID(int boxID) {
		this.boxID = boxID;
	}

	public void setImpossible(int data) {
		possible[data] = false;
	}

	public int getNumberOfPossibilities() {
		int count = 0;
		for (int x = 1; x < 10; x++)
			if (possible[x] == true)
				count++;
		return count;

	}

	public void listPossibilities() {
		for (int x = 1; x < 10; x++)
			if (possible[x] == true)
				System.out.print(x + " ");
		System.out.println();
	}

	public boolean getPossible(int data) {
		return possible[data];
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public node getUp() {
		return up;
	}

	public void setUp(node up) {
		this.up = up;
	}

	public node getDown() {
		return down;
	}

	public void setDown(node down) {
		this.down = down;
	}

	public node getLeft() {
		return left;
	}

	public void setLeft(node left) {
		this.left = left;
	}

	public node getRight() {
		return right;
	}

	public void setRight(node right) {
		this.right = right;
	}
}
