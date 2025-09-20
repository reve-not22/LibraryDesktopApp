package mainpkg;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
	
	static ArrayList<Book> bookList = new ArrayList<>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReadFile(new File("books.csv"));
	}
	
	public static void ReadFile(File f) {
		try (Scanner scnr = new Scanner(f)) {
			while (scnr.hasNextLine()) {
				String line = scnr.nextLine();
				if (!line.startsWith("book_id")) {
					String[] lineArr = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
					Book b = new Book(lineArr[0], lineArr[1], lineArr[2], lineArr[3], lineArr[4], lineArr[5],
							lineArr[6], lineArr[7], lineArr[8], lineArr[9], lineArr[10], lineArr[11], lineArr[12],
							lineArr[13], lineArr[14], lineArr[15], lineArr[16], lineArr[17], lineArr[18], lineArr[19],
							lineArr[20], lineArr[21]);
					System.out.println(b);
					bookList.add(b);
				}
			}
		}
		catch (FileNotFoundException e) {
			System.out.println("An error occurred");
			e.printStackTrace();
		}
	}

}
