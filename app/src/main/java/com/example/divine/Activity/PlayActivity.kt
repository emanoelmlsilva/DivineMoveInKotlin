package com.example.divine.Activity

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.*
import com.example.divine.DialogMessage.MyDialogMessage
import com.example.divine.Model.ChoseWord
import com.example.divine.R
import com.example.divine.Chronometer.MyChronometer
import com.example.divine.Controller.RecordController
import com.example.divine.Model.CounterScore
import com.example.divine.Model.Records
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_play.*
import kotlinx.android.synthetic.main.heartcont.*
import kotlinx.android.synthetic.main.keyboardlength24.*
import java.lang.Exception

class PlayActivity: AppCompatActivity(), View.OnClickListener{

    var userWord: ChoseWord? = null
    var listButton: ArrayList<Button>? = null
    val listIdButton = listOf(R.id.btnAction1, R.id.btnAction2,R.id.btnAction3,R.id.btnAction4,R.id.btnAction5,R.id.btnAction6,
        R.id.btnAction7,R.id.btnAction8, R.id.btnAction9,R.id.btnAction10,R.id.btnAction11,R.id.btnAction12,R.id.btnAction13,R.id.btnAction14,R.id.btnAction15,R.id.btnAction16,
        R.id.btnAction17,R.id.btnAction18,R.id.btnAction19,R.id.btnAction20,R.id.btnAction21,R.id.btnAction22,R.id.btnAction23,R.id.btnAction24)
    var positionLastWord: Int = 0
    var positionSelections: Int = 0
    var contLoseLif: Int = 0
    var totalCorrect: Int = 0
    var actualCorrect: Int = 0
    var actualImage: Int = 0
    val buttonsSelection = IntArray(23)
    val splash_time_out: Long = 100
    var words:CharArray? = null
    var alreadyChecked: Boolean? = null
    var chronometer: MyChronometer? = null
    var alertDialog: MyDialogMessage? = null
    var backMainIntent: Intent? = null
    var fasesDB: RecordController? = null
    var recordAtual: Records? = null
    var contScore: CounterScore = CounterScore(1)
    var thread: Thread? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)

        ball_observe_jump_json.visibility = View.INVISIBLE

        init()

        lav_android_wave_json.visibility = View.VISIBLE
        lav_android_wave_json.playAnimation()

        thread = Thread(myRunnable)
        thread!!.start()

    }

    val myRunnable = Runnable {

        try {
            Thread.sleep(10000)
        } catch (erro: InterruptedException) {
            erro.printStackTrace()
        }

        try {


            startTime()
            lav_android_wave_json.visibility = View.INVISIBLE
            lav_android_wave_json.cancelAnimation()

        }catch (erro: Exception){
            erro.printStackTrace()
        }

    }

    fun init(){
        fasesDB = RecordController(this)
        this.contLoseLif = 3
        this.backMainIntent = Intent(this,FasesActivity::class.java)
        instanceButtons()
        allActionButtonDeleteShowKeyBoard()
        clickButtn()
    }

    override fun onResume(){
        super.onResume()
        if(this.chronometer!!.timeFinish) this.chronometer!!.backFromStartChronometer()
    }

    override fun onPause(){
        super.onPause()
        this.chronometer?.stopAndResetChronometer()
    }

    fun instanceButtons(){

        this.alertDialog = MyDialogMessage(this,R.style.myalertDialogCustom)
        this.listButton = ArrayList(24)
        this.chronometer = MyChronometer(findViewById(R.id.cronometro))
        this.userWord = ChoseWord(this)
        this.totalCorrect = userWord!!.size()
        this.actualImage = 1
        initButtonsKeyBoard()
        setTextAll()
    }


    fun initButtonsKeyBoard(){
        for(i in 0..23){
            this.listButton?.add((findViewById(this.listIdButton!![i])) as Button)
        }
    }

    fun setWordButtonsKeyBoard(){
        for(i in 0 until this.listButton!!.size){

            this.listButton!![i].text = this.words?.get(i)?.toString()
        }
    }

    fun allActionButtonDeleteShowKeyBoard(){

        for(i in 0 until this.listButton!!.size){

            setOnClickKeyBoard(this.listButton!!.get(i))

        }

    }

    fun setOnClickKeyBoard(button: Button) {
        button.setOnClickListener(this)


    }

    fun clickButtn(){//onde esta dando erro

        btn_pass.setOnClickListener {

//            ball_observe_jump_json.visibility = View.VISIBLE
//            ball_observe_jump_json.playAnimation()
            nextDivine()
//            val thread2 = Thread(myRunnable2)
//            thread2!!.start()

        }

        imageDelete.setOnClickListener {
            alreadyChecked = false
            deleteSpaceClickButton()
        }
    }

    val myRunnable2 = Runnable {

        try {
            Thread.sleep(1000)
        } catch (erro: InterruptedException) {
            erro.printStackTrace()
        }

        try {
            nextDivine()
            ball_observe_jump_json.visibility = View.INVISIBLE
            ball_observe_jump_json.cancelAnimation()

        } catch (erro: Exception) {
            erro.printStackTrace()
        }

    }

    override fun onClick(view: View?) {

        var buttonAll: Button = view as Button
        takesWordClickButton(buttonAll.text.toString().toCharArray()[0],buttonAll)
        checkNameEqualsImage(view)

    }

    fun setTextAll(){
        this.alreadyChecked = false
        this.positionLastWord = 0
        this.positionSelections = 0
        this.userWord?.choseWordImage()
        this.textNameMove.text = this.userWord!!.mountLines()
        this.textContPoint.text = "${this.actualImage}/${this.totalCorrect}"
        words = userWord?.choseWordKeyboard()
        this.imageMoveAtual.setImageResource(this.userWord?.image!!)
        this.btn_pass
        this.setWordButtonsKeyBoard()
    }

    fun takesWordClickButton(word: Char,button: Button){

        var newWordLine: CharArray = this.textNameMove.text.toString().toCharArray()
        for(i in 0 until this.userWord!!.move.length){
            if(newWordLine[i] == '-' || (newWordLine[i] == ' ' && newWordLine[i+1] == '-')){
                if(newWordLine[i] == ' ') newWordLine[i+1] = word else newWordLine[i] = word
                this.textNameMove.setText(String(newWordLine).toUpperCase())
                if(newWordLine[i] == ' ') this.positionLastWord += 2 else this.positionLastWord++
                buttonVisibilite(button)
                break
            }
        }

    }

    fun deleteSpaceClickButton(){

        var newWordLine = this.textNameMove.text.toString().toCharArray()
        if(this.positionLastWord > 0 && newWordLine[positionLastWord-1] != ' '){
            newWordLine[--this.positionLastWord] = '-'
            buttonVisibilite(takeesButtonLastSelection())
        }else if(this.positionLastWord > 0 && newWordLine[positionLastWord-1] == ' '){
            this.positionLastWord--
        }
        if(this.positionLastWord == 0 && (newWordLine[0] != '-' && newWordLine[positionLastWord] != ' ')){
            newWordLine[0] = '-'
            this.positionLastWord = 0
        }
        this.textNameMove.setText(String(newWordLine))
    }

    fun buttonVisibilite(visibiliteButton: Button){
        if(visibiliteButton.visibility == View.INVISIBLE){
            visibiliteButton.visibility = View.VISIBLE
        }else{
            visibiliteButton.visibility = View.INVISIBLE
            addButtonSelection(visibiliteButton.id)
        }
    }

    fun addButtonSelection(idButton: Int){
        this.buttonsSelection!![this.positionSelections++] = idButton
    }

    fun takeesButtonLastSelection(): Button{
        return findViewById(this.buttonsSelection!![--this.positionSelections])
    }

    fun checkNameEqualsImage(view:View){
        if(this.positionLastWord == this.userWord!!.move.length && !this.alreadyChecked!!){
            if(this.userWord!!.checkWordEquals(this.userWord!!.getPositionImageArray(this.userWord!!.image!!),this.textNameMove.text.toString())){
                this.actualCorrect++
                snackbarCustom(view,"#FF2BA700","Resposta Certa")
                this.contScore.changeLevel(this.userWord!!.level)
                this.contScore.calculateScore()
                nextDivine()
            }else{
                snackbarCustom(view,"#AF0000","Resposta Errada")
                this.alreadyChecked = true
            }
        }
    }

    private fun snackbarCustom(view:View,textColorHexa: String,message: String){
        val snackbar: Snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
        val snackView: View = snackbar.view
        snackView.setBackgroundColor(Color.parseColor("#212121"))
        val textview: TextView = snackView.findViewById(R.id.snackbar_text)
        textview.setTextColor(Color.parseColor(textColorHexa))
        snackbar.show()
    }

    fun nextDivine(){
        Handler().postDelayed({
            resetTime()
            if(contLoseLif > 0){
                this.actualImage++
                finishAllRandom()
                loadingNextImage()
            }
        },splash_time_out)
    }

    fun loadingNextImage(){
        setTextAll()
        resetButton()
    }

    fun resetButton(){
        for(i in 0 until this.listButton!!.size){
            this.listButton?.get(i)!!.visibility = View.VISIBLE
        }
    }

    fun startTime(){
        this.chronometer?.startChronometer()
        this.chronometer?.finishTime(object : MyChronometer.TimeOver {
            override fun finishOver() {
                nextDivine()
                loseLife()
            }
        })
    }

    fun resetTime(){
        finishPlay()
        if(this.contLoseLif > 0){
            this.chronometer?.resetTime()
        }else{
            this.chronometer?.stopAndResetChronometer()
        }
    }

    fun loseLife(){
        when(this.contLoseLif--){
            3 -> this.heart3.setImageResource(R.drawable.heart_broken)
            2 -> this.heart2.setImageResource(R.drawable.heart_broken)
            1 -> this.heart1.setImageResource(R.drawable.heart_broken)
        }
    }

    fun finishPlay(){
        if(this.contLoseLif == 0){

            recordAtual = Records(contScore.getScoreFinal(),actualCorrect, this.userWord!!.size())

            this.alertDialog?.message("Game Over",object : MyDialogMessage.BackToMenu {
                override fun popBack() {

                    fasesDB?.addRecord(recordAtual)
                    startActivity(backMainIntent)
                    finish()
                }
            })
        }
    }

    fun finishAllRandom(){
        if(this.userWord!!.allRandom()) {

            if(contScore.getScoreFinal() > 0){
                recordAtual = Records(contScore.getScoreFinal(),actualCorrect, this.totalCorrect)

                fasesDB?.addRecord(recordAtual)
            }
            this.chronometer?.stopAndResetChronometer()
            this.alertDialog?.message("todas as imagens foram utilizadas", object : MyDialogMessage.BackToMenu {
                override fun popBack() {
//                    startActivity(backMainIntent)
                    finish()
                }
            })
        }
    }
}
