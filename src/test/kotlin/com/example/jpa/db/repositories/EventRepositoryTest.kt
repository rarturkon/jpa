package com.example.jpa.db.repositories

import com.example.jpa.db.entities.Event
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.*

@SpringBootTest
class EventRepositoryTest {

    @Autowired
    private val eventRepository: EventRepository? = null

    @Test
    fun testEventEntity_OK() {
        val event: Event? = eventRepository?.save(Event(0, "Testing Event"))
        val foundEvent: Optional<Event>? = event?.let { eventRepository?.findById(it.id) }

        Assertions.assertNotNull(foundEvent)
        Assertions.assertEquals(event?.title, foundEvent?.get()?.title)
    }
}