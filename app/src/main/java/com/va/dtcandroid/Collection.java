package com.va.dtcandroid;
import java.io.IOException;
import java.util.*;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.*;

/**
 * Created by jbarnett on 3/8/15.
 */
public class Collection implements Parcelable {
    private String _title;
    private String _description;
    private String _image;
    private List<Piece> _pieces;

    public Collection()
    {
        _pieces = new ArrayList<>();
    }

    public Collection(String title, String description, String image, List<Piece> pieces) {
        _title = title;
        _description = description;
        _image = image;
        _pieces = pieces;
    }

    public static Collection parse(JsonReader reader) throws IOException
    {
        Collection collection = new Collection();

        reader.beginObject();

        while (reader.hasNext())
        {
            String name = reader.nextName();

            if (name.equals("title"))
            {
                collection._title = reader.nextString();
            }
            else if (name.equals("description"))
            {
                collection._description = reader.nextString();
            }
            else if (name.equals("image"))
            {
                collection._image = reader.nextString();
            }
            else if (name.equals("pieces"))
            {
                collection._pieces = Piece.parsePieces(reader);
            }
        }

        reader.endObject();

        return collection;
    }

    public static List<Collection> parseCollections(JsonReader reader) throws IOException
    {
        List<Collection> collections = new ArrayList<Collection>();

        reader.beginArray();

        while (reader.hasNext())
        {
            collections.add(Collection.parse(reader));
        }

        reader.endArray();

        return collections;
    }

    public String getTitle() { return _title; }
    public String getDescription() { return _description; }
    public String getImage() { return _image; }
    public List<Piece> getPieces() { return _pieces; }

    public String toString()
    {
        StringBuilder buffer = new StringBuilder();

        buffer.append(String.format("Collection: { title: %s ", this._title));
        buffer.append(String.format("description: %s ", this._description));
        buffer.append(String.format("image: %s", this._image));

        StringBuilder pieceBuffer = new StringBuilder();
        for (Piece piece : _pieces)
        {
            pieceBuffer.append(String.format(" %s ", piece.toString()));
        }

        buffer.append(String.format("pieces: [ %s ]", pieceBuffer.toString()));

        return buffer.toString();
    }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(_title);
        dest.writeString(_description);
        dest.writeString(_image);
        dest.writeTypedList(_pieces);
    }

    private Collection(Parcel in)
    {
        this();
        _title = in.readString();
        _description = in.readString();
        _image = in.readString();
        in.readTypedList(_pieces, Piece.CREATOR);
    }

    public static final Parcelable.Creator<Collection> CREATOR
        = new Parcelable.Creator<Collection>()
    {
        public Collection createFromParcel(Parcel in)
        {
            return new Collection(in);
        }

        public Collection[] newArray(int size)
        {
            return new Collection[size];
        }
    };
}
