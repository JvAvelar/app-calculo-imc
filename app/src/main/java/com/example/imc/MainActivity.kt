package com.example.imc

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.imc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonCalculate.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if (view.id == binding.buttonCalculate.id)
            buttonCalculate()
    }

    /*
    * Função responsável por validar
    * */
    fun isValid() : Boolean{
        return  binding.textHeigth.text.toString() != "" &&
                binding.textWeigth.text.toString() != "" &&
                binding.textGender.text.toString() != "" &&
                binding.textGender.text.toString().lowercase() == "masculino" ||
                binding.textGender.text.toString().lowercase() == "feminino"
    }
    /*
    * Função responsável por informar o tipo de peso da pesssoa.
    * */
    fun tipoDePeso(result: Float): String {
        if (result < 18.5) {
            return "Abaixo do peso"
        } else if (result in 18.5..24.9) {
            return "Peso normal"
        } else if (result in 25.0..29.9) {
            return "Sobrepeso"
        } else if (result in 30.0..34.9) {
            return "Obesidade grau I"
        } else if (result in 35.0..39.9) {
            return "Obesidade grau II (severa)"
        } else if (result >= 40.0) {
            return "Obesidade grau III (mórbida)"
        }
        return "Você não é normal."
    }

    fun buttonCalculate(){

        // Convertendo os valores dos campos de string para float

        if (isValid()) {
            val altura = binding.textHeigth.text.toString().toFloat()
            val peso = binding.textWeigth.text.toString().toFloat()
            val valorDoImc = "%.1f".format(peso / (altura * altura)).toFloat()

            val tipoDePeso = tipoDePeso(valorDoImc)
            binding.textInformationWeigth.text = "Você está com $tipoDePeso"

            // Atribuindo o valor do resultado para a saída
            binding.textResult.text = "Seu IMC: $valorDoImc"

        } else {
            Toast.makeText(this, R.string.message_error, Toast.LENGTH_SHORT ).show()
        }
    }


}