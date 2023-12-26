package com.annm.zilliqa_project.dto;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserDto {
    @Size(min=2, message = "độ dài tối thiểu là 1")
    private String username;

    @Size(min = 5, max = 15, message = "Mật khẩu không hợp lệ!(5-15 kí tự)")
    private String password;

    private String repeatPassword;

    @Size(min = 2, max = 10, message = "Tên không hợp lệ!(1-10 kí tự)")
    private String firstName;

    @Size(min = 2, max = 10, message = "Tên không hợp lệ!(1-10 kí tự)")
    private String lastName;


    @Override
    public String toString() {
        return "RegisterUser{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", repeatPassword='" + repeatPassword + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
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

    public UserDto() {
    }

    public UserDto(String username, String password, String repeatPassword, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
