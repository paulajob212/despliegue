package com.bank.accountSystem.service;

import com.bank.accountSystem.dto.TransferRequest;
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
import static org.mockito.Mockito.*;

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

    @Test
    public void testFindByAccountNumber() {
        // Mocking the behavior of accountRepository.findByAccountNumber()
        String accountNumber = "123456";
        Account mockAccount = new Account(accountNumber, 100.0);
        when(accountRepository.findByAccountNumber(accountNumber)).thenReturn(mockAccount);

        // Call the method to be tested
        Account result = accountService.findByAccountNumber(accountNumber);

        // Verify that the result matches the mock account
        assertEquals(mockAccount, result);
    }

    @Test
    public void testDeleteAccountByAccNumber() {
        // Mocking the behavior of accountRepository.findByAccountNumber()
        String accountNumber = "123456";
        Account mockAccount = new Account(accountNumber, 100.0);
        when(accountRepository.findByAccountNumber(accountNumber)).thenReturn(mockAccount);

        // Call the method to be tested
        accountService.deleteAccountByAccNumber(accountNumber);

        // Verify that accountRepository.delete() is called with the correct account
        verify(accountRepository, times(1)).delete(mockAccount);
    }


    @Test
    public void testSaveAccount() {
        // Mocking the behavior of accountRepository.save()
        Account accountToSave = new Account("123456", 1000.0);
        Account savedAccount = new Account("123456", 1000.0);
        when(accountRepository.save(accountToSave)).thenReturn(savedAccount);

        // Call the method to be tested
        Account result = accountService.saveAccount(accountToSave);

        // Verify that accountRepository.save() is called with the correct account
        verify(accountRepository, times(1)).save(accountToSave);

        // Verify that the returned account matches the saved account
        assertEquals(savedAccount, result);
    }

}