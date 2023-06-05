package com.example.challengechapterlima.data.repository

import com.example.challengechapterlima.data.local.dao.FavoriteDao
import com.example.challengechapterlima.data.local.entity.FavoriteEntity
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class LocalRepositoryImplTest{

    @Mock
    private lateinit var favoriteDao: FavoriteDao
    private lateinit var localRepository: LocalRepository

    @Before
    fun setup(){
        MockitoAnnotations.openMocks(this)
        localRepository = LocalRepositoryImpl(favoriteDao)
    }

    @Test
    fun testGetFavoriteTickets() = runBlocking {
        val uuidUser = "12345"
        val favoriteTickets = listOf(FavoriteEntity(1, "adsasd", "adasdasd", "adada"))

        // Buat objek mock untuk favoriteDao
        val favoriteDao = mock<FavoriteDao>()
        // Tentukan perilaku mock favoriteDao
        Mockito.`when`(favoriteDao.getFavoriteTickets(uuidUser)).thenReturn(favoriteTickets)
        // Gunakan objek mock favoriteDao pada localRepository
        val localRepository = LocalRepositoryImpl(favoriteDao)
        val result = localRepository.getFavoriteTickets(uuidUser)
        assertEquals(favoriteTickets, result)
    }

    @Test
    fun testDeleteFavorite() = runBlocking {
        val idMovie = 123
        val uuidUser = "12345"
        val result = localRepository.deleteFavorite(idMovie,uuidUser)
        Assert.assertNotNull(result)
    }
}