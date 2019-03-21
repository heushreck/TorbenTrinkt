package com.master.drinkmaster3000

data class rule(val Modus:String,
                val Text:String,
                val Anzahl_Spieler:Int,
                val Kategorie:String,
                val Runde1:String,
                val Runde2:String,
                val Spiel:String)

data class stoff(val Frage: String,
                 val Edition: Int)

data class wahrheit(val Frage: String,
                 val Pflicht: Int)

data class nochnie(val Frage: String,
                    val Kategorie: Int)

data class black_story_karte(val Frage:String,
                             val Antwort:String,
                             val Titel:String,
                             val Edition:Int)

data class privacy_frage(val Frage: String)

data class trinkspiel(val Name:String,
                      val Anleitung:String,
                      val min_Spieler:Int,
                      val max_Spieler:Int,
                      val Drunk:Int)


data class r2(val Rule: rule,
              val Spieler1:String,
              val Spieler2:String,
              val Spieler3:String,
              val Spieler4:String)
