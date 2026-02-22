package com.rohit.springjpademo.entity.onetomany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rohit.springjpademo.entity.onetoone.User;
import jakarta.persistence.*;

@Entity
public class Family {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long familyId;
    private String familyName;
    private String familyEmail;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public Family() {
    }

    public Family(Long familyId, String familyName, String familyEmail, User user) {
        this.familyId = familyId;
        this.familyName = familyName;
        this.familyEmail = familyEmail;
        this.user = user;
    }

    public Long getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Long familyId) {
        this.familyId = familyId;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getFamilyEmail() {
        return familyEmail;
    }

    public void setFamilyEmail(String familyEmail) {
        this.familyEmail = familyEmail;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
