package com.example.back.config.security

import com.example.back.member.MemberRepository
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailServiceImpl (
    private val memberRepository: MemberRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String?) =
        MemberAuth(memberRepository.get(username!!))

}