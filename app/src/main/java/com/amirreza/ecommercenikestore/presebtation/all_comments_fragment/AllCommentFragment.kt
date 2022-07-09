package com.amirreza.ecommercenikestore.presebtation.all_comments_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amirreza.ecommercenikestore.R
import com.amirreza.ecommercenikestore.common.base.EXTRA_ALL_COMMENTS
import com.amirreza.ecommercenikestore.databinding.FragmentAllCommentBinding
import com.amirreza.ecommercenikestore.databinding.FragmentHomeBinding
import com.amirreza.ecommercenikestore.domain.entity.Comment
import com.amirreza.ecommercenikestore.presebtation.product_detail_fragment.comment_recyclerView.CommentAdapter
import com.sevenlearn.nikestore.common.getVerticalLinearLayoutManager
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class AllCommentFragment : Fragment() {
    private lateinit var binding:FragmentAllCommentBinding
    private val allCommentViewModel:AllCommentViewModel by inject {
        parametersOf(this.arguments?.get(EXTRA_ALL_COMMENTS))
    }
    private val commentAdapter = CommentAdapter(true)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllCommentBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpCommentsRecyclerView()
    }

    private fun setUpCommentsRecyclerView(){
        val allCommentRecyclerView = binding.commentsRv
        allCommentRecyclerView.layoutManager = getVerticalLinearLayoutManager(context)
        allCommentRecyclerView.adapter = commentAdapter

        allCommentViewModel.commentsLiveData.observe(viewLifecycleOwner){
            commentAdapter.comments = it as ArrayList<Comment>
        }
    }


}