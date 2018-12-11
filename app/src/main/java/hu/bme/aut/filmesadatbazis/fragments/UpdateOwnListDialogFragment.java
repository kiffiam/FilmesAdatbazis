package hu.bme.aut.filmesadatbazis.fragments;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import hu.bme.aut.filmesadatbazis.R;
import hu.bme.aut.filmesadatbazis.data.OwnList;

public class UpdateOwnListDialogFragment extends DialogFragment {

    public static final String TAG = "UpdateOwnListDialogFragment";

    private EditText nameEditText;
    private EditText descriptionEditText;

    public interface UpdateOwnListDialogListener {
        void onOwnListUpdated(OwnList ownList);
    }


    private UpdateOwnListDialogFragment.UpdateOwnListDialogListener listener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentActivity activity = getActivity();
        if (activity instanceof UpdateOwnListDialogFragment.UpdateOwnListDialogListener) {
            listener = (UpdateOwnListDialogFragment.UpdateOwnListDialogListener) activity;
        } else {
            throw new RuntimeException("Activity must implement the UpdateDialogInterface interface!");
        }
    }

    //Dialogus megjelenítése
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(requireContext())
                .setTitle(R.string.new_own_list)
                .setView(getContentView())
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (isValid()) {
                            listener.onOwnListUpdated(updateOwnList());
                        }
                    }
                })
                .setNegativeButton(R.string.cancel, null).create();
    }

    private OwnList updateOwnList() {

        OwnList ownList = new OwnList();
        ownList.id = getArguments().getLong("id",0);
        ownList.name = nameEditText.getText().toString();
        ownList.description = descriptionEditText.getText().toString();
        return ownList;
    }

    private View getContentView() {

        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_new_list, null);

        nameEditText = contentView.findViewById(R.id.ListNameEditText);
        nameEditText.setText(getArguments().getString("name",""));
        descriptionEditText = contentView.findViewById(R.id.ListDescriptionEditText);
        descriptionEditText.setText(getArguments().getString("description",""));

        return contentView;
    }

    private boolean isValid() {
        return nameEditText.getText().length() > 0 &&
                descriptionEditText.getText().length() > 0;

    }
}
