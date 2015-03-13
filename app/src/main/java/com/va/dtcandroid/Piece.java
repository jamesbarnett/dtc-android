package com.va.dtcandroid;
import java.io.IOException;
import java.util.*;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.*;

/**
 * Created by jbarnett on 3/8/15.
 */
public class Piece implements Parcelable {
    private String _title;
    private String _description;
    private String _image;

    public Piece()
    {

    }

    public static List<Piece> parsePieces(JsonReader reader) throws IOException
    {
        List<Piece> pieces = new ArrayList<Piece>();

        reader.beginArray();
        while (reader.hasNext())
        {
            pieces.add(Piece.parse(reader));
        }

        reader.endArray();

        return pieces;
    }

    public static Piece parse(JsonReader reader) throws IOException
    {
        Piece piece = new Piece();

        reader.beginObject();

        while (reader.hasNext())
        {
            String name = reader.nextName();

            if (name.equals("title"))
            {
                piece._title = reader.nextString();
            }
            else if (name.equals("description"))
            {
                piece._description = reader.nextString();
            }
            else if (name.equals("image"))
            {
                piece._image = reader.nextString();
            }
        }

        reader.endObject();

        return piece;
    }

    public String toString()
    {
        StringBuilder buffer = new StringBuilder();

        buffer.append(String.format("Piece: { title: %s ", this._title));
        buffer.append(String.format("description: %s ", this._description));
        buffer.append(String.format("image: %s }", this._image));

        return buffer.toString();
    }

    public String getTitle() { return _title; }
    public String getDescription() { return _description; }
    public String getImage() { return _image; }

    public int describeContents()
    {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags)
    {
        out.writeString(_title);
        out.writeString(_description);
        out.writeString(_image);
    }

    public static final Parcelable.Creator<Piece> CREATOR = new Parcelable.Creator<Piece>()
    {
        public Piece createFromParcel(Parcel in)
        {
            return new Piece(in);
        }

        public Piece[] newArray(int size)
        {
            return new Piece[size];
        }
    };

    private Piece(Parcel in)
    {
        _title = in.readString();
        _description = in.readString();
        _image = in.readString();
    }
}

