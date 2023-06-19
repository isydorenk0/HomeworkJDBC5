package org.example.utils;

import org.example.entity.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Objects;

import static org.example.utils.LogUtil.logException;
import static org.example.utils.LogUtil.logInfo;


public class BookHelper {
    private SessionFactory sessionFactory;
    private static final String CLASSNAME = BookHelper.class.getName();

    public BookHelper() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public Book getBookById(long id) {
        Book book = null;
        try (Session session = sessionFactory.openSession()) {
            logInfo("Selecting book with id = " + id, CLASSNAME);
            book = session.get(Book.class, id);
            if (Objects.isNull(book)) {
                logInfo("None found", CLASSNAME);
            }
        } catch (Exception e) {
            logException(e, CLASSNAME);
        }
        return book;
    }

    public void addBook(Book book) {
        logInfo("Adding " + book.getTitle(), CLASSNAME);
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(book);
            session.getTransaction().commit();
            logInfo(book.getTitle() + " added", CLASSNAME);
        } catch (Exception e) {
            logException(e, CLASSNAME);
        }
    }

    public void updateBookById(Book book) {
        try (Session session = sessionFactory.openSession()) {
            logInfo("Updating book with id = " + book.getId(), CLASSNAME);
            session.beginTransaction();
            session.merge(book);
            session.getTransaction().commit();
        } catch (Exception e) {
            logException(e, CLASSNAME);
        }
    }

    public List<Book> getBookList() {
        logInfo("Selecting all books", CLASSNAME);
        List<Book> bookList = null;
        try (Session session = sessionFactory.openSession()) {
            Query<Book> q = session.createQuery("from org.example.entity.Book", Book.class);
            bookList = q.list();
        } catch (Exception e) {
            logException(e, CLASSNAME);
        }
        return bookList;
    }

}
