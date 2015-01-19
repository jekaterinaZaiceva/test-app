package lv.j2304z.testapp.services;

import lv.j2304z.testapp.services.exceptions.DublicatedSymbolException;
import lv.j2304z.testapp.services.exceptions.SmallPasswodsException;
import lv.j2304z.testapp.services.exceptions.UserAlreadyExistException;
import org.junit.Before;
import org.junit.Test;

public class UserServiceTest {
    PasswordCheckService passwordCheckServise;

    @Before
    public void setup(){
        passwordCheckServise = new PasswordCheckService();

    }
    @Test(expected = SmallPasswodsException.class)
    public void testSmallPasswordFail()throws DublicatedSymbolException,UserAlreadyExistException,SmallPasswodsException{
        passwordCheckServise.check("qwer");
    }

    @Test
    public void testSmallPasswordSuccess()throws DublicatedSymbolException,UserAlreadyExistException,SmallPasswodsException{
        passwordCheckServise.check("qwert");
    }
    @Test
    public void testDublicateSimbolsSuccess()throws DublicatedSymbolException,UserAlreadyExistException,SmallPasswodsException{
        passwordCheckServise.check("aaaa1");
    }
    @Test(expected = DublicatedSymbolException.class)
    public void testDublicateSimbolsError()throws DublicatedSymbolException,UserAlreadyExistException,SmallPasswodsException{
        passwordCheckServise.check("aaaaa1");
    }
    @Test(expected = DublicatedSymbolException.class)
    public void testDublicateSimbolsMixedCase()throws DublicatedSymbolException,UserAlreadyExistException,SmallPasswodsException{
        passwordCheckServise.check("aaaAa");
    }
    @Test(expected = DublicatedSymbolException.class)
    public void testDublicateSimbolsNumbers()throws DublicatedSymbolException,UserAlreadyExistException,SmallPasswodsException{
        passwordCheckServise.check("11111");
    }
    @Test(expected = DublicatedSymbolException.class)
    public void testDublicateAllUpper()throws DublicatedSymbolException,UserAlreadyExistException,SmallPasswodsException{
        passwordCheckServise.check("AAAAA");
    }
}