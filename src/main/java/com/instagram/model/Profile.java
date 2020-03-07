package com.instagram.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Profile  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "user_name")
    private String user_name;

    public Profile() {
        super();
    }

    public Profile(Long id, String user_name) {
        super();
        this.id = id;
        this.user_name = user_name;
    }

    @Override
    public String toString() {
        return "Profile [id=" + id + ", user_name=" + user_name + "]";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

}  //End of Class
