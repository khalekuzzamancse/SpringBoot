package com.khalekuzzaman.just.cse.springboot.samples.data_source.mock

import com.khalekuzzaman.just.cse.springboot.samples.data_source.BankDataSource
import com.khalekuzzaman.just.cse.springboot.samples.model.Bank
import org.springframework.stereotype.Repository

/**
 * This is mock unit test
 */
@Repository
class MockBankDataSource:BankDataSource {
    override fun retrieveBanks(): Collection<Bank> {
        val banks=listOf(Bank("1234",1.0,1))
       return banks
    }
}