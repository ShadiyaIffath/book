package com.bookstore.book.services;

import com.bookstore.book.dto.CreateAccountDto;
import com.bookstore.book.entities.Account;
import com.bookstore.book.repositories.AccountRepository;
import com.bookstore.book.repositories.ReservationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class AccountServiceTest {
    @MockBean
    private AccountRepository accountRepository;
    @MockBean
    private ModelMapper modelMapper;
    @MockBean
    private ReservationRepository reservationRepository;
    @MockBean
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private AccountService accountService;

    @Test
    public void EmailInUseTest_Used()throws Exception{
        //arrange
        when(accountRepository.findByEmail(Mockito.anyString())).thenReturn(new Account());
        //act
        boolean valid = accountService.isEmailInUse("s@s.com");
        //assert
        assert(valid);
    }

    @Test
    public void EmailInUseTest_NotUsed()throws Exception{
        //arrange
        when(accountRepository.findByEmail(Mockito.anyString())).thenReturn(null);
        //act
        boolean valid = accountService.isEmailInUse("s@s.com");
        //assert
        assert(!valid);
    }

    @Test
    public void FindAccountByEmailTest()throws Exception{
        //arrange
        when(accountRepository.findByEmail(Mockito.anyString())).thenReturn(new Account());
        //act
        Account account = accountService.findByEmail("s@s.com");
        //assert
        assert(account != null);
    }

}
