package com.example.athi.rock.administrateur.Membre;


import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.athi.rock.R;
import com.example.athi.rock.utilisateur.equipe.Membre;
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
public class AjouterMembreFragment extends Fragment {
    private static final int PICK_IMAGE_REQUEST = 1;

    private Uri mImageUri;
    private ProgressBar mProgressBar;
    private StorageTask mUploadTask;

    StorageReference mStorageRef = FirebaseStorage.getInstance().getReference("uploads");
    DatabaseReference mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_ajouter_membre, container, false);
        Button btnvaliderMembre = (Button) view.findViewById(R.id.btnAjouter_membre_ajouter);
        Button btnAjouterPhoto = view.findViewById(R.id.btnPhoto_membre_ajouter);
        Button btnTelechargerPhoto = view.findViewById(R.id.btnTelechargerPhoto_membre_ajouter);




        //on récupère les références de fire base afin de pouvoir ajouter les donnés au bonne endroit (via le fichier google.json)
        final DatabaseReference membre = FirebaseDatabase.getInstance().getReference();

        btnAjouterPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });
        btnvaliderMembre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText nom = (EditText) getActivity().findViewById(R.id.id_nom_membre_ajouter);
                String nomMembre = nom.getText().toString();


                EditText prenom = (EditText) getActivity().findViewById(R.id.id_prenom_membre_ajouter);
                String prenomMembre = prenom.getText().toString();

                EditText role = (EditText) getActivity().findViewById(R.id.id_role_membre_ajouter);
                String roleMembre = role.getText().toString();


                EditText description = (EditText) getActivity().findViewById(R.id.id_description_membre_ajouter);
                String descriptionMembre = description.getText().toString();

                //on créé un nouvel objet que l'on ajoute à fire base.
                Membre nouveauMembre =new Membre(1,descriptionMembre,nomMembre,prenomMembre,roleMembre);
                membre.child("membre").push().setValue(nouveauMembre);
                Toast.makeText(getContext(),"L'événement est validé",Toast.LENGTH_SHORT).show();


                // Inflate the layout for this fragment


            }
        });
        btnTelechargerPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    uploadFile();
                }
        });
        return view;
    }
        private void openFileChooser() {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent, PICK_IMAGE_REQUEST);
        }
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

    private void uploadFile() {
        if (mImageUri != null) {
            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
                    + "." + getFileExtension(mImageUri));

            mUploadTask = fileReference.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                            Handler handler = new Handler();
//                            handler.postDelayed(new Runnable() {
//                                @Override
//                                public void run() {
//                                    mProgressBar.setProgress(0);
//                                }
//                            }, 500);


                            Photo upload = new Photo(taskSnapshot.getDownloadUrl().toString());
                            String uploadId = mDatabaseRef.push().getKey();
                            mDatabaseRef.child(uploadId).setValue(upload);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                        }
                    });
//                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
//                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
//                            mProgressBar.setProgress((int) progress);
//                        }
//                    });
        } else {

        }
    }



}
