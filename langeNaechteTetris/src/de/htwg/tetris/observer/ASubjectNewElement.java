package de.htwg.tetris.observer;

import java.util.List;

public abstract class ASubjectNewElement implements ISubjectNewElement {

	protected List<IObserverNewElement> observersNewElement;
	protected int countFullLine = 0;
	
	public final void notifyObservers(List<IObserverNewElement> observersNewElement) 
	{
		for (int i=0; i<observersNewElement.size();i++)
		{
			IObserverNewElement observer = observersNewElement.get(i);
			observer.update(countFullLine);
		}			
	}

	public final void registerObserver(IObserverNewElement o) 
	{
		observersNewElement.add(o);	
	}

	public final void removeObserver(IObserverNewElement o) 
	{
		int i = observersNewElement.indexOf(o);
		if(i>=0)
		{
			observersNewElement.remove(i);
		}	
	}
}