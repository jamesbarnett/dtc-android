package com.va.dtcandroid;

/**
 * Created by jbarnett on 3/8/15.
 */
public class Piece {
    private String _title;
    private String _description;
    private String _image;

    public Piece (String title, String description, String image)
    {
        _title = title;
        _description = description;
        _image = image;
    }

    public String getTitle() { return _title; }
    public String getDescription() { return _description; }
    public String getImage() { return _image; }
}
