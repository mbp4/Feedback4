package com.example.feedback4

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class ListadoNovelasFragmento:Fragment() {
    public lateinit var recyclerView: RecyclerView
    private lateinit var novelasAdapter: NovelasAdapter
    private var listadoNovelasF: MutableList<Novela> = mutableListOf()
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_listado, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerNovelas)
        recyclerView.layoutManager = LinearLayoutManager(context)

        cargarNovelas()
    }

    private fun cargarNovelas() {
        db.collection("dbNovelas")
            .get()
            .addOnSuccessListener { documentos ->
                listadoNovelasF.clear()
                for (documento in documentos) {
                    val novela = documento.toObject(Novela::class.java)
                    listadoNovelasF.add(novela)
                }
                prepararRecyclerView()
            }
            .addOnFailureListener({ exception ->
                Log.w(TAG, "Error al obtener las novelas de la base de datos", exception)
            })
    }

    private fun prepararRecyclerView() {
        novelasAdapter = NovelasAdapter(listadoNovelasF) { novela, accion ->
            if (accion == MainActivity.ACCION_VER) {
                verNovela(novela)
            }
        }
        recyclerView.adapter = novelasAdapter
        novelasAdapter.notifyDataSetChanged()
    }

    private fun verNovela(novela: Novela) {

        val fragment = VerNovelaFragment().apply {
            arguments = Bundle().apply {
                putString("Titulo", novela.titulo)
                putString("Autor", novela.autor)
                putInt("Año", novela.año)
                putString("Sinopsis", novela.sinopsis)
            }
        }

        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmento, fragment)
            .addToBackStack(null)
            .commit()
    }

}