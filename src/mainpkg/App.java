package mainpkg;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Book> bookArrList = ReadFile(new ArrayList<>(), new File("booksL.csv"));
		List<Book> bookLinkedList = ReadFile(new LinkedList<>(), new File("booksL.csv"));
		
		Run(bookLinkedList);
	}
	
	static void Run(List<Book> bList) {
		Boolean isRunning = true;
		while (isRunning) {
			System.out.println("1: Show top 10 records, 2: Search for records, 3: Sort by author, 4: Sort by publication year, 5: test performance, 0: quit");
			Scanner scnr = new Scanner(System.in);
			
			switch(scnr.nextInt()) {
			case 0:
				isRunning = false;
				break;
			case 1:
				ShowTopTenRecords(bList);
				break;
			case 2:
				System.out.println("book_id(1) or isbn(2)?");
				int recordType = scnr.nextInt();
				
				if (recordType == 1) {
					SearchList(bList, true, scnr);
				}
				else if (recordType == 2) {
					SearchList(bList, false, scnr);
				}
				break;
			case 3:
				System.out.println("Descending(0) or ascending(1)?");
				Boolean isAsc = (scnr.nextInt() != 0);
				
				SortList(bList, true, isAsc);
				break;
			case 4:
				System.out.println("Descending(0) or ascending(1)?");
				isAsc = (scnr.nextInt() != 0);
				
				SortList(bList, false, isAsc);
				break;
			case 5:
				testBinarySearchPerformance(bList, 3);
				testLinearSearchPerformance(bList, 3);
			}
		}
	}
	
	static void SortList(List<Book> bList, Boolean sortAuthor, Boolean sortAsc) {
		if (sortAuthor) {
			//sort by author
			if (sortAsc) {
				//sort ascending
				bList.sort(Comparator.comparing(Book::getAuthors));
			}
			else {
				//desc
				bList.sort(Comparator.comparing(Book::getAuthors).reversed());
			}
		}
		else {
			//sort by publication
			if (sortAsc) {
				//sort ascending
				bList.sort(Comparator.comparing(Book::getPublicationYear));
			}
			else {
				//desc
				bList.sort(Comparator.comparing(Book::getPublicationYear).reversed());
			}
		}
		ShowTopTenRecords(bList);
	}
	
	static void SearchList(List<Book> bList, Boolean searchBookID, Scanner scnr) {
		if (searchBookID) {
			//search by book ID
			System.out.println("Input book ID");
			int id = scnr.nextInt();
			
			//if arraylist, do binary search, otherwise do linear search
			if (ArrayList.class.isInstance(bList)) {
				System.out.println(binarySearchByBookID(bList, id).toString());
			}
			else {
				System.out.println(linearSearchID(bList, id).toString());
			}
		}
		else {
			//search by isbn
			System.out.println("Input ISBN");
			String isbn = scnr.next();
			
			//if arraylist, do binary search, otherwise do linear search
			if (ArrayList.class.isInstance(bList)) {
				System.out.println(binarySearchByISBN(bList, isbn).toString());
			}
			else {
				System.out.println(linearSearchISBN(bList, isbn).toString());
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
		System.out.println("Top 10 records: ");
		for (int i = 0; i < 10; i++) {
			System.out.println(bList.get(i).toString());
		}
		System.out.println("\n");
	}
	
	static Book linearSearchID(List<Book> list, int ID) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).book_id == ID) {
				return list.get(i);
			}
		}
		return null;
	}
	
	static Book linearSearchISBN(List<Book> list, String ISBN) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).isbn.equals(ISBN)) {
				return list.get(i);
			}
		}
		return null;
	}
	
	
	//Binary search method
	static Book binarySearchByBookID(List<Book> sortedList, int targetId) {
		sortedList.sort((b1, b2) -> Integer.compare(b1.book_id, b2.book_id));
		
		int low = 0;
		int high = sortedList.size() - 1;
		
		while (low <= high) {
			int mid = low + (high - low) / 2;
			Book midBook = sortedList.get(mid);
			
			if (midBook.book_id == targetId) {
				return midBook;
			} else if (midBook.book_id < targetId) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		
		return null;
	}
	
	static Book binarySearchByISBN(List<Book> sortedList, String targetISBN) {
		sortedList.sort((b1, b2) -> b1.isbn.compareTo(b2.isbn));
		int low = 0;
		int high = sortedList.size() - 1;
		
		while (low <= high) {
			int mid = low + (high - low) / 2;
			Book midBook = sortedList.get(mid);
			
			if (midBook.isbn.equals(targetISBN)) {
				return midBook;
			} else if (midBook.isbn.compareTo(targetISBN) < 0) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		
		return null;
	}
	
	//Performance testing method
	static void testBinarySearchPerformance(List<Book> books, int numTests) {
		List<Integer> targetIds = new ArrayList<>();
		Random rand = new Random();
		for (int i = 0; i <numTests && i < books.size(); i++) {
			targetIds.add(books.get(rand.nextInt(books.size())).book_id);
		}
		
		long startTime = System.nanoTime();
		
		for (int id : targetIds) {
			Book result = binarySearchByBookID(books, id);
		}
		
		long endTime = System.nanoTime();
		long duration = endTime - startTime;
		double durationInMillis = duration / 1_000_000.0;
		
		System.out.println("Performed " + numTests + " binary searches in " + durationInMillis + " ms");
	}
	
	static void testLinearSearchPerformance(List<Book> books, int numTests) {
		List<Integer> targetIds = new ArrayList<>();
		Random rand = new Random();
		for (int i = 0; i <numTests && i < books.size(); i++) {
			targetIds.add(books.get(rand.nextInt(books.size())).book_id);
		}
		
		long startTime = System.nanoTime();
		
		for (int id : targetIds) {
			Book result = linearSearchID(books, id);
		}
		
		long endTime = System.nanoTime();
		long duration = endTime - startTime;
		double durationInMillis = duration / 1_000_000.0;
		
		System.out.println("Performed " + numTests + " linear searches in " + durationInMillis + " ms");
	}
	

}
