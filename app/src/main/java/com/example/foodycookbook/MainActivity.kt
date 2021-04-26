package com.example.foodycookbook
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class MainActivity : AppCompatActivity() {

    val builder = GsonBuilder()
    val gson = builder.serializeNulls().create()
    private lateinit var productList : Array<Meal>
    private lateinit var recyclerView: RecyclerView
    private  lateinit var manager: RecyclerView.LayoutManager
    private lateinit var myFoodAdapter: RecyclerView.Adapter<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar.title=""
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        manager = GridLayoutManager(this,1)

        foodApi()

    }
    private fun foodApi(){
        RetrofitObject.instance.random().enqueue(object : Callback<JsonObject>{
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                if (response.code() == 200){
                    var res = gson.fromJson(response.body().toString(), FoodResponse::class.java)
                    productList = res.meals.toTypedArray()
                    myFoodAdapter = FoodAdapter(res)
                    recycler_product.apply{
                        myFoodAdapter = FoodAdapter(res)
                        layoutManager = manager
                        adapter = myFoodAdapter
                    }
                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Toast.makeText(applicationContext,t.message,Toast.LENGTH_LONG).show()
            }
        })
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.productbar_fav_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_search){

            startActivity(Intent(applicationContext, FavoriteFood::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

}