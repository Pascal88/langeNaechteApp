package de.htwg.tetris.observer;

import java.util.List;

public abstract class ASubject implements ISubject {

	protected List<IObserver> observers;
	
	public final void notifyObservers(List<IObserver> observers) 
	{
		for (int i=0; i<observers.size();i++)
		{
			IObserver observer = observers.get(i);
			observer.update();
		}		
	}	
	public final void registerObserver(IObserver o) {
		observers.add(o);	
	}

	@Override
	public final void removeObserver(IObserver o) {
		int i = observers.indexOf(o);
		if(i>=0)
		{
			observers.remove(i);
		}		
	}
}