package com.example.rpgapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.TextView

class PessoaApter(var context: Context,var listPessoas:List<Pessoa>): BaseAdapter(){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var p = LayoutInflater.from(context).inflate(R.layout.pessoas,parent,false)
        var name = p.findViewById<TextView>(R.id.textViewName)

        val pessoas = listPessoas.get(position)

        name.text = pessoas.nome

        return p
    }

    override fun getItem(position: Int): Any {
       return listPessoas.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return listPessoas.size
    }

}