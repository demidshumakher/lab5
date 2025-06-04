package ru.ifmo.se.collectionManager;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import ru.ifmo.se.exceptions.WrongInputException;
import ru.ifmo.se.models.Color;
import ru.ifmo.se.models.Coordinates;
import ru.ifmo.se.models.MusicBand;
import ru.ifmo.se.models.MusicGenre;
import ru.ifmo.se.models.Person;
import ru.ifmo.se.models.Location;
import ru.ifmo.se.models.comporators.MusicBandIdComparator;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Stream;

/**
 * The CollectionManager class is responsible for managing a collection of MusicBand objects.
 * It provides methods for adding, updating, removing, and retrieving elements from the collection.
 * The class also handles serialization and deserialization of the collection to/from XML format.
 */
public class CollectionManager {
    /** The stack that holds the music band collection */
    private Stack<MusicBand> collection;

    /** The date and time when this collection manager was initialized */
    private LocalDateTime initializationDate;

    /**
     * Constructs a new empty CollectionManager with the current date and time as initialization date.
     */
    public CollectionManager() {
        this.collection = new Stack<>();
        this.initializationDate = LocalDateTime.now();
    }

    /**
     * Adds a music band to the collection with a newly generated ID.
     * 
     * @param element the music band to add
     * @return true if the element was added successfully
     */
    public boolean add(MusicBand element) {
        element.setId(this.generateNewId());
        return this.collection.add(element);
    }

    /**
     * Updates a music band in the collection by its ID.
     * 
     * @param id the ID of the music band to update
     * @param element the new music band data
     * @return true if the element was updated successfully, false if no element with the given ID was found
     */
    public boolean updateById(int id, MusicBand element) {
        element.setId(id);

        MusicBand temp = this.collection.stream().filter(item -> item.getId() == id).findFirst().orElse(null);
        if (temp != null) {
            this.collection.set(this.collection.indexOf(temp), element);
            return true;
        }
        return false;
    }

    public boolean idExists(int id) {
        MusicBand temp = this.collection.stream().filter(item -> item.getId() == id).findFirst().orElse(null);
        return temp != null;
    }



    /**
     * Removes a specific music band from the collection.
     * 
     * @param element the music band to remove
     */
    public void removeElement(MusicBand element) {
        this.collection.remove(element);
    }

    /**
     * Removes a music band from the collection by its ID.
     * 
     * @param id the ID of the music band to remove
     */
    public void removeById(int id) {
        this.collection.stream().filter(item -> item.getId() == id).findFirst()
                .ifPresentOrElse(this::removeElement, () -> {throw new IndexOutOfBoundsException("неизвестный id");});
    }

    /**
     * Clears all elements from the collection.
     */
    public void clear() {
        this.collection.clear();
    }

    /**
     * Removes a music band at the specified index in the collection.
     * 
     * @param index the index of the music band to remove
     */
    public void removeAtIndex(int index) {
        if (index < 0 || index >= this.collection.size()) {
            throw new IndexOutOfBoundsException("элемент с таким индексом не существует");
        }
        this.collection.remove(index);
    }

    /**
     * Removes the last music band from the collection.
     */
    public void removeLast() {
        if (this.collection.isEmpty()) {
            throw new IndexOutOfBoundsException("коллекция пуста");
        }
        this.removeAtIndex(this.collection.size()-1);
    }

    /**
     * Returns a stream of all music bands in the collection, sorted by ID.
     * 
     * @return a sorted stream of music bands
     */
    public Stream<MusicBand> getItems() {
        return this.collection.stream().sorted(new MusicBandIdComparator());
    }

    /**
     * Generates a new unique ID for a music band.
     * 
     * @return a new ID that is one greater than the maximum existing ID, or 1 if the collection is empty
     */
    private int generateNewId() {
        return this.collection.stream().mapToInt(MusicBand::getId).max().orElse(0) + 1;
    }

    /**
     * Returns the number of music bands in the collection.
     * 
     * @return the size of the collection
     */
    public int size() {
        return this.collection.size();
    }

    /**
     * Returns information about the collection, including its type, initialization date, and size.
     * 
     * @return a string containing information about the collection
     */
    public String info() {
        return "Info{ type: " + this.collection.getClass().getName() + ", date of init: " +
                initializationDate + ", count of element: " + this.collection.size() + "}";
    }

    /**
     * A nested class used for XML serialization and deserialization of the collection.
     * It wraps the collection and initialization date for proper XML formatting.
     */
    public static class Wrapper {
        /** The list of music bands in the collection */
        List<MusicBand> collection;
        /** The initialization date of the collection */
        LocalDateTime initializationDate;

        /**
         * Constructs a new Wrapper with the given collection and initialization date.
         * 
         * @param collection the stack of music bands to wrap
         * @param initializationDate the initialization date of the collection
         */
        public Wrapper(Stack<MusicBand> collection, LocalDateTime initializationDate) {
            this.collection = new ArrayList<>(collection);
            this.initializationDate = initializationDate;
        }

        public Wrapper() {
            this.collection = new ArrayList<>();
            this.initializationDate = LocalDateTime.now();
        }

        /**
         * Configures and returns an XStream instance for XML serialization.
         * 
         * @return the configured XStream instance
         */
        public static XStream getXStream() {
            XStream xstream = new XStream();

            xstream.allowTypesByWildcard(new String[] {
                    "ru.ifmo.se.collectionManager.CollectionManager$Wrapper",
                    "ru.ifmo.se.models.**",
                    "java.util.*",
                    "java.time.**"
            });

            xstream.alias("DB", Wrapper.class);
            xstream.alias("collection", List.class);
            xstream.alias("location", Location.class);
            xstream.alias("music_band", MusicBand.class);
            xstream.alias("person", Person.class);
            xstream.alias("color", Color.class);
            xstream.alias("music_genre", MusicGenre.class);
            xstream.alias("coordinates", Coordinates.class);

            return xstream;
        }
    }

    /**
     * Serializes the collection to XML format and writes it to the specified Writer.
     * 
     * @param to the Writer to write the XML to
     */
    public void serialize(Writer to) {
        Wrapper wrapper = new Wrapper(this.collection, this.initializationDate);
        XStream xstream = Wrapper.getXStream();
        xstream.toXML(wrapper, to);
    }

    /**
     * Checks if the collection is valid.
     * A valid collection must not be null, must have a non-null initialization date,
     * and must contain at least one valid music band.
     * 
     * @return true if the collection is valid, false otherwise
     */
    public boolean isValid() {
        boolean validCollection = this.getItems().allMatch(MusicBand::isValid);
        return this.collection != null && this.initializationDate != null && validCollection;
    }

    /**
     * Deserializes a collection from XML format.
     * 
     * @param from the Reader to read the XML from
     * @return a new CollectionManager containing the deserialized collection
     * @throws IOException if an I/O error occurs
     * @throws WrongInputException if the XML contains invalid data
     */
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
