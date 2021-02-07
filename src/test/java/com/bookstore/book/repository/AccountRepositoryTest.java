package com.bookstore.book.repository;

import com.bookstore.book.entities.Account;
import com.bookstore.book.repositories.AccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AccountRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void whenFindByEmail_ThenReturnAccount(){
        //when
        Account account = new Account("Ela","Bridge","elabridge@gmail.com",
                011111111,"1234","ROLE",true);
       entityManager.persistAndFlush(account);
        //given
        Account found = accountRepository.findByEmail(account.getEmail());
        //then
        assert(found.getId()).equals(account.getId());
    }

    @Test
    public void whenFindByInvalidEmail_ThenReturnNull(){
        //given
        Account found = accountRepository.findByEmail("x@gmail.com");
        //then
        assertNull(found);
    }

    @Test
    public void whenBanAccountById_ThenReturnFalse(){
        //when
        Account account = new Account("Ela","Bridge",
                "elabridge@gmail.com",011111111,"1234","ROLE",true);
        account = entityManager.persistAndFlush(account);
        //given
        accountRepository.banAccountById(account.getId(),false);
        account = entityManager.find(Account.class,account.getId());
        //then
        assertFalse(account.getActive());
    }
}
