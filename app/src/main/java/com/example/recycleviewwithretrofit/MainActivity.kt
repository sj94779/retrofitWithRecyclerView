package com.example.recycleviewwithretrofit

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.littlemango.stacklayoutmanager.StackLayoutManager
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var adapter: MyAdapter
    private var articles = mutableListOf<Article>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var newsList  = findViewById<RecyclerView>(R.id.newsList)
        var container  = findViewById<ConstraintLayout>(R.id.container)
        adapter= MyAdapter(this@MainActivity, articles)
        newsList.adapter = adapter

        val layoutManager = StackLayoutManager(StackLayoutManager.ScrollOrientation.BOTTOM_TO_TOP)
        layoutManager.setPagerMode(true)
        layoutManager.setPagerFlingVelocity(3000)
        layoutManager.setItemChangedListener(object: StackLayoutManager.ItemChangedListener{
            override fun onItemChanged(position: Int) {
               container.setBackgroundColor(Color.parseColor(ColorPicker.getColors()))
            }

        })
        newsList.layoutManager = layoutManager

        getNews()

    }

    private fun getNews() {

       val news = NewsService.newsInstance.getHeadlines("in",1)
        news.enqueue(object : retrofit2.Callback<News>{
            override fun onResponse(call: retrofit2.Call<News>, response: Response<News>)
            {
                val news= response.body()
                if(news != null) {
                    Log.d("cheesycode", news.toString())
                    articles.addAll(news.articles)
                    adapter.notifyDataSetChanged()

                }
            }

            override fun onFailure(call: retrofit2.Call<News>, t: Throwable) {
               Log.d("cheesycode", "Error in fetching news", t)
            }
        })

        }
    }
