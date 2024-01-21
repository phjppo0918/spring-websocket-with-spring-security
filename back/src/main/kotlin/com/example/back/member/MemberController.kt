package com.example.back.member

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MemberController(
    private val memberRepository: MemberRepository
) {

    @PostMapping("/signup")
    fun signup(@RequestBody member: Member) {
        memberRepository.save(member)
    }

}