package com.va.dtcandroid;
import java.io.IOException;
import java.util.*;
import android.util.*;

/**
 * Created by jbarnett on 3/8/15.
 */
public class Catalog {
    private String _designer;
    private List<Collection> _collections;

    public static Catalog parse(JsonReader reader) throws IOException
    {
        Catalog catalog = new Catalog();

        reader.beginObject();

        while (reader.hasNext())
        {
            String name = reader.nextName();
            if (name.equals("designer"))
            {
                catalog._designer = reader.nextString();
            }
            else if (name.equals("collections"))
            {
                catalog._collections = Collection.parseCollections(reader);
            }
        }
        return catalog;
    }

    public Catalog()
    {

    }
    public Catalog(String designer, List<Collection> collections)
    {
        _designer = designer;
        _collections = collections;
    }

    public String getDesigner() { return _designer; }
    public List<Collection> getCollections() { return _collections; }

    public String toString()
    {
        StringBuilder buffer = new StringBuilder();
        buffer.append(String.format("Catalog: { designer: %s ", _designer));

        StringBuilder collectionBuffer = new StringBuilder();

        for (Collection collection : _collections)
        {
            collectionBuffer.append(String.format(" %s ", collection.toString()));
        }

        buffer.append(String.format("pieces: [ %s ]", collectionBuffer.toString()));

        return buffer.toString();
    }
}
