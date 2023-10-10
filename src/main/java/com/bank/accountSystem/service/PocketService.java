package com.bank.accountSystem.service;

import com.bank.accountSystem.dto.PocketRequest;
import com.bank.accountSystem.dto.TransferToPocketRequest;
import com.bank.accountSystem.model.Account;
import com.bank.accountSystem.model.Pocket;
import com.bank.accountSystem.repository.iAccountRepository;
import com.bank.accountSystem.repository.iPocketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PocketService {
    @Autowired
    private iAccountRepository accountRepository;
    @Autowired
    private iPocketRepository pocketRepository;
    public void createPocket(PocketRequest pocketRequest) {
        Account account = accountRepository.findByAccountNumber(pocketRequest.getAccountNumber());

        if (account == null) {
            throw new RuntimeException("Main account not found");
        }

        Pocket pocket = new Pocket();
        pocket.setPocketName(pocketRequest.getName());
        pocket.setPocketInitialBalance(pocketRequest.getPocketInitialBalance());
        pocket.setPocketNumber(pocketRequest.getPocketNumber());
        pocket.setAccount(account);

        if(pocketRequest.getPocketInitialBalance() > account.getInitial_balance()){
            throw new RuntimeException("Initial pocket balance cannot be greater than the account balance");
        }

        double newBalance = account.getInitial_balance() - pocketRequest.getPocketInitialBalance();
        account.setInitial_balance(newBalance);

        pocketRepository.save(pocket);
        accountRepository.save(account);
    }

    public void transferToPocket(TransferToPocketRequest transferToPocketRequest) {
        Account account = accountRepository.findByAccountNumber(transferToPocketRequest.getAccountNumber());

        if (account == null) {
            throw new RuntimeException("Main account not found");
        }

        Pocket pocket = pocketRepository.findByPocketNumber(transferToPocketRequest.getPocketNumber());

        if (pocket == null) {
            throw new RuntimeException("The pocket was not found");
        }

        if (account.getInitial_balance() < transferToPocketRequest.getAmount()) {
            throw new RuntimeException("Insufficient balance in main account");
        }

        double newAccountBalance = account.getInitial_balance() - transferToPocketRequest.getAmount();
        double newPocketBalance = pocket.getPocketInitialBalance() + transferToPocketRequest.getAmount();

        account.setInitial_balance(newAccountBalance);
        pocket.setPocketInitialBalance(newPocketBalance);

        accountRepository.save(account);
        pocketRepository.save(pocket);
    }
}
