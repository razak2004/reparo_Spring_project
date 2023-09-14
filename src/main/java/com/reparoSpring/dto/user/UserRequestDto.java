package com.reparoSpring.dto.user;

public class UserRequestDto extends UserResponseDto {
  private  String password;

    public UserRequestDto( String name, long number,String pass , int role) {
        super( name, number,role);
        this.password = pass;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
