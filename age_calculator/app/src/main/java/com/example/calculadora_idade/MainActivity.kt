package com.example.telakt

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // Variaveis para dos componentes da tela
    private lateinit var DataAtualDia : EditText
    private lateinit var DataAtualMes : EditText
    private lateinit var DataAtualAno : EditText

    private lateinit var DataNascDia : EditText
    private lateinit var DataNascMes : EditText
    private lateinit var DataNascAno : EditText

    private lateinit var textResIdade : TextView
    private lateinit var btnCalc : Button
    private lateinit var btnClear : Button

    var ano = 0
    var mes = 0
    var dia = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Relacionar variáveis aos componentes
        DataAtualDia = findViewById(R.id.textDataAtualDia)
        DataAtualMes = findViewById(R.id.textDataAtualMes)
        DataAtualAno = findViewById(R.id.textDataAtualAno)

        DataNascDia = findViewById(R.id.textDataNascDia)
        DataNascMes = findViewById(R.id.textDataNascMes)
        DataNascAno = findViewById(R.id.textDataNascAno)

        textResIdade = findViewById(R.id.textResIdade)

        btnCalc = findViewById(R.id.btnCalc)
        btnClear = findViewById(R.id.btnClear)

        btnCalc.setOnClickListener{calcular()} // Atribuição de uma function de cálculo para o botão
        btnClear.setOnClickListener{limpar()} // Atribuição de uma function de limpeza para o botão

    }

    // Funções que torna possível o fechamento do teclado virtual do celular ao clicar no botão de calcular
    fun Fragment.esconderTeclado() {
        view?.let { activity?.esconderTeclado(it) }
    }

    fun Activity.esconderTeclado() {
        if (currentFocus == null) View(this) else currentFocus?.let { esconderTeclado(it) }
    }

    fun Context.esconderTeclado(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    // Função de limpeza das entradas para o botão "Limpar"
    private fun limpar() {
        textDataAtualDia.setText("");
        textDataAtualMes.setText("");
        textDataAtualAno.setText("");
        textDataNascDia.setText("");
        textDataNascMes.setText("");
        textDataNascAno.setText("");
        textResIdade.setText("");
    }
    
    // Função do cálculo, e exibição do resultado da conta
    private fun calcular(){

            // Obtenção e conversão dos valores obtidos
            val dataAtualD = DataAtualDia.text.toString().toInt()
            val dataAtualM = DataAtualMes.text.toString().toInt()
            val dataAtualA = DataAtualAno.text.toString().toInt()

            val dataNascD = DataNascDia.text.toString().toInt()
            val dataNascM = DataNascMes.text.toString().toInt()
            val dataNascA = DataNascAno.text.toString().toInt()

                var diff =
                    365 * dataAtualA + 30 * dataAtualM + dataAtualD - 365 * dataNascA - 30 * dataNascM - dataNascD

                ano = diff / 365
                diff %= 365

                mes = diff / 30
                diff %= 30

                dia = diff

                if (ano < 0 || mes < 0 || dia < 0) {
                    textResIdade.setText("datas inválidas!")
                    esconderTeclado()

                } else if (dataAtualM > 12 || dataAtualD > 31 || dataNascM > 12 || dataNascD > 31) {
                    textResIdade.setText("datas inválidas!")
                    esconderTeclado()

                } else {
                    textResIdade.setText("" + ano + " ano(s), " + mes + " mes(es) e " + dia + " dia(s).")
                    esconderTeclado()
                }
        }
}


