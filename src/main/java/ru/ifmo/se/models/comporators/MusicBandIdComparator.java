package ru.ifmo.se.models.comporators;

import ru.ifmo.se.models.MusicBand;

import java.util.Comparator;

public class MusicBandIdComparator implements Comparator<MusicBand> {
    public int compare(MusicBand o1, MusicBand o2) {
        return o1.getId() - o2.getId();
    }
}
