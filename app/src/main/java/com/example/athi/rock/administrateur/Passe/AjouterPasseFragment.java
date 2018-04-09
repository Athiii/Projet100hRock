package com.example.athi.rock.administrateur.Passe;


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
import android.widget.Toast;

import com.example.athi.rock.MainActivity;
import com.example.athi.rock.R;
import com.example.athi.rock.utilisateur.equipe.Membre;
import com.example.athi.rock.utilisateur.evenement.Photo;
import com.example.athi.rock.utilisateur.passes.Passe;
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
public class AjouterPasseFragment extends Fragment {

    private static final int PICK_IMAGE_REQUEST = 1;

    private Boolean indicateurPhoto=false;
    private Uri mImageUri;
    private StorageTask mUploadTask;

    //on récupère les références de fire base afin de pouvoir ajouter les donnés au bonne endroit (via le fichier google.json)
    StorageReference mStorageRef = FirebaseStorage.getInstance().getReference("Photos");
    DatabaseReference mDatabaseRef = FirebaseDatabase.getInstance().getReference("Photos");
    DatabaseReference passe = FirebaseDatabase.getInstance().getReference();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_ajouter_passe, container, false);
        Button btnvaliderPasse = (Button) view.findViewById(R.id.btnValider_passe_ajouter);
        Button btnAjouterPhoto = view.findViewById(R.id.btnPhoto_passe_ajouter);

        //Bouton retour vers l'activité utilisateur (HomeHautFragment)
        Button returnButton = (Button) view.findViewById(R.id.btn_retour_utilisateur);

        //Renvoie des différents boutons

        btnAjouterPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();

            }
        });
        btnvaliderPasse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText nom=getActivity().findViewById(R.id.id_nom_passe_ajouter);
                String nomPasse = nom.getText().toString();

                EditText difficulte=getActivity().findViewById(R.id.id_difficulte_ajouter);
                int difficultePasse=Integer.valueOf(String.valueOf(difficulte.getText()));

                EditText lien=getActivity().findViewById(R.id.id_lien_passe_ajouter);
                String lienPasse=lien.getText().toString();

                if (indicateurPhoto == false || nomPasse==null || lienPasse==null ) {
                    Toast.makeText(getContext(),"Merci de remplir tous les champs et de séléctionner une photo",Toast.LENGTH_SHORT).show();

                }
//                if (mUploadTask.isInProgress()){
//                    Toast.makeText(getContext(),"Merci d'attendre la fin du téléchargement",Toast.LENGTH_SHORT).show();
//                }
                else{
                    uploadFile(nomPasse,difficultePasse,lienPasse);
                    //on créé un nouvel objet que l'on ajoute à fire base.

                    Toast.makeText(getContext(),"La passe est ajouté et validé",Toast.LENGTH_SHORT).show();
                }
            }
        });
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"retour à la maison",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
        indicateurPhoto=true;
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

    private void uploadFile(final String nomPasse, final int difficultePasse,final String lienPasse) {

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


                            Photo photos= new Photo(taskSnapshot.getDownloadUrl().toString(),"passePhoto",nomPasse);
                            String uploadId = mDatabaseRef.push().getKey();
                            mDatabaseRef.child(uploadId).setValue(photos);

                            Passe nouvelPasse =new Passe(nomPasse,difficultePasse,taskSnapshot.getDownloadUrl().toString(),lienPasse);
                            passe.child("passe").push().setValue(nouvelPasse);

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
