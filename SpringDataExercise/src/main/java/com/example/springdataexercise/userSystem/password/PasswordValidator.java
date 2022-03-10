package com.example.springdataexercise.userSystem.password;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class PasswordValidator implements ConstraintValidator<Password, String> {

    private int minLength;
    private int maxLength;
    private boolean containsDigit;
    private boolean containsLowerCase;
    private boolean containsUpperCase;
    private boolean containsSpecialSymbol;

    @Override
    public void initialize(Password password) {
        this.minLength = password.minLength();
        this.maxLength = password.maxLength();
        this.containsDigit = password.containsDigit();
        this.containsLowerCase = password.containsLowerCase();
        this.containsUpperCase = password.containsUpperCase();
        this.containsSpecialSymbol = password.containsSpecialSymbol();
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password.length() < this.minLength || password.length() > this.maxLength) {
            return false;
        }

        Pattern pattern = Pattern.compile("[A-Z]");
        Matcher matcher = pattern.matcher(password);
        if (!matcher.find() && this.containsUpperCase) {
            return false;
        }

        pattern = Pattern.compile("[a-z]");
        matcher = pattern.matcher(password);
        if (!matcher.find() && this.containsLowerCase) {
            return false;
        }

        pattern = Pattern.compile("[0-9]");
        matcher = pattern.matcher(password);
        if (!matcher.find() && this.containsDigit) {
            return false;
        }

        pattern = Pattern.compile("[$&+,:;=?@#|'<>.-^*()%!]");
        matcher = pattern.matcher(password);
        if (!matcher.find() && this.containsSpecialSymbol) {
            return false;
        }
        return true;
    }
}
