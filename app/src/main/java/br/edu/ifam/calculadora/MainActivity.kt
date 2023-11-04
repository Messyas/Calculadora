package br.edu.ifam.calculadora

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.edu.ifam.calculadora.databinding.ActivityMainBinding
import org.mariuszgromada.math.mxparser.Expression
import android.media.MediaPlayer
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        //serve para bloquear a orientacao da tela em todos as activitys caso necessario, da pra fazer pelo xml tambem


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mediaPlayer = MediaPlayer.create(this, R.raw.mousesom)

        setupCachedResult()//lembre-se que o binding precisa ser inicializado antes de retor os dados armazenados

        val calculo = binding.calculo

        binding.um.setOnClickListener {
            tocasom()
            "${calculo.text}1".also { calculo.text = it }
        }

        binding.dois.setOnClickListener {
            tocasom()
            "${calculo.text}2".also { calculo.text = it }
        }

        binding.tres.setOnClickListener {
            tocasom()
            "${calculo.text}3".also { calculo.text = it }
        }

        binding.quatro.setOnClickListener {
            tocasom()
            "${calculo.text}4".also { calculo.text = it }
        }

        binding.cinco.setOnClickListener {
            tocasom()
            "${calculo.text}5".also { calculo.text = it }
        }

        binding.seis.setOnClickListener {
            tocasom()
            "${calculo.text}6".also { calculo.text = it }
        }

        binding.sete.setOnClickListener {
            tocasom()
            "${calculo.text}7".also { calculo.text = it }
        }

        binding.oito.setOnClickListener {
            tocasom()
            "${calculo.text}8".also { calculo.text = it }
        }

        binding.nove.setOnClickListener {
            tocasom()
            "${calculo.text}9".also { calculo.text = it }
        }

        binding.parenteseAbrindo.setOnClickListener {
            tocasom()
            "${calculo.text}(".also { calculo.text = it }
        }

        binding.parenteseFechando.setOnClickListener {
            tocasom()
            "${calculo.text})".also { calculo.text = it }
        }

        binding.divisao.setOnClickListener {
            tocasom()
            "${calculo.text}/".also { calculo.text = it }
        }

        binding.multiplicacao.setOnClickListener {
            tocasom()
            "${calculo.text}*".also { calculo.text = it }
        }

        binding.subtracao.setOnClickListener {
            tocasom()
            "${calculo.text}-".also { calculo.text = it }
        }
        binding.variavel.setOnClickListener {
            tocasom()
            "${calculo.text}X".also { calculo.text = it }
        }

        binding.unidadeimaginaria.setOnClickListener {
            tocasom()
            "${calculo.text}i".also { calculo.text = it }
        }

        binding.logaritimoNatural.setOnClickListener {
            tocasom()
            "${calculo.text}ln(".also { calculo.text = it }
        }

        binding.logaritimoDecimal.setOnClickListener {
            tocasom()
            "${calculo.text}log(".also { calculo.text = it }
        }

        binding.seno.setOnClickListener {
            tocasom()
            "${calculo.text}sin(".also { calculo.text = it }
        }

        binding.cosseno.setOnClickListener {
            tocasom()
            "${calculo.text}cos(".also { calculo.text = it }
        }

        binding.tangente.setOnClickListener {
            tocasom()
            "${calculo.text}tan(".also { calculo.text = it }
        }

        binding.raizQuadrada.setOnClickListener {
            tocasom()
            "${calculo.text}sqrt(".also { calculo.text = it }
        }

        binding.elevado.setOnClickListener {
            tocasom()
            "${calculo.text}^".also { calculo.text = it }
        }

        binding.fatorial.setOnClickListener {
            tocasom()
            "${calculo.text}!".also { calculo.text = it }
        }

        binding.pi.setOnClickListener {
            tocasom()
            "${calculo.text}π".also { calculo.text = it }
        }

        binding.euler.setOnClickListener {
            tocasom()
            "${calculo.text}e".also { calculo.text = it }
        }

        binding.soma.setOnClickListener {
            tocasom()
            "${calculo.text}+".also { calculo.text = it }
        }

        binding.ponto.setOnClickListener {
            tocasom()
            "${calculo.text}.".also { calculo.text = it }
        }

        binding.zero.setOnClickListener {
            tocasom()
            "${calculo.text}0".also { calculo.text = it }
        }

        binding.apagar.setOnClickListener {
            tocasom()
            calculo.text.dropLast(1).also { calculo.text = it }
        }

        binding.ce.setOnClickListener {
            tocasom()
            calculo.text = ""
            binding.resultado.text = ""
        }

        binding.igual.setOnClickListener {
            tocasom()
            try {
                val resultadoCalculado = Expression(calculo.text.toString()).calculate()

                if (resultadoCalculado.isNaN()) {
                    binding.resultado.text = getString(R.string.Expressao_Invalida)
                } else {
                    binding.resultado.text = resultadoCalculado.toString()
                    saveSharedPreferences(resultadoCalculado.toFloat())
                    //salva o resultado calculado na sharedPreferences
                }
            } catch (e: Exception) {
                binding.resultado.text = getString(R.string.Erro_na_expressao)
            }
        }
    }
    private fun saveSharedPreferences(resultadoCalculado: Float) {
        val sharedPref = getSharedPreferences("Resultado", Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putFloat("ResultadoConta", resultadoCalculado)
            apply()
        }
    }
    private fun getSharedPref(): Float {
        val sharedPref = getSharedPreferences("Resultado", Context.MODE_PRIVATE)
        return sharedPref.getFloat("ResultadoConta", 0.0f)
    }

    private fun setupCachedResult() {
        val valorCalculado = getSharedPref()
        binding.resultado.text = valorCalculado.toString()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString("calculo_text", binding.calculo.text.toString())
        outState.putString("resultado_text", binding.resultado.text.toString())
        // Salve outros dados necessários
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        binding.calculo.text = savedInstanceState.getString("calculo_text", "")
        binding.resultado.text = savedInstanceState.getString("resultado_text", "")
        // Restaure outros dados salvos
    }
    private fun tocasom() {
        mediaPlayer?.start()
    }
    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
    }
}