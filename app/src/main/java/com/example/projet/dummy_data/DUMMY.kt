package com.example.projet.dummy_data

import com.example.projet.model.Plant

class DUMMY {


    companion object {
        val fakeUrl = "https://www.codelikethewind.org/content/images/size/w2000/2022/05/hello_world.png"

        val plantList = arrayListOf<Plant>(
            Plant(1, fakeUrl, "Plant One"),
            Plant(2, fakeUrl, "Plant Two"),
            Plant(1, fakeUrl, "Plant One"),
            Plant(2, fakeUrl, "Plant Two"),
            Plant(1, fakeUrl, "Plant One"),
            Plant(2, fakeUrl, "Plant Two"),
            Plant(1, fakeUrl, "Plant One"),
            Plant(2, fakeUrl, "Plant Two"),
            Plant(1, fakeUrl, "Plant One"),
            Plant(2, fakeUrl, "Plant Two"),
            Plant(1, fakeUrl, "Plant One"),
            Plant(2, fakeUrl, "Plant Two"),
            // Add more plant instances as needed
        )
    }
}