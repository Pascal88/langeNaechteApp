package de.htwg.tetris.move;

import java.util.Random;

import de.htwg.tetris.model.Element;
import de.htwg.tetris.model.ElementI;
import de.htwg.tetris.model.ElementJ;
import de.htwg.tetris.model.ElementL;
import de.htwg.tetris.model.ElementN;
import de.htwg.tetris.model.ElementO;
import de.htwg.tetris.model.ElementT;
import de.htwg.tetris.model.ElementZ;


public class NewElement implements INewElement
{	
	public Element newEl()
	{
		Element element[] = {
								new ElementL(), 
								new ElementO(), 
								new ElementN(), 
								new ElementZ(), 
								new ElementI(), 
								new ElementJ(), 
								new ElementT()
							};
		Random rand = new Random();
		
		int el = rand.nextInt() % 7;
		
		try {
			return element[el];
		} catch (ArrayIndexOutOfBoundsException e) {
			return element[el * -1];
		}
	}
}
