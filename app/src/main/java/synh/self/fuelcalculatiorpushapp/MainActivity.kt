package synh.self.fuelcalculatiorpushapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var resultView: TextView
    lateinit var moneyView: TextView

    lateinit var rangeView: EditText
    lateinit var consView: EditText
    lateinit var costView: EditText

    lateinit var calcButton: Button
    lateinit var clearButton: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewInit()
        buttonsInit()
    }

    @SuppressLint("SetTextI18n")
    private fun buttonsInit() {
        calcButton.setOnClickListener {
            val rangeStr = rangeView.text.toString()
            val consStr = consView.text.toString()
            val costStr = costView.text.toString()
            if (rangeStr.isEmpty() || consStr.isEmpty() || costStr.isEmpty()) {
                Toast.makeText(this, "Заполните все поля", Toast.LENGTH_LONG).show()
            } else {
                val range = rangeStr.toDouble()
                val cons = consStr.toDouble()
                val cost = costStr.toDouble()
                val result = range / 100 * cons
                resultView.text = result.toString()
                moneyView.text = "(" + (result * cost).toString() + "\u20BD)"
            }
        }

        clearButton.setOnClickListener {
            resultView.text = "0"
            moneyView.text = ""
            rangeView.text.clear()
            consView.text.clear()
            costView.text.clear()
            rangeView.requestFocus()
        }
    }

    private fun viewInit() {
        resultView = findViewById(R.id.result_litre)
        moneyView = findViewById(R.id.money_label)
        rangeView = findViewById(R.id.edit_range)
        consView = findViewById(R.id.edit_cons)
        costView = findViewById(R.id.edit_litre_cost)
        calcButton = findViewById(R.id.button_calc)
        clearButton = findViewById(R.id.button_clear)
    }
}
