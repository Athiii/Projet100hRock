package com.example.athi.rock.administrateur.Membre;


import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.athi.rock.MainActivity;
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
    private Boolean indicateurPhoto=false;
    EditText nom;
    EditText prenom;
    EditText role;
    EditText description;

    //on récupère les références de fire base afin de pouvoir ajouter les donnés au bonne endroit (via le fichier google.json)
    StorageReference mStorageRef = FirebaseStorage.getInstance().getReference("photos");
    DatabaseReference mDatabaseRef = FirebaseDatabase.getInstance().getReference("Photos");
    DatabaseReference membre = FirebaseDatabase.getInstance().getReference();

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_ajouter_membre, container, false);
        Button btnvaliderMembre = (Button) view.findViewById(R.id.btnAjouter_membre_ajouter);
        Button btnAjouterPhoto = view.findViewById(R.id.btnPhoto_membre_ajouter);

        btnAjouterPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });
        btnvaliderMembre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nom = (EditText) getActivity().findViewById(R.id.id_nom_membre_ajouter);
                String nomMembre = nom.getText().toString();


                prenom = (EditText) getActivity().findViewById(R.id.id_prenom_membre_ajouter);
                String prenomMembre = prenom.getText().toString();

                role = (EditText) getActivity().findViewById(R.id.id_role_membre_ajouter);
                String roleMembre = role.getText().toString();


                description = (EditText) getActivity().findViewById(R.id.id_description_membre_ajouter);
                String descriptionMembre = description.getText().toString();


                if (indicateurPhoto == false || descriptionMembre==null || roleMembre==null || prenomMembre==null || nomMembre==null ) {
                    Toast.makeText(getContext(),"Merci de remplir tous les champs et de séléctionner une photo",Toast.LENGTH_SHORT).show();

                }
//                if (mUploadTask.isInProgress()){
//                    Toast.makeText(getContext(),"Merci d'attendre la fin du téléchargement",Toast.LENGTH_SHORT).show();
//                }
                else{
                    showpopup(descriptionMembre,nomMembre,prenomMembre,roleMembre);

                }
            }
        });

        //Bouton retour vers l'activité utilisateur (HomeHautFragment)
        Button returnButton = (Button) view.findViewById(R.id.btn_retour_utilisateur);
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

    private PopupWindow pw;
    Button Close;
    Button Ajouter;
    private void showpopup(final String descriptionMembre, final String nomMembre, final String prenomMembre, final String roleMembre) {
        View viewpopup;
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        viewpopup = layoutInflater.inflate(R.layout.popup_ajouter,null);
        TextView textView= (TextView) viewpopup.findViewById(R.id.popup_nom);
        textView.setText(prenomMembre +" "+ nomMembre+ "\n"+roleMembre+"\n " + descriptionMembre);
        Close = (Button) viewpopup.findViewById(R.id.popup_non);
        Ajouter = (Button) viewpopup.findViewById(R.id.popup_oui);
        pw = new PopupWindow(viewpopup,300,600,true);
        pw.showAtLocation(viewpopup, Gravity.CENTER, 0, 0);
        pw.getAnimationStyle();
        Close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pw.dismiss();
            }
        });
        Ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadFile(descriptionMembre, nomMembre, prenomMembre, roleMembre);
                //on créé un nouvel objet que l'on ajoute à fire base.
                Toast.makeText(getContext(), "Le membre est ajouté et validé", Toast.LENGTH_SHORT).show();
                //vider les editTexts
                nom.getText().clear();
                prenom.getText().clear();
                description.getText().clear();
                role.getText().clear();
                pw.dismiss();
                Fragment fragment=null;

            }
        });
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

    private void uploadFile(final String descriptionMembre, final String nomMembre, final String prenomMembre, final String roleMembre) {

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


                            Photo photos= new Photo(taskSnapshot.getDownloadUrl().toString(),"membrePhoto",roleMembre);
                            String uploadId = mDatabaseRef.push().getKey();
                            mDatabaseRef.child(uploadId).setValue(photos);

                            Membre nouveauMembre =new Membre(taskSnapshot.getDownloadUrl().toString(),descriptionMembre,nomMembre,prenomMembre,roleMembre);
                            membre.child("membre").push().setValue(nouveauMembre);

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
