package trajectory;
import trajectory.*;
import java.io.*;
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
	PrintWriter f; 
	public void init() throws IOException{
		f = new PrintWriter(new BufferedWriter(new FileWriter("data.csv")), true);
		f.println("output,time");
	}

	public void setGains(double kV, double kA, double kP, double kI, double kD){
		this.kV = kV;
		this.kA = kA;
		this.kP = kP;
		this.kI = kI;
		this.kD = kD;
	}

	public void setLoopTime(double dt){
		this.dt = dt;
	}

	public void resetController(){
		sumError = 0;
		changeError = 0;
		prevError = 0;	
		error = 0;
		curr_segment = 0;
	}
	
	private double calcFeedForward(double curr_vel, double curr_accel){
		//System.out.println("KV" + kV);
		//System.out.println("FF " + (kV*curr_vel + kA*curr_accel));
		return kV*curr_vel + kA*curr_accel;
	}	

	private double calcFeedBack(double setpoint_pos, double curr_pos){
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

	public double calcMotorOutput(double curr_actual_dist) throws IOException{
		while (!isFinished()){ 
			Segment s = traj[curr_segment];
			curr_actual_dist = s.pos - Math.random()*0.25;
			feedForwardValue = calcFeedForward(s.vel, s.accel);
			feedBackValue = calcFeedBack(s.pos, curr_actual_dist);
			output = feedForwardValue + feedBackValue;
			curr_segment++;
			if (output>1) output = 1;
			if (output<-1) output = -1;
			System.out.println(Double.toString(curr_actual_dist) + "," + Double.toString(s.pos) + "," + Double.toString(s.vel) + "," + Double.toString(s.accel) +","+ Double.toString(output));
			f.println(s.vel+ "," + s.time);
			f.flush();
		}
		f.close();
		return 0;
	}
	
}
