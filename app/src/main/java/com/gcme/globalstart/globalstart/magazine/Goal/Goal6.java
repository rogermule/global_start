package com.gcme.globalstart.globalstart.magazine.Goal;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gcme.globalstart.globalstart.R;
import com.gcme.globalstart.globalstart.ViewMore;


public class Goal6 extends Fragment {
    Button readmore;

    public Goal6() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the splash for this fragment
        View view = inflater.inflate(R.layout.know_6, container, false);
        readmore = (Button) view.findViewById(R.id.goal_build_movement);
        readmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewMore viewMore = new ViewMore();
                Bundle bundle = new Bundle();
                bundle.putString("Title","Building Spiritual Movement");

                bundle.putString("Detail","A spiritual movement is God working through a team of likehearted disciples to win, build and send toward the fulfillment of the Great Commission.\n" +
                        "\nBuilding a movement involves encouraging students to fall in love with Jesus, walk by faith in the power of the Holy Spirit, and become spiritual multipliers at their high schools by sharing their faith and discipling those who respond. As a result, God will change lives, campuses and countries for eternity as students multiply their faith for a lifetime.\n");
                viewMore.setArguments(bundle);

                viewMore.show(getActivity().getFragmentManager(), "View More");

            }
        });
        return view;


    }
}
