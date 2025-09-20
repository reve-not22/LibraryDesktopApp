package mainpkg;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Book> bookArrList = ReadFile(new ArrayList<>(), new File("books.csv"));
		List<Book> bookLinkedList = ReadFile(new LinkedList<>(), new File("books.csv"));
		
		for (Book b : bookArrList) {
			System.out.println(b.toString());
		}
		for (Book b : bookLinkedList) {
			System.out.println(b.toString());
		}
	}
	
	public static List<Book> ReadFile(List<Book> bookList, File f) {
		try (Scanner scnr = new Scanner(f)) {
			while (scnr.hasNextLine()) {
				String line = scnr.nextLine();
				if (!line.startsWith("book_id")) {
					String[] lineArr = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
					Book b = new Book(lineArr[0], lineArr[1], lineArr[2], lineArr[3], lineArr[4], lineArr[5],
							lineArr[6], lineArr[7], lineArr[8], lineArr[9], lineArr[10], lineArr[11], lineArr[12],
							lineArr[13], lineArr[14], lineArr[15], lineArr[16], lineArr[17], lineArr[18], lineArr[19],
							lineArr[20], lineArr[21]);
					bookList.add(b);
				}
			}
		}
		catch (FileNotFoundException e) {
			System.out.println("An error occurred");
			e.printStackTrace();
		}
		
		return bookList;
	}

}
