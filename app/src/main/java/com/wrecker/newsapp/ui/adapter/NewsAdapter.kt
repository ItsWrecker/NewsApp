package com.wrecker.newsapp.ui.adapter



import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wrecker.newsapp.R
import com.wrecker.newsapp.databinding.NewsItemBinding
import com.wrecker.newsapp.db.entity.Article
import com.wrecker.newsapp.ui.factory.BaseFragmentFactory
import javax.inject.Inject

/**
 * Adapter to plug recyclerview into,
 */
class NewsAdapter @Inject constructor(): RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {


    inner class ArticleViewHolder(private val binding: NewsItemBinding) : RecyclerView.ViewHolder(binding.root){
        /**
         * Binding object's value to the view with data-binding :
         * One way (2-way is not required here) DiffUtils is sufficient to handle this.
         */
        fun bind(article: Article) {
            with(binding){
                newsTitle.text = article.title
                newsDescription.text = article.description
                newsAuthor.text = "Source: ${article.author?: "Unknown"}"
            }

        }
    }

    /**
     * Callback for diff utils
     */
    private val differCallBack = object : DiffUtil.ItemCallback<Article>() {

        /**
         * comparing the old and new list's item
         */
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        /**
         * checking for any duplicate content.
         */
        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NewsItemBinding.inflate(inflater)
        return ArticleViewHolder(binding)
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

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.bind(article)
        /**
         * Loading the image into image view and handling the onclick on item.
         */
        holder.itemView.apply {
            Glide.with(this).load(article.urlToImage)
                .centerCrop()
                .into(findViewById(R.id.newPreviewImage))
            setOnClickListener {
                /**
                 * Here passing the article(object) to the lambda function,
                 * for further sending the information to the fragment:
                 */
                onItemClickListener?.let {
                    it(article)
                }
            }
        }


    }
}