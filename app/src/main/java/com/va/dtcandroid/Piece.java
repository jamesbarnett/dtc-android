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
    private String mTitle;
    private String mDescription;
    private String mImage;

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
                piece.mTitle = reader.nextString();
            }
            else if (name.equals("description"))
            {
                piece.mDescription = reader.nextString();
            }
            else if (name.equals("image"))
            {
                piece.mImage = reader.nextString();
            }
        }

        reader.endObject();

        return piece;
    }

    public String toString()
    {
        StringBuilder buffer = new StringBuilder();

        buffer.append(String.format("Piece: { title: %s ", this.mTitle));
        buffer.append(String.format("description: %s ", this.mDescription));
        buffer.append(String.format("image: %s }", this.mImage));

        return buffer.toString();
    }

    public String getTitle() { return mTitle; }
    public String getDescription() { return mDescription; }
    public String getImage() { return mImage; }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags)
    {
        out.writeString(mTitle);
        out.writeString(mDescription);
        out.writeString(mImage);
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
        mTitle = in.readString();
        mDescription = in.readString();
        mImage = in.readString();
    }
}

