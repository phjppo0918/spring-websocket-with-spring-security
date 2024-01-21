package com.example.back.member

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Repository

@Repository
class MemberRepository(
    private val passwordEncoder: PasswordEncoder
) {
    val storage = mutableMapOf<String, Member>()

    fun save(member: Member) {
        storage[member.id] = Member(member.id, passwordEncoder.encode(member.password))
    }

    fun get(id: String) = storage[id] ?: throw RuntimeException()

}