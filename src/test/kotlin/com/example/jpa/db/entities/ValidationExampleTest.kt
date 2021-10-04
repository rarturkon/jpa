package com.example.jpa.db.entities

import org.hibernate.SessionFactory
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.junit.jupiter.api.Assertions.*
import javax.persistence.RollbackException
import javax.validation.Validator

@SpringBootTest
class ValidationExampleTest {

    @Autowired
    private var sessionFactory: SessionFactory? = null

    @Autowired
    private var validator: Validator? = null

    @Test
    fun emailValidationWithValidatorTest_FAIL() {
        val example = ValidationExample("Invalid Email")
        val constraintViolation = validator?.validate(example)

        assertNotNull(constraintViolation)
        assertEquals(1, constraintViolation?.size)
        assertEquals("must be a well-formed email address", constraintViolation?.iterator()?.next()?.message)
    }

    @Test
    fun emailValidationWithValidatorTest_OK() {
        val example = ValidationExample("Max.Musterman@gmail.com")
        val constraintViolation = validator?.validate(example)

        assertNotNull(constraintViolation)
        assertEquals(0, constraintViolation?.size)
    }


    @Test
    fun emailValidationTest_FAIL() {
        val exception = assertThrows(RollbackException::class.java) {
            saveValidationExample("Invalid Email")
        }

        assertNotNull(exception)
        assertEquals("Error while committing the transaction", exception.message)
    }

    @Test
    fun emailValidationTest_OK() {
        val email = "Max.Musterman@gmail.com"
        saveValidationExample(email)

        // boilerplate code start
        val session = sessionFactory!!.openSession()
        session.beginTransaction()
        // boilerplate code end

        val foundEvents: List<ValidationExample> = session.createQuery("from ValidationExample").list() as List<ValidationExample>

        assertNotNull(foundEvents)
        assertEquals(1, foundEvents.size)
        assertEquals(email, foundEvents[0].email)

        // boilerplate code start
        session.transaction.commit()
        session.close()
        // boilerplate code end
    }


    fun saveValidationExample(email: String) {
        // boilerplate code start
        var session = sessionFactory!!.openSession()
        session.beginTransaction()
        // boilerplate code end

        val example = ValidationExample(email)
        session.save(example)

        // boilerplate code start
        session.transaction.commit()
        session.close()
        // boilerplate code end
    }
}