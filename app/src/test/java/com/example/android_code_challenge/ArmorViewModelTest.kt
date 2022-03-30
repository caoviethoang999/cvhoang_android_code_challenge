package com.example.android_code_challenge

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.android_code_challenge.model.ArmorModel
import com.example.android_code_challenge.repository.IArmorRepository
import com.example.android_code_challenge.viewmodel.ArmorViewModel
import com.nhaarman.mockitokotlin2.given
import io.reactivex.Single
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.net.SocketTimeoutException
import java.net.UnknownHostException

@ExperimentalCoroutinesApi
class ArmorViewModelTest {
    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var testSchedulerRule = RxImmediateScheduler()

    // Subject under test
    private lateinit var viewModel: ArmorViewModel

    @Mock
    private lateinit var repository: IArmorRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewModel = ArmorViewModel(repository)
    }

    @Test
    fun `test get Armor list return result`() {
        val response = listOf(ArmorModel(id = 1))
        given { repository.fetchArmor() }.willReturn(Single.just(response))
        viewModel.getArmor()
        //TODO:
        assert(viewModel.armorList.value == response)
    }

    @Test
    fun `test get Armor list return empty`() {
        val response = listOf<ArmorModel>()
        given { repository.fetchArmor() }.willReturn(Single.just(response))
        viewModel.getArmor()
        //TODO:
        assert(viewModel.armorList.value == response)
    }

    @Test
    fun `test get Armor list return list`() {
        val response = listOf(ArmorModel(id = 1), ArmorModel(id = 2))
        given { repository.fetchArmor() }.willReturn(Single.just(response))
        viewModel.getArmor()
        //TODO:
        assert(viewModel.armorList.value?.size == 2)
    }

    @Test
    fun `test get Armor list return network error`() {
        val error = UnknownHostException()
        val response = listOf(ArmorModel(id = 1),ArmorModel(id = 2))
        given { repository.fetchArmor() }.willReturn(Single.error(error))
        given { repository.getAllArmorLocal() }.willReturn(Single.just(response))
        viewModel.getArmor()
        //TODO:
        assert(viewModel.armorList.value?.size == 2)
    }

    @Test
    fun `test get armor list return socket time out error`() {
        val error = SocketTimeoutException()
        val message = "Socket Time out. Please try again."
        given { repository.fetchArmor() }.willReturn(Single.error(error))
        viewModel.getArmor()
        //TODO:
        assert(viewModel.message.value == message)
    }
    @Test
    fun `test get ArmorFromLocal list return result`() {
        val response = listOf(ArmorModel(id = 1))
        given { repository.getAllArmorLocal() }.willReturn(Single.just(response))
        viewModel.getArmorLocal()
        //TODO:
        assert(viewModel.armorList.value == response)
    }

    @Test
    fun `test get ArmorFromLocal list return empty`() {
        val response = listOf(ArmorModel())
        given { repository.getAllArmorLocal() }.willReturn(Single.just(response))
        viewModel.getArmorLocal()
        //TODO:
        assert(viewModel.armorList.value == response)
    }

    @Test
    fun `test get ArmorFromLocal list return list`() {
        val response = listOf(ArmorModel(id = 1), ArmorModel(id = 2))
        given { repository.getAllArmorLocal() }.willReturn(Single.just(response))
        viewModel.getArmorLocal()
        //TODO:
        assert(viewModel.armorList.value?.size == 2)
    }

    @Test
    fun `test search ArmorByName list return result`() {
        val response = listOf(ArmorModel(id = 1, name = "Leather"))
        val name="Leather"
        given { repository.searchArmorByName(name) }.willReturn(Single.just(response))
        viewModel.searchArmorByName(name)
        //TODO:
        assert(viewModel.armorList.value == response)
    }

    @Test
    fun `test search ArmorByName list return empty`() {
        val response = listOf<ArmorModel>()
        val name = "Bone"
        given { repository.searchArmorByName(name) }.willReturn(Single.just(response))
        viewModel.searchArmorByName(name)
        //TODO:
        assert(viewModel.armorList.value == response)
    }

    @Test
    fun `test search ArmorByName list return list`() {
        val response = listOf(ArmorModel(id = 1, name = "Leather Belt"), ArmorModel(id = 2, name = "Leather Chest"))
        val name="Leather"
        given { repository.searchArmorByName(name) }.willReturn(Single.just(response))
        viewModel.searchArmorByName(name)
        //TODO:
        assert(viewModel.armorList.value?.size == 2)
    }


    // @Test
    // fun `test something`(){
    //     val backingLiveData = MutableLiveData<String>()
    //     val testLiveData = backingLiveData.map { it.toUpperCase() }
    //     backingLiveData.value = "abc"
    //     assert(backingLiveData.value.equals("abc"))
    //     println(backingLiveData.value)
    //     testLiveData.getOrAwaitValue() == "ABC"
    //     println(testLiveData.value)
    // }
}
