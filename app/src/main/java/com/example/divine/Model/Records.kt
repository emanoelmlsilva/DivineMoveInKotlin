package com.example.divine.Model

class Records (var id: Int,var nick: String,var score: Int,var totalHit: Int,var totalImage: Int) {

    constructor(scoreSec: Int,totalHitSec: Int,totalImageSec: Int) : this(0,"Chico",scoreSec,totalHitSec,totalImageSec){

    }

}