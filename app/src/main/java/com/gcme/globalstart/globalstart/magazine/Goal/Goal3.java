package com.gcme.globalstart.globalstart.magazine.Goal;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gcme.globalstart.globalstart.R;
import com.gcme.globalstart.globalstart.ViewMore;


public class Goal3 extends Fragment {
    Button readmore1, readmore2;

    public Goal3() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the splash for this fragment
        View view = inflater.inflate(R.layout.know_3, container, false);

        readmore1 = (Button) view.findViewById(R.id.goal_sowing_with_school);
        readmore2 = (Button) view.findViewById(R.id.goal_sowing_with_no_school);

        readmore1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewMore viewMore = new ViewMore();
                Bundle bundle = new Bundle();
                bundle.putString("Title","Evangelism with Access to school");

                bundle.putString("Detail","Become an insider within the school through serving them. As you serve you can meet and begin to develop relationships with students in school, at lunch, after school, or any way that you can gain access to them. \n" +
                        "Ideas to serve the school: \n• Offer school-wide events and programs through athletic competitions, motivational speakers and fun activities. \n• Tutor students. \n• Speak in classes about morals, values, character or felt needs. \n• Volunteer at school events. • Get involved with musical groups, leadership students or clubs. \n• Take cookies or food to the administrators and let them know that you are praying for them.");
                viewMore.setArguments(bundle);

                viewMore.show(getActivity().getFragmentManager(), "View More");

            }
        });

        readmore2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewMore viewMore = new ViewMore();
                Bundle bundle = new Bundle();
                bundle.putString("Title","Evangelism with No Access to school");

                bundle.putString("Detail","Partner with churches and train the Christian students to share their faith. You can send the students back to their schools to share Christ with their friends and then invite their friends to activities off campus. Students can have outreaches and parties in their homes, in parks, at restaurants or in many other locations. " +
                        "\n\nCan you use social media to meet kids and build trust in relationships with those you have met? \n" +
                        "\nInvite students to fun activities, English corners, sports competitions and weekly meetings at student’s homes that include games and a short biblical message.\n");
                viewMore.setArguments(bundle);

                viewMore.show(getActivity().getFragmentManager(), "View More");
            }
        });

        return view;


    }
}
