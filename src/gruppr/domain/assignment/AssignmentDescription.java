package gruppr.domain.assignment;
import java.util.HashMap;
import java.util.Map;

public class AssignmentDescription {
	
	private Map<String, String> descriptionList = new HashMap<String, String>();
	
	public AssignmentDescription(String assignmentName, String description) {
		this.descriptionList.put(assignmentName, description);
	}
	
	public void addDescription(String assignmentName, String description) {
		this.descriptionList.put(assignmentName, description);
	}
	
	public String getDescription(Assignment assignmentName){
		return this.descriptionList.get(assignmentName);
	}
}