package com.example.alcoolougasolina

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var percentual:Double = 0.7
    private var isSwitchChecked: Boolean = false

    private lateinit var btCalc : Button
    private lateinit var gasEdtTxt : EditText
    private lateinit var alcEdtTxt : EditText
    private lateinit var resTxt : TextView
    private lateinit var swPercent : Switch
    private lateinit var sharedPreferences : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("PDM23","No onCreate, $percentual")
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        percentual = sharedPreferences.getFloat("percentual", 0.7F).toDouble()
        isSwitchChecked = sharedPreferences.getBoolean("switchChecked", false)


        btCalc = findViewById(R.id.btCalcular)
        gasEdtTxt = findViewById(R.id.edGasolina)
        alcEdtTxt = findViewById(R.id.edAlcool)
        resTxt = findViewById(R.id.result)
        swPercent= findViewById(R.id.swPercentual)

        swPercent.isChecked = isSwitchChecked

        btCalc.setOnClickListener(View.OnClickListener {
            if (gasEdtTxt.text.isNotEmpty() and alcEdtTxt.text.isNotEmpty()) {
                var gasPrice: Double = gasEdtTxt.text.toString().toDouble()
                var etanolPrice: Double = alcEdtTxt.text.toString().toDouble()
                if(etanolPrice <= percentual*gasPrice) {
                    resTxt.setText("Álcool vale a pena!")
                } else {
                    resTxt.setText("Álcool NÃO vale a pena!")
                }
            } else {
                resTxt.setText("Insira valores válidos")
            }
        })

        swPercent.setOnCheckedChangeListener { compoundButton, isChecked ->
            percentual = if (!isChecked) {
                0.7
            } else {
                0.75
            }
            isSwitchChecked = isChecked

            sharedPreferences.edit()
                .putFloat("percentual", percentual.toFloat())
                .putBoolean("switchChecked", isSwitchChecked)
                .apply()
        }
    }
override fun onResume(){
    super.onResume()
    Log.d("PDM23","No onResume, $percentual")
}
override fun onStart(){
    super.onStart()
    Log.d("PDM23","No onResume")
}
override fun onPause(){
    super.onPause()
    Log.d("PDM23","No onResume")
}
override fun onStop(){
    super.onStop()
    Log.d("PDM23","No onResume")
}
override fun onDestroy(){
    super.onDestroy()
    Log.d("PDM23","No onResume")
}
}