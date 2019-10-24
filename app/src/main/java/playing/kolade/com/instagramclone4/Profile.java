package playing.kolade.com.instagramclone4;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;


/**
 * A simple {@link Fragment} subclass.
 */
public class Profile extends Fragment {


    private EditText edtProfileName, edtHobbies, edtProfession, edtSport, edtBio;
    private Button btnUpdate;

    public Profile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);

        edtProfileName = view.findViewById(R.id.edtProfileName);
        edtBio = view.findViewById(R.id.edtBio);
        edtProfession = view.findViewById(R.id.edtProfession);
        edtHobbies = view.findViewById(R.id.edtHobbies);
        edtSport = view.findViewById(R.id.edtSport);
        btnUpdate = view.findViewById(R.id.btnUpdate);

        final ParseUser parseUser = ParseUser.getCurrentUser();

        edtProfileName.setText(parseUser.get("profileName")+"");
        edtBio.setText(parseUser.get("profileBio")+"");
        edtProfession.setText(parseUser.get("profileProfession")+"");
        edtHobbies.setText(parseUser.get("profileHobbies")+"");
        edtSport.setText(parseUser.get("profileFavSport")+"");



        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                parseUser.put("profileName", edtProfileName.getText().toString());
                parseUser.put("profileBio", edtBio.getText().toString());
                parseUser.put("profileProfession", edtProfession.getText().toString());
                parseUser.put("profileHobbies", edtHobbies.getText().toString());
                parseUser.put("profileFavSport", edtSport.getText().toString());

                parseUser.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e==null){

                            Toast.makeText(getContext(),edtProfileName.getText().toString()+ " has been saved", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(getContext(), e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });

        return view;

    }

}
