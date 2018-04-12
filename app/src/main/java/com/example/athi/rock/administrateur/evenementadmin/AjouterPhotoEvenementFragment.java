package com.example.athi.rock.administrateur.evenementadmin;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.Toast;

import com.example.athi.rock.R;
import com.example.athi.rock.utilisateur.evenement.Photo;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.

 */

public class AjouterPhotoEvenementFragment extends Fragment {
    private static final int PICK_IMAGE_REQUEST = 1;

    private Uri mImageUri;
    private StorageTask mUploadTask;

    StorageReference mStorageRef = FirebaseStorage.getInstance().getReference("Photos");
    DatabaseReference mDatabaseRef = FirebaseDatabase.getInstance().getReference("Photos");

    public AjouterPhotoEvenementFragment(){
        //Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ajouter_photo_evenement, container, false);

        //On Récupère le nom de l'évenement sur lequel à cliqué l'utilisateur précédement
        Bundle bundle = getArguments();
        final String nomEvent = bundle.getString("NomEvent");
        Toast.makeText(getContext(), "vous avez cliqué sur: " + nomEvent, Toast.LENGTH_SHORT).show();

        Button btnAjouterPhotoEvent = (Button) view.findViewById(R.id.btnPhoto_event_ajouter);
        Button btnTelechargerPhotoEvent = (Button) view.findViewById(R.id.btnTelechargerPhoto_event_ajouter);

//      Definition de la fonction appelée par chaque boutons
        btnAjouterPhotoEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });
        btnTelechargerPhotoEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {uploadFile(nomEvent);
            }
        });

        //Bouton retour vers la liste des événèments pour ajouter des photos
        Button returnButton = (Button) view.findViewById(R.id.btn_retour_liste);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "retour à la liste", Toast.LENGTH_SHORT).show();
                FragmentManager fm = getFragmentManager();
                fm.popBackStack();
            }
        });

        return view;
    }
//  fonction qui permet d'ouvrir le navigateur de fichier
    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }
// donne l'adresse du fichier et le copie
    private String getFileExtension(Uri uri) {
        ContentResolver cR = getActivity().getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            mImageUri = data.getData();
        }
    }

    private void uploadFile(final String nomEvenementImage) {
        if (mImageUri != null) {
            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
                    + "." + getFileExtension(mImageUri));

            mUploadTask = fileReference.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Photo upload = new Photo(taskSnapshot.getDownloadUrl().toString(), nomEvenementImage, "titi");
                            String uploadId = mDatabaseRef.push().getKey();
                            mDatabaseRef.child(uploadId).setValue(upload);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                        }
                    });
        }
    }
}
