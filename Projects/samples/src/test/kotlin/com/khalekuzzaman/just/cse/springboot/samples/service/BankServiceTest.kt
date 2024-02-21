package com.khalekuzzaman.just.cse.springboot.samples.service

import com.khalekuzzaman.just.cse.springboot.samples.data_source.BankDataSource
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

internal class BankServiceTest{
private val dataSource = mockk<BankDataSource>()
private val bankService = BankService(dataSource = dataSource)

@Test
fun `should call it data source to retrieve data`() {
    ///give
    every { dataSource.retrieveBanks() } returns emptyList()
    val banks = bankService.getBanks()

    //then
    //verify that dataSource.retrieve() bank is called
    //we want to make sure it called exactly once
    verify(exactly = 1) { dataSource. retrieveBanks()}


}
}