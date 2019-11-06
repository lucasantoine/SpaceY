package fr.spacey.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import fr.spacey.exceptions.TypeUnknownException;
import fr.spacey.model.SpaceModel;
import fr.spacey.model.entity.Entity;
import fr.spacey.model.entity.EntityType;
import fr.spacey.model.entity.Fixe;
import fr.spacey.model.entity.Simule;
import fr.spacey.model.entity.Vaisseau;

/**
 * SpaceY - IUT A de Lille - 3e Semestre
 * 
 * @author ANTOINE Lucas, BARBIER Benoit, POMIER Mathys, RYCKEBUSH Corentin
 * @since 26 oct. 2019
 * 
 *        Classe permettant de lire un fichier et de creer un Array d'Entite a
 *        partir de ce fichier.
 */
public class AstroParser {

	/**
	 * Retourne une liste d'entites generee a�partir d'un fichier de configuration
	 * au format astro.
	 * 
	 * @param spaceModel
	 * @param filepath   Chemin vers un fichier de configuration au format astro.
	 * @return Liste d'entites generee a�partir d'un fichier de configuration au
	 *         format astro.
	 */
	public static List<Entity> loadAstroFile(SpaceModel spaceModel, String filepath) {
		List<Entity> entities = new ArrayList<Entity>();
		File file = new File(filepath);
		String[] values;
		try (BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"))) {
			String line = "";
			while ((line = bf.readLine()) != null) {
				if (!line.startsWith("#")) {
					if (line.startsWith("PARAMS")) {
						values = line.split(" ");
						SpaceModel.G = Double.valueOf(values[1].split("=")[1]);
						spaceModel.setDt(Float.valueOf(values[2].split("=")[1]));
						spaceModel.setFa(Double.valueOf(values[3].split("=")[1]));
						spaceModel.setRayon(Integer.valueOf(values[4].split("=")[1]));
					} else if (line.contains(":")) {
						values = line.split(":");
						String name = values[0];
						values = values[1].split(" ");
						EntityType entityType = EntityType.getByName(values[1]);
						switch (entityType) {
						case FIXE:
							entities.add(new Fixe(name, // Name
									Double.valueOf(values[2].split("=")[1]), // Masse
									new Vector( // Position
											Integer.valueOf(values[3].split("=")[1]), // Pos X
											Integer.valueOf(values[4].split("=")[1])) // Pos Y
							));
							break;
						case SIMULE: 
							entities.add(new Simule(name, // name
									Double.valueOf(values[2].split("=")[1]), // masse
									new Vector( // Position
											Integer.valueOf(values[3].split("=")[1]), // Pos X
											Integer.valueOf(values[4].split("=")[1])), // Pos Y
									new Vector( // Velocity
											Double.valueOf(values[5].split("=")[1]), // Velocity X
											Double.valueOf(values[6].split("=")[1])) // Velocity Y
							));
							break;
						case VAISSEAU:
							entities.add(new Vaisseau(name, // name
									Double.valueOf(values[2].split("=")[1]), // masse
									new Vector( // Position
											Integer.valueOf(values[3].split("=")[1]), // Pos X
											Integer.valueOf(values[4].split("=")[1])), // Pos Y
									new Vector( // Velocity
											Double.valueOf(values[5].split("=")[1]), // Velocity X
											Double.valueOf(values[6].split("=")[1])), // Velocity Y
									Double.valueOf(values[7].split("=")[1]), // Propulseur principal
									Double.valueOf(values[8].split("=")[1]) // Propulseur retro
							));
							break;
						case CERCLE:
							// TODO
							break;
						case ELLIPSE:
							// TODO
							break;
						default:
							break;
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (TypeUnknownException e) {
			e.printStackTrace();
		}
		return entities;
	}

}
