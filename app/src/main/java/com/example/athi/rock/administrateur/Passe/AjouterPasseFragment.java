package com.example.athi.rock.administrateur.Passe;


import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.athi.rock.MainActivity;
import com.example.athi.rock.R;
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
    public AjouterPasseFragment(){
        //required empty public constructor
    }

    private static final int PICK_IMAGE_REQUEST = 1;
    private Boolean indicateurPhoto=false;
    private Uri mImageUri;
    private StorageTask mUploadTask;

    AjouterPasseFragment listener;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ajouter_passe, container, false);
        Button btnvaliderPasse = (Button) view.findViewById(R.id.btnValider_passe_ajouter);
        Button btnAjouterPhoto = view.findViewById(R.id.btnPhoto_passe_ajouter);


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
                EditText nom = getActivity().findViewById(R.id.id_nom_passe_ajouter);
                String nomPasse = nom.getText().toString();

                EditText difficulte = getActivity().findViewById(R.id.id_difficulte_ajouter);
//                String difficultePasseString= difficulte.getText().toString();
                Integer difficulteInt = Integer.parseInt(difficulte.getText().toString());


                EditText lien = getActivity().findViewById(R.id.id_lien_passe_ajouter);
                String lienPasse = lien.getText().toString();
                if (lienPasse.length() < 21) {
                    Toast.makeText(getContext(), "Donner un lien https://m.youtube.com", Toast.LENGTH_LONG).show();
                } else {
                    String indicateurYoutube = lienPasse.substring(0, 21);
                    //
                    if (indicateurPhoto == false || nomPasse == null || lienPasse == null || difficulteInt == null || difficulteInt > 5 || difficulteInt < 0) {
                        Toast.makeText(getContext(), "Merci de remplir tous les champs et de séléctionner une photo", Toast.LENGTH_SHORT).show();

                    }
                    else {
                        if (indicateurYoutube.equals("https://m.youtube.com")) {
                            showpopup(nomPasse, difficulteInt, lienPasse);
                            nom.getText().clear();
                            difficulte.getText().clear();
                            lien.getText().clear();
                        } else {
                            Toast.makeText(getContext(), "Merci de mettre un lien Youtube", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
        //Bouton retour vers l'activité utilisateur (HomeHautFragment)
        //Bouton retour vers l'activité utilisateur (HomeHautFragment)
        Button returnButton = (Button) view.findViewById(R.id.btn_retour_utilisateur);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "retour à la maison", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
    @Override
    public void onDetach() {
        super.onDetach();
        this.listener = null;
    }
    /*showpopup retourne une popup pour valider ou non la passe et si oui l'enregistrer sur firebase
    * parametres entrées: les differents éléments du constructeur Passe.
    * */
    private PopupWindow pw;
    Button Close;
    Button Ajouter;
    private void showpopup(final String nomPasse, final Integer difficulteInt, final String lienPasse) {
        View viewpopup;
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        viewpopup = layoutInflater.inflate(R.layout.popup_ajouter,null);
        TextView textView= (TextView) viewpopup.findViewById(R.id.popup_nom);
        textView.setText(nomPasse + "\n"+difficulteInt.toString()+"\n" + lienPasse);
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
                //on créé un nouvel objet que l'on ajoute à fire base.
                uploadFile(nomPasse, difficulteInt, lienPasse);
                Toast.makeText(getContext(), "La passe a été ajoutée", Toast.LENGTH_SHORT).show();
                pw.dismiss();
            }});
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

    private void uploadFile(final String nomPasse, final int difficultePasse, final String lienPasse) {
        //on récupère les références de fire base afin de pouvoir ajouter les donnés au bonne endroit (via le fichier google.json)

        final StorageReference mStorageRef= FirebaseStorage.getInstance().getReference("Photos");
        if (mImageUri != null) {
            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
                    + "." + getFileExtension(mImageUri));

            mUploadTask = fileReference.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            DatabaseReference passeBase = FirebaseDatabase.getInstance().getReference();
                            Passe nouvelPasse =new Passe(nomPasse,difficultePasse,taskSnapshot.getDownloadUrl().toString(),lienPasse);
                            passeBase.child("passe").push().setValue(nouvelPasse);

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
