package org.example.mobileleoffers.model.user;

import org.example.mobileleoffers.model.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class MobileleUserDetails extends User {
    private String firstName;
    private String lastName;
    private String userId;

    public MobileleUserDetails(
            String email,
            String password,
            Collection<? extends GrantedAuthority> authorities,
            String firstName,
            String lastName,
            String userId
    ) {
        super(email, password, authorities);
        this.firstName = firstName;
        this.lastName = lastName;
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFullName() {
        StringBuilder fullName = new StringBuilder();
        if (firstName != null) {
            fullName.append(firstName);
        }
        if (lastName != null) {
            if (!fullName.isEmpty()) {
                fullName.append(" ");
            }
            fullName.append(lastName);
        }

        return fullName.toString();
    }

    public static MobileleUserDetails mapToMobileleUserDetails(UserEntity userEntity) {
        return new MobileleUserDetails(userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.getRoles().stream()
                        .map(r -> new SimpleGrantedAuthority("ROLE_" + r.getRole().name()))
                        .toList(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getUserId().toString()
        );
    }
}
