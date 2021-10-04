package com.example.jpa.db.entities

import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.Email

@Entity
@Table(name = "VALIDATION_EXAMPLE")
class ValidationExample {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private var id: UUID? = null

    @Email
    var email: String? = null

    constructor() {
        // this form used by Hibernate
    }

    constructor(email: String?) {
        this.email = email
    }

}