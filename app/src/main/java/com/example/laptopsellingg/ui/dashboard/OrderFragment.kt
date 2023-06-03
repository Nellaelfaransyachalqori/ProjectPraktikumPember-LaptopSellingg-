package com.example.laptopsellingg.ui.dashboard

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.laptopsellingg.Preferences
import com.example.laptopsellingg.databinding.FragmentOrderBinding
import com.example.laptopsellingg.room.Order

class OrderFragment : Fragment(), DeleteClick {

    private var _binding: FragmentOrderBinding? = null
    private val binding get() = _binding!!

    lateinit var viewModel: OrderViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[OrderViewModel::class.java]

        _binding = FragmentOrderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.orderRv.layoutManager = LinearLayoutManager(context)

        val preferences = context?.let { Preferences(it) }

        preferences?.getSession()?.let {
            viewModel.getAllOrder(it)?.observe(viewLifecycleOwner) {
                binding.orderRv.adapter = OrderAdapter(it, this)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(order: Order) {
        val dialog = AlertDialog.Builder(context)
        dialog.setMessage("Apakah Kamu Yakin Ingin Menghapus Order Laptopmu ?")
        dialog.setPositiveButton("Yes") { d, a ->
            viewModel.deleteOrder(order)
            d.dismiss()
        }
        dialog.setNegativeButton("No") { d, a ->
            d.dismiss()
        }
        dialog.show()
    }
}