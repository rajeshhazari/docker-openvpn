package org.openvpn.api.controller;

public class ResourceNotFoundException extends Throwable{

    public ResourceNotFoundException(String message){
        super(message);
    }
}
