package com.khalekuzzaman.just.cse.springboot.samples.service

import com.khalekuzzaman.just.cse.springboot.samples.data_source.BankDataSource
import com.khalekuzzaman.just.cse.springboot.samples.model.Bank
import org.springframework.stereotype.Service

@Service
class BankService (
    private val dataSource:BankDataSource
){
    fun getBanks(): Collection<Bank> {
        return dataSource.retrieveBanks()
    }
}