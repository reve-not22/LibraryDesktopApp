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
		
		Run(bookArrList);
	}
	
	static void Run(List<Book> bList) {
		Boolean isRunning = true;
		while (isRunning) {
			System.out.println("1: Search for records, 2: Sort by author, 3: Sort by publication");
			Scanner scnr = new Scanner(System.in);
			
			switch(scnr.nextInt()) {
			case 1:
				System.out.println("book_id(1) or isbn(2)?");
				int recordType = scnr.nextInt();
				
				if (recordType == 1) {
					SearchList(bList, true, scnr);
				}
				else if (recordType == 2) {
					SearchList(bList, false, scnr);
				}
				break;
			case 2:
				int isAsc = scnr.nextInt();
				System.out.println("Ascending(1) or descending(2)?");
				if (isAsc == 1) {
					
				}
				else if (isAsc == 2) {
					
				}
				break;
			case 3:
				int isDesc = scnr.nextInt();
				System.out.println("Ascending(1) or descending(2)?");
				if (isDesc == 1) {
					
				}
				else if (isDesc == 2) {
					
				}
				break;
			}
		}
	}
	
	static void SearchList(List<Book> bList, Boolean searchBookID, Scanner scnr) {
		if (searchBookID) {
			//search by book ID
			System.out.println("Input book ID");
			int id = scnr.nextInt();
			
			for (Book b : bList) {
				if (id == b.book_id) {
					System.out.println(b.toString());
				}
			}
		}
		else {
			//search by isbn
			System.out.println("Input ISBN");
			String isbn = scnr.next();
			
			for (Book b : bList) {
				if (isbn.trim().compareTo(b.isbn) == 0) {
					System.out.println(b.toString());
				}
			}
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
	
	static void ShowTopTenRecords(List<Book> bList) {
		for (int i = 0; i < 10; i++) {
			System.out.println(bList.get(i).toString());
		}
	}

}
