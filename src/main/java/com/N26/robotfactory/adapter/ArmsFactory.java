package com.N26.robotfactory.adapter;

import com.N26.robotfactory.adapter.Purple;
import com.N26.robotfactory.adapter.Red;
import com.N26.robotfactory.gateway.AbstractFactory;
import com.N26.robotfactory.gateway.ColorExample;

public class FaceFactory implements AbstractFactory<ColorExample> {

    public static final String HUMANOID = "HUMANOID";
    public static final String LCD = "LCD";
    public static final String STEAMPUNK = "STEAMPUNK";

    @Override
    public ColorExample create(String faceType) {

        switch (faceType) {
            case HUMANOID:
                return new Humanoid();
            case LCD:
                return new LCDFace();
            case STEAMPUNK:
                return new SteamPunk();

        }

        if ("Purple".equalsIgnoreCase(faceType)) {
            return new Purple();
        } else if ("Red".equalsIgnoreCase(faceType)) {
            return new Red();
        }

        return null;
    }
}
