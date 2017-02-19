package msu.ece.xiaozeng.mpf3.entity;

import java.io.Serializable;
import java.util.ArrayList;

import msu.ece.xiaozeng.mpf3.R;

/**
 * Created by xiaozeng on 2/17/17.
 */

public class Pill implements Serializable {

    public String name;
    public String imprint;
    public String color;
    public String description;
    public int frontPicID;
    public int backPicID;


    public Pill(String name, String imprint, String color, String description, int frontPic, int backPic) {
        this.name = name;
        this.imprint = imprint;
        this.color = color;
        this.description = description;
        this.frontPicID = frontPic;
        this.backPicID = backPic;
    }

    public static ArrayList<Pill> initializeData(){
        ArrayList<Pill> pills = new ArrayList<>();
        pills.add(new Pill("3e281f50", "TV 51", "gray", "pill description",
                R.drawable.sf_1_3e281f50, R.drawable.sb_1_3e281f50));
        pills.add(new Pill("a022d026", "93 51", "gray", "pill description",
                R.drawable.sf_2_a022d026,R.drawable.sb_2_a022d026));
        pills.add(new Pill("00280060", "TV 58", "gray", "pill description",
                R.drawable.sf_3_00280060,R.drawable.sb_3_00280060));
        pills.add(new Pill("5829ac4d", "TV 58", "gray", "pill description",
                R.drawable.sf_4_5829ac4d,R.drawable.sb_4_5829ac4d));
        pills.add(new Pill("15298a8c", "93 132", "gray", "pill description",
                R.drawable.sf_5_15298a8c,R.drawable.sb_5_15298a8c));
        pills.add(new Pill("39281ce0", "TV 135", "gray", "pill description",
                R.drawable.sf_6_39281ce0,R.drawable.sb_6_39281ce0));
        pills.add(new Pill("d32169fb", "93 135", "gray", "pill description",
                R.drawable.sf_7_d32169fb,R.drawable.sb_7_d32169fb));

        pills.add(new Pill("6f29b7bd", "93 154", "gray", "pill description",
                R.drawable.sf_8_6f29b7bd,R.drawable.sb_8_6f29b7bd));

        pills.add(new Pill("992b4cea", "93 536", "gray", "pill description",
                R.drawable.sf_9_992b4cea,R.drawable.sb_9_992b4cea));

        pills.add(new Pill("942b4a7a", "93 537", "gray", "pill description",
                R.drawable.sf_10_942b4a7a,R.drawable.sb_10_942b4a7a));

        pills.add(new Pill("822b417a", "G70", "gray", "pill description",
                R.drawable.sf_11_822b417a,R.drawable.sb_11_822b417a));

        pills.add(new Pill("c029e06f", "93 48", "gray", "pill description",
                R.drawable.sf_12_c029e06f,R.drawable.sb_12_c029e06f));

        pills.add(new Pill("ce29e75f", "93 49", "gray", "pill description",
                R.drawable.sf_13_ce29e75f,R.drawable.sb_13_ce29e75f));

        pills.add(new Pill("cc2de65f", "93 1172", "gray", "pill description",
                R.drawable.sf_14_cc2de65f,R.drawable.sb_14_cc2de65f));

        pills.add(new Pill("b52b5afa", "93 1174", "gray", "pill description",
                R.drawable.sf_15_b52b5afa,R.drawable.sb_15_b52b5afa));

        return pills;
    }

    @Override
    public String toString() {
        return "Pill{" +
                "name='" + name + '\'' +
                ", imprint='" + imprint + '\'' +
                ", color='" + color + '\'' +
                ", description='" + description + '\'' +
                ", frontPicID=" + frontPicID +
                ", backPic=" + backPicID +
                '}';
    }
}
