package com.capgemini.web.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ObjectNotFoundException extends Exception{
    public ObjectNotFoundException(){
        super();
    }
    public ObjectNotFoundException(String message){
        super(message);
    }
}
