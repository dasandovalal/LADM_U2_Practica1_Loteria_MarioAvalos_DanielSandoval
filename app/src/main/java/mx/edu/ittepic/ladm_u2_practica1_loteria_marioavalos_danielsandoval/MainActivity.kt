package mx.edu.ittepic.ladm_u2_practica1_loteria_marioavalos_danielsandoval

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    var baraja = arrayOf(R.drawable.carta1,R.drawable.carta2,
        R.drawable.carta3,R.drawable.carta4,R.drawable.carta5,
        R.drawable.carta6,R.drawable.carta7,R.drawable.carta8,
        R.drawable.carta9,R.drawable.carta10,R.drawable.carta11,
        R.drawable.carta12,R.drawable.carta13,R.drawable.carta14,
        R.drawable.carta15,R.drawable.carta16,R.drawable.carta17,
        R.drawable.carta18,R.drawable.carta19,R.drawable.carta20,
        R.drawable.carta21,R.drawable.carta22,R.drawable.carta23,
        R.drawable.carta24,R.drawable.carta25,R.drawable.carta26,
        R.drawable.carta27,R.drawable.carta28,R.drawable.carta29,
        R.drawable.carta30,R.drawable.carta31,R.drawable.carta32,
        R.drawable.carta33,R.drawable.carta34,R.drawable.carta35,
        R.drawable.carta36,R.drawable.carta37,R.drawable.carta38,
        R.drawable.carta39,R.drawable.carta40,R.drawable.carta41,
        R.drawable.carta42,R.drawable.carta43,R.drawable.carta44,
        R.drawable.carta45,R.drawable.carta46,R.drawable.carta47,
        R.drawable.carta48,R.drawable.carta49,R.drawable.carta50,
        R.drawable.carta51,R.drawable.carta52,R.drawable.carta53,
        R.drawable.carta54)

    var barajeada = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.hide()

        var hiloBarajear = HiloBaraja(this)
        hiloBarajear.start()

        bt_tirar.setOnClickListener {
            corrutinaImagenLoteria()
        }
    }

    fun corrutinaImagenLoteria() = GlobalScope.launch {

        for (carta in barajeada) {
            runOnUiThread() {
                iv_cartaJugada.setImageResource(baraja[carta])
            }
            delay(3000)
        }
    }


}

class HiloBaraja(main:MainActivity):Thread(){
    val m = main
    var carta = 0
    var cartaAleatoria = 0
    override fun run() {
        super.run()
        barajear()
    }

    fun barajear(){
        cartaAleatoria = ((Math.random()*54)).toInt()
        m.barajeada.add(cartaAleatoria)
        while (carta<53){
            cartaAleatoria = ((Math.random()*54)).toInt()
            if(!m.barajeada.contains(cartaAleatoria)){
                m.barajeada.add(cartaAleatoria)
                carta++
                sleep(10)
            }
        }
        /*for (i in 0..m.barajeada.size-1){
            Log.i("Barajeada No. $tiro","${m.barajeada[i]}")
            tiro++
        }*/ //Verificar que todas las cartas de barajearon sin repetir
    }
}