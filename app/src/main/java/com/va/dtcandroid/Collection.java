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
    private String mTitle;
    private String mDescription;
    private String mImage;
    private List<Piece> mPieces;

    public Collection()
    {
        mPieces = new ArrayList<>();
    }

    public Collection(String title, String description, String image, List<Piece> pieces) {
        mTitle = title;
        mDescription = description;
        mImage = image;
        mPieces = pieces;
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
                collection.mTitle = reader.nextString();
            }
            else if (name.equals("description"))
            {
                collection.mDescription = reader.nextString();
            }
            else if (name.equals("image"))
            {
                collection.mImage = reader.nextString();
            }
            else if (name.equals("pieces"))
            {
                collection.mPieces = Piece.parsePieces(reader);
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

    public String getTitle() { return mTitle; }
    public String getDescription() { return mDescription; }
    public String getImage() { return mImage; }
    public List<Piece> getPieces() { return mPieces; }

    public String toString()
    {
        StringBuilder buffer = new StringBuilder();

        buffer.append(String.format("Collection: { title: %s ", this.mTitle));
        buffer.append(String.format("description: %s ", this.mDescription));
        buffer.append(String.format("image: %s", this.mImage));

        StringBuilder pieceBuffer = new StringBuilder();
        for (Piece piece : mPieces)
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
        dest.writeString(mTitle);
        dest.writeString(mDescription);
        dest.writeString(mImage);
        dest.writeTypedList(mPieces);
    }

    private Collection(Parcel in)
    {
        this();
        mTitle = in.readString();
        mDescription = in.readString();
        mImage = in.readString();
        in.readTypedList(mPieces, Piece.CREATOR);
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
