package com.example.divine.ArchiveRecover

import android.content.Context
import android.content.res.AssetManager
import android.util.Log
import com.example.divine.Model.DivineImageWord
import java.io.IOException
import java.io.InputStream

class Reader(){

    companion object{
        var fileName: String = "arquivosImageString"
    }

    fun setFile(file: String){
        fileName = file
    }

    fun recoverDivine(myContext: Context):MutableList<DivineImageWord>{
        val arrayChose = mutableListOf<DivineImageWord>()
        val assetManagerList: AssetManager = myContext.assets
        val recoverDivineList: String
        var divineimage: DivineImageWord
        try {
            val inputStream: InputStream = assetManagerList.open(directory() + fileNameArquive())
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            recoverDivineList = String(buffer)
            val arrayDivine: List<String> = recoverDivineList.split("\n")
            for (stringDivine in arrayDivine) {
                var image: String = stringDivine.split(" - ")[0]
                var move: String = stringDivine.split(" - ")[1]
                var resId: Int = myContext.resources.getIdentifier(image, "drawable", myContext.packageName)
                if (resId == 0) {
                    println("erro drawable não exites");
                } else if (!searchForDivineIquals(move, resId,arrayChose)) {
                    divineimage = DivineImageWord(resId, move)
                    arrayChose.add(divineimage)
                }
            }
        }catch(erro: IOException){
            Log.i("exception","não foi possivel emcontar o diretorio/archive")
        }
        return arrayChose
    }

    //    procura se existe dois filmes iguais
    private fun searchForDivineIquals(name: String,imageAtual: Int,myArrayChose: List<DivineImageWord>):Boolean{
        for(divineImageWord in myArrayChose){
            if(divineImageWord.word.equals(name) || divineImageWord.image == imageAtual){
                return true
            }
        }
        return false
    }

    //    retornar o diretorio
    private fun directory():String{
        return "archive/"
    }

    //    retorna o nome do arquivo
    fun fileNameArquive(): String{
        return fileName
    }
}