package trajectory;
import trajectory.*;
public class TestController{
	
	Segment[] leftTraj;
	Segment[] rightTraj;
	
	TrajectoryGenerator leftGenerator;
	TrajectoryGenerator rightGenerator;	

	TrajectoryFollower leftFollower;
	TrajectoryFollower rightFollower;

	public void init(){
		leftGenerator.setConfig(12,15,0.005);
		rightGenerator.setConfig(12,15,0.005);
		leftTraj = leftGenerator.generateTraj(0,0,20);
		rightTraj = rightGenerator.generateTraj(0,0,20);
		leftFollower.setTrajectory(leftTraj);
		rightFollower.setTrajectory(rightTraj);		
	}
	
}
