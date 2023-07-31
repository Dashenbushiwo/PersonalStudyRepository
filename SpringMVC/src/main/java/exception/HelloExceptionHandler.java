package main.java.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@ControllerAdvice
public class HelloExceptionHandler {

    @ExceptionHandler({ ArithmeticException.class})
    public ModelAndView handleArithmeticException(ArithmeticException e) {
        System.out.println("nnd" + e.getMessage());
        ModelAndView modelAndView = new ModelAndView("success");
        modelAndView.addObject("date", new Date());
        return modelAndView;

    }
}
