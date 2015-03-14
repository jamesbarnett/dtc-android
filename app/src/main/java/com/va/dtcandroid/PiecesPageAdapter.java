package com.va.dtcandroid;

import java.util.*;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

/**
 * Created by jbarnett on 3/13/15.
 */
public class PiecesPageAdapter extends FragmentStatePagerAdapter {
    private Collection mCollection;

    public PiecesPageAdapter(FragmentManager fm, Collection collection) {
        super(fm);
        mCollection = collection;
    }

    @Override
    public int getCount() {
        return mCollection.getPieces().size();
    }

    @Override
    public Fragment getItem(int position) {
        return ArrayListFragment.newInstance(mCollection, position);
    }

    public static class ArrayListFragment extends ListFragment {
        private int mIndex;
        private Collection mCollection;

        public static final String SELECTED_COLLECTION = "selectedCollection";
        public static final String INDEX = "index";

        static ArrayListFragment newInstance(Collection collection, int position) {
            ArrayListFragment fragment = new ArrayListFragment();
            Bundle args = new Bundle();
            args.putParcelable(SELECTED_COLLECTION, collection);
            args.putInt(INDEX, position);
            fragment.setArguments(args);

            return fragment;
        }

        @Override
        public void onCreate(Bundle savedInstance) {
            super.onCreate(savedInstance);
            extractArgs(savedInstance);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);

            extractArgs(savedInstanceState);
            View v = inflater.inflate(R.layout.fragment_piece, container, false);
            TextView tv = (TextView) v.findViewById(R.id.pieceTitleText);
            tv.setText("Fragment #" + mIndex);

            return v;
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            extractArgs(savedInstanceState);
            List<String> list = new ArrayList<String>();

            if (mCollection != null) {
                for (Piece piece : mCollection.getPieces()) {
                    list.add(piece.getTitle());
                }
            }

            setListAdapter(new ArrayAdapter<String>(getActivity(), R.layout.fragment_piece_list,
                    list));
        }

        private void extractArgs(Bundle bundle) {
            if (bundle != null) {
                mCollection = bundle.getParcelable(SELECTED_COLLECTION);
                mIndex = bundle.getInt(INDEX);
            }
        }
    }
}
