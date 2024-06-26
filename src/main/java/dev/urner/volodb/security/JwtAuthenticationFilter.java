package dev.urner.volodb.security;

import java.io.IOException;
import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtDecoder jwtDecoder;
  private final JwtToPrincipalConverter jwtToPrincipalConverter;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    try {
      extractTokenFromRequest(request)
          .map(jwtDecoder::decode)
          .map(jwtToPrincipalConverter::convert)
          .map(UserPrincipalAuthenticationToken::new)
          .ifPresent(authentication -> {
            System.out.println(authentication);
            SecurityContextHolder.getContext().setAuthentication(authentication);
          });
    } catch (TokenExpiredException e) {
      // Handle expired token specifically
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      response.getWriter().write("Token has expired");
    } catch (JWTDecodeException e) {
      // Handle invalid token
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      response.getWriter().write("Invalid token");
    } catch (Exception e) {
      // Handle other exceptions
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      response.getWriter().write("Unauthorized");
    }

    filterChain.doFilter(request, response);
  }

  private Optional<String> extractTokenFromRequest(HttpServletRequest request) {
    var token = request.getHeader("Authorization");
    if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
      return Optional.of(token.substring(7));

    }
    return Optional.empty();
  }

}
