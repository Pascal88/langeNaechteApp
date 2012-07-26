package de.htwg.tetris.controller;


public interface IMechanikController {
	public void setMechanic(Thread mechanic);

	public Thread getMechanic();
	public void stopMechanic();
	public void newMechanik();
	public boolean isMechanikAlive();
	public IMechanikController getInstance();
}
