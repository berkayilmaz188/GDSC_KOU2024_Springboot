package com.example.demo.user.dto;


import com.example.demo.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class UserDTO {

    int id;
    String username;

    String name;

    String surname;

    String phoneNumber;

    String email;

    String city;

    String latitude;

    String longitude;
    String activationToken;
    String password;



    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.phoneNumber = user.getPhoneNumber();
        this.city = user.getCity();
        this.email = user.getEmail();
        this.latitude = user.getLatitude();
        this.longitude = user.getLongitude();
    }

}
