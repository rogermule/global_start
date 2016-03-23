package com.gcme.globalstart.globalstart.magazine.Goal;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gcme.globalstart.globalstart.R;
import com.gcme.globalstart.globalstart.ViewMore;


public class Goal2 extends Fragment {
    Button readmore;
    public Goal2() {
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
        View view = inflater.inflate(R.layout.know_2, container, false);
        readmore = (Button) view.findViewById(R.id.goal_win_readmore);
        readmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewMore viewMore = new ViewMore();
                Bundle bundle = new Bundle();
                bundle.putString("Title","Win");

                bundle.putString("Detail",
                        "How can you expose as many students to the gospel and then follow up and disciple those who respond? This will look different in every country, city or school, depending on the openness of the school campus and your access to students.\n" +
                        "\n Identify target groups: A target group is a group of students who have common interests because of friendships, talents or personalities. They can be organized clubs and athletic teams or natural groups of students because of classes, friendships, social groups or where they live. If you reach out to target groups you will have a group of students who trust each other and are more likely to learn and grow together. \n ");

                viewMore.setArguments(bundle);

                viewMore.show(getActivity().getFragmentManager(), "View More");
                //viewMore.setText("Vdksaglkdjgal;ksjdglkajs;ldkgja;lksdjg ");

            }
        });
        return view;


    }
}
