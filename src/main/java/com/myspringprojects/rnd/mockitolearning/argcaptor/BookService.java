package com.myspringprojects.rnd.mockitolearning.argcaptor;


public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    /**
     * Method to test stubbing of a void method using mockito.
     *
     * @param book The book to be saved.
     */
    public void addBook(Book book) {

        // If the price of the book is greater than 500, save it.
        bookRepository.save(book);
    }


    /**
     * Add a book using a BookRequest object. The method internally creates a Book
     * instance and uses the repository to save it.
     *
     * @param bookRequest The BookRequest to be used to create the book to be saved.
     */
    public void addBook(BookRequest bookRequest) {

        // Create a book instance from the bookRequest instance.
        Book book = new Book(null, bookRequest.getTitle(),
                bookRequest.getPrice(), bookRequest.getPublishedDate());

        // Now call the save method, so that the repository save uses a different
        // object than what is passed to the service, from the test case.
        bookRepository.save(book);
    }

    public void updatePrice(String bookId, int updatedPrice) {
        // If the bookId is null, don't do anything.
        if (bookId == null) {
            return;
        }
        Book book = bookRepository.findById(bookId);

        // If the updated price is same as the existing price, return.
        if (book.getPrice() == updatedPrice) {
            return;
        }
        book.setPrice(updatedPrice);
        bookRepository.save(book);
    }
}
