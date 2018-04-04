package com.example.athi.rock.utilisateur.equipe;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.athi.rock.R;
import java.util.List;

/**
 * Created by Athi on 11/02/2018.
 * Gerer les éléments des lignes de la liste des membres
 */

public class MembreAdapter extends ArrayAdapter<Membre>{

    public class MembreViewHolder {
        public TextView role;
        public TextView nom;
        public TextView prenom;
        public TextView desciption;
        public ImageView photoRole;
    }

    public MembreAdapter(Context context, List<Membre> membres) {
        super(context, 0,membres);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.ligne_liste_role,parent, false);
        }

        MembreViewHolder viewHolder = (MembreViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new MembreViewHolder();
            viewHolder.role = (TextView) convertView.findViewById(R.id.id_role);
            viewHolder.prenom = (TextView) convertView.findViewById(R.id.id_prenom);
            viewHolder.nom = (TextView) convertView.findViewById(R.id.id_nom);
            viewHolder.desciption = (TextView) convertView.findViewById(R.id.id_description);
            viewHolder.photoRole = (ImageView) convertView.findViewById(R.id.id_photoRole);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Membre> membres
        Membre membre = getItem(position);

        //associer les éléments visuels de chaque ligne avec ceux de l'objet Java Membre
        viewHolder.role.setText(membre.getRole());
        viewHolder.prenom.setText(membre.getPrenom());
        viewHolder.nom.setText(membre.getNom());
        viewHolder.desciption.setText(membre.getDescription());
        viewHolder.photoRole.setImageDrawable(new ColorDrawable(membre.getColor()));

        return convertView;
    }

}
