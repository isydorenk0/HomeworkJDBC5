package org.example;

import org.example.entity.Author;
import org.example.entity.Book;
import org.example.utils.AuthorHelper;
import org.example.utils.BookHelper;

import java.util.List;
import java.util.Objects;

public class Main {

    public static void main(String[] args) {

        AuthorHelper authorHelper = new AuthorHelper();
        Author author = new Author("Shevchenko");
        authorHelper.addAuthor(author);
        author = new Author("Andrii", "Franko");
        authorHelper.addAuthor(author);

        author.setName("Ivan");
        authorHelper.updateAuthorById(author);

        for (Author auth : authorHelper.getAuthorList()) {
            System.out.println(auth);
        }

        author = authorHelper.getAuthorById(1);
        if (Objects.nonNull(author)){
            System.out.println(author);
        }
        authorHelper.delAuthor("Shevchenko");

        List<Author> listAuthor = authorHelper.getAuthorList();
        for (Author auth : listAuthor) {
            System.out.println(auth);
        }

        BookHelper bookHelper = new BookHelper();
        bookHelper.addBook(new Book("Franko", listAuthor.get(0).getId()));
        bookHelper.addBook(new Book("Volu", listAuthor.get(0).getId()));

        for (Book book : bookHelper.getBookList()) {
            System.out.println(book);
        }
        Book book = bookHelper.getBookById(1);
        if (Objects.nonNull(book)) {
            book.setTitle("NewTitle");
            bookHelper.updateBookById(book);
        }

       authorHelper.flush();

    }
}