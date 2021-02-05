package com.wrecker.newsapp.ui.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wrecker.newsapp.R
import com.wrecker.newsapp.db.entity.Article


class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {


    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallBack = object : DiffUtil.ItemCallback<Article>() {

        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {

        val article = differ.currentList[position]
        holder.itemView.apply {

            setOnClickListener {
                onItemClickListener?.let { it(article) }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    /**
     * for opening the webView page.
     */
    private var onItemClickListener: ((Article) -> Unit)? = null


    fun setOnClickListener(listener: (Article) -> Unit) {
        onItemClickListener= listener
    }
}