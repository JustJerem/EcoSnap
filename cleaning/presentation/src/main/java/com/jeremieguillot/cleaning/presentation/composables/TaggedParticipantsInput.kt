package com.jeremieguillot.cleaning.presentation.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jeremieguillot.cleaning.presentation.R

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TaggedParticipantsInput(
    taggedParticipants: List<String>,
    addParticipant: (String) -> Unit,
    removeParticipant: (String) -> Unit,
) {
    var text by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxWidth()) {
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            taggedParticipants.forEach { participant ->
                InputChip(
                    onClick = { /*Nothing*/ },
                    label = { Text(participant) },
                    trailingIcon = {
                        Icon(
                            modifier = Modifier.clickable { removeParticipant(participant) },
                            imageVector = Icons.Default.Close,
                            contentDescription = stringResource(R.string.cd_remove)
                        )
                    },
                    selected = false
                )
            }
        }

        // Text Field for tagging participants
        OutlinedTextField(
            value = text,
            onValueChange = { newText ->
                if (newText.contains(",")) {
                    addParticipant(newText.replace(",", "").trim())
                    text = ""
                } else {
                    text = newText
                }
            },
            label = { Text(stringResource(R.string.tag_participants)) },
            placeholder = { Text(stringResource(R.string.enter_participants_separated_by_commas)) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun ResetOnCommaTextField() {
    var text by remember { mutableStateOf("") }
    val mutableList =
        remember { mutableStateListOf<String>() } // Utilisation de mutableStateListOf pour suivre les changements

    Column {
        // Affichage des valeurs saisies avant la virgule
        Text(mutableList.joinToString(", "), modifier = Modifier.padding(8.dp))

        TextField(
            value = text,
            onValueChange = { newText ->
                if (newText.contains(",")) {
                    mutableList.add(newText.trimEnd(',')) // Ajoute le texte avant la virgule
                    text = "" // Réinitialisation du champ de texte
                } else {
                    text = newText
                }
            },
            label = { Text("Entrez du texte") },
            modifier = Modifier.fillMaxWidth()
        )
    }
}