package com.proano.estefano.cazarpatos

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RankingAdapter(private val dataSet: ArrayList<Player>) : RecyclerView.Adapter<RecyclerView.ViewHolder>()  {
    private val TYPE_HEADER : Int = 0
    private val TYPE_PLAYER : Int = 1
    class ViewHolderHeader(view : View) : RecyclerView.ViewHolder(view){
        val textViewPosicion: TextView = view.findViewById(R.id.textViewPosicion)
        val textViewPatosCazados: TextView = view.findViewById(R.id.textViewPatosCazados)
        val textViewUsuario: TextView = view.findViewById(R.id.textViewUsuario)
        val imageViewMedal: ImageView = view.findViewById(R.id.imageViewMedal)
    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewPosicion: TextView
        val textViewPatosCazados: TextView
        val textViewUsuario: TextView
        val imageViewMedal: ImageView
        init {
            textViewPosicion = view.findViewById(R.id.textViewPosicion)
            textViewPatosCazados = view.findViewById(R.id.textViewPatosCazados)
            textViewUsuario = view.findViewById(R.id.textViewUsuario)
            imageViewMedal = view.findViewById(R.id.imageViewMedal)
        }
    }
    override fun getItemViewType(position: Int): Int {
        if(position == 0){
            return TYPE_HEADER
        }
        return TYPE_PLAYER
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.ranking_list, parent, false)
        return if(viewType == TYPE_HEADER){
            ViewHolderHeader(view)
        } else {
            ViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolderHeader){
            // Configurar el header
            holder.textViewPosicion.text = "#"
            holder.textViewPosicion.paintFlags = holder.textViewPosicion.paintFlags or Paint.UNDERLINE_TEXT_FLAG
            holder.textViewPosicion.setTextColor(holder.textViewPosicion.context.getColor(R.color.colorPrimaryDark))

            holder.textViewUsuario.text = holder.itemView.context.getString(R.string.text_user)
            holder.textViewUsuario.paintFlags = holder.textViewUsuario.paintFlags or Paint.UNDERLINE_TEXT_FLAG
            holder.textViewUsuario.setTextColor(holder.textViewUsuario.context.getColor(R.color.colorPrimaryDark))

            holder.textViewPatosCazados.text = holder.itemView.context.getString(R.string.num_patos)
            holder.textViewPatosCazados.paintFlags = holder.textViewPatosCazados.paintFlags or Paint.UNDERLINE_TEXT_FLAG
            holder.textViewPatosCazados.setTextColor(holder.textViewPatosCazados.context.getColor(R.color.colorPrimaryDark))

            // Ocultar la medalla en el header
            holder.imageViewMedal.visibility = View.INVISIBLE
        }
        else if (holder is ViewHolder) {
            val playerPosition = position

            when (playerPosition) {
                1 -> holder.imageViewMedal.setImageResource(R.drawable.gold)
                2 -> holder.imageViewMedal.setImageResource(R.drawable.silver)
                3 -> holder.imageViewMedal.setImageResource(R.drawable.bronze)
                else -> holder.imageViewMedal.visibility = View.INVISIBLE // Ocultar para posiciones 4 y 5
            }

            holder.textViewPosicion.text = playerPosition.toString()
            holder.textViewPatosCazados.text = dataSet[playerPosition-1].huntedDucks.toString()
            holder.textViewUsuario.text = dataSet[playerPosition-1].username
        }
    }

    override fun getItemCount() = dataSet.size + 1
}