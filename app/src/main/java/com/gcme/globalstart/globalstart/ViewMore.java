package com.gcme.globalstart.globalstart;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Roger on 3/18/2016.
 */
public class ViewMore extends DialogFragment {
    TextView tvTitle;
    TextView tvMore;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        String txtTitle = getArguments().getString("Title");
        String txtMore = getArguments().getString("Detail");
        View view = inflater.inflate(R.layout.fragment_view_more,null);

        tvTitle = (TextView) view.findViewById(R.id.tv_viewMore_title);
        tvTitle.setText(txtTitle);
        tvMore = (TextView) view.findViewById(R.id.tv_viewMore_detail);
        tvMore.setText(txtMore);

        builder.setView(view)
                .setPositiveButton(R.string.btn_close, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        ViewMore.this.getDialog().cancel();
                    }
                });


        return builder.create();
    }
}
