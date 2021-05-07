package com.demo.keycloak_java_demo.utils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.AccessToken;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.server.ResponseStatusException;

public class UserDetailsArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(UserDetails.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {

        if (supportsParameter(methodParameter)) {
            return createUserDetails(webRequest);
        }
        else {
            return WebArgumentResolver.UNRESOLVED;
        }
    }

    @SuppressWarnings("unchecked")
    private Object createUserDetails(NativeWebRequest webRequest) {
        KeycloakSecurityContext keycloakSecurityContext = (KeycloakSecurityContext) ((HttpServletRequest) webRequest.getNativeRequest()).getAttribute(KeycloakSecurityContext.class.getName());

        if (keycloakSecurityContext != null) {
            AccessToken token = keycloakSecurityContext.getToken();

            UserDetails userDetails = new UserDetails();

            userDetails.setUsername(token.getPreferredUsername());

            userDetails.setUserId(token.getSubject());

            userDetails.setListRoles(token.getRealmAccess().getRoles().stream().filter(s -> !s.equals("offline_access") && !s.equals("uma_authorization")).collect(Collectors.toList()));

            Map<String, Object> otherClaims = token.getOtherClaims();

            userDetails.setListGroups((List<String>) otherClaims.get("groups"));

            return userDetails;
        } else
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Login to access the application");
    }
}
