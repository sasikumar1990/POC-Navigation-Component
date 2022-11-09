package com.navigation.navigationcomponent.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.navigation.navigationcomponent.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<DetailsFragmentArgs>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.apply {
            itemName.text = args.name
            itemPrice.text = args.price
            selectText.text = "You has been selected ${args.name} product and total cost is ${args.price}. Thanks for choosing owr product"
            //Image Loader
            Glide.with(itemImage.context)
                .load(args.image)
                .into(itemImage)
            // back button
            backBtn.setOnClickListener {
                val action = DetailsFragmentDirections.actionDetailFragmentToHomeFragment()
                findNavController().navigate(action)
            }

            arrowIcon.setOnClickListener {
                val action = DetailsFragmentDirections.actionDetailFragmentToHomeFragment()
                findNavController().navigate(action)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}