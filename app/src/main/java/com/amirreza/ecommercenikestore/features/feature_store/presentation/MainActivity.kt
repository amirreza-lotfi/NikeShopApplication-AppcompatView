package com.amirreza.ecommercenikestore.features.feature_store.presentation

import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.amirreza.ecommercenikestore.R
import com.amirreza.ecommercenikestore.databinding.ActivityMainBinding
import com.amirreza.ecommercenikestore.features.feature_cart.domain.entity.cart.ProductCountInShoppingCart
import com.amirreza.ecommercenikestore.features.feature_store.common.base.NikeActivity
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.color.MaterialColors
import com.amirreza.ecommercenikestore.features.feature_store.common.util.convertDpToPixel
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : NikeActivity() {
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding:ActivityMainBinding
    private val viewModel:MainActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        navController = navHostFragment.navController

        // Setup the bottom navigation view with navController
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setupWithNavController(navController)

        // Setup the ActionBar with navController and 3 top level destinations
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.navigation_profile, R.id.navigation_home, R.id.navigation_cart)
        )
    }

    override fun onResume() {
        super.onRestart()
        viewModel.getCartItemsCount()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    fun cartItemChanged(cartItemCount: ProductCountInShoppingCart){
        Log.i("mainActivity","eventBusss")
        val badge = binding.bottomNavigation.getOrCreateBadge(R.id.navigation_cart)
        badge.apply {
            badgeGravity = BadgeDrawable.TOP_END
            isVisible = cartItemCount.count>0
            number = cartItemCount.count
            verticalOffset = convertDpToPixel(10f, this@MainActivity).toInt()
            backgroundColor = MaterialColors.getColor(binding.bottomNavigation, androidx.appcompat.R.attr.colorPrimary)
        }
    }
}