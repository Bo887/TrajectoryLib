public class TrajectoryFollower {
	
	double kP, kI, kD, kV, kA;
	double error, sumError, changeError;
	Segment[] traj;
	
	public TrajectoryFollower(){
		
	}
	
	public void setTrajectory(Segment[] traj){
		this.traj = traj;
	}
	
	public void setGains(double kP, double kI, double kD, double kV, double kA){
		this.kP = kP;
		this.kI = kI;
		this.kD = kD;
		this.kV = kV;
		this.kA = kA;
	}

	public double calcFeedForward(double curr_vel, double curr_accel){
		return (kV*curr_vel + kA*curr_accel);
	}
	
	public double calcFeedBack(double setpoint_pos, double curr_pos){
		error = setpoint_pos-curr_pos;
		sumError += error;
		double 
		changeError 
	}
}