package ru.ifmo.se.collectionManager;

import com.thoughtworks.xstream.XStream;
import ru.ifmo.se.exceptions.WrongInputException;
import ru.ifmo.se.models.Color;
import ru.ifmo.se.models.Coordinates;
import ru.ifmo.se.models.MusicBand;
import ru.ifmo.se.models.MusicGenre;
import ru.ifmo.se.models.Person;
import ru.ifmo.se.models.Location;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Stream;



public class CollectionManager {
    private Stack<MusicBand> collection;

    private LocalDateTime initializationDate;

    public CollectionManager() {
        this.collection = new Stack<>();
        this.initializationDate = LocalDateTime.now();
    }

    public boolean add(MusicBand element) {
        element.setId(this.generateNewId());
        return this.collection.add(element);
    }

    public boolean updateById(int id, MusicBand element) {
        element.setId(id);

        MusicBand temp = this.collection.stream().filter(item -> item.getId() == id).findFirst().orElse(null);
        if (temp != null) {
            this.collection.set(this.collection.indexOf(temp), element);
            return true;
        }
        return false;
    }

    public void removeElement(MusicBand element) {
        this.collection.remove(element);
    }

    public void removeById(int id) {
        this.collection.stream().filter(item -> item.getId() == id).findFirst()
                .ifPresent(this::removeElement);
    }

    public void clear() {
        this.collection.clear();
    }

    public void removeAtIndex(int index) {
        this.collection.remove(index);
    }

    public void removeLast() {
        this.collection.removeLast();
    }

    public Stream<MusicBand> getItems() {
        return this.collection.stream();
    }

    private int generateNewId() {
        return this.collection.stream().mapToInt(MusicBand::getId).max().orElse(0) + 1;
    }

    public int size() {
        return this.collection.size();
    }

    public String info() {
        return "Info{ type: " + this.collection.getClass().getName() + ", date of init: " +
                initializationDate + ", count of element: " + this.collection.size() + "}";
    }

    public static class Wrapper {
        List<MusicBand> collection;
        LocalDateTime initializationDate;
        Wrapper(Stack<MusicBand> collection, LocalDateTime initializationDate) {
            this.collection = new ArrayList<>(collection);
            this.initializationDate = initializationDate;
            System.out.println(this.initializationDate + " " + this.collection);
        }

        public static XStream getXStream() {
            XStream xstream = new XStream();

            xstream.allowTypesByWildcard(new String[] {
                    "ru.ifmo.se.collectionManager.CollectionManager$Wrapper",
                    "ru.ifmo.se.models.**",
                    "java.util.*",
                    "java.time.**"
            });


            xstream.alias("DB", CollectionManager.class);
            xstream.alias("collection", Stack.class);
            xstream.alias("location", Location.class);
            xstream.alias("music_band", MusicBand.class);
            xstream.alias("person", Person.class);
            xstream.alias("color", Color.class);
            xstream.alias("music_genre", MusicGenre.class);
            xstream.alias("coordinates", Coordinates.class);
            return xstream;
        }
    }

    public void serialize(Writer to) {
        Wrapper wrapper = new Wrapper(this.collection, this.initializationDate);
        XStream xstream = Wrapper.getXStream();
        xstream.toXML(wrapper, to);
    }

    public boolean isValid() {
        boolean validCollection = this.getItems().anyMatch(MusicBand::isValid);

        return this.collection != null && this.initializationDate != null && validCollection;
    }

    public static CollectionManager deserialize(Reader from) throws IOException, WrongInputException {
        XStream xstream = Wrapper.getXStream();
        Wrapper wrapper = (Wrapper) xstream.fromXML(from);
        CollectionManager collectionManager = new CollectionManager();
        collectionManager.collection = new Stack<>();
        collectionManager.collection.addAll(wrapper.collection);
        collectionManager.initializationDate = wrapper.initializationDate;

        if (collectionManager.isValid()) {
            return collectionManager;
        }

        throw new WrongInputException("Некорректные данные в файле");
    }

}
