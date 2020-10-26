package code;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class linkedGrid {
	
	private node root;
	private int dimension;

	public linkedGrid(int size) throws IOException {

		// Creating the first node
		this.dimension = size;
		root = new node();
	
		node marker = root;
	
		// Creating the first row
		for (int x = 0; x < dimension - 1; x++) {
			node temp = new node();
			marker.setRight(temp);
			temp.setLeft(marker);
			marker = temp;
		}
	
		node rowMarker = root;
	
		for (int y = 0; y < dimension - 1; y++) {
			node temp = new node();
			rowMarker.setDown(temp);
			temp.setUp(rowMarker);
	
		for (int x = 0; x < dimension - 1; x++) {
			marker = temp;
			temp = new node();
			marker.setRight(temp);
			temp.setLeft(marker);
			temp.getLeft().getUp().getRight().setDown(temp);
			temp.setUp(temp.getLeft().getUp().getRight());
		}
	
		rowMarker = rowMarker.getDown();
	
		}
		setBoxIDs();

	}

	public void display() {
		node rowMarker = root;
		int rowCount = 0;
		int columnCount = 0;
	
		while (rowMarker != null) {
			node temp = rowMarker;
	
		while (temp != null) {
			System.out.print(temp.getData() + " ");
			temp = temp.getRight();
			columnCount++;
		if(columnCount %3 ==0)
			System.out.print("");
		}
		
			System.out.println();
			rowMarker = rowMarker.getDown();
			rowCount++;
			if(rowCount %3 == 0)
			System.out.println("");
		} 
	}
	//============================================================================
	//Method for solving the grid 
	public void solveGrid(){
		//Keeps the loop running while the grid is not solved
		while(stillPossible()){
			node rowMarker = root;
	
			while (rowMarker != null) {
				node temp = rowMarker;
		
			while (temp != null) {
				//Solves the currentnode if the node has a possibility of one
				solvenode(temp);
				temp = temp.getRight();
		
			}
				rowMarker = rowMarker.getDown();
			}
		}
		//displays the grid once it has solved it
		System.out.println("Grid Solved!");
		display();
	}
	
	//Method for checking if the grid is not solved yet
	private boolean stillPossible(){
		node rowMarker = root;

		while (rowMarker != null) {
			node temp = rowMarker;
	
		while (temp != null) {
			//If the current data of that node is equal to one it means it has not been solved
			if(temp.getData() == 0){ 
				//therefore it returns true else it keeps looping till it finds a value 0 or else it returns false
				return true;
			}
			temp = temp.getRight();
	
		}
			rowMarker = rowMarker.getDown();
		}
		
		return false;
	}
	
	
	//Solves the node if the possibility of that node is one
	private void solvenode(node current){
		
		node temp = current;
		
		//If the currentnode has a possibility of one it will run the code below to solve
		if(temp.getNumberOfPossibilities() == 1){
			for(int i = 1; i < 10; i++){
				
				//If the getPossible at i is true it means that values is the only 1 that is possible
				if(temp.getPossible(i)){
					//sets the data of that node
					temp.setData(i);
					//runs the solve method to eliminate the possibility of that integer in the row and column
					solve(temp, i);
				}
			}
		}
	}
	//============================================================================
	
	
	private void setBoxIDs() throws IOException{
		File idList = new File("idlist.txt");
		Scanner input = new Scanner(idList);
	
		node rowMarker = root;
	
		while (rowMarker != null) {
			node temp = rowMarker;
	
		while (temp != null) {
			temp.setBoxID(input.nextInt());
			temp = temp.getRight(); 
		}
			rowMarker = rowMarker.getDown();
		} 
		input.close();
	}
	
	//Solves the node, by setting the notPossible values  
	private void solve(node currentnode, int number){
		currentnode.setData(number);
	
		for(int x = 0; x < 10; x++)
		if(x != number)
		currentnode.setImpossible(x);
	
		//Eliminating possibilities going up from node
		node temp = currentnode;
		while(temp.getUp()!= null){
			temp = temp.getUp();
			temp.setImpossible(number);
		}
		temp = currentnode;
	
		//Eliminating possibilities going down from node
		while(temp.getDown()!= null){
			temp = temp.getDown();
			temp.setImpossible(number);
		}
		temp = currentnode;
	
		//Eliminating possibilities going left from node
		while(temp.getLeft()!= null){
			temp = temp.getLeft();
			temp.setImpossible(number);
		}
		temp = currentnode;
	
		//Eliminating possibilities going right from node
		while(temp.getRight()!= null){
			temp = temp.getRight();
			temp.setImpossible(number);
		}
	
		//Eliminate the possibility for all cells with same boxID
		node rowMarker = root;
	
		while (rowMarker != null) {
		temp = rowMarker;
	
		while (temp != null) {
			if(temp.getBoxID() == currentnode.getBoxID())
				temp.setImpossible(number);
				temp = temp.getRight();
	
		}
			rowMarker = rowMarker.getDown();
		}
	}
	
	//Method for populating the grid from a file
	public void populate() throws IOException{

	File infile = new File("sudoku3.txt");
	Scanner input = new Scanner (infile);

	int data = 0;

	node rowMarker = root;

		while (rowMarker != null) {
			node temp = rowMarker;
	
		while (temp != null) {
			data = input.nextInt();
			if(data != 0)
				solve(temp, data);
				temp = temp.getRight();
	
		}
			rowMarker = rowMarker.getDown();
		}
		
		input.close();
	}
	
	//Displays the data of the grid for possibilities and the number
	public void displayData() {
		node rowMarker = root;

		while (rowMarker != null) {
		node temp = rowMarker;
	
		while (temp != null) {
			System.out.println("node Data:" + temp.getData() + " ");
			System.out.print("Possibilities:");
			temp.listPossibilities();
			temp = temp.getRight();
			System.out.println("--------------------");
			}
		
		rowMarker = rowMarker.getDown();
		} 
	}
}
