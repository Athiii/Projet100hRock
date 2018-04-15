package com.example.athi.rock.administrateur.evenementadmin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.athi.rock.R;
import com.example.athi.rock.utilisateur.evenement.Evenement;

import java.util.List;

/**
 * Created by Athi on 09/04/2018.
 */

public class EvenementsListePhotosAjouterAdapter extends ArrayAdapter<Evenement> {

    public class EvenementListePhotosViewHolder{
        public TextView nomEvent;
    }
    public EvenementsListePhotosAjouterAdapter(Context context, List<Evenement> evenementList) {
        super(context, 0,evenementList);
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.ligne_liste_evenement_photos_ajoutees,parent, false);
        }

       EvenementListePhotosViewHolder viewHolder = (EvenementListePhotosViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new EvenementListePhotosViewHolder();
            viewHolder.nomEvent= (TextView) convertView.findViewById(R.id.id_nom_evenement_photos_ajoutes);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Evenement> evenements
        final Evenement evenement= getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.nomEvent.setText(evenement.getNomEvent());

        return convertView;
    }
}
