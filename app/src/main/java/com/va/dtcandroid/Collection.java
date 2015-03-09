package com.va.dtcandroid;
import java.io.IOException;
import java.util.*;
import android.util.*;

/**
 * Created by jbarnett on 3/8/15.
 */
public class Collection {
    private String _title;
    private String _description;
    private String _image;
    private List<Piece> _pieces;

    public Collection()
    {

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
}
