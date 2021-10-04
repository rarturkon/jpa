package com.example.jpa.db.entities

import org.hibernate.SessionFactory
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.junit.jupiter.api.Assertions.*


@SpringBootTest
class EventTest {

    @Autowired
    private var sessionFactory: SessionFactory? = null

    @Test
    fun testBasicUsage() {
        // boilerplate code start
        var session = sessionFactory!!.openSession()
        session.beginTransaction()
        // boilerplate code end

        val event = Event(0,"Testing Event")
        session.save(event)

        // boilerplate code start
        session.transaction.commit()
        session.close()
        // boilerplate code end

        // boilerplate code start
        session = sessionFactory!!.openSession()
        session.beginTransaction()
        // boilerplate code end

        val foundEvents: List<Event> = session.createQuery("from Event").list() as List<Event>

        assertNotNull(foundEvents)
        assertEquals(1, foundEvents.size)
        assertEquals(event?.title, foundEvents[0].title)

        // boilerplate code start
        session.transaction.commit()
        session.close()
        // boilerplate code end
    }
}