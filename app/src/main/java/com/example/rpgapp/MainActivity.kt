package com.example.rpgapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var ref:DatabaseReference
    lateinit var pessoalist :MutableList<Pessoa>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ref = FirebaseDatabase.getInstance().getReference("pessoas")
        pessoalist = mutableListOf()

        button.setOnClickListener {
            pessoasalva()
        }


        ref.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {

                if(p0!!.exists()){
                    pessoalist.clear()

                    for(h in p0.children){
                        val pessoa = p0.getValue(Pessoa::class.java)
                        pessoalist.add(pessoa!!)
                    }
                    val adapter = PessoaApter(applicationContext,pessoalist)
                    listView.adapter = adapter
                }


            }

        })


    }

    private fun pessoasalva(){
        val nome = editText.text.toString()

        if(nome.isEmpty()){
            editText.error = "por favor escreva seu nome"
            return
        }

        val pessoaid = ref.push().key
        val pessoa = Pessoa(pessoaid!!,nome)

        ref.child(pessoaid).setValue(pessoa).addOnCompleteListener{
            Toast.makeText(applicationContext,"Pessoa salvado com sucesso",Toast.LENGTH_LONG).show()
        }
    }
}
