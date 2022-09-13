package com.amirreza.ecommercenikestore.di

import android.app.Application
import android.content.SharedPreferences
import android.os.Bundle
import com.amirreza.ecommercenikestore.feature_auth.data.repository.AuthRepositoryImpl
import com.amirreza.ecommercenikestore.feature_auth.data.source.local.AuthLocalDataSourceImp
import com.amirreza.ecommercenikestore.feature_auth.data.source.remote.AuthRemoteDataSourceImpl
import com.amirreza.ecommercenikestore.feature_auth.domain.repository.AuthRepository
import com.amirreza.ecommercenikestore.feature_auth.presentation.AuthViewModel
import com.amirreza.ecommercenikestore.http.createInstanceOfApiService
import com.amirreza.ecommercenikestore.feature_store.data.repository.CommentRepositoryImpl
import com.amirreza.ecommercenikestore.feature_store.data.repository.FrescoImageLoadingService
import com.example.nikeshop.feature_shop.data.repository.BannerRepositoryImpl
import com.example.nikeshop.feature_shop.data.repository.ProductRepositoryImpl
import com.amirreza.ecommercenikestore.feature_store.data.source.banner_data_source.BannerRemoteDataSource
import com.amirreza.ecommercenikestore.feature_cart.data.data_source.cart_data_source.CartDataSourceI
import com.amirreza.ecommercenikestore.feature_cart.data.data_source.cart_data_source.CartRemoteDataSource
import com.amirreza.ecommercenikestore.feature_cart.data.repository.CartRepositoryImpl
import com.amirreza.ecommercenikestore.feature_cart.domain.repository.CartShoppingRepository
import com.amirreza.ecommercenikestore.feature_store.data.source.comment_data_source.RemoteCommentDataSource
import com.amirreza.ecommercenikestore.feature_store.data.source.product_data_spurce.ProductLocalDataSource
import com.amirreza.ecommercenikestore.feature_store.domain.repository.CommentRepositoryI
import com.amirreza.ecommercenikestore.feature_store.domain.repository.ImageLoaderI
import com.example.nikeshop.feature_shop.data.source.product_data_spurce.ProductRemoteDataSource
import com.example.nikeshop.feature_shop.domain.repository.BannerRepositoryI
import com.amirreza.ecommercenikestore.feature_store.domain.repository.ProductRepositoryI
import com.amirreza.ecommercenikestore.feature_store.domain.useCases.BannerUseCases
import com.amirreza.ecommercenikestore.feature_cart.domain.useCases.CartUseCase
import com.amirreza.ecommercenikestore.feature_store.domain.useCases.CommentUseCase
import com.amirreza.ecommercenikestore.feature_store.domain.useCases.ProductUseCases
import com.amirreza.ecommercenikestore.feature_store.domain.useCases.banner_usecase.GetBannerUC
import com.amirreza.ecommercenikestore.feature_cart.domain.useCases.cart_usecases.*
import com.amirreza.ecommercenikestore.feature_cart.presentation.card_fragment.CartViewModel
import com.amirreza.ecommercenikestore.feature_store.domain.useCases.comment_usecases.GetComments
import com.amirreza.ecommercenikestore.feature_store.domain.useCases.product_usecases.AddProductToFavoritesUC
import com.amirreza.ecommercenikestore.feature_store.domain.useCases.product_usecases.DeleteProductFromFavoritesUC
import com.amirreza.ecommercenikestore.feature_store.domain.useCases.product_usecases.GetFavoriteProductsUC
import com.amirreza.ecommercenikestore.feature_store.domain.useCases.product_usecases.GetProductsUC
import com.amirreza.ecommercenikestore.feature_store.presentation.MainActivityViewModel
import com.amirreza.ecommercenikestore.feature_store.presentation.all_comments_fragment.AllCommentViewModel
import com.amirreza.ecommercenikestore.feature_store.presentation.all_product_fragment.AllProductViewModel
import com.amirreza.ecommercenikestore.feature_store.presentation.all_product_fragment.product_recycler_view.AllProductListAdaper
import com.amirreza.ecommercenikestore.feature_store.presentation.home_fragment.HomeFragmentViewModel
import com.amirreza.ecommercenikestore.feature_store.presentation.home_fragment.product_list_util.ProductListAdapter
import com.amirreza.ecommercenikestore.feature_store.presentation.product_detail_fragment.ProductDetailViewModel
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
            factory<ProductRepositoryI> {
                ProductRepositoryImpl(
                    ProductRemoteDataSource(get()),
                    ProductLocalDataSource()
                )
            }
            factory<CommentRepositoryI>{
                CommentRepositoryImpl(
                    RemoteCommentDataSource(get())
                )
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
                    GetProductsUC(get()),
                    GetFavoriteProductsUC(get()),
                    AddProductToFavoritesUC(get()),
                    DeleteProductFromFavoritesUC(get()),
                )
            }

        }
        val viewModelsModule = module {
            viewModel {
                HomeFragmentViewModel(get(), get())
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
        }
        startKoin {
            androidContext(this@NikeShopApplication)
            modules(listOf(module, viewModelsModule))
        }

        val authRepository:AuthRepository = get()
        authRepository.loadToken()
    }
}
