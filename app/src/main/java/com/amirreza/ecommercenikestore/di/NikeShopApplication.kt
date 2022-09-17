package com.amirreza.ecommercenikestore.di

import android.app.Application
import android.content.SharedPreferences
import android.os.Bundle
import androidx.room.Room
import com.amirreza.ecommercenikestore.database.AppDataBase
import com.amirreza.ecommercenikestore.features.feature_auth.data.repository.AuthRepositoryImpl
import com.amirreza.ecommercenikestore.features.feature_auth.data.source.local.AuthLocalDataSourceImp
import com.amirreza.ecommercenikestore.features.feature_auth.data.source.remote.AuthRemoteDataSourceImpl
import com.amirreza.ecommercenikestore.features.feature_auth.domain.repository.AuthRepository
import com.amirreza.ecommercenikestore.features.feature_auth.presentation.AuthViewModel
import com.amirreza.ecommercenikestore.http.createInstanceOfApiService
import com.amirreza.ecommercenikestore.features.feature_store.data.repository.CommentRepositoryImpl
import com.amirreza.ecommercenikestore.features.feature_store.data.repository.FrescoImageLoadingService
import com.example.nikeshop.feature_shop.data.repository.BannerRepositoryImpl
import com.amirreza.ecommercenikestore.features.feature_store.data.repository.ProductRepositoryImpl
import com.amirreza.ecommercenikestore.features.feature_store.data.source.banner_data_source.BannerRemoteDataSource
import com.amirreza.ecommercenikestore.features.feature_cart.data.data_source.cart_data_source.CartDataSourceI
import com.amirreza.ecommercenikestore.features.feature_cart.data.data_source.cart_data_source.CartRemoteDataSource
import com.amirreza.ecommercenikestore.features.feature_cart.data.repository.CartRepositoryImpl
import com.amirreza.ecommercenikestore.features.feature_cart.data.repository.OrderRepositoryImpl
import com.amirreza.ecommercenikestore.features.feature_cart.domain.repository.CartShoppingRepository
import com.amirreza.ecommercenikestore.features.feature_cart.domain.repository.OrderRepository
import com.amirreza.ecommercenikestore.features.feature_store.data.source.comment_data_source.RemoteCommentDataSource
import com.amirreza.ecommercenikestore.features.feature_store.data.source.product_data_spurce.ProductLocalDataSource
import com.amirreza.ecommercenikestore.features.feature_store.domain.repository.CommentRepositoryI
import com.amirreza.ecommercenikestore.features.feature_store.domain.repository.ImageLoaderI
import com.amirreza.ecommercenikestore.features.feature_store.data.source.product_data_spurce.ProductRemoteDataSource
import com.example.nikeshop.feature_shop.domain.repository.BannerRepositoryI
import com.amirreza.ecommercenikestore.features.feature_store.domain.repository.ProductRepositoryI
import com.amirreza.ecommercenikestore.features.feature_store.domain.useCases.BannerUseCases
import com.amirreza.ecommercenikestore.features.feature_cart.domain.useCases.CartUseCase
import com.amirreza.ecommercenikestore.features.feature_store.domain.useCases.CommentUseCase
import com.amirreza.ecommercenikestore.features.feature_store.domain.useCases.ProductUseCases
import com.amirreza.ecommercenikestore.features.feature_store.domain.useCases.banner_usecase.GetBannerUC
import com.amirreza.ecommercenikestore.features.feature_cart.domain.useCases.cart_usecases.*
import com.amirreza.ecommercenikestore.features.feature_cart.presentation.card_fragment.CartViewModel
import com.amirreza.ecommercenikestore.features.feature_cart.presentation.checkout_fragment.CheckoutViewModel
import com.amirreza.ecommercenikestore.features.feature_cart.presentation.receipt_fragment.OrderResultViewModel
import com.amirreza.ecommercenikestore.features.feature_profile.data.FavoriteRepositoryImpl
import com.amirreza.ecommercenikestore.features.feature_profile.data.OrderHistoryRepositoryImpl
import com.amirreza.ecommercenikestore.features.feature_profile.data.ProfileRepositoryImpl
import com.amirreza.ecommercenikestore.features.feature_profile.domain.repo.FavoriteRepository
import com.amirreza.ecommercenikestore.features.feature_profile.domain.repo.OrderHistoryRepository
import com.amirreza.ecommercenikestore.features.feature_profile.domain.repo.ProfileRepository
import com.amirreza.ecommercenikestore.features.feature_profile.presentation.favorites_fragment.FavoriteViewModel
import com.amirreza.ecommercenikestore.features.feature_profile.presentation.order_history_fragment.OrderHistoryViewModel
import com.amirreza.ecommercenikestore.features.feature_profile.presentation.profile_fragment.ProfileViewModel
import com.amirreza.ecommercenikestore.features.feature_store.domain.useCases.comment_usecases.GetComments
import com.amirreza.ecommercenikestore.features.feature_store.domain.useCases.product_usecases.GetProductsUC
import com.amirreza.ecommercenikestore.features.feature_store.presentation.MainActivityViewModel
import com.amirreza.ecommercenikestore.features.feature_store.presentation.all_comments_fragment.AllCommentViewModel
import com.amirreza.ecommercenikestore.features.feature_store.presentation.all_product_fragment.AllProductViewModel
import com.amirreza.ecommercenikestore.features.feature_store.presentation.all_product_fragment.product_recycler_view.AllProductListAdaper
import com.amirreza.ecommercenikestore.features.feature_store.presentation.home_fragment.HomeFragmentViewModel
import com.amirreza.ecommercenikestore.features.feature_store.presentation.home_fragment.product_list_util.ProductListAdapter
import com.amirreza.ecommercenikestore.features.feature_store.presentation.product_detail_fragment.ProductDetailViewModel
import com.facebook.drawee.backends.pipeline.Fresco
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module

class NikeShopApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)

        val module = module {
            single { createInstanceOfApiService() }
            single<AppDataBase> {
                Room.databaseBuilder(
                    this@NikeShopApplication,
                    AppDataBase::class.java,
                    "nike_database"
                ).build()
            }

            factory<ProductRepositoryI> {
                ProductRepositoryImpl(
                    ProductRemoteDataSource(get()),
                    ProductLocalDataSource()
                )
            }

            factory<OrderRepository> {
                OrderRepositoryImpl(get())
            }
            factory<CommentRepositoryI>{
                CommentRepositoryImpl(
                    RemoteCommentDataSource(get())
                )
            }

            factory<OrderHistoryRepository> {
                OrderHistoryRepositoryImpl(get())
            }

            factory<BannerRepositoryI> {
                BannerRepositoryImpl(
                    BannerRemoteDataSource(get())
                )
            }
            factory<ImageLoaderI> {
                FrescoImageLoadingService()
            }
            factory {
                ProductListAdapter(get())
            }
            factory { (viewType:Int)->
                AllProductListAdaper(viewType,get())
            }
            factory<CartDataSourceI>{
                CartRemoteDataSource(get())
            }
            single<ProfileRepository> {
                ProfileRepositoryImpl(get())
            }
            single<FavoriteRepository> {
                FavoriteRepositoryImpl(get<AppDataBase>().favoriteDao())
            }
            single<CartShoppingRepository>{
                CartRepositoryImpl(get())
            }
            single<SharedPreferences>{
                this@NikeShopApplication.getSharedPreferences("appAuth", MODE_PRIVATE)
            }
            single<AuthRepository>{
                AuthRepositoryImpl(
                    AuthLocalDataSourceImp(get()),
                    AuthRemoteDataSourceImpl(get())
                )
            }

            //use cases
            single {
                CartUseCase(
                    AddToCartUC(get()),
                    GetProductsInShoppingListUC(get()),
                    RemoveProductFromShoppingCartUC(get()),
                    ChangeCountUC(get()),
                    GetItemsInTheShoppingCart(get())
                )
            }
            single {
                BannerUseCases(
                    GetBannerUC(get())
                )
            }
            single {
                CommentUseCase(
                    GetComments(get())
                )
            }
            single {
                ProductUseCases(
                    GetProductsUC(get())
                )
            }

        }
        val viewModelsModule = module {
            viewModel {
                HomeFragmentViewModel(get(), get(), get())
            }
            viewModel { (bundle: Bundle)->
                ProductDetailViewModel(bundle,get(),get())
            }
            viewModel { (int:Int)->
                AllCommentViewModel(int,get())
            }
            viewModel { (sortType:Int)->
                AllProductViewModel(sortType, get())
            }
            viewModel {
                AuthViewModel(get())
            }
            viewModel {
                CartViewModel(get())
            }
            viewModel{
                MainActivityViewModel(get())
            }
            viewModel { (orderId:Int) ->
                OrderResultViewModel(get(),orderId)
            }
            viewModel{ (bundle:Bundle)->
                CheckoutViewModel(bundle,get())
            }
            viewModel {
                ProfileViewModel(get())
            }
            viewModel{
                FavoriteViewModel(get())
            }
            viewModel {
                OrderHistoryViewModel(get())
            }
        }
        startKoin {
            androidContext(this@NikeShopApplication)
            modules(listOf(module, viewModelsModule))
        }

        val authRepository:AuthRepository = get()
        authRepository.loadToken()
    }
}
