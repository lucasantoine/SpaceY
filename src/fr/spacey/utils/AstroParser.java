package fr.spacey.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import fr.spacey.exceptions.AstroParserException;
import fr.spacey.exceptions.TypeUnknownException;
import fr.spacey.model.SpaceModel;
import fr.spacey.model.entity.Circle;
import fr.spacey.model.entity.Ellipse;
import fr.spacey.model.entity.Entity;
import fr.spacey.model.entity.EntityType;
import fr.spacey.model.entity.Fixe;
import fr.spacey.model.entity.Simule;
import fr.spacey.model.entity.Vaisseau;

public class AstroParser {

	private static int nbLine;
	
	public static List<Entity> loadAstroFile(SpaceModel spaceModel, String filepath) throws Exception {
		List<Entity> entities = new ArrayList<Entity>();
		String[] values;
		boolean paramsset = false;
		boolean vaisseaualreadycreate = false;
		IntStream chars;
		long count;
		EntityType entityType;
		double masse;
		double posx;
		double posy;
		
		nbLine = 1;
		System.out.println(filepath);
		
		try (BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filepath)), "UTF8"))) {
			String line = "";
			while ((line = bf.readLine()) != null) {
				line = formatLine(line);
				if(!line.startsWith("#") && !line.isEmpty()) {
					chars = line.chars();
					chars = chars.filter(ch -> ch == ':');
					count = chars.count();
					if(line.startsWith("PARAMS") || line.startsWith("PARMS")) {
						line = line.replace("PARAMS ", "");
						line = line.replace("PARMS ", "");
						values = line.split(" "); 
						final double g = getDouble("G", values);
						final double dt = getDouble("dt", values);
						final double fa = getDouble("fa", values);
						final double rayon = getInt("rayon", values);
						SpaceModel.G = g;
						spaceModel.setDt(dt);
						spaceModel.setFa(fa);
						spaceModel.setRayon((int) rayon);
						paramsset = true;
					}else if(line.contains(":") && count == 1){
						if(paramsset) {
							values = line.split(" ");
							String name = getName(values);
							if(entityAlreadyExists(name, entities)) { throw new AstroParserException("An entity with the name " + name + " already exists - line : " + nbLine); }
							entityType = getType(values);
							masse = getDouble("masse", values);
							posx = getDouble("posx", values);
							posy = getDouble("posy", values);
							if(posx < -spaceModel.getRayon() || posy < -spaceModel.getRayon() || posx > spaceModel.getRayon() || posy > spaceModel.getRayon()) {
								throw new AstroParserException("One of the positions is outside the radius - line : " + nbLine);
							}
							Vector position = new Vector(posx, posy);
							switch (entityType) {
							case FIXE:
								entities.add(new Fixe(name, // Name
											masse, // Masse
											position // Position
								));
								break;
							case SIMULE: 
								entities.add(new Simule(name, // name
										masse, // masse
										position, // position
										new Vector( // Velocity
												getDouble("vitx", values), // Velocity X
												getDouble("vity", values)) // Velocity Y
								));
								break;
							case VAISSEAU:
								if(vaisseaualreadycreate) throw new AstroParserException("The system can only have one ship - line : " + nbLine);
								entities.add(new Vaisseau(name, // name
										masse, // masse
										position, // position
										new Vector( // Velocity
												getDouble("vitx", values), // Velocity X
												getDouble("vity", values)), // Velocity Y
										getDouble("pprincipal", values), // Propulseur principal
										getDouble("pretro", values) // Propulseur retro
								));
								vaisseaualreadycreate = true;
								break;
							case CERCLE:
								entities.add(new Circle(
										name, 
										masse, 
										position, 
										getEntity("centre", entities, values)
								));
								break;
							case ELLIPSE:
								entities.add(new Ellipse(
										name, 
										masse,
										position, 
										getFixeEntity("f1", entities, values), 
										getFixeEntity("f2", entities, values)
								));
								break;
							}
						}else {
							throw new AstroParserException("PARAMS is undefined");
						}
					}else {
						throw new AstroParserException("Formatting error - line : " + nbLine);
					}
				}
				nbLine++;
			}
		} catch (UnsupportedEncodingException e) {
			throw e;
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		}
		return entities;
	}

	private static String getName(String... values) throws Exception {
		String name = values[0];
		if(name.length() > 1 && !name.contains(" ") && name.contains(":") && name.indexOf(":") == name.length()-1) {
			name = name.substring(0, name.length()-1);
			return name;
		}
		throw new AstroParserException("Erreur lors de la cr�ation d'une entit� : formatage du nom");
	}
	
	private static EntityType getType(String... values) throws Exception {
		for (String string : values) {
			if(!string.contains("=") && !string.contains(":")) {
				try {
					EntityType type = EntityType.getByName(string);
					return type;
				} catch (TypeUnknownException e) {
					e.printStackTrace();
				}
			}
		}
		throw new AstroParserException("Erreur lors de la cr�ation d'une entit� : Type manquant");
	}
	
	private static String getValue(String label, String... values) throws Exception {
		String[] temp;
		String value;
		for (String string : values) {
			temp = string.split("=");
			value = temp[0];
			if(value.equalsIgnoreCase(label)) {
				return temp[1];
			}
		}
		throw new AstroParserException("valeur manquante (" + label + ") - line : " + nbLine);
	}
	
	private static double getInt(String label, String... values) throws Exception {
		String value = getValue(label, values);
		if(value == null) return -1;
		try {
			double d = Integer.parseInt(value);
			return d;
		}catch (NumberFormatException e) {
			throw new AstroParserException(value + " is not an int value - line : " + nbLine);
		}
	}
	
	private static double getDouble(String label, String... values) throws Exception {
		String value = getValue(label, values);
		if(value == null) return -1;
		try {
			double d = Double.parseDouble(value);
			return d;
		}catch (NumberFormatException e) {
			throw new AstroParserException(value + " is not a double value - line : " + nbLine);
		}
	}
	
	private static Entity getEntity(String label, List<Entity> entities, String...values) throws Exception {
		String value = getValue(label, values);
		String name;
		for (Entity entity : entities) {
			name = entity.getName();
			if(name.equalsIgnoreCase(value)) {
				return entity;
			}
		}
		throw new AstroParserException(value + " entity doesn't exist - line : " + nbLine);
	}
	
	private static Fixe getFixeEntity(String label, List<Entity> entities, String...values) throws Exception {
		Entity entity = getEntity(label, entities, values);
		if(entity.getType() == EntityType.FIXE) {
			return (Fixe) entity;
		}
		throw new AstroParserException(entity.getName() + " isn't an fix entity - line : " + nbLine);
	}
	
	private static boolean entityAlreadyExists(String name, List<Entity> entities) {
		String nameValue;
		for (Entity entity : entities) {
			nameValue = entity.getName();
			if(nameValue.equalsIgnoreCase(name)) return true;
		}
		return false;
	}
	
	private static String formatLine(String line) {
		String formatedLine = line;
		formatedLine = formatedLine.replaceAll("  *", " ");
		formatedLine = formatedLine.replaceAll("\t", "");
		if(formatedLine.indexOf(" ") == 0) {
			formatedLine = formatedLine.replaceFirst("\\s*", "");
		}
		return formatedLine;
	}
	
}
