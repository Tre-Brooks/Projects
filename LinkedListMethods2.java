import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;
 import java.util.Scanner;
 
public class LinkedListMethods2 {
	/**
	 * This method returns a new linked list created from the lines in in .
	 * @param Scanner - in
	 * @return new linked list from lines created
	 */
	public static Node<String> createListFromInput(Scanner in) {
		Node<String> head = null;
		Node<String> last = null;

		if (in.hasNextLine()) {
			head = new Node<String>(in.nextLine());
			last = head;
		}
		while (in.hasNextLine()) {
			last.next = new Node<String>(in.nextLine());
			last = last.next;
		}
		return head;

	}
	/**
	 * This method indicates whether the word to look up is in the given dictionary
	 * @param head - pointer to locate the head
	 * @param scanner -  dictionary
	 * @return True if the word is in the dictionary, and False otherwise
	 */

	public static boolean isWord(Node<Character> head, Scanner dictionary) {
		String word = "";
		Node<Character> first = head;

		while (first != null) {
			word += Character.toString(first.value);
			first = first.next;
		}
		while (dictionary.hasNext()) {
			if (dictionary.next().equals(word)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * 
	 * @param head - pointer to locate the head
	 * @param output - file that you write the reverse list to 
	 */

	public static void reverse(Node<Integer> head, File output) {
		try {
			PrintWriter writer = new PrintWriter(output);
			Node<Integer> newHead = null;
			Node<Integer> newTail = null;
			Node<Integer> current = null;

			int size = 0;
			
			current = head;

			while (current != null) {
				current = current.next;
				size++;
			}
			for (int i = 0; i < size; i++) {
				newTail = head;
				
				while (head != null) {
					if (newHead == null) {
						newHead = new Node<Integer>(head.value);
						newTail = newHead;
					}
					else {
						Node<Integer> n = new Node<Integer>(head.value, newHead);
						newHead = n;
					}
					head = head.next;
				}
				while (newHead != null) {
					writer.println(newHead.value);
					newHead = newHead.next;
				}
			}
			writer.flush();
			writer.close();
		} catch (IOException e) {
			return;
		}
	}
}



