package com.example.jpa.db.repositories

import com.example.jpa.db.entities.Event
import org.springframework.data.jpa.repository.JpaRepository

interface EventRepository: JpaRepository<Event, Int>