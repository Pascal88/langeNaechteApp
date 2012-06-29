package de.htwg.tetris.model;

import java.util.Random;



public class NewElement implements INewElement
{	
	private static final long serialVersionUID = -7527568406441884157L;

	public IElement newEl()
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
