package org.CFT.client;

public class FailedToCreateProjectException extends Exception {
    @Override
    public String getMessage(){
        return "Could not create project.";
    }
}
