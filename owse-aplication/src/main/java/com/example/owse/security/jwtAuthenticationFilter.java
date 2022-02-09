package com.example.owse.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.owse.constante.jWTConstante;
import com.example.owse.util.jwtTokenUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;


@Component
public class jwtAuthenticationFilter extends OncePerRequestFilter {

	
	@Autowired
	private jwtUserService JwtUsersService;
	
	@Autowired
	private jwtTokenUtil JwtTokenUtil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String header = request.getHeader(jWTConstante.HEADER_STRING);
		String username = null;
		String authToken = null;
		
		if(header != null && header.startsWith(jWTConstante.TOKEN_PREFIX)) {
			authToken = header.replace(jWTConstante.TOKEN_PREFIX, "");
			username = JwtTokenUtil.getUsernameFromToken(authToken);
		}
		
		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = JwtUsersService.loadUserByUsername(username);
			
			if(JwtTokenUtil.validateToken(authToken, userDetails)) {
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, 
							Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));
				
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authentication);
				
			}
		}
		
		filterChain.doFilter(request, response);
		
	}
	
}
