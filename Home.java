package implant.lata.com.dumetschool;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Home extends BaseActivity implements ReadOperationDelegate {


    ArrayList<Item> responceVoArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_home);

        LayoutInflater inflater = getLayoutInflater();

        inflater.inflate(R.layout.app_bar_main, (ViewGroup) findViewById(R.id.container));
        navItemIndex = 0;
        navigationView.getMenu().getItem(0).setChecked(true);
        CURRENT_TAG = TAG_HOME;
        callGetHome();
    }

    private void callGetHome() {
        String url = "http://www.dumetschool.com/api/post";
        Log.v("LataUrl", url);
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    ArrayList<Item> responceVoArrayList = new ArrayList<>();

                    @Override
                    public void onResponse(String response) {
                        // make responceVoArrayList empty for new result
                        Log.v("Lata", "onResponse ");
                        responceVoArrayList.clear();
                        Log.v("Lata", "Responce is: " + response);
                        try {
                            // convert json to array and use for loop to access each car
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("items");
                            for (int index = 0; index < jsonArray.length(); index++) {
                                JSONObject obj = jsonArray.getJSONObject(index);
                                Log.d("Original object", obj.toString());
                                // parse json object to CarVo
                                Item item = new Item(obj);
                                Log.d("Converted object", item.toString());
                                // add car object in responceVoArrayList
                                responceVoArrayList.add(item);
                            }
                        } catch (Throwable t) {
                            Log.e("Lata", "Could not parse malformed JSON:" + t.toString());
                        }
                        updateListHomeData(responceVoArrayList);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("Lata", "That didn't work! " + error.toString());
                updateListHomeData(null);
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    private void updateListHomeData(ArrayList<Item> responceVoArrayList) {
        this.responceVoArrayList = responceVoArrayList;
        ArrayList<Object> listToShow = new ArrayList<>();
        ListView listView = (ListView) findViewById(R.id.lv_home);
        //checkedItems = new boolean[responceVoArrayList.size()];

        if (responceVoArrayList == null) {
            listToShow.add("No Home data available emptys");
        } else if (responceVoArrayList.isEmpty()) {
            listToShow.add("No Home data available lata");
        } else {
            for (int index = 0; index < responceVoArrayList.size(); index++) {
                listToShow.add(responceVoArrayList.get(index));
            }
        }
        listView.setAdapter(new HomeAdapter(this, listToShow, this));
    }

    /***
     * Load navigation menu header information
     * like background image, profile image
     * name, website, notifications action view (dot)
     */
    private void loadNavHeader() {

    }

    /***
     * Returns respected fragment that user
     * selected from navigation menu
     */
    void loadHomeFragment() {
        // selecting appropriate nav menu item
        selectNavMenu();

        // set toolbar title
        setToolbarTitle();

        // if user select the current navigation menu again, don't do anything
        // just close the navigation drawer
//        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
//            drawer.closeDrawers();
//
//            // show or hide the fab button
//            toggleFab();
//            return;
//        }

        // Sometimes, when fragment has huge data, screen seems hanging
        // when switching between navigation menus
        // So using runnable, the fragment is loaded with cross fade effect
        // This effect can be seen in GMail app
        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
//                // update the main content by replacing fragments
//                Fragment fragment = getHomeFragment();
//                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
//                        android.R.anim.fade_out);
//                fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
//                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        // If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }

        // show or hide the fab button
     //   toggleFab();

        //Closing drawer on item click
        drawer.closeDrawers();

        // refresh toolbar menu
        invalidateOptionsMenu();
    }




    void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

//    void setUpNavigationView() {
//        //Contact Navigation View Item Selected Listener to handle the item click of the navigation menu
//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//
//            // This method will trigger on item Click of navigation menu
//            @Override
//            public boolean onNavigationItemSelected(MenuItem menuItem) {
//
//                //Check to see which item was being clicked and perform appropriate action
//                switch (menuItem.getItemId()) {
//                    //Replacing the main content with ContentFragment Which is our Inbox View;
//                    case R.id.nav_home:
//                        navItemIndex = 0;
//                        CURRENT_TAG = TAG_HOME;
//                        break;
//                    case R.id.nav_blog:
//                        navItemIndex = 1;
//                        CURRENT_TAG = TAG_Blog;
//                        break;
//                    case R.id.nav_product:
//                        navItemIndex = 2;
//                        CURRENT_TAG = TAG_PRODUCT;
//                        Intent intent = new Intent(Home.this, Product.class);
//                        startActivity(intent);
//                        break;
//                    case R.id.nav_contact:
//                        navItemIndex = 3;
//                        CURRENT_TAG = TAG_CONTACT;
//                        break;
//                    case R.id.nav_setting:
//                        navItemIndex = 4;
//                        CURRENT_TAG = TAG_SETTING;
//                        break;
//                    default:
//                        navItemIndex = 0;
//                }
//
//                //Checking if the item is in checked state or not, if not make it in checked state
//                if (menuItem.isChecked()) {
//                    menuItem.setChecked(false);
//                } else {
//                    menuItem.setChecked(true);
//                }
//                menuItem.setChecked(true);
//
//                loadHomeFragment();
//
//                return true;
//            }
//        });
//
//
//        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {
//
//            @Override
//            public void onDrawerClosed(View drawerView) {
//                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
//                super.onDrawerClosed(drawerView);
//            }
//
//            @Override
//            public void onDrawerOpened(View drawerView) {
//                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
//                super.onDrawerOpened(drawerView);
//            }
//        };
//
//        //Contact the actionbarToggle to drawer layout
//        drawer.setDrawerListener(actionBarDrawerToggle);
//
//        //calling sync state is necessary or else your hamburger icon wont show up
//        actionBarDrawerToggle.syncState();
//    }



//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        // show menu only when home fragment is selected
//        if (navItemIndex == 0) {
//            getMenuInflater().inflate(R.menu.main, menu);
//        }
//        // when fragment is notifications, load the menu created for notifications
//        if (navItemIndex == 3) {
//            getMenuInflater().inflate(R.menu.notifications, menu);
//        }
//        return true;
//    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_share) {
//           Intent intent = new Intent(Intent.ACTION_SEND);
//            intent.setType("text/plain");
//            intent.putExtra(Intent.EXTRA_SUBJECT, "SUBJECT");
//            intent.putExtra(Intent.EXTRA_TEXT, "TEXT");
//            String asd = "http://www.dumetschool.com/blog/Tips-dan-Trik-Mengenai-Pembuatan-Situs";
//            asd = asd + "http://www.dumetschool.com/blog/Tips-dan-Trik-Mengenai-Pembuatan-Situs";
//            intent.putExtra(Intent.EXTRA_TEXT, asd);
//            startActivity(Intent.createChooser(intent, "Share"));
//            return true;
//        }
//
//
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public void seeMoreButtonTapped(Item post) {
        Intent intent = new Intent(Home.this, Blog.class);
        intent.putExtra(Blog.objectToShowKey, post.toJsonObject().toString());
        startActivity(intent);
    }

}


