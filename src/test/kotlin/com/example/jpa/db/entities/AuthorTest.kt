package com.example.jpa.db.entities

import org.hibernate.SessionFactory
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.junit.jupiter.api.Assertions.*
import org.springframework.test.annotation.DirtiesContext

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class AuthorTest {

    @Autowired
    private var sessionFactory: SessionFactory? = null

    @Test
    fun saveAuthorWithoutBooksTest_OK() {
        // boilerplate code start
        var session = sessionFactory!!.openSession()
        session.beginTransaction()
        // boilerplate code end

        val author = Author(0, "Thorben Janssen");
        session.save(author)

        // boilerplate code start
        session.transaction.commit()
        session.close()
        // boilerplate code end

        // boilerplate code start
        session = sessionFactory!!.openSession()
        session.beginTransaction()
        // boilerplate code end

        val authors: List<Author> = session.createQuery("from Author").list() as List<Author>

        assertNotNull(authors)
        assertEquals(1, authors.size)
        assertNotEquals(0, authors[0].id)
        assertEquals(0, authors[0].books?.size)

        // boilerplate code start
        session.transaction.commit()
        session.close()
        // boilerplate code end
    }

    @Test
    fun saveAuthorWithBooksTest_OK() {
        // boilerplate code start
        var session = sessionFactory!!.openSession()
        session.beginTransaction()
        // boilerplate code end

        val author = Author(0, "Thorben Janssen");
        author.addBook(Book(0, "Hibernate Tips"))
        author.addBook(Book(0, "JPA/Hibernate"))
        session.save(author)

        // boilerplate code start
        session.transaction.commit()
        session.close()
        // boilerplate code end

        // boilerplate code start
        session = sessionFactory!!.openSession()
        session.beginTransaction()
        // boilerplate code end

        val authors: List<Author> = session.createQuery("from Author").list() as List<Author>

        assertNotNull(authors)
        assertEquals(1, authors.size)
        assertNotEquals(0, authors[0].id)
        assertEquals(2, authors[0].books?.size)

        // boilerplate code start
        session.transaction.commit()
        session.close()
        // boilerplate code end
    }

    @Test
    fun saveBookWithAuthorTest_OK() {
        // boilerplate code start
        var session = sessionFactory!!.openSession()
        session.beginTransaction()
        // boilerplate code end

        val book = Book(0, "Hibernate Tips", Author(0, "Thorben Janssen"));
        session.save(book)

        // boilerplate code start
        session.transaction.commit()
        session.close()
        // boilerplate code end

        // boilerplate code start
        session = sessionFactory!!.openSession()
        session.beginTransaction()
        // boilerplate code end

        val books: List<Book> = session.createQuery("from Book").list() as List<Book>

        assertNotNull(books)
        assertEquals(1, books.size)
        assertNotEquals(0, books[0].id)
        assertEquals("Hibernate Tips", books[0].title)
        assertNotNull(books[0].author)
        assertNotEquals(0, books[0].author?.id)
        assertEquals("Thorben Janssen", books[0].author?.name)


        // boilerplate code start
        session.transaction.commit()
        session.close()
        // boilerplate code end
    }

    @Test
    fun saveBookWithEmptyAuthorTest_OK() {
        // boilerplate code start
        var session = sessionFactory!!.openSession()
        session.beginTransaction()
        // boilerplate code end

        val book = Book(0, "Hibernate Tips");
        session.save(book)

        // boilerplate code start
        session.transaction.commit()
        session.close()
        // boilerplate code end

        // boilerplate code start
        session = sessionFactory!!.openSession()
        session.beginTransaction()
        // boilerplate code end

        val books: List<Book> = session.createQuery("from Book").list() as List<Book>

        assertNotNull(books)
        assertEquals(1, books.size)
        assertNotEquals(0, books[0].id)
        assertEquals("Hibernate Tips", books[0].title)
        assertEquals(null, books[0].author)

        // boilerplate code start
        session.transaction.commit()
        session.close()
        // boilerplate code end
    }
}