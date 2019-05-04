package com.example.divine.Activity

import android.content.Intent
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

class PlayActivity : AppCompatActivity(), View.OnClickListener{

    var userWord: ChoseWord? = null
    var listButton: ArrayList<Button>? = null
    var listIdButton: IntArray? = null
    var imageDelete: ImageView? = null
    var showTraceWord: TextView? = null
    var textCorrect: TextView? = null
    var heart1: ImageView? = null
    var heart2: ImageView? = null
    var heart3: ImageView? = null
    var imageMove: ImageView? = null
    var positionLastWord: Int = 0
    var positionSelections: Int = 0
    var contLoseLif: Int = 0
    var totalCorrect: Int = 0
    var actualCorrect: Int = 0
    var buttonsSelection: IntArray? = null
    val splash_time_out: Long = 1000
    var words: CharArray? = null
    var alreadyChecked: Boolean? = null
    var chronometer: MyChronometer? = null
    var alertDialog: MyDialogMessage? = null
    var backMainIntent: Intent? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)
        init()
        startTime()

    }

    fun init(){

        this.contLoseLif = 3
        this.backMainIntent = Intent(this,MainActivity::class.java)
        instanceButtons()
        allActionButtonDeleteShow()
    }

    override fun onResume(){
        super.onResume()
        this.chronometer!!.backFromStartChronometer()
    }

    override fun onPause(){
        super.onPause()
        this.chronometer!!.stopAndResetChronometer()
    }

    fun instanceButtons(){

        this.listIdButton = intArrayOf(R.id.btnAction1, R.id.btnAction2,R.id.btnAction3,R.id.btnAction4,R.id.btnAction5,R.id.btnAction6,R.id.btnAction7,R.id.btnAction8, R.id.btnAction9,R.id.btnAction10,R.id.btnAction11,R.id.btnAction12,R.id.btnAction13,R.id.btnAction14,R.id.btnAction15,R.id.btnAction16,R.id.btnAction17,R.id.btnAction18,R.id.btnAction19,R.id.btnAction20,R.id.btnAction21,R.id.btnAction22,R.id.btnAction23,R.id.btnAction24)
        this.alertDialog = MyDialogMessage(this,R.style.Base_Theme_MaterialComponents_Dialog_Alert)
        this.listButton = ArrayList(24)
        this.chronometer = MyChronometer(findViewById(R.id.cronometro))
        this.userWord = ChoseWord(this)
        this.imageMove = findViewById(R.id.imageMoveAtual)
        this.textCorrect = findViewById(R.id.textContPoint)
        this.showTraceWord = findViewById(R.id.textNameMove)
        this.imageDelete = findViewById(R.id.imageDelete)
        this.buttonsSelection = IntArray(23)
        this.totalCorrect = userWord!!.size()
        this.actualCorrect = 0
        this.heart1 = findViewById(R.id.heart1)
        this.heart2 = findViewById(R.id.heart2)
        this.heart3 = findViewById(R.id.heart3)
        initButtons()
        setTextAll()
    }

    fun initButtons(){
        for(i in 0..23){
            this.listButton!!.add((findViewById<Button>(this.listIdButton!![i])) as Button)
        }
    }

    fun setButtons(){
        for(i in 0..this.listButton!!.size-1){
            this.listButton!![i].setText(this.words!![i].toString())
        }
    }

    fun allActionButtonDeleteShow(){

        for(i in 0..this.listButton!!.size-1){

            this.listButton?.get(i)?.let { setOnClick(it) }

        }

        this.imageDelete!!.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                alreadyChecked = false
                deleteSpaceClickButton()
            }
        })

    }

    fun setOnClick(buttonAction: Button) {
        buttonAction.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
       var buttonAll: Button = view as Button
        takesWordClickButton(buttonAll.text.toString().toCharArray()[0],buttonAll)
        checkNameEqualsImage()
    }

    fun setTextAll(){

        this.alreadyChecked = false
        this.positionLastWord = 0
        this.positionSelections = 0
        this.userWord?.choseWordImage()
        this.showTraceWord!!.text = this.userWord!!.mountLines()
        this.textCorrect!!.text = "${this.actualCorrect}/${this.totalCorrect}"
        this.words = userWord!!.choseWordKeyboard()
        this.imageMove!!.setImageResource(this.userWord?.image!!)
        this.setButtons()
    }

    fun takesWordClickButton(word: Char,button: Button){

        var newWordLine: CharArray = this.showTraceWord!!.text.toString().toCharArray()
        for(i in 0..this.userWord!!.move.length-1){
            if(newWordLine[i] == '-'){
                newWordLine[i] = word
                this.showTraceWord!!.setText(String(newWordLine).toUpperCase())
                this.positionLastWord++
                buttonVisibilite(button)
                break
            }
        }
    }

    fun deleteSpaceClickButton(){
        var newWordLine = this.showTraceWord!!.text.toString().toCharArray()
        if(this.positionLastWord > 0){
            newWordLine[--this.positionLastWord] = '-'
            buttonVisibilite(takeesButtonLastSelection())
        }
        if(this.positionLastWord == 0 && newWordLine[0] != '-'){
            newWordLine[0] = '-'
            this.positionLastWord = 0
        }
        this.showTraceWord!!.setText(String(newWordLine))
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

    fun checkNameEqualsImage(){
        if(this.positionLastWord == this.userWord!!.move.length && !this.alreadyChecked!!){
            if(this.userWord!!.checkWordEquals(this.userWord!!.getPositionImageArray(this.userWord!!.image!!),this.showTraceWord!!.text.toString())){
                Toast.makeText(applicationContext,"certa",Toast.LENGTH_SHORT).show()
                this.actualCorrect++
                nextDivine()
            }else{
                Toast.makeText(applicationContext,"errado",Toast.LENGTH_SHORT).show()
                this.alreadyChecked = true
            }
        }
    }

    fun nextDivine(){
        Handler().postDelayed({
                resetTime()
                if(contLoseLif > 0){
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
        for(i in 0..this.listButton!!.size-1){
            this.listButton?.get(i)!!.visibility = View.VISIBLE
        }
    }

    fun startTime(){
        this.chronometer!!.startChronometer()
        this.chronometer!!.finishTime(object : MyChronometer.TimeOver {
            override fun finishOver() {
                nextDivine()
                loseLife()
            }
        })
    }

    fun resetTime(){
        finishPlay()
        if(this.contLoseLif > 0){
            this.chronometer!!.resetTime()
        }else{
            this.chronometer!!.stopAndResetChronometer()
        }
    }

    fun loseLife(){
        when(this.contLoseLif--){
            3 -> this.heart3!!.setImageResource(R.drawable.heart_broken)
            2 -> this.heart2!!.setImageResource(R.drawable.heart_broken)
            1 -> this.heart1!!.setImageResource(R.drawable.heart_broken)
        }
    }

    fun finishPlay(){
        if(this.contLoseLif == 0){
            this.chronometer!!.startChronometer()
            this.alertDialog!!.message("jogo finalizado",object : MyDialogMessage.BackToMenu {
                override fun popBack() {
                    startActivity(backMainIntent)
                }
            })
        }
    }

    fun finishAllRandom(){
        if(this.userWord!!.allRandom()) {
            this.alertDialog!!.message("todas as imagens foram utilizadas", object : MyDialogMessage.BackToMenu {
                override fun popBack() {
                    startActivity(backMainIntent)
                }
            })
        }
    }
}
