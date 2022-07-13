package com.example.formulaone.models.ResultsByYear;

import com.example.formulaone.models.Race;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RBYRaceTable {
    @SerializedName("season")
    private String season = null;

    @SerializedName("round")
    private String round = null;

    @SerializedName("Races")
    private List<Race> races = null;
}
