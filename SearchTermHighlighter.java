import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * 
 * @author treron brooks
 * This class hat reads in all lines from a file, generates several new versions of each line with significant terms,
 *and writes out the results to a file. 
 */
public class SearchTermHighlighter {

	ArrayList<String>	input	= new ArrayList<String>();
	ArrayList<String>	output	= new ArrayList<String>();
	
	/**
	 * Constructor that takes the name of the input file in a String as the only parameter. 
	 * @param fileName - file to be read
	 * @throws FileNotFoundException - throw exception if no file was found
	 */
	public SearchTermHighlighter(String fileName) throws FileNotFoundException {
		Scanner scan = new Scanner(new File(fileName));

		try {

			while (scan.hasNextLine()) {
				input.add(scan.nextLine());
			}

			
		} catch (Exception e) {
			System.out.println("File cannot be open");
		}finally{
			scan.close();
		}

	}
	/**
	 * This method takes a list of common words in an ArrayList of String objects. Makes every line input
data, highlights each word in turn, and stores new versions of each line
	 * @param commonWords
	 */
	public void parseContents(ArrayList<String> commonWords) {

		for (int i = 0; i < input.size(); i++) {

			ArrayList<String> usedWords = new ArrayList<String>();
			String line = input.get(i);

			Scanner scan = new Scanner(line);
			scan.useDelimiter(" ");

			
			whileLoop : while (scan.hasNext()) {
				String word = scan.next();

				if (usedWords.contains(word)) {
					continue;
				}

				for (int k = 0; k < commonWords.size(); k++) {
					if (commonWords.get(k).equalsIgnoreCase(word)) {
						continue whileLoop;

					}
				}

				usedWords.add(word);

				int indexOfWord = line.indexOf(word);
				String first = line.substring(0, indexOfWord).toLowerCase();
				String last = line.substring(indexOfWord + word.length()).toLowerCase();
				String newOutputWord = first + word.toUpperCase() + last;
				output.add(newOutputWord);
			}
			
			scan.close();
		}
	}
	/**
	 * This methods copies the lists
	 * @return A copy of the list 
	 */

	public ArrayList<String> getInputFileContents() {
		ArrayList<String> clone = new ArrayList<String>();

		for (int i = 0; i < input.size(); i++) {
			clone.add(input.get(i));
		}

		return clone;
	}
	/**
	 * This methods copies the lists
	 * @return A copy of the list 
	 */
	public ArrayList<String> getOutputFileContents() {
		ArrayList<String> clone = new ArrayList<String>();

		for (int i = 0; i < output.size(); i++) {
			clone.add(output.get(i));
		}

		return clone;
	}
	/**
	 * This method prints out the processed lines containing capitalized words to a file.
	 * @param path- - output file
	 * @throws FileNotFoundException if no file is found
	 */
	
	public void writeFile(String path) throws FileNotFoundException {
		
		PrintWriter pw = new PrintWriter(path);
		ArrayList<String> list = getOutputFileContents();
		
		try{
			for(int i = 0; i < list.size(); i++){
				pw.println(list.get(i));
			}
		}catch(Exception e){
			System.out.println("Could not open file");
		}finally {
			pw.close();
		}
	}
}
