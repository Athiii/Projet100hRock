package com.example.athi.rock.administrateur;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.athi.rock.DanseFragment;
import com.example.athi.rock.EquipeFragment;
import com.example.athi.rock.HomeFragment;
import com.example.athi.rock.PagerAdapter;
import com.example.athi.rock.R;
import com.example.athi.rock.administrateur.Evenement.EvenementAdminFragment;
import com.example.athi.rock.administrateur.Membre.MembreAdminFragment;
import com.example.athi.rock.administrateur.Musique.MusiqueAdminFragment;
import com.example.athi.rock.administrateur.Passe.PasseAdminFragment;

public class AdministrateurActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_evenement:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_passe:
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_musique:
                    viewPager.setCurrentItem(2);
                    return true;
                case R.id.navigation_membre:
                    viewPager.setCurrentItem(3);
                    return true;
            }
            return false;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrateur);
        getSupportActionBar().hide();
        //Initianisation du viewPager
        viewPager = (ViewPager) findViewById(R.id.pagerAdmin);
        setupViewPager(viewPager);

        final BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigationAdmin);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //quand l'appli s'ouvre le fragment 1 sera ouvert


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        navigation.setSelectedItemId(R.id.navigation_evenement);
                        break;
                    case 1:
                        navigation.setSelectedItemId(R.id.navigation_passe);
                        break;
                    case 2:
                        navigation.setSelectedItemId(R.id.navigation_musique);
                        break;
                    case 3:
                        navigation.setSelectedItemId(R.id.navigation_membre);

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    public void setupViewPager(ViewPager upViewPager) {
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new EvenementAdminFragment());
        adapter.addFragment(new PasseAdminFragment());
        adapter.addFragment(new MusiqueAdminFragment());
        adapter.addFragment(new MembreAdminFragment());
        viewPager.setAdapter(adapter);
    }
}
