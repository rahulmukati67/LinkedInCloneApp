package com.example.linkedinclone.activity

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.FrameLayout
import androidx.appcompat.app.ActionBarDrawerToggle

import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.linkedinclone.R
import com.example.linkedinclone.fragments.*
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    lateinit var drawerLayout: DrawerLayout
    lateinit var coordinatorLayout: CoordinatorLayout
    lateinit var toolbar: androidx.appcompat.widget.Toolbar
    lateinit var frameLayout: FrameLayout
    lateinit var navigationView: NavigationView
    lateinit var homeButton: Button
    lateinit var jobButton: Button
    lateinit var notifcationButton: Button
    lateinit var postButton: Button
    lateinit var networkButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawerLayout)
        coordinatorLayout = findViewById(R.id.coordinatorLayout)
        toolbar  = findViewById(R.id.toolbar)
        frameLayout  = findViewById(R.id.frame)
        navigationView = findViewById(R.id.navigationLView)
        homeButton= findViewById(R.id.homeButton)
        jobButton=findViewById(R.id.jobButton)
        networkButton=findViewById(R.id.networkButton)
        notifcationButton=findViewById(R.id.notificationButton)
        postButton=findViewById(R.id.postButton)




        postButton.setOnClickListener(){
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame, postFragment())
                .commit()
            supportActionBar?.title="Upload Post"
        }



        setUpToolbar()
        var previousMenuItem : MenuItem?= null

        onHome()

        val actionBarDrawerToggle = ActionBarDrawerToggle(this@MainActivity , drawerLayout,
            R.string.open_drawer,
            R.string.close_drawer
        )

        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        navigationView.setNavigationItemSelectedListener(){

            if(previousMenuItem !=null){
                previousMenuItem?.isChecked=false
            }
            it.isCheckable=true
            it.isChecked=true
            previousMenuItem=it

            when(it.itemId){
                R.id.home ->{
                    onHome()
                }
                R.id.profile ->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, ProfileFragment())
                        .commit()
                    supportActionBar?.title="User Profile"
                    drawerLayout.closeDrawers()
                }
                R.id.group ->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, GroupFragment())
                        .commit()
                    supportActionBar?.title="Group"
                    drawerLayout.closeDrawers()
                }
                R.id.event ->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, EventFragment())
                        .commit()
                    supportActionBar?.title="Event"
                    drawerLayout.closeDrawers()
                }
                R.id.setting ->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, SettingFragment())
                        .commit()
                    supportActionBar?.title="Setting"
                    drawerLayout.closeDrawers()
                }
            }
            return@setNavigationItemSelectedListener true
        }
    }

    fun setUpToolbar(){
        setSupportActionBar(toolbar)
        supportActionBar?.title="LinkedIn"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id= item.itemId
        if(id== android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }

    fun onHome(){

        supportFragmentManager.beginTransaction()
            .replace(R.id.frame, HomeFragment())
            .commit()
        supportActionBar?.title="Home"
        drawerLayout.closeDrawers()

    }

    override fun onBackPressed() {

        val frag = supportFragmentManager.findFragmentById(R.id.frame)
        when(frag){
            !is HomeFragment ->onHome()
            else-> super.onBackPressed()

        }

    }


}




