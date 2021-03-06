/*
ASSUMPTIONS
    -   I used the Google/Android material.io documentation to design the wireframes. Tabs and a list seemed the
        best solutions for navigation and the inventory list.

        When I researched how to build it, I found that AndroidStudio generated the heart of what I needed
        -   new Activity/Tabbed Activity
        -   new Fragment/ Fragment (list)
        -   As a bonus, the generated Tabbed Activity included a FloatingActionButton, which looked better than
            what I had designed in the wireframe mockup. So, I modified it and used it

        I have assumed it is OK to let the framework do the heavy lifting (generating), and for me to understand it,
         modify and adapt, to create a modern looking app.

    -   The generated Activity uses bindings (to access views). I have used these before, so continued to use them.
        I have assumed it is OK to use techniques that have evolved since the course was written.
*/

package nz.co.afleet.bit603_a2_johnmcpherson.ui.main;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import nz.co.afleet.bit603_a2_johnmcpherson.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);
    }
}