package mainpkg;

public class Book {
	int book_id, goodreads_book_id, best_book_id, 
	work_id, books_count, ratings_count,
	work_ratings_count, work_text_reviews_count, 
	ratings_1, ratings_2, ratings_3, ratings_4, ratings_5;
	long isbn13;
	String authors, original_title, title, langauge_code, image_url, isbn;
	float original_publication_year, average_rating;
	
	public Book(String book_id, String goodreads_book_id, String best_book_id, String work_id, String books_count, String isbn, 
			String isbn13, String authors, String original_publication_year, String original_title, String title,
			String langauge_code, String average_rating, String ratings_count, String work_ratings_count, 
			String work_text_reviews_count, String ratings_1, String ratings_2,
			String ratings_3, String ratings_4, String ratings_5, String image_url) {
		
		this.book_id = Integer.parseInt(book_id) ;
		this.goodreads_book_id = Integer.parseInt(goodreads_book_id);
		this.best_book_id = Integer.parseInt(best_book_id);
		this.work_id = Integer.parseInt(work_id);
		this.books_count = Integer.parseInt(books_count);
		this.isbn = isbn;
		this.isbn13 = (long) Double.parseDouble(isbn13);
		this.authors = authors;
		this.original_publication_year = Float.parseFloat(original_publication_year);
		this.original_title = original_title;
		this.title = title;
		this.langauge_code = langauge_code;
		this.average_rating = Float.parseFloat(average_rating);
		this.ratings_count = Integer.parseInt(ratings_count);
		this.work_ratings_count = Integer.parseInt(work_ratings_count);
		this.work_text_reviews_count = Integer.parseInt(work_text_reviews_count);
		this.ratings_1 = Integer.parseInt(ratings_1);
		this.ratings_2 = Integer.parseInt(ratings_2);
		this.ratings_3 = Integer.parseInt(ratings_3);
		this.ratings_4 = Integer.parseInt(ratings_4);
		this.ratings_5 = Integer.parseInt(ratings_5);
		this.image_url = image_url;
	}
}
