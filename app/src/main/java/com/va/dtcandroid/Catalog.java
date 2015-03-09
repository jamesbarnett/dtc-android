package com.va.dtcandroid;
import java.util.*;

/**
 * Created by jbarnett on 3/8/15.
 */
public class Catalog {
    private String _designer;
    private List<Collection> _collections;

    public static Catalog parse()
    {
        return new Catalog("Danh Ta", new ArrayList<Collection>());
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
        buffer.append(String.format("Catalog: { designer: %s }", _designer));
        return buffer.toString();
    }
}
