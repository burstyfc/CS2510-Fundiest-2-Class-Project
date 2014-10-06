/*
assignment 8
pair 2
Chao Fang
bursty
Amanda Fode
afode11
 */
import java.util.Iterator;

import javalib.tunes.Note;
import javalib.tunes.SoundConstants;

public class Song implements SoundConstants, Iterable<Note> {

    Note a = new Note("B3n1");
    Note c2 = new Note("C5n2");
    Note d2 = new Note("E4n2");
    Note e2 = new Note("A2n2");
    Note e = new Note("D3n1");
    Note f2 = new Note("C5n2");
    Note f = new Note("E4n1");
    Note g2 = new Note("E3n2");
    Note g = new Note("B5n1");
    Note cup2 = new Note("A4n2");
    Note cup = new Note("E2n1");
    Note dup = new Note("B3n1");
    Note eup = new Note("E1n1");
    Note silent = new Note(0, 0);


    Note[] backGroundMusic = new Note[] {

        this.g, this.g, this.g, this.g, this.a,  this.a, this.a, this.a, 
        this.e2, this.e2,  this.e2, this.e2, this.silent, this.silent, 
        this.silent, this.silent, this.c2, this.c2, this.c2, this.c2, 
        this.silent, this.silent, this.silent, this.silent,     
        // on the hill side
        this.g, this.g, this.g, this.g, this.a, this.a, this.a, this.a, 
        this.e2, this.e2, this.e2, this.e2, this.silent, this.silent, 
        this.silent, this.silent, this.c2, this.c2, this.c2, this.c2, 
        this.silent, this.silent, this.silent, this.silent,    

        this.g, this.g, this.g, this.g, this.g, this.g, this.g, 
        this.g, 
        this.cup, this.cup, this.cup, this.cup, this.silent, 
        this.silent, 
        this.silent, this.silent, this.cup, this.cup, this.cup, 
        this.cup, 
        this.silent, this.silent, this.silent, this.silent,

        this.cup, this.cup, this.cup, this.cup, this.dup, this.dup, 
        this.dup, 
        this.dup, this.eup, this.eup, this.eup, this.eup, this.dup,
        this.dup, 
        this.dup, this.dup, this.cup2, this.cup2, this.cup2, 
        this.cup2, 
        this.silent, this.silent, this.silent, this.silent,

        this.cup, this.cup, this.cup, this.cup, this.a, this.a, 
        this.a, 
        this.a, 
        this.g2, this.g2, this.g2, this.g2, this.silent, this.silent, 
        this.silent, 
        this.silent, this.g2, this.g2, this.g2, this.g2, this.silent, 
        this.silent, 
        this.silent, this.silent,

        this.g, this.g, this.g, this.g, this.a, this.a, this.a, this.a, 
        this.f2, 
        this.f2, this.f2, this.f2, this.silent, this.silent, 
        this.silent, 
        this.silent, this.f2, this.f2, this.f2, this.f2, this.silent, 
        this.silent, 
        this.silent, this.silent,

        this.f, this.f, this.f, this.f, this.g, this.g, this.g, this.g, 
        this.e2, 
        this.e2, this.e2, this.e2, this.silent, this.silent, 
        this.silent, 
        this.silent, this.e2, this.e2, this.e2, this.e2, this.silent, 
        this.silent, this.silent, this.silent, 

        this.e, this.e, this.e, this.e, this.f, this.f, this.f, this.f, 
        this.d2, this.d2, this.d2, this.d2, this.silent, this.silent, 
        this.silent, this.silent, this.silent, this.silent, this.silent, 
        this.silent, this.silent, this.silent, this.silent, this.silent   
    };

    public Iterator<Note> iterator() {
        return new SongIterator(this.backGroundMusic);
    }
}
//a circular iterator for the given Array of notes
//must play a silent note when pausing
class SongIterator implements Iterator<Note> {

    Note[] song;
    int i;
    int len;

    SongIterator(Note[] song) {
        this.song = song;
        this.len = song.length;
        this.i = this.len - 1;
    }


    // circular iterator - never runs out of notes ot play
    public boolean hasNext() {
        return true;
    }

    // produce the next note to play
    public Note next() {
        this.i = (i + 1) % 192;
        return this.song[i];
    }

    // do nothing on remove
    public void remove() { }
}



