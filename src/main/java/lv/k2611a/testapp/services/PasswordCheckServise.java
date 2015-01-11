package lv.k2611a.testapp.services;

import lv.k2611a.testapp.services.exceptions.DublicatedSymbolException;
import lv.k2611a.testapp.services.exceptions.SmallPasswodsException;
import org.springframework.stereotype.Component;

/**
 * Created by jekaterina.zaiceva on 11.01.2015.
 */

@Component
public class PasswordCheckServise {


    public void check(String password) throws DublicatedSymbolException, SmallPasswodsException {
        if(!validateDublicatedSymbols(password)){
            throw new DublicatedSymbolException();
        }
        if(!validLenghtPassword(password)){
            throw new SmallPasswodsException();
        }
    }
    private boolean validateDublicatedSymbols(final String password) {
        String passwordLower = password.toLowerCase();
        int countOfDublicated;
        char symbol;
        for (int i = 0; i < passwordLower.length(); ++i) {
            countOfDublicated = 0;
            symbol = passwordLower.charAt(i);
            for (int j = i; j < passwordLower.length(); ++j) {
                if (passwordLower.charAt(j) == symbol) {
                    if (++countOfDublicated > 4) return false;
                }
            }
        }
        return true;
    }
    private boolean validLenghtPassword(final String password){
        if(password.length()>4){
            return true;
        }
        else return  false;
    }
}
