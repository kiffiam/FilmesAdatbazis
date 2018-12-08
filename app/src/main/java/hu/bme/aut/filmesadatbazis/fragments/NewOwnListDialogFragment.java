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

public class NewOwnListDialogFragment extends DialogFragment {

    public static final String TAG = "NewOwnListDialogFragment";

    public interface NewOwnListDialogListener {
        void onOwnListCreated(OwnList newItem);
    }

    private NewOwnListDialogListener listener;

    private EditText nameEditText;
    private EditText descriptionEditText;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentActivity activity = getActivity();
        if (activity instanceof NewOwnListDialogListener) {
            listener = (NewOwnListDialogListener) activity;
        } else {
            throw new RuntimeException("Activity must implement the NewMovieDialogListener interface!");
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
                        // TODO implement list creation
                    }
                })
                .setNegativeButton(R.string.cancel, null).create();
    }

    private View getContentView() {
        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_new_list, null);

        nameEditText = contentView.findViewById(R.id.MovieTitleEditText);
        descriptionEditText = contentView.findViewById(R.id.MovieOpinionEditText);

        return contentView;
    }
}
