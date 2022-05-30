package com.indigoag.android.homework.ui.home.bids.list

import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.indigoag.repository.network.bids.Bid
import java.util.*
import kotlin.collections.ArrayList

typealias BidSelectedListener = (Bid) -> Unit

class HomeListAdapter(private val onBidSelectedListener: BidSelectedListener) :
    RecyclerView.Adapter<HomeListHolder>(), Filterable {

    var bidList: List<Bid> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var bidFilterList: List<Bid> = arrayListOf()

    var stringFilter: String = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeListHolder {
        return HomeListHolder.from(parent, onBidSelectedListener, parent.context)
    }

    override fun onBindViewHolder(holder: HomeListHolder, position: Int) {
        if (stringFilter.isEmpty())
            holder.bind(bidList[position])
        else
            holder.bind(bidFilterList[position])
    }

    override fun getItemCount(): Int {
        return if (stringFilter.isEmpty()) bidList.size
        else bidFilterList.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                bidFilterList = if (charSearch.isEmpty()) {
                    bidList as ArrayList<Bid>
                } else {
                    val resultList = ArrayList<Bid>()
                    for (row in bidList) {
                        if (row.product.lowercase(Locale.getDefault())
                                .contains(constraint.toString().lowercase(Locale.getDefault()))
                        ) {
                            resultList.add(row)
                        }
                    }
                    resultList
                }
                val filterResults = FilterResults()
                filterResults.values = bidFilterList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                bidFilterList = results?.values as ArrayList<Bid>
                stringFilter = constraint.toString()
                notifyDataSetChanged()
            }
        }
    }
}