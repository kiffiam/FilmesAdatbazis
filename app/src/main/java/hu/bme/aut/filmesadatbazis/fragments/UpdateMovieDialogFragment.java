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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import hu.bme.aut.filmesadatbazis.R;
import hu.bme.aut.filmesadatbazis.data.Movie;

public class UpdateMovieDialogFragment extends DialogFragment {

    public static final String TAG = "UpdateMovieDialogFragment";

    private EditText titleEditText;
    private Spinner pointSpinner;
    private Spinner genreSpinner;
    private EditText opinionEditText;

    public interface UpdateMovieDialogListener {
        void onMovieUpdated(Movie newMovie);
    }

    private UpdateMovieDialogFragment.UpdateMovieDialogListener listener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentActivity activity = getActivity();
        if (activity instanceof UpdateMovieDialogFragment.UpdateMovieDialogListener) {
            listener = (UpdateMovieDialogFragment.UpdateMovieDialogListener) activity;
        } else {
            throw new RuntimeException("Activity must implement the NewMovieDialogListener interface!");
        }

    }


    //Dialogus megjelenítése
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {



        return new AlertDialog.Builder(requireContext())
                .setTitle(R.string.new_movie)
                .setView(getContentView())
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (isValid()) {
                            listener.onMovieUpdated(updateMovie());
                        }
                    }
                })
                .setNegativeButton(R.string.cancel, null).create();
    }

    private Movie updateMovie() {

        Movie movie = new Movie();
        movie.title = titleEditText.getText().toString();
        movie.opinion = opinionEditText.getText().toString();
        movie.point = Integer.parseInt(pointSpinner.getSelectedItem().toString());
        movie.genre = Movie.Genre.getByOrdinal(genreSpinner.getSelectedItemPosition());

        return movie;
    }

    public void addItemsOnPointSpinner(View contView){
        Integer[] arraySpinner = new Integer[] {1,2,3,4,5,6,7,8,9,10};
        pointSpinner = contView.findViewById(R.id.MoviePointSpinner);
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pointSpinner.setAdapter(adapter);


    }

    public void addItemsOnGenreSpinner(View contView){
        genreSpinner = contView.findViewById(R.id.MovieGenreSpinner);
        genreSpinner.setAdapter(new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.genre_items)));

    }


    private View getContentView() {

        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_new_movie, null);

        titleEditText = contentView.findViewById(R.id.MovieTitleEditText);
        opinionEditText = contentView.findViewById(R.id.MovieOpinionEditText);

        //PointSpinner fill with integers 1-10
        addItemsOnPointSpinner(contentView);

        //genreSpinner fill with genres
        addItemsOnGenreSpinner(contentView);

       /* if (getArguments() != null) {
            titleEditText.setText(getArguments().getString("title",""));
            opinionEditText.setText(getArguments().getString("opinion",""));
            pointSpinner.setSelection(getArguments().getInt("point",1));
            genreSpinner.setSelection(getArguments().getInt("genre",1));
        }*/

        return contentView;
    }

    private boolean isValid() {
        return titleEditText.getText().length() > 0 &&
                opinionEditText.getText().length() > 0;

    }
}
