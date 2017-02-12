package MathGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class GameFiles {

	public static ArrayList<int[][]> allBoards;
	public int[] rookPolys;
	public int reqSize;

	public static void main (String[] args) throws Exception
	{	
		GameFiles gf = new GameFiles();
		allBoards = new ArrayList<int[][]>();
		gf.readFile("4.txt");
		for(int i = 0; i < allBoards.size(); i++) {
			System.out.println(Arrays.deepToString(allBoards.get(i)));
		}
		
	}

	public void readFile(String filename) throws Exception {
		
		try {
	        
			File file = new File(filename);
			
			// open the data file
			Scanner fileReader = new Scanner(file);

			// while there is a next line
			while (fileReader.hasNextLine()) {

				String text = fileReader.nextLine();
				String [] part = text.split(" ");
				int size = Integer.parseInt(part[0]);
				int[] boardVals = new int[size*size];

				for(int i = 1; i < (boardVals.length); i++) {
					boardVals[i-1] = Integer.parseInt(part[i]);
				}
				
				createBoard(size, boardVals);

			}

			// close file
			fileReader.close();
		}

		// could not find file
		catch (FileNotFoundException error) {
			throw new FileNotFoundException();
		}

		catch (Exception error){
			System.out.println(error);
			throw new Exception();
		}

	}

	public void createBoard(int size, int[] values){
		
		int count = 0;
		int[][] board = new int[size][size];
		
		for(int i = 0; i <= size-1; i++) {
			for(int j = 0; j <= size-1; j++) {
				board[i][j] = values[count];
				count++;
			}
		}
		
		allBoards.add(board);

	}


}
