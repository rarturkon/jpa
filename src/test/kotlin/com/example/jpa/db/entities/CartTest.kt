package com.example.jpa.db.entities

import org.hibernate.SessionFactory
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.junit.jupiter.api.Assertions.*

@SpringBootTest
class CartTest {

    @Autowired
    private var sessionFactory: SessionFactory? = null

    @Test
    fun saveCartTest_OK() {
        // boilerplate code start
        var session = sessionFactory!!.openSession()
        session.beginTransaction()
        // boilerplate code end

        val cart = Cart(0, "Cart Example", listOf(Item(0, "Pen",1.5), Item(0, "Ball",9.5)));
        session.save(cart)

        // boilerplate code start
        session.transaction.commit()
        session.close()
        // boilerplate code end

        // boilerplate code start
        session = sessionFactory!!.openSession()
        session.beginTransaction()
        // boilerplate code end

        val foundCarts: List<Cart> = session.createQuery("from Cart").list() as List<Cart>

        assertNotNull(foundCarts)
        assertEquals(1, foundCarts.size)
        assertNotEquals(0, foundCarts[0].id)
        assertEquals(2, foundCarts[0].items.size)

        // boilerplate code start
        session.transaction.commit()
        session.close()
        // boilerplate code end
    }
}