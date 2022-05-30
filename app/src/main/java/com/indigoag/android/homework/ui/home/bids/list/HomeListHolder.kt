package com.indigoag.android.homework.ui.home.bids.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.indigoag.android.homework.R
import com.indigoag.android.homework.databinding.RowCardBidsInfoBinding
import com.indigoag.repository.network.bids.Bid

class HomeListHolder(
    private val binding: RowCardBidsInfoBinding,
    private val onClicSelectedListener: BidSelectedListener,
    private val context: Context
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun from(
            parent: ViewGroup,
            onClicSelectedListener: BidSelectedListener,
            context: Context
        ): HomeListHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = RowCardBidsInfoBinding.inflate(layoutInflater, parent, false)
            val context = context
            return HomeListHolder(binding, onClicSelectedListener,context)
        }
    }

    fun bind(item: Bid) {
        binding.bid = item
        animationPrice(binding,context)
        binding.processAction.setOnClickListener {
            onClicSelectedListener.invoke(item)
        }
        binding.executePendingBindings()
    }

    private fun animationPrice(binding: RowCardBidsInfoBinding,context: Context){
        binding.price.startAnimation(AnimationUtils.loadAnimation(context, R.anim.move_it));

    }
}