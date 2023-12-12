package com.annm.zilliqa_project.web;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class RegisterUser {
    @NotNull(message = "thông tin bắt buộc")
    @Size(min=1, message = "độ dài tối thiểu là 1")
    private String username;

    @NotNull(message = "thông tin bắt buộc")
    @Size(min=8, message = "độ dài tối thiểu là 8")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[@#$%^&+=!])(?=\\S+$).*$",
            message = "Mật khẩu phải chứa ít nhất 1 chữ số và 1 ký tự đặc biệt")
    private String password;
    private String firstName;
    private String lastName;


}
