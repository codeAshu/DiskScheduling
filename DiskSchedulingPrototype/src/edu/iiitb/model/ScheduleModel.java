package edu.iiitb.model;

public class ScheduleModel {
	
	
	int algoType;
	String trackInput;
	int startPoint;
	
	
	public ScheduleModel(int algoType, String trackInput, int startPoint)
	{
		super();
		this.algoType = algoType;
		this.trackInput = trackInput;
		this.startPoint = startPoint;
		
	}
	
	int scheduleID;
	/**
	 * @return the seekTime
	 */
	
	//Default Constructor
	public ScheduleModel(){}

	/**
	 * @return the scheduleID
	 */
	public int getScheduleID() {
		return scheduleID;
	}

	/**
	 * @param scheduleID the scheduleID to set
	 */
	public void setScheduleID(int scheduleID) {
		this.scheduleID = scheduleID;
	}

	/**
	 * @return the algoType
	 */
	public int getAlgoType() {
		return algoType;
	}

	/**
	 * @param algoType the algoType to set
	 */
	public void setAlgoType(int algoType) {
		this.algoType = algoType;
	}

	/**
	 * @return the trackInput
	 */
	public String getTrackInput() {
		return trackInput;
	}

	/**
	 * @param trackInput the trackInput to set
	 */
	public void setTrackInput(String trackInput) {
		this.trackInput = trackInput;
	}

	/**
	 * @return the startPoint
	 */
	public int getStartPoint() {
		return startPoint;
	}

	/**
	 * @param startPoint the startPoint to set
	 */
	public void setStartPoint(int startPoint) {
		this.startPoint = startPoint;
	}

	
}
