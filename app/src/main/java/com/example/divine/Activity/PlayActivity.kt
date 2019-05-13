package com.example.divine.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.*
import com.example.divine.DialogMessage.MyDialogMessage
import com.example.divine.Model.ChoseWord
import com.example.divine.R
import com.example.divine.Chronometer.MyChronometer
import kotlinx.android.synthetic.main.activity_play.*
import kotlinx.android.synthetic.main.heartcont.*
import kotlinx.android.synthetic.main.keyboardlength24.*

class PlayActivity : AppCompatActivity(), View.OnClickListener{

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
    val buttonsSelection = IntArray(23)
    val splash_time_out: Long = 1000
    var words:CharArray? = null
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
        this.backMainIntent = Intent(this,InitGameActivity::class.java)
        instanceButtons()
        allActionButtonDeleteShow()
    }

    override fun onResume(){
        super.onResume()
        this.chronometer!!.backFromStartChronometer()
    }

    override fun onPause(){
        super.onPause()
        this.chronometer?.stopAndResetChronometer()
    }

    fun instanceButtons(){

        this.alertDialog = MyDialogMessage(this,R.style.Theme_MaterialComponents_Dialog_Alert)
        this.listButton = ArrayList(24)
        this.chronometer = MyChronometer(findViewById(R.id.cronometro))
        this.userWord = ChoseWord(this)
        this.totalCorrect = userWord!!.size()
        this.actualCorrect = 0
        initButtons()
        setTextAll()
    }

    fun initButtons(){
        for(i in 0..23){
            this.listButton?.add((findViewById<Button>(this.listIdButton!![i])) as Button)
        }
    }

    fun setButtons(){
        for(i in 0 until this.listButton!!.size){

            this.listButton!![i].text = this.words?.get(i)?.toString()
        }
    }

    fun allActionButtonDeleteShow(){

        for(i in 0 until this.listButton!!.size){

            this.listButton?.get(i)?.let { setOnClick(it) }

        }

        this.imageDelete.setOnClickListener(object: View.OnClickListener{
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
        this.textNameMove.text = this.userWord!!.mountLines()
        this.textContPoint.text = "${this.actualCorrect}/${this.totalCorrect}"
        words = userWord?.choseWordKeyboard()
        this.imageMoveAtual.setImageResource(this.userWord?.image!!)
        this.setButtons()
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

    fun checkNameEqualsImage(){
        if(this.positionLastWord == this.userWord!!.move.length && !this.alreadyChecked!!){
            if(this.userWord!!.checkWordEquals(this.userWord!!.getPositionImageArray(this.userWord!!.image!!),this.textNameMove.text.toString())){
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
            this.chronometer?.startChronometer()
            this.alertDialog?.message("jogo finalizado",object : MyDialogMessage.BackToMenu {
                override fun popBack() {
                    startActivity(backMainIntent)
                }
            })
        }
    }

    fun finishAllRandom(){
        if(this.userWord!!.allRandom()) {
            this.alertDialog?.message("todas as imagens foram utilizadas", object : MyDialogMessage.BackToMenu {
                override fun popBack() {
                    startActivity(backMainIntent)
                }
            })
        }
    }
}
