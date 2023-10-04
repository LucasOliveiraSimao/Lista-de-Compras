package com.lucassimao.listadecompras.ui

import com.google.common.truth.Truth
import com.lucassimao.listadecompras.data.PurchaseRepository
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

class PurchaseViewModelTest : BaseUnitTest() {

    private val repository: PurchaseRepository = mock()
    private val viewModel = PurchaseViewModel(repository)
    private val purchase = PurchaseModel(0, "Product", 1, "1.99")
    private val purchaseItem = PurchaseModel(1, "Product", 1, "1.99")
    private val updatePurchaseItem = PurchaseModel(1, "Product", 3, "1.99")
    private val deletePurchaseItem = PurchaseModel(1, "Product", 3, "1.99")
    private val purchaseList = arrayListOf(
        PurchaseModel(1, "Product", 1, "1.99"),
        PurchaseModel(2, "Feijão", 4, "R$ 24,00"),
        PurchaseModel(3, "Frango", 1, "R$ 32,00"),
    )
    private val purchaseListWithUpdatedItem = listOf(
        PurchaseModel(1, "Product", 3, "1.99"),
        PurchaseModel(2, "Feijão", 4, "R$ 14,00"),
        PurchaseModel(3, "Frango", 1, "R$ 32,00"),
    )
    private val purchaseListWithDeletedItem = listOf(
        PurchaseModel(2, "Feijão", 4, "R$ 14,00"),
        PurchaseModel(3, "Frango", 1, "R$ 32,00")
    )
    private val emptyPurchaseList = listOf<PurchaseModel>()

    @Test
    fun `should invoke the insert function into the repository layer's database`(): Unit =
        runBlocking {

            viewModel.insert("Product", 1, "1.99")

            verify(repository, times(1)).insert(purchase)
        }

    @Test
    fun `should insert new purchase in the database`(): Unit = runBlocking {

        viewModel.insert("Product", 1, "1.99")

        val actual = mockSuccessfulCase().getAllPurchase.first()[0]

        Truth.assertThat(actual).isEqualTo(purchaseItem)
    }

    @Test
    fun `should invoke the update function into the repository layer's database`(): Unit =
        runBlocking {

            viewModel.update(1, "Product", 1, "1.99")

            verify(repository, times(1)).update(purchaseItem)
        }

    @Test
    fun `should update data of a purchase saved in the database`(): Unit = runBlocking {

        viewModel.update(1, "Product", 3, "1.99")

        val actual = mockUpdateSuccessfulCase().getAllPurchase.first()[0]

        Truth.assertThat(actual).isEqualTo(updatePurchaseItem)
    }

    @Test
    fun `should invoke the delete function into the repository layer's database`(): Unit =
        runBlocking {

            viewModel.delete(purchaseItem)

            verify(repository, times(1)).delete(purchaseItem)
        }

    @Test
    fun `should delete an item from the purchase list`(): Unit = runBlocking {

        viewModel.delete(deletePurchaseItem)

        val actual = mockDeleteSuccessfulCase().getAllPurchase.first()[0]

        Truth.assertThat(actual).isNotEqualTo(deletePurchaseItem)
    }

    @Test
    fun `should invoke the deleteAllPurchases function into the repository layer's database`(): Unit =
        runBlocking {

            viewModel.deleteAllPurchase()

            verify(repository, times(1)).deleteAllPurchases()
        }

    @Test
    fun `should delete all items from the purchase list`(): Unit = runBlocking {

        var actual = listOf<PurchaseModel>()

        viewModel.deleteAllPurchase()

        mockEmptyCase().getAllPurchase.collect {
            actual = it
        }

        Truth.assertThat(actual).isEqualTo(emptyPurchaseList)
    }

    @Test
    fun `should return a purchase list`(): Unit = runBlocking {

        viewModel.getAllPurchase

        val actual = arrayListOf<PurchaseModel>()

        mockSuccessfulCase().getAllPurchase.collect { purchaseModels ->
            purchaseModels.forEach {
                actual.add(it)
            }
        }

        Truth.assertThat(actual).isEqualTo(purchaseList)
    }

    @Test
    fun `should return an empty purchase list`(): Unit = runBlocking {

        viewModel.getAllPurchase

        var actual = listOf<PurchaseModel>()

        mockEmptyCase().getAllPurchase.collect{
            actual = it
        }

        Truth.assertThat(actual).isEqualTo(emptyPurchaseList)
    }

    private fun mockEmptyCase(): PurchaseViewModel {
        whenever(repository.getAllPurchase).thenReturn(
            flow {
                emit(emptyPurchaseList)
            }
        )

        return PurchaseViewModel(repository)
    }

    private fun mockSuccessfulCase(): PurchaseViewModel {
        whenever(repository.getAllPurchase).thenReturn(
            flow {
                emit(purchaseList)
            }
        )

        return PurchaseViewModel(repository)
    }

    private fun mockUpdateSuccessfulCase(): PurchaseViewModel {
        whenever(repository.getAllPurchase).thenReturn(
            flow {
                emit(purchaseListWithUpdatedItem)
            }
        )

        return PurchaseViewModel(repository)
    }

    private fun mockDeleteSuccessfulCase(): PurchaseViewModel {
        whenever(repository.getAllPurchase).thenReturn(
            flow {
                emit(purchaseListWithDeletedItem)
            }
        )

        return PurchaseViewModel(repository)
    }

}