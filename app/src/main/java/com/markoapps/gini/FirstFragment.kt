package com.markoapps.gini

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.markoapps.gini.adapters.MainAdapter
import com.markoapps.gini.databinding.FragmentFirstBinding
import com.markoapps.gini.di.Providers
import com.markoapps.gini.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

@AndroidEntryPoint
class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding

    val viewModel: MainViewModel by hiltNavGraphViewModels(R.id.nav_graph)
    lateinit var adapter: MainAdapter

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFirstBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            adapter = MainAdapter()
            list.layoutManager = GridLayoutManager(context, 3)
            list.adapter = adapter
        }


        viewModel.mainSet.observe(viewLifecycleOwner) {
            adapter.setList(it.numbers)
        }

    }
}