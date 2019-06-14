package synh.self.fuelcalculatiorpushapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {

    lateinit var resultView: TextView
    lateinit var moneyView: TextView

    lateinit var rangeView: EditText
    lateinit var consView: EditText
    lateinit var costView: EditText

    lateinit var calcButton: Button
    lateinit var clearButton: TextView

    lateinit var currencySpinner: Spinner

    private lateinit var currency: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewInit()
        buttonsInit()
        calcInit()

        currencySpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when(position){
                    0 -> currency = "\u20BD"
                    1 -> currency = "$"
                    2 -> currency = "€"
                }
                calcFuel()
            }

        }
    }

    private fun calcInit() {
        rangeView.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                calcFuel()
            }
        })

        consView.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                calcFuel()
            }
        })

        costView.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                calcFuel()
            }
        })
    }

    @SuppressLint("SetTextI18n")
    private fun buttonsInit() {
        calcButton.setOnClickListener {
            val rangeStr = rangeView.text.toString()
            val consStr = consView.text.toString()
            val costStr = costView.text.toString()

            var isValid = true
            if (rangeStr.isEmpty() || consStr.isEmpty() || costStr.isEmpty()){
                isValid = false
                //Можно дописать визуальное отображение ошибок
            }
            if (isValid){
                val range = rangeStr.toDouble()
                val cons = consStr.toDouble()
                val cost = costStr.toDouble()
                val result = range / 100 * cons
                resultView.text = result.toString()
                moneyView.text = "(" + (result * cost).toString() + currency + ")"
            } else{
                Toast.makeText(this, "Заполните все поля", Toast.LENGTH_LONG).show()
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
        currencySpinner = findViewById(R.id.spinner_currency)
    }

    @SuppressLint("SetTextI18n")
    private fun calcFuel(){
        val rangeStr = rangeView.text.toString()
        val consStr = consView.text.toString()
        val costStr = costView.text.toString()

        var isValid = true
        if (rangeStr.isEmpty() || consStr.isEmpty() || costStr.isEmpty()){
            isValid = false
            //Можно дописать визуальное отображение ошибок
        }

        if (isValid){
            val range = rangeStr.toDouble()
            val cons = consStr.toDouble()
            val cost = costStr.toDouble()
            val result = range / 100 * cons
            resultView.text = result.toString()
            moneyView.text = "(" + (result * cost).toString() + currency + ")"
        }
    }
}
