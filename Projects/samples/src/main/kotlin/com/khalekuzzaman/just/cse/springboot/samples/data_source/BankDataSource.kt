package com.khalekuzzaman.just.cse.springboot.samples.data_source

import com.khalekuzzaman.just.cse.springboot.samples.model.Bank

interface BankDataSource {
    fun retrieveBanks():Collection<Bank>
}