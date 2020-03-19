package com.interview.calculator.exception;

import java.util.Date;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "error")
public class ErrorDetails
{
    public ErrorDetails(String message, List<String> details) {
        super();
        this.message = message;
        this.details = details;
    }

    //General error message about nature of error
    private String message;

    //Specific errors in API request processing
    private List<String> details;


    public String getMessage() {
        return message;
    }

    public List<String> getDetails() {
        return details;
    }
    //Getter and setters
}
