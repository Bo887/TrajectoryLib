package trajectory;
import trajectory.*;
import java.io.*;
public class TestController{

	public static void main(String[] args) throws IOException{	
		Segment[] leftTraj;
		Segment[] rightTraj;
	
		TrajectoryGenerator leftGenerator = new TrajectoryGenerator();
		TrajectoryGenerator rightGenerator = new TrajectoryGenerator();	
	
		TrajectoryFollower leftFollower = new TrajectoryFollower();
		TrajectoryFollower rightFollower = new TrajectoryFollower();

		leftGenerator.setConfig(15,20,0.02);
		rightGenerator.setConfig(15,20,0.02);
		leftTraj = leftGenerator.generateTraj(0,0,1);
		rightTraj = rightGenerator.generateTraj(0,0,1);
		leftFollower.setTrajectory(leftTraj);
		rightFollower.setTrajectory(rightTraj);		
		double kv = (1.0)*1/15;
		double dt = 1.0/50.0;
		rightFollower.setGains(kv,0,0,0,0);
		leftFollower.setLoopTime(dt);
		rightFollower.setLoopTime(dt);
			
		leftFollower.init();
		rightFollower.init();
				
		double curr_pos =0;
		//only testing right follower, bc left is the same
		rightFollower.calcMotorOutput(curr_pos);
		
	}
		
}
