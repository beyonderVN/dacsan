package ngohoanglong.com.killsometime;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ngohoanglong.com.killsometime.recyclerview.MumAdapter;
import ngohoanglong.com.killsometime.recyclerview.holderfactory.HolderFactoryImpl;
import ngohoanglong.com.killsometime.recyclerview.holdermodel.BaseHM;
import ngohoanglong.com.killsometime.recyclerview.holdermodel.SimpleVerticalHM;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NavFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NavFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;


    public NavFragment() {
        // Required empty public constructor
    }

    @BindView(R.id.rvList)
    RecyclerView rvList;

    public static NavFragment newInstance(String param1, String param2) {
        NavFragment fragment = new NavFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_nav, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        final StaggeredGridLayoutManager staggeredGridLayoutManagerVertical =
                new StaggeredGridLayoutManager(
                        2, //The number of Columns in the grid
                        LinearLayoutManager.VERTICAL);
        staggeredGridLayoutManagerVertical.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        staggeredGridLayoutManagerVertical.invalidateSpanAssignments();
        staggeredGridLayoutManagerVertical.setItemPrefetchEnabled(false);

        rvList.setLayoutManager(staggeredGridLayoutManagerVertical);
        rvList.setHasFixedSize(true);

        MumAdapter baseAdapter = new MumAdapter(getActivity(), new HolderFactoryImpl());
        List<BaseHM> baseHMs = new ArrayList<>();
        for (int i =0;i<10;i++){
            baseHMs.add(new SimpleVerticalHM("Hello "+i));
        }
        baseAdapter.setItems(baseHMs);
        rvList.setAdapter(baseAdapter);

    }


}
