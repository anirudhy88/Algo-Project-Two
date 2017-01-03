import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class LCS_Anirudh_Yellapagada_ayellapr {

	public static void main(String[] args) throws IOException {
		int k = 0;
		int n = 0; // length of string one
		int m = 0; // length of string two

		// Reading the input file
		FileReader fr = new FileReader("input.txt");
		BufferedReader br = new BufferedReader(fr);
		String stringOne = null;
		String stringTwo = null;
		String currentLine;

		while ((currentLine = br.readLine()) != null) {
			if (k == 0) {
				stringOne = currentLine;
				k++;
			} else {
				stringTwo = currentLine;
				break;
			}
		}
		// Get the length of the each string
		n = stringOne.length();
		m = stringTwo.length();
		int[][] indication = new int[n + 1][m + 1];
		int[][] lengths = new int[n + 1][m + 1];
		for (int i = 0; i <= n; i++) {
			lengths[i][0] = 0;
		}
		for (int j = 0; j <= m; j++) {
			lengths[0][j] = 0;
		}
		// I do not think length matter here : the for loops can be in any order
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if (stringOne.charAt(i - 1) == stringTwo.charAt(j - 1)) {
					lengths[i][j] = lengths[i - 1][j - 1] + 1;
					indication[i][j] = 0; // Indicates arrow pointing diagonally
											// upwards
				} else if (lengths[i][j - 1] > lengths[i - 1][j]) {
					lengths[i][j] = lengths[i][j - 1];
					indication[i][j] = 1; // Indicates arrow pointing vertically
											// leftwards
				} else {
					lengths[i][j] = lengths[i - 1][j];
					indication[i][j] = 2; // Indicates arrow pointing
											// horizontally upwards
				}
			}
		}

		String output = new String();

		// To find the characters in the LCS
		int x = n, y = m;
		while (x > 0 && y > 0) {
			if (indication[x][y] == 0) {
				output += stringOne.charAt(x - 1);
				x = x - 1;
				y = y - 1;
			} else if (indication[x][y] == 1) {
				y = y - 1;
			} else {
				x = x - 1;
			}
		}

		// Creating output file
		File myFile = new File("output.txt");
		if(!myFile.exists()) {
			myFile.createNewFile();
		}
		
		// Writing into output.txt
		int newl = lengths[n][m];
		FileWriter fw = new FileWriter(myFile.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(newl + "");
		bw.newLine();
		for (int i = output.length() - 1; i >= 0; i--) {
			bw.write(output.charAt(i));
		}

		System.out.println("Length of LCS is : " + lengths[n][m]);
		for (int i = output.length() - 1; i >= 0; i--) {
			System.out.print(output.charAt(i));
		}
		
		bw.close();
		br.close();

	}
}
