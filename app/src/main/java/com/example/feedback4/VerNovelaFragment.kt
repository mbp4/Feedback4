package com.example.feedback4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

class VerNovelaFragment : Fragment() {
    private lateinit var txt1: TextView
    private lateinit var txt2: TextView
    private lateinit var txt3: TextView
    private lateinit var txt4: TextView
    private lateinit var btnVolver: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_ver, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        txt1 = view.findViewById(R.id.txt1)
        txt2 = view.findViewById(R.id.txt2)
        txt3 = view.findViewById(R.id.txt3)
        txt4 = view.findViewById(R.id.txt4)
        btnVolver = view.findViewById(R.id.btnVolver)

        arguments?.let {
            txt1.text = it.getString("Titulo")
            txt2.text = it.getString("Autor")
            txt3.text = it.getInt("Año").toString()
            txt4.text = it.getString("Sinopsis")
        }

        btnVolver.setOnClickListener {
            parentFragmentManager.popBackStack() // Navega hacia atrás en el stack de fragmentos

            // Al regresar a la actividad principal, muestra el RecyclerView y oculta el fragmento
            (activity as? MainActivity)?.let {
                it.findViewById<RecyclerView>(R.id.recyclerNovelas).visibility = View.VISIBLE
                it.findViewById<FrameLayout>(R.id.fragmento).visibility = View.GONE
            }
        }
    }
}