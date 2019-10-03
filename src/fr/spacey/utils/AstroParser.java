package fr.spacey.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

import fr.spacey.SpaceY;
import fr.spacey.exceptions.TypeUnknownException;
import fr.spacey.model.entity.Entity;
import fr.spacey.model.entity.EntityType;
import fr.spacey.model.entity.Fixe;
import fr.spacey.model.entity.Simule;
import fr.spacey.model.entity.Vaisseau;

public class AstroParser {

	public static Set<Entity> loadAstroFile(String filepath) {
		Set<Entity> entities = new HashSet<Entity>();
		File file = new File(filepath);
		String[] values;
		try(BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"))){
			String line = "";
			while((line = bf.readLine()) != null) {
				if(!line.startsWith("#")) {
					if(line.startsWith("PARAMS")) {
						values = line.split(" ");
						SpaceY.getInstance().gravite = Double.valueOf(values[1].split("=")[1]);
						SpaceY.getInstance().dt = Double.valueOf(values[2].split("=")[1]);
						SpaceY.getInstance().fa = Double.valueOf(values[3].split("=")[1]);
						SpaceY.getInstance().rayon = Double.valueOf(values[4].split("=")[1]);
					}else if(line.contains(":")) {
						values = line.split(":");
						String name = values[0];
						values = values[1].split(" ");
						System.out.println(values[1]);
						EntityType entityType = EntityType.getByName(values[1]);
						switch (entityType) {
						case FIXE:
							entities.add(new Fixe(
									name, // Name
									entityType, // EntityType
									Double.valueOf(values[2].split("=")[1]), // Masse
									new Position( // Position
											Integer.valueOf(values[3].split("=")[1]), // Pos X
											Integer.valueOf(values[4].split("=")[1])) // Pos Y
									));
							break;
						case SIMULE:
							entities.add(new Simule(
									name, // name
									entityType, // EntityType
									Double.valueOf(values[2].split("=")[1]), // masse
									new Position( // Position
											Integer.valueOf(values[3].split("=")[1]), // Pos X
											Integer.valueOf(values[4].split("=")[1])), // Pos Y
									new Velocity( // Velocity
											Double.valueOf(values[5].split("=")[1]), // Velocity X
											Double.valueOf(values[6].split("=")[1])) // Velocity Y
									));
							break;
						case VAISEAU:
							entities.add(new Vaisseau(
									name, // name
									entityType, // EntityType
									Double.valueOf(values[2].split("=")[1]), // masse
									new Position( // Position 
											Integer.valueOf(values[3].split("=")[1]), // Pos X
											Integer.valueOf(values[4].split("=")[1])), // Pos Y
									new Velocity( // Velocity
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
