package com.example.back.config.security

import com.example.back.member.Member
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class MemberAuth(
    private val member: Member
) : UserDetails {
    override fun getAuthorities() = listOf(SimpleGrantedAuthority("USER"))

    override fun getPassword() = member.password
    override fun getUsername() = member.id
    override fun isAccountNonExpired() = true
    override fun isAccountNonLocked() = true
    override fun isCredentialsNonExpired() = true
    override fun isEnabled() = true
}