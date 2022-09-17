package com.amirreza.ecommercenikestore.features.feature_home.presentation.all_comments_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amirreza.ecommercenikestore.utils.util.EXTRA_ALL_COMMENTS
import com.amirreza.ecommercenikestore.utils.base.NikeFragment
import com.amirreza.ecommercenikestore.databinding.FragmentAllCommentBinding
import com.amirreza.ecommercenikestore.features.feature_home.domain.entity.Comment
import com.amirreza.ecommercenikestore.features.feature_home.presentation.product_detail_fragment.comment_recyclerView.CommentAdapter
import com.amirreza.ecommercenikestore.utils.util.getVerticalLinearLayoutManager
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class AllCommentFragment : NikeFragment() {
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
        setUpPrograssBar()
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

    private fun setUpPrograssBar(){
        allCommentViewModel.progressBarIndicatorLiveData.observe(viewLifecycleOwner){
            setProgressBarIndicator(it)
        }
    }

}