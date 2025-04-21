package ru.ifmo.se.models.comporators;

import ru.ifmo.se.models.MusicBand;

import java.util.Comparator;

public class MusicBandCreationDateComparator implements Comparator<MusicBand> {
    public int compare(MusicBand o1, MusicBand o2) {
        return o1.getCreationDate().compareTo(o2.getCreationDate());
    }
}
