package com.bank.accountSystem.service;

import com.bank.accountSystem.model.Account;
import com.bank.accountSystem.repository.iAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {
    @Mock
    private iAccountRepository accountRepository;
    @Mock
    private AccountService accountService;
    @BeforeEach
    public void setUp(){
        accountRepository = mock(iAccountRepository.class);
        accountService = new AccountService(accountRepository);
    }
    @Test
    void getAllAccounts() {
        // Mocking the behavior of accountRepository.findAll()
        List<Account> mockAccounts = new ArrayList<>();
        mockAccounts.add(new Account("123", 100.0));
        mockAccounts.add(new Account("456", 200.0));
        when(accountRepository.findAll()).thenReturn(mockAccounts);

        List<Account> result = accountService.getAllAccounts();

        // Verify that the result matches the mock accounts
        assertEquals(mockAccounts, result);
    }
}