package com.annm.zilliqa_project.service.serviceImpl;

import com.annm.zilliqa_project.entity.Blocks;
import com.annm.zilliqa_project.entity.Exceptions;
import com.annm.zilliqa_project.entity.Transactions;
import com.annm.zilliqa_project.repository.TransactionRepository;
import com.annm.zilliqa_project.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public Long count() {
        return transactionRepository.count();
    }

    @Override
    public List<Transactions> getByBlockNumber(int id) {
        List<Transactions> transactions =  transactionRepository.findByBlocksNumber(id);
        return transactions;
    }

    @Override
    public Transactions getByNumber(int id) {
        Transactions transaction = transactionRepository.getById(id);
        return transaction;
    }

    @Override
    public Transactions getById(int id) {
        Transactions transactions = transactionRepository.findById(id);
        return transactions;
    }

    @Override
    public Transactions update(Transactions transactions) {
        Transactions transaction = transactionRepository.getById(transactions.getT_id());
        transaction.setT_id(transactions.getT_id());
        transaction.setBlockTimestamp(transactions.getBlockTimestamp());
        transaction.setAmount(transactions.getAmount());
        transaction.setCumulativeGas(transactions.getCumulativeGas());
        transaction.setData(transactions.getData());
        transaction.setSender(transactions.getSender());
        transaction.setToAddress(transactions.getToAddress());
        transaction.setSenderPubKey(transactions.getSenderPubKey());
        transaction.setSignature(transactions.getSignature());
        transaction.setSuccess(transactions.isSuccess());
        transaction.setTransactionId(transactions.getTransactionId());
        transaction.setVersion(transactions.getVersion());
        return transactionRepository.save(transaction);
    }

    @Override
    public Transactions getByTransactionId(String id) {
        Transactions transactions = transactionRepository.findByTransactionId(id);
        return transactions;
    }

    @Override
    public List<Transactions> findTop10Transactions() {
        List<Transactions> transactions = transactionRepository.findFirst10ByOrderByAmountDesc();
        return transactions;
    }

    @Override
    public Page<Transactions> searchTransactions(int pageNo, String keyword) {
        List<Transactions> transactions = transactionRepository.findAllByKeyWord(keyword);
        Pageable pageable = PageRequest.of(pageNo, 5);
        Page<Transactions> transactionsPage = toPage(transactions, pageable);
        return transactionsPage;
    }

    private Page toPage(List<Transactions> list, Pageable pageable){
        if (pageable.getOffset() >= list.size()){
            return Page.empty();
        }
        int startIndex = (int) pageable.getOffset();
        int endIndex = ((pageable.getOffset() + pageable.getPageSize()) >= list.size())
                ? list.size() : (int) (pageable.getOffset() + pageable.getPageSize());
        List subList = list.subList(startIndex, endIndex);
        return new PageImpl(subList, pageable, list.size());
    }
}
