package mx.edu.ittepic.ladm_u2_practica1_loteria_marioavalos_danielsandoval

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception

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

    var audioCartas = arrayOf(R.raw.carta1,R.raw.carta2,R.raw.carta3,
        R.raw.carta4,R.raw.carta5,R.raw.carta6,R.raw.carta7,R.raw.carta8,
        R.raw.carta9,R.raw.carta10,R.raw.carta11,R.raw.carta12,R.raw.carta13,
        R.raw.carta14,R.raw.carta15,R.raw.carta16,R.raw.carta17,R.raw.carta18,
        R.raw.carta19,R.raw.carta20,R.raw.carta21,R.raw.carta22,R.raw.carta23,
        R.raw.carta24,R.raw.carta25,R.raw.carta26,R.raw.carta27,R.raw.carta28,
        R.raw.carta29,R.raw.carta30,R.raw.carta31,R.raw.carta32,R.raw.carta33,
        R.raw.carta34,R.raw.carta35,R.raw.carta36,R.raw.carta37,R.raw.carta38,
        R.raw.carta39,R.raw.carta40,R.raw.carta41,R.raw.carta42,R.raw.carta43,
        R.raw.carta44,R.raw.carta45,R.raw.carta46,R.raw.carta47,R.raw.carta48,
        R.raw.carta49,R.raw.carta50,R.raw.carta51,R.raw.carta52,R.raw.carta53,
        R.raw.carta54)

    var barajeada = ArrayList<Int>()

    var juegoEmpezado = false
    var jugando = false
    var ganador = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.hide()

        corrutinaBarajeadora()
        val juego = HiloJuego(this)

        bt_jugar.setOnClickListener {
            if (!juegoEmpezado) {
                var seCorre = MediaPlayer.create(this,R.raw.iniciar_loteria)
                seCorre.start()
                Thread.sleep(3000)
                bt_jugar.text = "¡LOTERIA!"
                bt_jugar.textSize = 40f
                juego.start()

            }else if (juegoEmpezado){
                jugando = !jugando
                ganador = true
                if (jugando){
                    bt_jugar.text = "Pausa"
                }
                else if(!jugando){
                    bt_jugar.text = "Seguir... "
                    bt_jugar.textSize = 30f
                }
            }
        }

        bt_nuevo.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            this.startActivity(intent)
            finishAffinity()
        }
    }

    fun corrutinaBarajeadora() = GlobalScope.launch {
        var tiro = 0
        var carta = 0
        var cartaAleatoria = ((Math.random()*54)).toInt()
        barajeada.add(cartaAleatoria)
        while (carta<53){
            cartaAleatoria = ((Math.random()*54)).toInt()
            if(!barajeada.contains(cartaAleatoria)){
                barajeada.add(cartaAleatoria)
                carta++
                delay(10)
            }
        }
        /*for (i in 0..barajeada.size-1){
            Log.i("Barajeada No. $tiro","${barajeada[i]}")
            tiro++
        } *///Verificar que todas las cartas de barajearon sin repetir

    }
}

class HiloJuego(m:MainActivity):Thread(){
    val m = m
    var cartaHilo = 0
    lateinit var cantor : MediaPlayer
    override fun run() {
        super.run()
        m.juegoEmpezado = true
        m.jugando = true
        try{
            while (m.juegoEmpezado){
                while (m.jugando){
                    if (cartaHilo<54 && !m.ganador) {
                        m.runOnUiThread {
                            m.iv_cartaJugada.setImageResource(m.baraja[m.barajeada[cartaHilo]])
                            cantor = MediaPlayer.create(m,m.audioCartas[m.barajeada[cartaHilo]])
                            cantor.start()
                            cartaHilo++
                        }
                        sleep(3000)
                    }
                    else if (cartaHilo<54 && m.ganador){
                        m.runOnUiThread {
                            m.iv_cartaJugada.setImageResource(m.baraja[m.barajeada[cartaHilo]])
                            cantor = MediaPlayer.create(m,m.audioCartas[m.barajeada[cartaHilo]])
                            cantor.start()
                            cartaHilo++
                        }
                        sleep(1750)
                    }else{
                        m.runOnUiThread {
                            m.bt_jugar.visibility = View.INVISIBLE
                            m.bt_nuevo.visibility = View.VISIBLE
                        }
                        sleep(10)
                    }
                }
            }
        }catch (e:Exception){
            Log.d("~Problema: ","${e.message}")
        }
    }
}