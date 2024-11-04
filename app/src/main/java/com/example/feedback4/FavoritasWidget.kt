package com.example.feedback4

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.RemoteViews
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

class FavoritasWidget: AppWidgetProvider() {

    private val db: FirebaseFirestore = Firebase.firestore
    private var listaFavoritas = mutableListOf<String>()

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        for (appWidgetId in appWidgetIds) {
            actualizarWidget(context, appWidgetManager, appWidgetId)
        }
    }

    private fun actualizarWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {
        val views = RemoteViews(context.packageName, R.layout.widget_novelas_favoritas)

        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
        views.setOnClickPendingIntent(R.id.widgetFavo, pendingIntent)

        db.collection("dbNovelas")
            .whereEqualTo("fav", true)
            .get()
            .addOnSuccessListener { documents ->
                listaFavoritas = documents.map { it.getString("titulo") ?: "" }.toMutableList()

                for (i in 0 until minOf(listaFavoritas.size, 3)) {
                    views.setTextViewText(context.resources.getIdentifier("novela_${i + 1}", "id", context.packageName), listaFavoritas[i])
                    listaFavoritas.add(documents.documents[i].id)
                }

                appWidgetManager.updateAppWidget(appWidgetId, views)
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error al coger las favoritas", exception)
            }
    }

}