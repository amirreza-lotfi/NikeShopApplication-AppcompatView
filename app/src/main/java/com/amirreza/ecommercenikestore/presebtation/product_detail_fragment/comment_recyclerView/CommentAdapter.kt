package com.amirreza.ecommercenikestore.presebtation.product_detail_fragment.comment_recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amirreza.ecommercenikestore.R
import com.amirreza.ecommercenikestore.databinding.ItemCommentBinding
import com.amirreza.ecommercenikestore.domain.entity.Comment

class CommentAdapter : RecyclerView.Adapter<CommentAdapter.CommentHolder>() {
    var comments:ArrayList<Comment> = arrayListOf()
        set(value)  {
            field = value
        }
    inner class CommentHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val commentTitle: TextView = item.findViewById(R.id.commentTitle)
        private val time: TextView = item.findViewById(R.id.commentTime)
        private val body: TextView = item.findViewById(R.id.commentTime)
        private val writer:TextView = item.findViewById(R.id.authorComment)

        fun onBind(comment: Comment) {
            time.text = comment.date
            body.text = comment.content
            writer.text = comment.author.email
            commentTitle.text = comment.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentHolder {
        return CommentHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_comment,parent,false))
    }

    override fun onBindViewHolder(holder: CommentHolder, position: Int) {
        holder.onBind(comments[position])
    }

    override fun getItemCount(): Int {
        return 3
    }
}