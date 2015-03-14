package com.va.dtcandroid;
import java.io.IOException;
import java.util.*;
import android.util.*;

/**
 * Created by jbarnett on 3/8/15.
 */
public class Catalog {
    private String mDesigner;
    private List<Collection> mCollections;

    public static Catalog parse(JsonReader reader) throws IOException
    {
        Catalog catalog = new Catalog();

        reader.beginObject();

        while (reader.hasNext())
        {
            String name = reader.nextName();
            if (name.equals("designer"))
            {
                catalog.mDesigner = reader.nextString();
            }
            else if (name.equals("collections"))
            {
                catalog.mCollections = Collection.parseCollections(reader);
            }
        }
        return catalog;
    }

    public Catalog()
    {

    }
    public Catalog(String designer, List<Collection> collections)
    {
        mDesigner = designer;
        mCollections = collections;
    }

    public String getDesigner() { return mDesigner; }
    public List<Collection> getCollections() { return mCollections; }

    public String toString()
    {
        StringBuilder buffer = new StringBuilder();
        buffer.append(String.format("Catalog: { designer: %s ", mDesigner));

        StringBuilder collectionBuffer = new StringBuilder();

        for (Collection collection : mCollections)
        {
            collectionBuffer.append(String.format(" %s ", collection.toString()));
        }

        buffer.append(String.format("pieces: [ %s ]", collectionBuffer.toString()));

        return buffer.toString();
    }
}
