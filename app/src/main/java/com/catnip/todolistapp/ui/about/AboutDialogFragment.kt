package com.catnip.todolistapp.ui.about

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.catnip.todolistapp.databinding.FragmentAboutDialogBinding

class AboutDialogFragment : DialogFragment() {

    private lateinit var binding : FragmentAboutDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAboutDialogBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews()
    }

    private fun bindViews() {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        context?.let {
            Glide.with(it)
                .load("https://avatars.githubusercontent.com/u/46629310?v=4")
                .circleCrop()
                .into(binding.ivImageProfile)
        }
        binding.btnRedirectGithub.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://github.com/zdnkarim")
            startActivity(intent)
        }
    }

}