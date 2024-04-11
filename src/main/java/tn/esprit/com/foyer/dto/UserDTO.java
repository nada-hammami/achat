package tn.esprit.com.foyer.dto;

import tn.esprit.com.foyer.entities.User;


public class UserDTO {

    private String email;
    private String role;
    // Other fields as needed

    public UserDTO() {
        // Default constructor
    }

    public UserDTO( String email, String role) {

        this.email = email;
        this.role = role;
        // Initialize other fields as needed
    }
    public static UserDTO fromEntity(User user) {
        UserDTO dto = new UserDTO();
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole().toString());
        // Set other fields as needed
        return dto;
    }
    // Getter and Setter methods for firstName


    // Getter and Setter methods for email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getter and Setter methods for role
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // Other getters and setters for additional fields
}
