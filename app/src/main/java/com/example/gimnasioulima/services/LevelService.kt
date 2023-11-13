package com.example.gimnasioulima.services

import com.example.gimnasioulima.models.Level

class LevelService {
    var levelList: ArrayList<Level> = ArrayList<Level>()

    constructor(){
        levelList.add(Level(id = 1, name = "SUPERIOR HOMBRE - PRINCIPIANTE"))
        levelList.add(Level(id = 2, name = "SUPERIOR HOMBRE - INTERMEDIO"))
    }
}