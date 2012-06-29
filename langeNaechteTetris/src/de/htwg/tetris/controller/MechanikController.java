package de.htwg.tetris.controller;

public class MechanikController implements IMechanikController {

	public static IMechanikController INSTANCE = null;
	
	private GameMechanic gameMechanic = null;	
	private Thread mechanic = null;
	
	public MechanikController() {
		
		if(MechanikController.INSTANCE == null) {
			MechanikController.INSTANCE = this;
		}
		gameMechanic = new GameMechanic();
		setMechanic(new Thread(gameMechanic));
	}
	public void stopMechanic()
	{
		gameMechanic.shutdown();	//Thread exit 
	}
	
	public void newMechanik(){
			gameMechanic = new GameMechanic();
			setMechanic(new Thread(gameMechanic));
			mechanic.start();
	}
		
	public final void setMechanic(Thread mechanic) {
		this.mechanic = mechanic;
	}

	public Thread getMechanic() {
		return mechanic;
	}
	@Override
	public IMechanikController getInstance() {
		return MechanikController.INSTANCE;
	}
}
