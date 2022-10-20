package mx.edu.ittepic.ladm_u2_practica1_loteria_marioavalos_danielsandoval

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    var imagenes = arrayOf(R.drawable.i1,R.drawable.i2,R.drawable.i3,R.drawable.i4,R.drawable.i5,R.drawable.i6,
        R.drawable.i7,R.drawable.i8,R.drawable.i9,R.drawable.i10,R.drawable.i11,R.drawable.i12,R.drawable.i13,
        R.drawable.i14,R.drawable.i15,R.drawable.i16,R.drawable.i17,R.drawable.i18,R.drawable.i19,R.drawable.i20,
        R.drawable.i21,R.drawable.i22,R.drawable.i23,R.drawable.i24,R.drawable.i26,R.drawable.i27,R.drawable.i28,
        R.drawable.i29,R.drawable.i30,R.drawable.i31,R.drawable.i32,R.drawable.i33,R.drawable.i34,R.drawable.i35,
        R.drawable.i36,R.drawable.i37,R.drawable.i38,R.drawable.i39,R.drawable.i40,R.drawable.i41,R.drawable.i42,
        R.drawable.i43,R.drawable.i44,R.drawable.i45,R.drawable.i46,R.drawable.i47,R.drawable.i48,R.drawable.i49,
        R.drawable.i50,R.drawable.i51,R.drawable.i52,R.drawable.i53,R.drawable.i54)

    var arregloNumeros = ArrayList<Int>()
    var contador = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bt_tirar.setOnClickListener {
            corrutinaImagenLoteria()
        }
    }

    fun corrutinaImagenLoteria() = GlobalScope.launch {

        while (contador < 54){
            var numeroAleatorio = Math.random()*54
            arregloNumeros.add(numeroAleatorio.toInt())
            contador++
        }

        for (imagen in arregloNumeros) {
            runOnUiThread() {
                iv_baraja.setImageResource(imagenes[imagen])
            }
            delay(1000)
        }
    }
}