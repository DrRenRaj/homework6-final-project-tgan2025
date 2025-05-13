/* Tim Gan
 * CS 1050 Final Project
 */


package hw6;

import java.util.Scanner;
import java.util.ArrayList;

class Book {
	
	private String title;
	private String author;
	private String isbn;
	private boolean isAvailable;
	
	/*constructor for book class
	 * assigns title, author and isbn to each book and defaults availability to true
	 */
	public Book (String title, String author, String isbn) {
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.isAvailable = true;
	}
	// getters and setters to access variables outside of the book class 
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public boolean isAvailable() {
		return isAvailable;
	}
	public void setAvailable(boolean available) {
		this.isAvailable = available;	
	}
	//toString method that returns details of book
	public String toString() {
		return "Title: " + title + " Author: " + author + " ISBN: " + isbn + " Available: " + (isAvailable ? " Available" : " Not Available \n");
	}

}
	//creates array list to store all books
class Library{
	
	ArrayList<Book> books;
	public Library() {
		this.books = new ArrayList<Book>();	
	}
	//method to add a new book that checks isbn to ensure the book cannot be added again
	public String addBook(Book newBook) {
		for (Book book: books) {
			if (book.getIsbn().equals(newBook.getIsbn())){
				return "This book is already in the library \n";
			}
		}
		books.add(newBook);
		return "This book has been added to the library \n";
	}
	// method to remove a book based on isbn and checks to make sure the book is in library first and can be removed
	public String removeBook(String isbn) {
		for (Book book: books) {
			if (book.getIsbn().equals(isbn)) {
				books.remove(book);
				return "This book has been removed from the library \n";
			}
		}
		return "This book was not in the library \n";	
	}
	//method to display all book details, if library has no books it will inform the user there are no books
	public void displayBooks() {
		if (books.isEmpty()) {
			System.out.println("There are currently no books in the library \n");
		} else {
				for (Book book: books) {
					System.out.println(book.toString());
				}
		}
	}
	//searches by title, ignoring lower or upper case to avoid issues when searching
	public String searchByTitle(String title) {
		for (Book book: books) {
			if (book.getTitle().equalsIgnoreCase(title)) {
				return book.toString();
			}
		}
		return "Book with this title not found in library \n";
	}
	//searches by author, ignoring lower or upper case letters to avoid search issues. Would be helpful to implement a way to search via last name only
	public String searchByAuthor(String author) {
		for (Book book: books) {
			if (book.getAuthor().equalsIgnoreCase(author)) {
				return book.toString();
			}		
		}			
		return "Books from this author not found found in library \n";
	}
	//method for checking out the book, changes availability status upon check out. alerts user if already unavailable or not in the library
	public String checkOut(String isbn) {
		for (Book book: books) {
			if (book.getIsbn().equals(isbn)) {
				if (book.isAvailable() == false) { 
					return "This book has already been checked out \n";
				}
				book.setAvailable(false);
				return "You have successfully checked out this book \n";
			}
		}
		return "This book is not in the library \n";
	}
	//method for return the book via isbn, if not checked out it alerts user the book has not been checked out
	public String returnBook(String isbn) {
		for (Book book: books) {
			if (book.getIsbn().equals(isbn)) {
				if (book.isAvailable() == false) {
					book.setAvailable(true);
					return "You have successfully returned this book \n";		
				}
			}
			return "This book was not checked out and cannot be returned \n";
		}
		return "This book is not in the library";
	}
}

public class Main {
	
	public static Scanner scan = new Scanner(System.in);
	public static Library library = new Library();
	
	/*main method to display user menu using a while loop so the menu continues to reappear after making selections
	 * scanner reads input and the if else loop calls the method based on the input, displays error and asks for new input if entry is not 1-8
	 */
	public static void main(String[] args) {
		
		while (true) {
			System.out.println("Please select an option (1-8) to continue.");
			System.out.println();
			System.out.println("1. Add Book");
			System.out.println("2. Remove Book");
			System.out.println("3. Display All Books");
			System.out.println("4. Search By Title");
			System.out.println("5. Search By Author");
			System.out.println("6. Check Out Book");
			System.out.println("7. Return Book");
			System.out.println("8. Exit Menu");
			System.out.println();
			
			String selection = scan.nextLine();
			
			if (selection.equals("1")) {
				addBook();
			} else if (selection.equals("2")) {
				removeBook();
			} else if (selection.equals("3")) {
				displayBooks();
			} else if (selection.equals("4")) {
				titleSearch();
			} else if (selection.equals("5")) {
				authorSearch();
			} else if (selection.equals("6")) {
				checkOut();
			} else if (selection.equals("7")) {
				returnBook();
			} else if (selection.equals("8")) {
				System.out.println("Exiting Menu");
				break;
			} else {
				System.out.println("Invalid entry, please enter a valid selection (1-8)");
			}
		}
	}
	
	//receives user input to add new book with details, then calls the library add book method to add to array list
	public static void addBook() {
		System.out.println("Enter Book Title: ");
		String title = scan.nextLine();
		System.out.println("Enter Author's Name: ");
		String author = scan.nextLine();
		System.out.println("Enter ISBN : ");
		String isbn = scan.nextLine();	
		System.out.println(library.addBook(new Book(title, author, isbn)));
	}
	//takes isbn input from user and calls library remove book method to remove from array list
	public static void removeBook() {
		System.out.println("Enter ISBN to remove book");
		System.out.println(library.removeBook(scan.nextLine()));
	}
	//displays books
	public static void displayBooks() {
		library.displayBooks();
	}
	//receives input from user and calls library search by title method and then prints title if found
	public static void titleSearch() {
		System.out.println("Enter title to search");
		String title = scan.nextLine();
		System.out.println(library.searchByTitle(title));
	}
	//receives user input and calls library search by author method and prints title if found
	public static void authorSearch() {
		System.out.println("Enter author's name to search");
		String author = scan.nextLine();
		System.out.println(library.searchByAuthor(author));
		
	}
	//takes isbn from user and calls check out method in library class
	public static void checkOut() {
		System.out.println("Enter ISBN to check out a book");
		String isbn = scan.nextLine();
		System.out.println(library.checkOut(isbn));
	}
	//takes isbn from user and calls return book method in library class
	public static void returnBook() {
		System.out.println("Enter ISBN to return a book");
		String isbn = scan.nextLine();
		System.out.println(library.returnBook(isbn));
	}
}
  



