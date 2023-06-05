package com.example.challengechapterlima.ui.favorite

import CoroutineTestRule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.challengechapterlima.data.local.entity.FavoriteEntity
import com.example.challengechapterlima.data.repository.LocalRepository
import com.google.firebase.auth.FirebaseUser
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class FavoriteViewModelTest{

    @get:Rule
    val instantExecutor = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = CoroutineTestRule()

    private lateinit var viewModel : FavoriteViewModel

    @Mock
    private lateinit var localRepository: LocalRepository

    @Mock
    private lateinit var favoriteObserver : Observer<List<FavoriteEntity>>

    @Mock
    private lateinit var userObserver: Observer<FirebaseUser?>

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        viewModel = FavoriteViewModel(localRepository)
        viewModel.favorite.observeForever(favoriteObserver)
        viewModel.user.observeForever(userObserver)
    }

    @After
    fun tearDown(){
        viewModel.favorite.removeObserver(favoriteObserver)
        viewModel.user.removeObserver(userObserver)
    }

    @Test
    fun `test getAllFavorites`() = coroutineRule.testDispatcher.runBlockingTest {

        //arrange
        val uuid = "example-uuid"
        val favoriteList = listOf(
            FavoriteEntity(1, "wkwkwkwk", "wkwwkkwkw", "wkwkwkwk"),
            FavoriteEntity(2, "wkwkwkwk", "wkwkwkwkw", "wkwkwkwk")
        )
        Mockito.`when`(localRepository.getFavoriteTickets(uuid)).thenReturn(favoriteList)

        viewModel.getAllFavorites(uuid)

        verify(favoriteObserver).onChanged(favoriteList)
    }
}