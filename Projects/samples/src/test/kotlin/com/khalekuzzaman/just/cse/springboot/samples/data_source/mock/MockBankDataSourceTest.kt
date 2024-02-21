package com.khalekuzzaman.just.cse.springboot.samples.data_source.mock

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * Make the test failed first
 */
internal class MockBankDataSourceTest {
    private val dataSource = MockBankDataSource()

    @Test
    fun `should provide a collection of bank`() {
        //given

        //when
        val banks = dataSource.retrieveBanks()
        //then
        assertThat(banks).isNotNull()
    }
    @Test
    fun `should provide some mock data`() {
        //given

        //when
        val banks = dataSource.retrieveBanks()
        //then
        assertThat(banks).allMatch{bank->bank.account.isNotEmpty()}
    }


}