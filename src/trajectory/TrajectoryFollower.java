package trajectory;
import trajectory.*;
public class TrajectoryFollower{
	
	private double kP, kI, kD, kV, kA, dt;
	double PID, error, sumError=0, changeError=0, prevError=0;
	private Segment[] traj;
	private int curr_segment;
	private double output, feedForwardValue, feedBackValue;
		
	public TrajectoryFollower(){

	}

	public void setTrajectory(Segment[] traj){
		this.traj = traj;
	}
	
	public void setGains(double kV, double kA, double kP, double kI, double kD){
		this.kV = kV;
		this.kA = kA;
		this.kP = kP;
		this.kI = kI;
		this.kD = kD;
	}

	public void setLoopTime(double dt){
		this.dt=dt;
	}

	public void resetController(){
		sumError = 0;
		changeError = 0;
		prevError = 0;	
		error = 0;
		curr_segment = 0;
	}
	
	public void calcFeedForward(double curr_vel, double curr_accel){
		return kV*curr_vel + kA*curr_accel;
	}	

	public double calcFeedBack(double setpoint_pos, double curr_pos){
		error = setpoint_pos-curr_pos;
		sumError+=error;
		changeError = (error-prevError)/dt;
		prevError = error;
		PID = kP*error + kI*sumError + kD*changeError;
		return PID;
	}
	
	public boolean isFinished(){
		return curr_segment >= traj.length;
	}

	public double calcMotorOutput(double curr_actual_dist){
		if (!isFinishedTrajectory()){ 
			Segment s = new traj[curr_segment];
			feedForwardValue = calcFeedForward(s.vel, s.accel);
			feedBackValue = calcFeedBack(s.pos, curr_actual_dist);
			output = feedForwardValue + feedBackValue;
			currenst_segment++;
		}
		else return 0;
	}
}
