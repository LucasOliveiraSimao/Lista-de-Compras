package com.lucassimao.listadecompras.data

import com.google.common.truth.Truth
import com.lucassimao.listadecompras.data.db.PurchaseDAO
import com.lucassimao.listadecompras.data.model.PurchaseModel
import com.lucassimao.listadecompras.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Test

class PurchaseRepositoryTest : BaseUnitTest() {

    private val dao: PurchaseDAO = mock()
    private val purchaseItem = mock<PurchaseModel>()
    private val purchaseList = mock<List<PurchaseModel>>()
    private val purchaseListEmpty = listOf<PurchaseModel>()

    @Test
    fun shouldInsertPurchase_inDatabase(): Unit = runBlocking {
        val repository = PurchaseRepository(dao)

        repository.insert(purchaseItem)

        verify(dao, times(1)).insertPurchase(purchaseItem)
    }

    @Test
    fun shouldUpdatePurchase_inDatabase(): Unit = runBlocking {
        val repository = PurchaseRepository(dao)

        repository.update(purchaseItem)

        verify(dao, times(1)).updatePurchase(purchaseItem)
    }

    @Test
    fun shouldDeletePurchase_inDatabase(): Unit = runBlocking {
        val repository = PurchaseRepository(dao)

        repository.delete(purchaseItem)

        verify(dao, times(1)).deletePurchase(purchaseItem)
    }

    @Test
    fun shouldGetAllPurchase_fromDatabase(): Unit = runBlocking {

        val repository = mockSuccessfulCase()

        Truth.assertThat(purchaseList).isEqualTo(repository.getAllPurchase.first())
    }

    @Test
    fun shouldReturnEmptyList_whenGetAllPurchaseIsEmpty(): Unit = runBlocking {

        val repository = mockEmptyCase()

        Truth.assertThat(purchaseListEmpty.size).isEqualTo(repository.getAllPurchase.first().size)
    }

    private fun mockEmptyCase(): PurchaseRepository {
        whenever(dao.getAllPurchase()).thenReturn(
            flow {
                emit(purchaseListEmpty)
            }
        )

        return PurchaseRepository(dao)
    }

    private fun mockSuccessfulCase(): PurchaseRepository {
        whenever(dao.getAllPurchase()).thenReturn(
            flow {
                emit(purchaseList)
            }
        )

        return PurchaseRepository(dao)
    }

}