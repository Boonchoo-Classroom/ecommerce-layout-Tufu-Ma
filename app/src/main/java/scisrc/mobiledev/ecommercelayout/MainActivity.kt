package scisrc.mobiledev.ecommercelayout

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import scisrc.mobiledev.ecommercelayout.databinding.ActivityMainBinding
import scisrc.mobiledev.ecommercelayout.ui.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup toolbar
        setSupportActionBar(binding.toolbar)

        // Initialize drawer layout
        drawerLayout = binding.drawerLayout

        // Add hamburger icon
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            binding.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Handle NavigationView item clicks
        binding.navView.setNavigationItemSelectedListener { menuItem ->
            val selectedFragment = when (menuItem.itemId) {
                R.id.nav_home -> HomeFragment()
                R.id.nav_cart -> CartFragment()
                R.id.nav_favorite -> FavoriteFragment()
                R.id.nav_product -> ProductFragment()
                R.id.nav_profile -> ProfileFragment()
                else -> HomeFragment()
            }

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, selectedFragment)
                .commit()

            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        // Load default fragment (HomeFragment)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment())
                .commit()
            binding.navView.setCheckedItem(R.id.nav_home)
        }
    }
}
