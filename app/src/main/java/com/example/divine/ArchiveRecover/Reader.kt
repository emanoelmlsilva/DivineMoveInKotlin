package com.example.divine.ArchiveRecover

import android.content.Context
import android.content.res.AssetManager
import android.util.Log
import com.example.divine.Model.DivineImageWord
import java.io.IOException
import java.io.InputStream

class Reader{

    lateinit var assetManagerList: AssetManager
    lateinit var inputStream: InputStream
    var arrayChose = mutableListOf<DivineImageWord>()

    fun recoverDivine(myContext: Context):MutableList<DivineImageWord>{ // usar o try
        this.assetManagerList = myContext.assets
        val recoverDivineList: String
        var divineimage: DivineImageWord
        try {
            this.inputStream = this.assetManagerList.open(directory() + fileName())
            val size: Int = this.inputStream.available()
            val buffer = ByteArray(size)
            this.inputStream.read(buffer)
            this.inputStream.close()
            recoverDivineList = String(buffer)
            val arrayDivine: List<String> = recoverDivineList.split("\n")
            for (stringDivine in arrayDivine) {
                var image: String = stringDivine.split(" - ")[0]
                var move: String = stringDivine.split(" - ")[1]
                var resId: Int = myContext.resources.getIdentifier(image, "drawable", myContext.packageName)
                if (resId == 0) {
                    println("erro drawable não exites");
                } else if (!searchForDivineIquals(move, resId)) {
                    divineimage = DivineImageWord(resId, move)
                    arrayChose.add(divineimage)
                }
            }
        }catch(erro: IOException){
            Log.i("exception","não foi possivel emcontar o diretorio/archive")
        }
        return this.arrayChose
    }

    //    procura se existe dois filmes iguais
    fun searchForDivineIquals(name: String,imageAtual: Int):Boolean{
        for(divineImageWord in this.arrayChose){
            if(divineImageWord.word.equals(name) || divineImageWord.image == imageAtual){
                return true
            }
        }
        return false
    }

    //    retornar o diretorio
    fun directory():String{
        return "archive/"
    }

    //    retorna o nome do arquivo
    fun fileName(): String{
        return "arquivosImageString"
    }
}