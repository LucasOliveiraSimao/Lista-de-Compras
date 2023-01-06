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
    private val purchase = PurchaseModel(1, "Arroz", 1, "R$ 2,00")

    private val purchaseList = arrayListOf(
        PurchaseModel(1, "Arroz", 1, "R$ 2,00"),
        PurchaseModel(2, "Feijão", 4, "R$ 24,00"),
        PurchaseModel(3, "Frango", 1, "R$ 32,00"),
    )
    private val purchaseListWithUpdatedItem = listOf(
        PurchaseModel(1, "Macarrão", 5, "R$ 4,50"),
        PurchaseModel(2, "Feijão", 4, "R$ 14,00"),
        PurchaseModel(3, "Frango", 1, "R$ 32,00"),
    )
    private val purchaseListWithDeletedItem = listOf(
        PurchaseModel(2, "Feijão", 4, "R$ 14,00"),
        PurchaseModel(3, "Frango", 1, "R$ 32,00")
    )
    private val emptyPurchaseList = listOf<PurchaseModel>()

    @Test
    fun should_invoke_insert_function(): Unit = runBlocking {
        val repository = PurchaseRepository(dao)

        repository.insert(purchase)

        verify(dao, times(1)).insertPurchase(purchase)

    }

    @Test
    fun should_insert_new_purchase_in_database(): Unit = runBlocking {
        val repository = PurchaseRepository(dao)

        repository.insert(purchase)

        val expected = mockSuccessfulCase().getAllPurchase.first().contains(purchase)

        Truth.assertThat(expected).isTrue()

    }

    @Test
    fun should_invoke_update_function(): Unit = runBlocking {
        val repository = PurchaseRepository(dao)

        repository.update(purchase)

        verify(dao, times(1)).updatePurchase(purchase)

    }

    @Test
    fun should_update_purchase_fields_in_database(): Unit = runBlocking {
        val repository = mockUpdateSuccessfulCase()
        val updatedPurchase = PurchaseModel(1, "Macarrão", 5, "R$ 4,50")

        repository.update(updatedPurchase)

        val expected = mockUpdateSuccessfulCase().getAllPurchase.first().contains(updatedPurchase)

        Truth.assertThat(expected).isTrue()
    }

    @Test
    fun should_invoke_delete_function(): Unit = runBlocking {
        val repository = PurchaseRepository(dao)

        repository.delete(purchase)

        verify(dao, times(1)).deletePurchase(purchase)
    }

    @Test
    fun should_delete_an_item_from_purchase_list(): Unit = runBlocking {
        val repository = mockDeleteSuccessfulCase()

        repository.delete(purchase)

        val expected = mockDeleteSuccessfulCase().getAllPurchase.first().contains(purchase)

        Truth.assertThat(expected).isFalse()
    }

    @Test
    fun should_invoke_deleteAll_function(): Unit = runBlocking {
        val repository = mockEmptyPurchaseListCase()

        repository.deleteAllPurchases()

        verify(dao, times(1)).deleteAllPurchase()
    }

    @Test
    fun should_delete_all_purchase_list_from_database(): Unit = runBlocking {
        val repository = mockEmptyPurchaseListCase()

        repository.deleteAllPurchases()

        val expected = mockEmptyPurchaseListCase().getAllPurchase.first()

        Truth.assertThat(expected).isEqualTo(emptyPurchaseList)
    }

    @Test
    fun should_get_purchase_list_from_database(): Unit = runBlocking {

        val repository = mockSuccessfulCase()

        Truth.assertThat(purchaseList).isEqualTo(repository.getAllPurchase.first())
    }

    @Test
    fun should_invoke_getAllPurchase_function(): Unit = runBlocking {
        val repository = mockSuccessfulCase()

        repository.getAllPurchase

        verify(dao, times(1)).getAllPurchase()
    }

    @Test
    fun should_return_an_empty_list_when_there_are_no_purchase_saved_in_database(): Unit = runBlocking {

        val repository = mockEmptyPurchaseListCase()

        Truth.assertThat(emptyPurchaseList.size).isEqualTo(repository.getAllPurchase.first().size)
    }

    private fun mockEmptyPurchaseListCase(): PurchaseRepository {
        whenever(dao.getAllPurchase()).thenReturn(
            flow {
                emit(emptyPurchaseList)
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

    private fun mockUpdateSuccessfulCase(): PurchaseRepository {
        whenever(dao.getAllPurchase()).thenReturn(
            flow {
                emit(purchaseListWithUpdatedItem)
            }
        )

        return PurchaseRepository(dao)
    }

    private fun mockDeleteSuccessfulCase(): PurchaseRepository {
        whenever(dao.getAllPurchase()).thenReturn(
            flow {
                emit(purchaseListWithDeletedItem)
            }
        )

        return PurchaseRepository(dao)
    }

}