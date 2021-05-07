package com.demo.keycloak_java_demo.utils;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDetails {

    /** The user id. */
    private String userId;

    /** The username. */
    private String username;

    /** The list roles. */
    private List<String> listRoles;

    /** The list groups. */
    private List<String> listGroups;

    /**
     * Instantiates a new user details.
     */
    public UserDetails() {
        listRoles = new ArrayList<>();
        listGroups = new ArrayList<>();
    }
}
