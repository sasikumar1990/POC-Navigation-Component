package com.navigation.navigationcomponent.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.navigation.navigationcomponent.data.model.Data
import com.navigation.navigationcomponent.data.model.MyDetail
import com.navigation.navigationcomponent.data.status.Status
import com.navigation.navigationcomponent.databinding.FragmentHomeBinding
import com.navigation.navigationcomponent.ui.adapter.MainAdapter
import com.navigation.navigationcomponent.ui.adapter.MyDetailAdapter
import com.navigation.navigationcomponent.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings
import kotlinx.android.synthetic.main.fragment_home.*

@AndroidEntryPoint
@WithFragmentBindings
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    //Not required to initialize view model by view model provider
    private val viewModel: MainViewModel by viewModels()

    private lateinit var adapter: MainAdapter
    private lateinit var conCatAdapter: ConcatAdapter
    private lateinit var myDetailAdapter: MyDetailAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
        setupUI()
        setupObservers()
    }

    //setting the adapter and data
    private fun setupUI() {
        val myDetail = MyDetail(1, "Hi", "Welcome to Home Screen")
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = MainAdapter(arrayListOf())
        myDetailAdapter = MyDetailAdapter(myDetail)

        //Concat Adapter
        val listOfAdapters = listOf(myDetailAdapter,adapter)
        conCatAdapter = ConcatAdapter(listOfAdapters)
        recyclerView.adapter = conCatAdapter
        myDetailAdapter.onItemClick = {
            Toast.makeText(activity,"Selected ${it.name}", Toast.LENGTH_LONG).show()
        }
        adapter.onItemClick = { data ->
            val id = data.id
            val name = data.name
            val price = data.price.toString()
            val imageUrl = data.image
            activity?.let {

                val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(name,"$ $price",imageUrl)
                findNavController().navigate(action)//
            }
        }
    }

    private fun setupObservers() {
        viewModel.res.observe(viewLifecycleOwner, Observer {
            when(it.status){
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    recyclerView.visibility = View.VISIBLE
                    it.data.let {res->
                        if (res?.responseCode == 200){
                            res.data?.let { it1 -> retrieveList(it1) }
                        }else{
                        }
                    }
                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    progressBar.visibility = View.GONE
                    recyclerView.visibility = View.VISIBLE
                }
            }
        })

    }

    private fun retrieveList(list: List<Data>) {

        adapter.apply {
            addList(list)
            notifyDataSetChanged()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}