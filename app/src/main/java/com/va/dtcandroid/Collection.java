package com.va.dtcandroid;
import java.util.*;

/**
 * Created by jbarnett on 3/8/15.
 */
public class Collection {
    private String _title;
    private String _description;
    private String _image;
    private List<Piece> _pieces;

    public Collection(String title, String description, String image, List<Piece> pieces) {
        _title = title;
        _description = description;
        _image = image;
        _pieces = pieces;
    }

    public String getTitle() { return _title; }
    public String getDescription() { return _description; }
    public String getImage() { return _image; }
    public List<Piece> getPieces() { return _pieces; }
}
