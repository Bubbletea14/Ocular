package com.github.bubbletea14.ocular.ocular.tables;
import jakarta.persistence.*;;

@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true)
    private String username;
    
    private String password;
    private String email;

    public Users() {

    }

    public Users (long id, 
                String username, 
                String password, 
                String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    // Getter and Setter
    public long getId() {
        return id;
    }

    public void setId(Long newId) {
        this.id = newId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String newUsername) {
        this.username = newUsername;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User {" + 
                "UserID=" + id +
                ", Username=" + username + 
                ", Password='" + password + '\'' +
                ", email='" + email + '\'' + 
                '}';
    }

    
}
