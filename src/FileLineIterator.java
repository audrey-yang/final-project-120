import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * FileLineIterator provides a useful wrapper around Java's provided
 * BufferedReader and provides practice with implementing an Iterator.
 * Your solution should not read the entire file into memory at once, instead
 * reading a line whenever the next() method is called. See
 * Java's documentation for BufferedReader to learn how to construct one given
 * a path to a file. Then, think about how you can use BufferedReader's
 * methods within this class to implement our desired functionality.
 * 
 * Note: Any IOExceptions thrown by readers should be caught and handled properly.
 */
public class FileLineIterator implements Iterator<String> {
	BufferedReader br;
	String current;

	/**
	 * Creates a FileLineIterator for the file located at filePath.
	 * Fill out the constructor so that a user can instantiate a FileLineIterator.
	 * Feel free to create and instantiate any variables that your implementation requires here.
	 * See recitation and lecture notes for guidance.
	 *
	 * If an IOException is thrown by the BufferedReader or FileReader, then set next to null.
	 *
	 * @param filePath - the path to the CSV file to be turned to an Iterator
	 * @throws IllegalArgumentException if filePath is null or if the file doesn't exist
	 */
	public FileLineIterator(String filePath) {
		if (filePath == null) throw new IllegalArgumentException();
		try {
			br = new BufferedReader(new FileReader(filePath));
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException();
		}
		try {
			current = br.readLine();
		} catch (IOException e) {
			System.out.println("Uh oh! Couldn't read the next line.");
		}
		
	}

	 /**
 	 * Returns true if there are lines left to read in the file, and
	 * false otherwise.
	 * 
	 * If there are no more lines left, this method should close the
	 * BufferedReader.
 	 *
 	 * @return a boolean indicating whether the FileLineIterator
 	 * can produce another line from the file
 	 */
	@Override
	public boolean hasNext() {
		if (current == null) {
			try {
				br.close();
			} catch (IOException e) {
				System.out.println("Uh oh! Couldn't close the reader.");
			}
		}
		return current != null;
	}

	/**
 	 * Returns the next line from the file, or throws a NoSuchElementException
 	 * if there are no more strings left to return (i.e. hasNext() is false).
 	 * 
 	 * This method also advances the iterator in preparation for another
 	 * invocation.  If an IOException is thrown during this process, the
 	 * subsequent call should return null. 
 	 *
 	 * @return the next line in the file
 	 * @throws NoSuchElementException if there is no more data in the file
 	 */
	@Override
	public String next() {
		if (hasNext()) { 
			String temp = current;
			try {
				current = br.readLine();
			} catch (IOException e) {
				System.out.println("Uh oh! Couldn't read the next line.");
			}
			return temp; 
		} else {
			try {
				br.close();
			} catch (IOException e) {
				System.out.println("Uh oh! Couldn't close the reader.");
			}
			throw new NoSuchElementException();
		}
	}
}
