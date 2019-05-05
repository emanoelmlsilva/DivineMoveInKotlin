package com.example.divine.Model

import android.content.Context
import android.util.Log
import com.example.divine.ArchiveRecover.Reader
import kotlin.random.Random

class ChoseWord(var myContext: Context)  {

    val listWordChar:CharArray
    val arrayChose: MutableList<DivineImageWord>
    var image: Int? = null
    var random: Int = 0
    val lengthKeyboard: Int
    var move:String = ""
    val listRandom = ArrayList<Int>()
    var readerAssets = Reader()

    init{
        this.arrayChose = readerAssets.recoverDivine(myContext)
        listWordChar = charArrayOf('a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','w','x','y','z')
        lengthKeyboard = 24
    }

//  forma uma string com - do mesmo tamanho da palavra
    fun mountLines():String{
        var lines: String = ""
        for(i in 0..move.length-1){
            lines += "-"
        }
        return lines
    }

//    verificar palavras iguais
    fun checkWordEquals(indexNameCheck:Int,wordChecked:String):Boolean{
        val nameMove: String = this.arrayChose.get(indexNameCheck).word.toLowerCase()
        return nameMove.toLowerCase().equals(wordChecked.toLowerCase())
    }


//    verificar embaralha palavra do teclado
    fun choseWordKeyboard():CharArray {
        var cont: Int = 0
        val arrayWordsort = CharArray(lengthKeyboard)
        for(i in 0..move.length-1){
            if(move[i] != ' '){
                arrayWordsort[i] = move.get(i)
            }
        }

        var numberRandom: Int
        for(i in move.length..lengthKeyboard-1){
            numberRandom = Random.nextInt(lengthKeyboard)
            arrayWordsort[i] = listWordChar[numberRandom]
        }

        return shufflesWord(arrayWordsort)
    }

//    função para embaralhar as letras do teclado
    fun shufflesWord(words: CharArray): CharArray{
        var aux: Char
        for(i in 0..words.size-1){
            random = Random.nextInt(lengthKeyboard)
            aux = words[random]
            words[random] = words[i]
            words[i] = aux
        }
        return words
    }

//  escolher image e filme
    fun choseWordImage(){
        random = Random.nextInt(arrayChose.size)
        if(listLakerandom(random)){
            choseWordImage()
        }else{
            image = arrayChose.get(random).image
            move = arrayChose.get(random).word
        }
    }

//    verificar se id ja foi escolhido no random
    fun listLakerandom(laktRandom: Int): Boolean{

        if(!allRandom()){
            for(randomLake in this!!.listRandom!!){
                if(randomLake == laktRandom){
                    return true
                }
            }
            if(this.listRandom!!.size < this.arrayChose.size){
                Log.i("botao","cadastrando random"+this.listRandom.size)
                this.listRandom!!.add(random)
            }
        }

    return false
    }

//    compara os tamanho das listas dos random gerados para condiçao de parar se forem iguais
    fun allRandom(): Boolean{
        if(this.listRandom!!.size == this.arrayChose.size ){
            return true
        }
        return false

    }

//    pegar posição da imagem na lista
    fun getPositionImageArray(idImage: Int): Int{
        for(i in 0..this.arrayChose.size-1){
            if(idImage == this.arrayChose.get(i).image){
                return i;
            }
        }
        return -1
    }

//    retornar o total de DivineImageWord cadastrados
    fun size(): Int{
        return this.arrayChose.size
    }

}