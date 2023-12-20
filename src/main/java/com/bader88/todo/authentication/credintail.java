package com.bader88.todo.authentication;

import org.springframework.stereotype.Service;

@Service
public class credintail {
    public boolean IsValidCredintail(String username ,String password){
        if(username.equals("x") &&  password.equals("x"))
         return true;
        return false;
    }
}
